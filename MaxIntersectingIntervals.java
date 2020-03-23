import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxIntersectingIntervals {

    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        // (1,100), (2,3), (5, 10), (6,12)
        intervals.add(new Interval(1,100));
        intervals.add(new Interval(2,3));
        intervals.add(new Interval(5,10));
        intervals.add(new Interval(6,12));
        int result = findMaxIntervals(intervals);
        System.out.println(result);

        intervals = new ArrayList<>();
        // (2,5), (3,6), (8,10),(9,12),(12,20)
        intervals.add(new Interval(2,5));
        intervals.add(new Interval(3,6));
        intervals.add(new Interval(8,10));
        intervals.add(new Interval(9,12));
        intervals.add(new Interval(12,20));
        result = findMaxIntervals(intervals);
        System.out.println(result);
    }

    private static int findMaxIntervals(List<Interval> intervals) {
        List<Pair> list = new ArrayList<>();
        for (Interval i : intervals){
            list.add(new Pair(i.start, 1));
            list.add(new Pair(i.end, -1));
        }
        list.sort(Comparator.naturalOrder());
        int count = 0;
        int max = 0;
        for (Pair p : list){
            if (p.y == 1) {
                count++;
                max = Math.max(max, count);
            }
            else {
                count--;
            }
        }
        return max;
    }

    static class Pair implements Comparable<Pair>{
        public Pair(int x, int y){
            this.x = x;
            this.y = y;
        }
        int x;
        int y;

        @Override
        public int compareTo(Pair o) {
            return this.x - o.x;
        }
    }


    static class Interval{
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }
        int start;
        int end;
    }
}
