import java.util.Arrays;

public class Assignment8 {

    /*
    Time Complexity of this method is O(nLogn).

    The idea is to consider all visits (start and end) in sorted order.
    Once we have all visits in sorted order, we can trace the number of groups
    at any time keeping track of groups that have arrived, but not finished yet
     */

    public static void findMinGuidesNeeded(int start[], int end[], int n) {
        // Sort arrival and exit arrays
        Arrays.sort(start);
        Arrays.sort(end);
        // groups_started indicates number of groups at a time
        int groups_started = 1, max_groups = 1, time = start[0];
        int i = 1, j = 0;
        // all events in sorted order
        while (i < n && j < n) {
            // If next event in sorted order is arrival,
            // increment count of groups
            if (start[i] <= end[j]) {
                groups_started++;
                // Update max_groups if needed
                if (groups_started > max_groups) {
                    max_groups = groups_started;
                }
                i++; //increment index of arrival array
            } else // If event is end, decrement count
            { // of groups.
                groups_started--;
                j++;
            }
        }
        System.out.println("Minimum number of Guides needed = " + max_groups);
    }
}
