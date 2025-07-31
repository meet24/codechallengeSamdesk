import java.io.*;

public class SafeReportChecker2 {

    // Check if a report is safe under original rules
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

    // Check if a report is safe after removing one level
    public static boolean isSafeWithOneRemoval(int[] report) {
        int n = report.length;
        if (n <= 2) return false;  // Too short to be valid after removal

        for (int i = 0; i < n; i++) {
            int[] modified = new int[n - 1];
            for (int j = 0, k = 0; j < n; j++) {
                if (j != i) {
                    modified[k++] = report[j];
                }
            }
            if (isSafe(modified)) return true;
        }
        return false;
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

                if (isSafe(report) || isSafeWithOneRemoval(report)) {
                    safeCount++;
                }
            }

            System.out.println("Number of safe reports (with Problem Dampener): " + safeCount);

        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}
