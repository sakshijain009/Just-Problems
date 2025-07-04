/*

  Given a binary array arr[] and an integer k. A k-bit flip involves selecting a contiguous subarray 
  of length k from arr[] and flipping all its bits - changing every 0 to 1 and every 1 to 0 simultaneously.
  Your task is to return the minimum number of k-bit flips needed to eliminate all 0s from the array. If it 
  is impossible to achieve, return -1.

  What XOR (^) Does:
  It compares two bits (or integers at the bit level) and returns:
  1 if the bits are different
  0 if the bits are the same

*/
class Solution {
    static int kBitFlips(int[] arr, int k) {
         int n = arr.length;
        int flips = 0;
        int flipped = 0; 
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
           
            if (!queue.isEmpty() && queue.peek() == i) {
                queue.poll();
                flipped ^= 1; 
            }

            if ((arr[i] ^ flipped) == 0) { 
                if (i + k > n) return -1; 
                
                flips++;
                queue.add(i + k); 
                flipped ^= 1;
            }
        }
        return flips;
    }
}

/*
 🔹 Input:
    arr = [1, 1, 0, 0, 0, 1, 1, 0, 1, 1, 1], k = 2

 🔹 Initial State:
    flips = 0
    flipped = 0
    queue = [] (to track flip end indices)
    n = 11

    We'll iterate from i = 0 to 10.

  🔸 i = 0:
     queue is empty
     arr[0] ^ flipped = 1 ^ 0 = 1 → ✅ No flip needed

  🔸 i = 1:
     arr[1] ^ flipped = 1 ^ 0 = 1 → ✅ No flip needed

  🔸 i = 2:
     arr[2] ^ flipped = 0 ^ 0 = 0 → ❌ Flip needed
     Can flip → 2 + 2 = 4 <= 11 ✅
     flips = 1
     Add 4 to queue
     Toggle flipped = 1

  🔸 i = 3:
     arr[3] ^ flipped = 0 ^ 1 = 1 → ✅ No flip needed

  🔸 i = 4:
     queue.peek() == 4 → remove 4 from queue
     Toggle flipped = 0
     arr[4] ^ flipped = 0 ^ 0 = 0 → ❌ Flip needed
     Can flip → 4 + 2 = 6 <= 11 ✅
     flips = 2
     Add 6 to queue
     Toggle flipped = 1

  🔸 i = 5:
     arr[5] ^ flipped = 1 ^ 1 = 0 → ❌ Flip needed
     Can flip → 5 + 2 = 7 <= 11 ✅
     flips = 3
     Add 7 to queue
     Toggle flipped = 0

  🔸 i = 6:
     queue.peek() == 6 → remove 6
     Toggle flipped = 1
     arr[6] ^ flipped = 1 ^ 1 = 0 → ❌ Flip needed
     Can flip → 6 + 2 = 8 <= 11 ✅
     flips = 4
     Add 8 to queue
     Toggle flipped = 0

  🔸 i = 7:
    queue.peek() == 7 → remove 7
    Toggle flipped = 1
    arr[7] ^ flipped = 0 ^ 1 = 1 → ✅ No flip needed

 🔸 i = 8:
    queue.peek() == 8 → remove 8
    Toggle flipped = 0
    arr[8] ^ flipped = 1 ^ 0 = 1 → ✅ No flip needed

 🔸 i = 9:
    arr[9] ^ flipped = 1 ^ 0 = 1 → ✅ No flip needed

 🔸 i = 10:
    arr[10] ^ flipped = 1 ^ 0 = 1 → ✅ No flip needed

✅ Final Answer:
flips = 4

🧠 Summary of Flips:
Flip at i = 2 → flips [2,3]
Flip at i = 4 → flips [4,5]
Flip at i = 5 → flips [5,6]
Flip at i = 6 → flips [6,7]

Effective bits after all these flips become all 1.

*/
