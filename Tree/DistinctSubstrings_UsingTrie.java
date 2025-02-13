class Main {
    public static int countDistinctSubstring(String st){
        int count = 0;
        TrieNode root = new TrieNode();

        for(int i=0; i<st.length(); i++) {

            TrieNode node = root;
            for(int j=i; j<st.length(); j++) {

                char ch = st.charAt(j);

                if(node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                    count++;
                }
                node = node.children[ch - 'a'];
            }
        }

        return count+1; // add 1 for empty string
    }

    static class TrieNode {
        boolean isEndOfWord;
        TrieNode[] children;

        public TrieNode() {
            this.children = new TrieNode[26];
            this.isEndOfWord = false;
        }
    }
}
