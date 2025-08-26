/*
Q: You are given an infinite two-dimensional grid. There are N balloons placed at certain coordinates.
You can shoot an arrow from any point to any point. When fired, all balloons lying on the line segment
between the start and target burst. Find the maximum number of balloons you can burst in one shot.

This reduces to the classic "Maximum points on a line" problem.
We need to find the maximum number of points (balloons) that lie on a single straight line.
*/

import java.util.*;

public class MaxBalloons {
    
    public int maxBalloons(int[][] arr) {
        int n = arr.length;
        if (n <= 2) return n; // if <=2 points, all are collinear

        int result = 0;

        for (int i = 0; i < n; i++) {
            Map<String, Integer> slopeCount = new HashMap<>();
            int samePoint = 1; // count duplicates
            int localMax = 0;

            for (int j = i + 1; j < n; j++) {
                int dx = arr[j][0] - arr[i][0];
                int dy = arr[j][1] - arr[i][1];

                if (dx == 0 && dy == 0) {
                    samePoint++;
                    continue;
                }

                int gcd = gcd(Math.abs(dx), Math.abs(dy));
                dx /= gcd;
                dy /= gcd;

                // normalize slope to unique form
                if (dx < 0) {
                    dx = -dx;
                    dy = -dy;
                } else if (dx == 0) {
                    dy = 1; // vertical line
                } else if (dy == 0) {
                    dx = 1; // horizontal line
                }

                String slope = dy + "/" + dx;
                slopeCount.put(slope, slopeCount.getOrDefault(slope, 0) + 1);
                localMax = Math.max(localMax, slopeCount.get(slope));
            }

            result = Math.max(result, localMax + samePoint);
        }

        return result;
    }

    private int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // Driver
    public static void main(String[] args) {
        MaxBalloons sol = new MaxBalloons();

        int[][] arr = {{1,2}, {2,3}, {3,4}};
        System.out.println("Max balloons burst = " + sol.maxBalloons(arr));
    }
}
