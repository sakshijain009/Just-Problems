import java.util.HashMap;
import java.util.Set;

class HashMap1 {
    public static void main(String[] args) {
        HashMap<String, Integer> hp = new HashMap<>();

        // All functions run in O(1)
        hp.put("US",1200);
        hp.put("Nigeria",1234);
        hp.put("Latin America",8877);
        hp.put("India",567);

        System.out.println(hp.get("France"));
        System.out.println(hp.get("India"));

        System.out.println(hp.containsKey("India"));
        System.out.println(hp.containsKey("France"));

        hp.put("France",9009);

        System.out.println(hp.containsKey("France"));
        System.out.println(hp.get("France"));

        /*
            Outputs:
            null
            567
            true
            false
            true
            9009
         */

        Set<String> keys = hp.keySet(); // Random Order
        System.out.println(keys);
        // [Nigeria, France, US, Latin America, India]
    }
}
