
class TrieImplementation {

    static class TrieNode {
        boolean isEndOfWord;
        TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }

        public boolean containsKey(char ch) {
            return children[ch - 'a'] != null;
        }

        public void put(char ch, TrieNode node) {
            children[ch - 'a'] = node;
        }

        public TrieNode get(char ch) {
            return children[ch - 'a'];
        }

        public void setEndOfWord() {
            isEndOfWord = true;
        }

        public boolean isEndOfWord() {
            return isEndOfWord;
        }
    }

    static class Trie {
        TrieNode root;

        public Trie() {
            this.root = new TrieNode();
        }

        // Inserts a word into the Trie
        public void insert(String word) {
            TrieNode node = root;

            for(char c : word.toCharArray()) {
                if(!node.containsKey(c)) {
                    node.put(c, new TrieNode());
                }
                node = node.get(c);
            }

            node.setEndOfWord();
        }

        // Searches for a word in the Trie
        public boolean search(String word) {
            TrieNode node = root;

            for(char c : word.toCharArray()) {
                if(node.containsKey(c)) {
                    node = node.get(c);
                } else {
                    return false;
                }
            }

            return node.isEndOfWord();
        }

        // Checks if any word in the Trie starts with the given prefix
        public boolean startsWith(String prefix) {
            TrieNode node = root;

            for (char c : prefix.toCharArray()) {
                if (!node.containsKey(c)) {
                    return false;
                }
                node = node.get(c);
            }

            return true;
        }

    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert words into the Trie
        trie.insert("apple");
        trie.insert("app");
        trie.insert("banana");

        // Search for words
        System.out.println(trie.search("apple"));   // Output: true
        System.out.println(trie.search("app"));    // Output: true
        System.out.println(trie.search("appl"));    // Output: false

        // Check for prefixes
        System.out.println(trie.startsWith("app")); // Output: true
        System.out.println(trie.startsWith("ban")); // Output: true
        System.out.println(trie.startsWith("cat")); // Output: false
    }
}
