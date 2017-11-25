public class Assignment3 {

    public static int getArraySize(int[] array) {
        int i;
        for (i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                break;
            }
        }
        return i;
    }

    public static int[] trimArray(int[] array) {
        int size = getArraySize(array);
        int[] trimmedArray = new int[size];
        for (int i = 0; i < size; i++) {
            trimmedArray[i] = array[i];
        }
        return trimmedArray;
    }

    /*
        Proof of Correctness:

            For the base case:
                 1) If the array is empty return -1.

                 2) If the array contains only one element then it is surely a major element.

                 3) If the array contains two elements then we check if both of them are the same, if so
                    we return anyone of them because that way it is a major element else there is no
                    major elements because the elements occur in 50:50 ratio in the array.

                 4) We use the concept of divide and conquer to create an array containing only elements that
                    are repeated after each other, as in order for an element to be a major there must be some
                    kind of repetition, then we recursively call the function with the minimized array containing
                    the candidate elements to be majors.

                 5) After our function terminates we take the candidate element resulting from the divide and conquer
                    approach and we linearly count the number of times it appears in the array to make sure it
                    is truly a major and print it if so, or print that there are no majors.
     */

    public static int getCandidateElement(int[] array) {
        if (array.length == 0) {
            return -1;
        }
        if (array.length == 1) {
            return array[0];
        }
        if (array.length == 2) {
            if (array[0] == array[1]) {
                return array[0];
            } else {
                return -1;
            }
        }
        int[] tmp = new int[array.length];
        int tmpIndex = 0;
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] == array[i + 1]) {
                tmp[tmpIndex++] = array[i];
            }
        }
        return getCandidateElement(trimArray(tmp));
    }

    public static boolean isMajorityElement(int[] array, int candidate) {
        int count = 0;
        for (int current : array) {
            if (current == candidate) {
                if (++count > array.length / 2) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void getMajorityElement(int[] a) {
        int candidate = getCandidateElement(a);
        if (candidate != -1 && isMajorityElement(a, candidate)) {
            System.out.println(candidate);
        } else {
            System.out.println("No Majority Element Found");
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 3, 3, 2, 4, 3, 3, 3, 3, 3, 6, 1, 2, 3};
        getMajorityElement(array);
    }
}
