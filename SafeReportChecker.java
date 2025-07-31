import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SafeReportChecker {
    public static boolean isSafe(int[] report) {
        int n = report.length;
        int[] diffs = new int[n - 1];

        for (int i = 0; i < n - 1; i++) {
            diffs[i] = report[i + 1] - report[i];
        }

        for (int d : diffs) {
            if (d == 0 || Math.abs(d) < 1 || Math.abs(d) > 3) {
                return false;
            }
        }

        boolean allPositive = true;
        boolean allNegative = true;
        for (int d : diffs) {
            if (d <= 0) allPositive = false;
            if (d >= 0) allNegative = false;
        }

        return allPositive || allNegative;
    }

    public static void main(String[] args) {
        String filename = "input.txt";
        int safeCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                int[] report = new int[parts.length];

                for (int i = 0; i < parts.length; i++) {
                    report[i] = Integer.parseInt(parts[i]);
                }

                if (isSafe(report)) {
                    safeCount++;
                }
            }

            System.out.println("Number of safe reports: " + safeCount);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
