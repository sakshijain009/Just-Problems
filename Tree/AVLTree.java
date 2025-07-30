class Node {
    int data;
    Node left;
    Node right;
    int height;

    Node(int data) {
        this.data = data;
        this.height = 1;
    }
}

class AVLTree {

    public Node insertToAVL(Node node, int data) {
        if (node == null) {
            return new Node(data);
        }

        if (data < node.data) {
            node.left = insertToAVL(node.left, data);
        } else if (data > node.data) {
            node.right = insertToAVL(node.right, data);
        } else {
            // Duplicate keys not allowed
            return node;
        }

        updateHeight(node);
        return balance(node);
    }

    public Node deleteNode(Node root, int key) {
        if (root == null)
            return root;

        if (key < root.data) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.data) {
            root.right = deleteNode(root.right, key);
        } else {
            // Node with one child or no child
            if ((root.left == null) || (root.right == null)) {
                Node temp = root.left != null ? root.left : root.right;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.right);
                root.data = temp.data;
                root.right = deleteNode(root.right, temp.data);
            }
        }

        if (root == null)
            return root;

        updateHeight(root);
        return balance(root);
    }

    private Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }

    private void updateHeight(Node node) {
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));
    }

    private int getHeight(Node node) {
        return node == null ? 0 : node.height;
    }

    private int getBalance(Node node) {
        return node == null ? 0 : getHeight(node.left) - getHeight(node.right);
    }

    private Node balance(Node node) {
        int balance = getBalance(node);

        // Left Left
        if (balance > 1 && getBalance(node.left) >= 0)
            return rightRotate(node);

        // Left Right
        if (balance > 1 && getBalance(node.left) < 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Right
        if (balance < -1 && getBalance(node.right) <= 0)
            return leftRotate(node);

        // Right Left
        if (balance < -1 && getBalance(node.right) > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    public void inOrder(Node root) {
        if (root != null) {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }
}

 class Main {
    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        Node root = null;

        // Insert nodes
        int[] nodes = { 10, 20, 30, 40, 50, 25 };
        for (int val : nodes) {
            root = tree.insertToAVL(root, val);
        }

        System.out.print("Inorder after insertions: ");
        tree.inOrder(root);
        System.out.println();

        // Delete a node
        root = tree.deleteNode(root, 30);
        System.out.print("Inorder after deleting 30: ");
        tree.inOrder(root);
    }
}
