import java.io.*;
import java.util.*;
import java.util.regex.*;

/**
 * 🔹 Problem: Multiple Requests at a Time
 *
 * Given a log file where each line contains a web server access log record in the following format:
 *
 *   hostname - - [DD/mmm/YYYY:HH:MM:SS -0400] "request" response_code bytes
 *
 * Example line:
 *   burger.letters.com - - [01/Jul/1995:00:00:12 -0400] "GET /video/live.gif HTTP/1.0" 200 0
 *
 * Task:
 * 1. Read the log file whose name is provided as input (e.g., "hosts_access_log_00.txt").
 * 2. Extract the timestamp part: DD/mmm/YYYY:HH:MM:SS (ignore the timezone -0400).
 * 3. Identify all timestamps that appear more than once.
 * 4. Write these duplicate timestamps (one per line) to a new file named:
 *    "req_<filename>" (e.g., "req_hosts_access_log_00.txt")
 *
 * Notes:
 * - Only timestamps that occur more than once should be written.
 * - Output file must be written to the current directory.
 * - Line order in output does not matter.
 *
 * Categories: File I/O, Regex, String Parsing, HashMap (Frequency Count), Real-world log processing
 */

public class Solution {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        // Step 1: Read input filename from standard input
        String filename = scan.nextLine();
        String outputFilename = "req_" + filename;

        // Step 2: Prepare a map to count timestamps
        Map<String, Integer> timestampCount = new HashMap<>();

        // Regex pattern to extract timestamp (excluding timezone)
        Pattern pattern = Pattern.compile("\\[(\\d{2}/[A-Za-z]{3}/\\d{4}:\\d{2}:\\d{2}:\\d{2})\\s-0400\\]");

        // Step 3: Read the input file and extract timestamps
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = pattern.matcher(line);
                if (matcher.find()) {
                    String timestamp = matcher.group(1);
                    timestampCount.put(timestamp, timestampCount.getOrDefault(timestamp, 0) + 1);
                }
            }
        }

        // Step 4: Write only timestamps with more than one occurrence
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilename))) {
            for (Map.Entry<String, Integer> entry : timestampCount.entrySet()) {
                if (entry.getValue() > 1) {
                    writer.write(entry.getKey());
                    writer.newLine();
                }
            }
        }

        // Step 5: Print success message
        System.out.println("✅ Output written to: " + outputFilename);
    }
}
