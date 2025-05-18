class Solution {
    public List<int[]> mergeOverlap(int[][] arr) {
        if(arr.length==0 || arr==null) {
            return Collections.emptyList();
        }
        
        List<int[]> list = new ArrayList<>();
        Arrays.sort(arr, (a,b) -> a[0] - b[0]);
        
        int start = arr[0][0];
        int end = arr[0][1];
        
        for(int i=1; i<arr.length; i++) {
            if(arr[i][0] <= end) {
                end = Math.max(end, arr[i][1]);
            } else {
                list.add(new int[]{start, end});
                start = arr[i][0];
                end = arr[i][1];
            }
        }
        
        list.add(new int[] {start, end});
        
        return list;
    }
}
