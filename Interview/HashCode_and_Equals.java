import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Example class to demonstrate overriding equals() and hashCode() in Java.
 * 
 * Why?
 * ----
 * - Collections like HashSet, HashMap, and HashTable rely on hashCode() and equals()
 *   for efficient storage and retrieval.
 * - hashCode() decides the "bucket" where the object is stored.
 * - equals() is used to compare objects within that bucket.
 * 
 * Rule:
 * -----
 * - If two objects are equal according to equals(), they MUST return the same hashCode().
 * - Otherwise, hash-based collections will behave incorrectly (duplicates, failed lookups).
 */
class User {
    private int id;
    private String name;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Custom equality:
     * Two User objects are considered equal if they have the same id,
     * regardless of their name.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // same reference
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return id == user.id; // equality based only on id
    }

    /**
     * Must be consistent with equals().
     * If two users have the same id, they should produce the same hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id); // only id considered for hashing
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }

    // Demo usage inside the same class
    public static void main(String[] args) {
        Set<User> users = new HashSet<>();

        // Add two users with the same id but different names
        users.add(new User(1, "Alice"));
        users.add(new User(1, "Bob")); // Duplicate based on id

        users.add(new User(2, "Charlie"));

        // HashSet will treat Alice and Bob as the same user (id=1)
        System.out.println(users);
    }
}
