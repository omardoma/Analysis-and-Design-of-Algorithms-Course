
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Assignment2 {

    public static int[][] loadTestCases(String filePath) throws IOException {
        BufferedReader reader = new BufferedReader(
                new FileReader(filePath));
        String line = reader.readLine();
        if (line == null) {
            reader.close();
            throw new IOException();
        } else {
            int testCasesNumber = Integer.parseInt(line);
            int[][] testCases = new int[testCasesNumber][];
            int testCasesIndex = 0;
            for (int i = 0; ((line = reader.readLine()) != null); i++) {
                if ((i % 2 != 0)) {
                    String[] testCase = line.split(" ");
                    int[] parsedTestCase = new int[testCase.length];
                    for (int j = 0; j < testCase.length; j++) {
                        parsedTestCase[j] = Integer.parseInt(testCase[j]);
                    }
                    testCases[testCasesIndex++] = parsedTestCase;
                }
            }
            reader.close();
            return testCases;
        }
    }

    /*
        Time Complexity Argument:

            T(n)= T(n/2) + c
            T(n)= T(n/4) + c + c
            T(n)= T(n/8) + c + c + c
            T(n)= T(n/(2^k)) + c.k

            Substitute k = log(n)

            T(n) = T(n/(2^log(n))) + c.log(n)
                 = T(1) + c.log(n)
                 = O(log(n))
    */
    /*
        Proof of Correctness:

            1. For the base case we check for two conditions:
                    1- testCase array length is less than 3, and we know that always the first and last
                       pixels equal 0, which means that there is no peak element and we return -1.

                    2- testCase array length equals 3, and we know that always only the first and last
                       pixels equal zero, which means that the middle pixel is always the standout
                       pixel, so we return the index of the middle pixel which is 1.

           2. We compare middle element with its neighbors, and return the middle element index if it’s
              bigger than them.

           3. If middle element is not a standout and its left neighbor is greater than it ,then left
              half must have a peak element and we call the same recursive function on that left half.

           4. If middle element is not a standout and its right neighbor is greater than it, then right
              half must have a peak element and we call the same recursive function on that right half.

           The algorithm is returning correct values and theoretically handling all of the possible
           cases, so it would be appropriate if we called it a correct algorithm.
     */
    public static int findStandOut(int[] testCase, int low, int high) {
        int n = testCase.length;
        if (n < 3) {
            return -1;
        } else if (n == 3) {
            return 1;
        } else {
            int mid = low + (high - low) / 2;

            if ((mid > 0 && testCase[mid - 1] <= testCase[mid]) && testCase[mid + 1] <= testCase[mid]) {
                return mid;
            } else if (mid > 0 && testCase[mid - 1] > testCase[mid]) {
                return findStandOut(testCase, low, (mid - 1));
            } else {
                return findStandOut(testCase, (mid + 1), high);
            }
        }
    }

    public static void main(String[] args) {
        try {
            int[][] testCases = loadTestCases("Assignment2.in");
            for (int i = 0; i < testCases.length; i++) {
                System.out.println("Test Case " + (i + 1) + ": " + findStandOut(testCases[i], 0, testCases[i].length - 1));
            }
        } catch (IOException e) {
            System.err.println("Could not read the test cases file.");
        }
    }
}
