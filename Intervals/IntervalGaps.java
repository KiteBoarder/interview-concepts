import java.util.*;

/*
const testInputs1 = [
    [5, 10],
    [6, 7],
    [1, 3],
    [15, 18],
]  // [[3, 5], [10, 15]]

const testInputs2 = [
    [-4, 10],
    [120, 130],
    [4, 11],
    [2, 5],
    [0, 11],
    [40, 70],
    [31, 32],
    [15, 21],
    [110, Math.pow(140, 10)],
    [14, 18],
    [51, 80],
]  // [[11, 14], [21, 31], [32, 40], [80, 110]]


function findGaps(ranges) {
    // Find the gaps between the ranges. The start point is inclusive and
    // the end point is exclusive.
}
 */

class FindGaps {

    private static List<int[]> findGaps(int[][] ranges) { // sort by start, end
        List<int[]> points = new ArrayList<>();
        for (int[] r : ranges){
            points.add(new int[]{r[0], 1});
            points.add(new int[]{r[1], -1});
        }
        points.sort((a,b) -> a[0]-b[0]);

        List<int[]> result = new ArrayList<>();
        int counter = 0;
        Integer start = null;
        for(int i = 0; i < points.size(); i++){
            int[] point = points.get(i);
            counter += point[1];

            if (counter == 0)
                start = point[0];
            else if (counter == 1 && start != null){
                result.add(new int[]{start, point[0]});
                start = null;
            }
        }
        return result;
    }

    private static List<int[]> findGaps2(int[][] ranges) { // sort by start
        Arrays.sort(ranges, (r1, r2) -> r1[0]-r2[0]);
        List<int[]> result = new ArrayList<>();
        if (ranges.length == 0)
            return result;
        int end = ranges[0][1];
        for (int[] r : ranges){
            if (r[0] > end)
                result.add(new int[]{end, r[0]});
            end = Math.max(end, r[1]);
        }
        return result;
    }

    private static List<int[]> findGaps3(int[][] ranges) { // sort by end
        Arrays.sort(ranges, (r1, r2) -> r1[1]-r2[1]);
        List<int[]> result = new ArrayList<>();
        if (ranges.length == 0)
            return result;
        int end = ranges[0][1];
        for (int[] r : ranges){
            if (r[0] > end)
                result.add(new int[]{end, r[0]});
            end = Math.max(end, r[1]);
        }
        return result;
    }


    public static void main(String[] args) {
        int[][] testInputs1 = {
                {5, 10},
                {6, 7},
                {1, 3},
                {15, 18},
        };

        List<int[]> points = findGaps2(testInputs1);
        for (int[] p : points){
            System.out.println(Arrays.toString(p));
        }

        System.out.println();

        int[][] testInputs2 = {
                {-4, 10},
                {120, 130},
                {4, 11},
                {2, 5},
                {0, 11},
                {40, 70},
                {31, 32},
                {15, 21},
                {110, Integer.MAX_VALUE},
                {14, 18},
                {51, 80},
        };

        points = findGaps3(testInputs2);
        for (int[] p : points){
            System.out.println(Arrays.toString(p));
        }
    }
}