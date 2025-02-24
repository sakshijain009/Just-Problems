import java.util.HashMap;

class LRUCache {
    private Node head, tail;

    private HashMap<Integer, Node> cache;

    private int capacity = 0;

    // Constructor for initializing the cache capacity with the given value.
    LRUCache(int cap) {
        this.capacity = cap;
        this.cache  = new HashMap<>();
        head = null;
        tail = null;
    }

    // Function to return value corresponding to the key.
    public int get(int key) {
        if (!cache.containsKey(key)) {
            return -1; // Key not found in the cache
        }

        // Update Priority of Key
        Node node = cache.get(key);
        removeNodeFromList(node);
        addToHighestPriority(node);

        return node.value;
    }

    // Function for storing key-value pair.
    public void put(int key, int value) {
        if(cache.containsKey(key)) {
            Node node = cache.get(key);
            node.value = value;

            removeNodeFromList(node);
            addToHighestPriority(node);
        } else {
            Node newNode = new Node(key, value);

            if(cache.size() == capacity) {
                cache.remove(tail.key);
                removeNodeFromList(tail);
            }

            cache.put(key, newNode);
            addToHighestPriority(newNode);
        }
    }

    public void addToHighestPriority(Node node) {
        if(head==null) {
            head = tail = node;
        } else{
            head.prev = node;
            node.next = head;
            head = node;
        }
    }

    public void removeNodeFromList(Node node) {
        if(head==node && tail==node) {  // Single element
            head = tail = null;
        } else if(head==node) {         // First elemeent
            head = head.next;
            head.prev = null;
        } else if(tail==node) {         // Last element
            tail = tail.prev;
            tail.next = null;
        }else {                         // Middle element
            node.prev.next = node.next;
            node.next.prev = node.prev;
        }

        node.prev = null;
        node.next = null;
    }

    class Node {
        int key;
        int value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.prev = null;
            this.next = null;
        }
    }

    public static void main(String[] arg) {

        // Cache capacity
        int cap = 2;

        String[][] Queries = {
                {"PUT", "1", "2"},
                {"PUT", "2", "3"},
                {"PUT", "1", "5"},
                {"PUT", "4", "5"},
                {"PUT", "6", "7"},
                {"GET", "4"},
                {"PUT", "1", "2"},
                {"GET", "3"}
        };

        // Initialize the LRUCache
        LRUCache cache = new LRUCache(cap);

        // Process the queries
        for (String[] query : Queries) {
            String operation = query[0]; // Operation type (PUT or GET)
            if (operation.equals("PUT")) {
                int key = Integer.parseInt(query[1]);
                int value = Integer.parseInt(query[2]);
                cache.put(key, value); // Perform PUT operation
            } else if (operation.equals("GET")) {
                int key = Integer.parseInt(query[1]);
                int value = cache.get(key); // Perform GET operation
                System.out.println(value);
            }
        }
    }
}
