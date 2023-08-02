import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Trie {

    // Wildcards
    final char WILDCARD = '.';

    private class TrieNode {
        // TODO: Create your TrieNode class here.
        int[] presentChars = new int[62];
        TrieNode[] children;
        char key;
        boolean flag;

        TrieNode() {
            this.children = new TrieNode[62];
            this.flag = false;
        }

        TrieNode(char key) {
            this.key = key;
            this.flag = false;
            this.children = new TrieNode[62];
        }
    }

    TrieNode root;

    public Trie() {
        // TODO: Initialise a trie class here.
        root = new TrieNode();
    }

    /**
     * Inserts string s into the Trie.
     *
     * @param s string to insert into the Trie
     */
    void insert(String s) {
        // TODO
        TrieNode node = root;
        for (char c : s.toCharArray()) {
            int index = getIndex(c);
            if (node.children[index] == null)
                node.children[index] = new TrieNode(c);
            node = node.children[index];
        }
        node.flag = true;
    }

    public int getIndex(char ch) {
        int ascii = (int) ch;
        // Checking if ascii is 0-9
        if (ascii >= 48 && ascii <= 57) {
            return ascii - 48;
        }
        // Checking if ascii is A-Z
        else if (ascii >= 65 && ascii <= 90) {
            return ascii - 55;
        }
        // Checking if ascii is a-z
        else if (ascii >= 97 && ascii <= 122) {
            return ascii - 61;
        } else {
            return -1;
        }
    }

    /**
     * Checks whether string s exists inside the Trie or not.
     *
     * @param s string to check for
     * @return whether string s is inside the Trie
     */
    boolean contains(String s) {
        // TODO
        TrieNode node = root;
        for (char ch : s.toCharArray()) {
            int index = getIndex(ch);
            if (node.children[index] == null) {
                return false;
            }
            node = node.children[index];
        }
        return node.flag;
    }

    /**
     * Searches for strings with prefix matching the specified pattern sorted by lexicographical order. This inserts the
     * results into the specified ArrayList. Only returns at most the first limit results.
     *
     * @param s       pattern to match prefixes with
     * @param results array to add the results into
     * @param limit   max number of strings to add into results
     */
    void prefixSearch(String s, ArrayList<String> results, int limit) {
        prefixSearchHelper(s, 0, this.root, results, new StringBuilder(), limit);
    }

    /**
     * Recursive helper that searches for strings with prefix matching the specified pattern sorted by lexicographical order. This inserts the
     * results into the specified ArrayList. Only returns at most the first limit results.
     *
     * @param s       pattern to match prefixes with
     * @param count   current pointer index on s
     * @param node    current node
     * @param results array to add the results into
     * @param sb      string builder that appends every char while traversing
     * @param limit   max number of strings to add into results
     */
    public void prefixSearchHelper(String s, int count, TrieNode node, ArrayList<String> results, StringBuilder sb, int limit) {
        if (results.size() >= limit || s.length() <= count) {
            //add string into arraylist when s is done searching or when the strings hit the limit
            if (results.size() < limit && node.flag) {
                results.add(sb.toString());
            }
            //traverse the remaining node to add any more strings that have the current word inside
            for (TrieNode child : node.children) {
                if (child != null) {
                    prefixSearchHelper(s, count, child, results, new StringBuilder(sb).append(child.key), limit);
                    if (results.size() == limit) {
                        break;
                    }
                }
            }
        } else {
            //s is not done searching - recursively traverse children if the character exists
            char c = s.charAt(count);
            int index = getIndex(c);
            //if normal character, only recurse the specified child node
            if (c != WILDCARD && index > 0) {
                TrieNode child = node.children[index];
                if (child != null) {
                    prefixSearchHelper(s, count + 1, child, results, sb.append(child.key), limit);
                }
            } else {
                //if wildcard, traverse the entire children
                for (TrieNode child : node.children) {
                    if (child != null) {
                        prefixSearchHelper(s, count + 1, child, results, new StringBuilder(sb).append(child.key), limit);
                    }
                }
            }
        }
    }

    // Simplifies function call by initializing an empty array to store the results.
    // PLEASE DO NOT CHANGE the implementation for this function as it will be used
    // to run the test cases.
    String[] prefixSearch(String s, int limit) {
        ArrayList<String> results = new ArrayList<String>();
        prefixSearch(s, results, limit);
        return results.toArray(new String[0]);
    }


    public static void main(String[] args) {
        Trie t = new Trie();
        t.insert("peter");
        t.insert("piper");
        t.insert("picked");
        t.insert("a");
        t.insert("peck");
        t.insert("of");
        t.insert("pickled");
        t.insert("peppers");
        t.insert("pepppito");
        t.insert("pepi");
        t.insert("pik");
        t.insert("abd");
        t.insert("a");
        t.insert("abba");
        t.insert("abbde");
        t.insert("abcd");
        t.insert("abcdef");
        t.insert("abed");
        t.insert("dbec");


        //String[] result1 = t.prefixSearch("pe", 10);
        String[] result1 = t.prefixSearch("ab.d", 10);
        String[] result2 = t.prefixSearch("p.ck", 10);
        // result1 should be:
        //System.out.println(Arrays.toString(result1));
        // ["peck", "pepi", "peppers", "pepppito", "peter"]
        // result2 should contain the same elements with result1 but may be ordered arbitrarily
    }
}
