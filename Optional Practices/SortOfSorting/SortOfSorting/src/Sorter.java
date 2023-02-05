import java.util.Arrays;
import java.util.Comparator;

class Sorter {

    public static void sortStrings(String[] arr) {
        // TODO: implement your sorting function here
        Arrays.sort(arr, new SortComparator());
    }

    static class SortComparator implements Comparator<String> {
        @Override
        public int compare(String o1, String o2) {
            return o1.substring(0,2).compareTo(o2.substring(0,2));
        }
    }
}
