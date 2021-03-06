  # interview-concepts
### todo: combination, trie, permutation, powerset, quicksort (functions from clr), mergesort, s notes, my notes
### todo: graph 4 different ways, dijkstra, A*, spanning tree
### todo: create unchecked exception, iterator, iterable, comparator, priority queue(orders)
### todo: streams (Java 8)
### todo: list remove with iterator(is that the only way: no remove from end to start is one option too, removeAll is another optionn)? 




# Java: 
Init array: 
```
int[] array = new int[3];
int[] array = {1, 2, 3};
int[] array = new int[]{1, 2, 3};
```
Note: It's not possible to initialize an array after the declaration using the 2nd method approach. An attempt to do so will result in a compilation error.

The third way of initializing is useful when you declare the array first and then initialize it. 

The third way is also good for return statements. 

Init 2d array:
```
String[][] names = { 
                    {"John", "Smith"}, 
                    {"Javin", "Paul"}, 
                    {"James", "Gosling"}, 
                   };
```
Init List: 
```
List<String> strings = Arrays.asList("foo", "bar", "baz");
List<Integer> ints = Arrays.asList(1, 2, 3);
```
Init immutable vs mutable:
```
List<Integer> list=Arrays.asList(1, 2, 3); // immutable list (it's not mutable). List.of java 9 is immutable. See comment below
List<Integer> list=new ArrayList<>(Arrays.asList(1, 2, 3)); // mutable list
```
Actually Arrays.asList is not immutable. See [this post](https://stackoverflow.com/a/46579348/3769451) in SO. 

### convert int[] to List\<Integers\>
There is no direct way to make the conversion.  
The best way is to use something like this:
```
List<Integer> list = Arrays.stream(arr)
                                .boxed()        // Stream<Integer>
                                .collect(Collectors.toList());  
```

### print array: 
```
String[] array = new String[] {"John", "Mary", "Bob"};
System.out.println(Arrays.toString(array));
Output: [John, Mary, Bob]
```
Map, List can be printed directly: System.out.println(list); 

### print 2D array:
```
System.out.println(Arrays.deepToString(array)
                         .replace("],","\n").replace(",","\t| ")
                         .replaceAll("[\\[\\]]", " "));
```
will print: 
```
 1  |  2  |  3
 4  |  5  |  6
 7  |  8  |  9
 10 |  11 |  12
 13 |  15 |  15
```



### reverse List:
```
Collections.reverse(list); 
```
### reverse array:
```
 // method 1: using collections. However this is not possible for raw types. 
  public static void reverse() { 
    String[] arr = {“a”, “b”, “c”}; 
    System.out.println("array before reverse: " + Arrays.toString(arr) ); 
    List<String> list = Arrays.asList(arr); 
    Collections.reverse(list); 
    String[] reversed = list.toArray(arr); 
    System.out.println("array after reverse: " + Arrays.toString(reversed) ); 
  } 
// method 2: 
for(int i=0; i<array.length/2; i++){ 
  int temp = array[i]; 
  array[i] = array[array.length -i -1]; 
  array[array.length -i -1] = temp; 
}
```


### binary search:
Arrays.binarySearch, Collections.binarySearch
```
public static int binarySearch(int[] a,
               int key)
public static int binarySearch(int[] a,
               int fromIndex, // inclusive
               int toIndex, // exclusive
               int key)

```
Returns:  
index of the search key, if it is contained in the array within the specified range; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element in the range greater than the key, or toIndex if all elements in the range are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.


### dedup list using set: 
```
	List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 2, 2));
        List<Integer> dedup = new ArrayList<>(new HashSet<>(list));
        System.out.println(dedup); // will print [1, 2, 3]
```

<hr/>

### checked, unchecked exceptions: 
Runtime Exceptions : Runtime exceptions are referring to as unchecked exceptions. All other exceptions are checked exceptions, and they don't derive from java.lang.RuntimeException.  

Checked Exceptions : A checked exception must be caught somewhere in your code. If you invoke a method that throws a checked exception but you don't catch the checked exception somewhere, your code will not compile. That's why they're called checked exceptions : the compiler checks to make sure that they're handled or declared.  

Example for unchecked exception: [java.util.NoSuchElementException](https://docs.oracle.com/javase/7/docs/api/java/util/NoSuchElementException.html) that is returned for [queue.remove()](https://docs.oracle.com/javase/7/docs/api/java/util/Queue.html#remove())

Throwing unchecked exception example: 
```
throw new RuntimeException("something wrong!"); 
```

Example for extending RuntimeException [here](https://www.tutorialspoint.com/how-to-create-a-custom-unchecked-exception-in-java), to create a custom RuntimeException. 

### Exception: 
```
throw new Exception("test");
```
Also add this to function: throws Exception 
```
public void func() throws Exception
```

### hashcode, equal
```
public class Point 
{
    public int x, y;

    @Override
    public boolean equals(Object other) 
    {
        if (this == other)
          return true;

        if (!(other instanceof Point))
          return false;

        Point otherPoint = (Point) other;
        return otherPoint.x == x && otherPoint.y == y;
    }
    
    // new method After Java 7 
    @Override public int hashCode() {
     return Objects.hash(x, y); // or similar hash(x, y, z) or any other fields.
    }

    @Override
    public int hashCode() {
      return 31 * x + y;
    }
}

//another version:
public int hashCode() {
    int hash = 7;
    hash = 71 * hash + this.x;
    hash = 71 * hash + this.y;
    return hash;
}
```
[from this link stackoverflow](https://stackoverflow.com/a/9135980/3769451)  
[source for JDK 7+](https://mkyong.com/java/java-how-to-overrides-equals-and-hashcode/)

### iterable, iterator: 
Interfaces.   Iterable (introduced java 1.5), Iterator (introduced java 1.2). 

The iterator implementation in Java is just an inner class that implements the iterator interface. (It can be also another outside class, but has no usage, so there is no point to create a separate class). 

As explained [here](https://www.java-success.com/%E2%99%A5-java-iterable-vs-iterator-differences-and-know-how/), The “Iterable” was introduced to be able to use in the foreach loop. A class implementing the Iterable interface can be iterated over.

```
public interface Iterator<E>
{
    boolean hasNext();
    E next();
    void remove(); //optional
}
```
```
public interface Iterable<T>
{
    Iterator<T> iterator();
    
    // In java 8:
    default void forEach(Consumer<? super T> action);
}
```

[Sample Iterator](./Java/IteratorTest.java)

### Comparator vs Comparable: 
**Comparable**

A comparable object is capable of comparing itself with another object. The class itself must implements the java.lang.Comparable interface in order to be able to compare its instances.

method: `int compareTo(T o)`

**Comparator**

A comparator object is capable of comparing two different objects. The class is not comparing its instances, but some other class’s instances. This comparator class must implement the java.util.Comparator interface.

method: `int compare(T o1, T o2)`

It also has many comparators such as: naturalOrder(), reverseOrder(). 

### Collections.reverseOrder() vs Comparator.reverseOrder(): 
They are the same. 
```
     public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(2, 1, 3));
        list1.sort(Collections.reverseOrder());
        System.out.println(list1); // prints [3, 2, 1]

        List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 1, 3));
        list2.sort(Comparator.reverseOrder());
        System.out.println(list2); // prints [3, 2, 1]

    }
```

Under the hood: 
```
public static <T extends Comparable<? super T>> Comparator<T> reverseOrder() {
    return Collections.reverseOrder();
}
```
There is a small note about type safety, which prefers Collections.reverseOrder(). See [the comment here](https://stackoverflow.com/a/61178603/3769451).

### Comparator and lambdas: 
Example for multiline: 
```
    public static void main(String[] args) {
        Integer[] arr = {1, 3, 4, 5, 9};
        Function<Integer, Integer> f = x -> x*x;
        Comparator<Integer> comparator = (o1, o2) -> {
            o1 = o1 > 0? f.apply(o1) : -o1;
            o2 = o2 > 0? f.apply(o2) : -o2;
            return o1 - o2;
        };

        int result = Arrays.binarySearch(arr, -20, comparator);
        if (result < 0)
            result = -result - 1;
        System.out.printf("index: %d, element: %d", result, arr[result]); // prints index: 3, element: 5
    }
```
Example for one line: 
```
Function<Integer, Integer> f = x -> x*x;

Comparator<Integer> comp =
                (a, b) -> Integer.compare(f.apply(a), f.apply(b));
		

Comparator<Integer> comp = (a, b) -> b - a;		
```

### Comparator<? super T>
Meaning anything super type of T. 

This follows the PECS mnemonic: "Producer Extends, Consumer Super", which means that producers of things should work with things that have an upper bound ( ? extends T) and consumers (like comparator implementations that use things) should work with lower bounds ( ? super T). [Link](https://stackoverflow.com/a/13786317/3769451)

Why ? super T:   
It means T or any super class of T. Consider we are sorting an array of Dogs. As the comparator we can pass an Animal comparator (which for example compares animals based on their weights). 

### method references (Java 8): 
```
List<String> list = ...;
list.replaceAll(String::toUpperCase);
```
to create a new list: 
```
List<String> upper = list.stream().map(String::toUpperCase).collect(Collectors.toList());
```

### Queue, Deque
both are interface.  
```
Queue<E> q = new LinkedList<>(); 
Deque<E> dq = new LinkedList<>();   
```
Queue methods:  
throw exception: add, remove, element 
return special value: offer (boolean for capacity violation), poll(return null if empty), peek (return null if empty) 

Deque methods:
throw exception: addFirst, removeFirst, getFirst, addLast, removeLast, getLast  
Special value: offerFirst, pollFirst, peekFirst, offerLast, pollLast, peekLast   

Deque equivalent queue methods:
add -> addLast, offer -> offerLast
remove -> removeFirst, poll -> pollFirst
peek -> peekFirst

Queue exceptions are unchecked, no need for try catch or adding throws to function. 

### sorting
Arrays.sort, Collections.sort, list.sort

Arrays.sort works with or without comparator. 
Arrays.sort example:
```
String[] words = {"abc", "a", "ac"}; 
Arrays.sort(words, (a,b) -> a.length() - b.length()); 
System.out.println(Arrays.toString(words)); // prints [a, ac, abc]
```

list.sort() always takes a comparator but can pass null for natural ordering.  
list.sort was introduced in java 8. 
```
list.sort(null); 
//equivalent to: 
list.sort(Comparator.naturalOrder());
```
Collections.sort (which was introduced in Java 1.2) can take no argument, or a comparator. 
```
Collections.sort(list); 
```

### enum: 
```
public enum Level {
    HIGH,
    MEDIUM,
    LOW
}
```
```
Level level = Level.HIGH;
if( level == Level.HIGH) {

} else if( level == Level.MEDIUM) {

} else if( level == Level.LOW) {

}
```
```
Level level = ...  //assign some Level constant to it

switch (level) {
    case HIGH   : ...; break;
    case MEDIUM : ...; break;
    case LOW    : ...; break;
}
```
```
for (Level level : Level.values()) {
    System.out.println(level);
}
// HIGH
// MEDIUM
// LOW
```
<hr/>

## String: 

### Iterate over string characters: 
```
  int[] chars = new int[26]; //count all letters
  for (char c : text.toCharArray()) {
      chars[c - 'a']++;
  }
```
Also has an example of counting characters using character counter.   

Another way for counting string characters:
```
	int[] map = new int[256];
        for(int i=0; i< str.length(); i++){
            map[t.charAt(i)]++;
        }
```

## java map: 
#### foreach: 
```
Map<String, String> map = ...
for (Map.Entry<String, String> entry : map.entrySet()) {
    System.out.println(entry.getKey() + "/" + entry.getValue());
}
```

## Treemap: 
Sorted keyset. TreeMap, which is an implementation of the SortedMap interface. It presents its keys in sorted order.
```
Map<String, Object> map = new TreeMap<String, Object>();
/* Add entries to the map in any order. */
...
/* Now, iterate over the map's contents, sorted by key. */
for (Map.Entry<String, ?> entry : map.entrySet()) {
  System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

### Java8 forEach:
(Some examples:)[https://mkyong.com/java8/java-8-foreach-examples/]
```
//Map:
  items.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
	
	items.forEach((k,v)->{
		System.out.println("Item : " + k + " Count : " + v);
		if("E".equals(k)){
			System.out.println("Hello E");
		}
	});
  
// List: 
	items.forEach(item->System.out.println(item));
		
	items.forEach(item->{
		if("C".equals(item)){
			System.out.println(item);
		}
	});
		
	//method reference
	items.forEach(System.out::println);
	
	//Stream and filter
	items.stream()
		.filter(s->s.contains("B"))
		.forEach(System.out::println);
```

#### Arrays as keys
arrays can be put as map keys ([leetcode 436 - find-right-interval](https://leetcode.com/problems/find-right-interval)), but lookup only works for the same object ref, not for the objects with same value: 
```
public int[] findRightInterval(int[][] intervals) {
        Map<int[], Integer> map = new HashMap(); 
        for (int i = 0; i < intervals.length; i++){
            map.put(intervals[i], i);
        }...
```
For objects with same value, use List instead. The hashcode for the List depends on its objects, not the List itself. 
More info: https://stackoverflow.com/a/15576112/3769451


### Priority Queue: 
Default is min priority Queue, basead on natural ordering. 
```
PriorityQueue<Integer> pq = new PriorityQueue<>(); 
pq.add(1); 
int min = pq.peek(); 
min = pq.remove();
```
To create a max priority queue use one of these methods (In Java 8+): 
```
PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder()); 
PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a,b) -> b - a); 
PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a,b) -> b.compareTo(a)); 
```
### Int pass by reference:
Use a ref class, or use Atomic integer, (or use array):
```
class Ref{
	int val; 
}
...
	Ref i = new Ref(); 
	i.val = 2; 
	
// Generic case: 
public class Ref<T>{
    public T val;

    public Ref(T value)
    {
        this.val = val;
    }
}
...
	Ref<Integer> i = new Ref<>(); 
	i.val = 2; 

public static <T> void swap(Ref<T> t1, Ref<T> t2){
        T temp = t1.val;
        t1.val = t2.val;
        t2.val = temp;
}
```

<hr/>

## Greedy 
### Intervals

### Max intersecting intervals
Given a collection of intervals, find the maximum number of intersecting intervals. The intervals are inclusive  
Input: (1,100), (2,3), (5, 10), (6,12)  
Output: 3  
Input: (2,5), (3,6), (8,10),(9,12),(12,20)  
Output: 2

[Solution ](./MaxIntersectingIntervals.java)

### Maximal Disjoint Intervals
[Complete question] (https://www.geeksforgeeks.org/maximal-disjoint-intervals/)  
Choose lowest end points: Sort by end point, iterate and pick compatible intervals.   

<hr/>

## Sorting

### quick select 
quick select has average complexity of O(n), regardless of K.  
Quick select: find the Kth smallest or largest element.   
Find median: can be done via quick select in linear time.  
**Defition of median**: Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.

## Tree: 

### serialization: 
pre order or level order, both with null values.   
pre order cons: using stack, stack normally gets consumed faster than heap.   
Level order is with queue, memory coming from heap so it has more memory available.  

## LinkedList

### Doubly LinkedList:
doubly linked list with a dummy head and dummy tail. This way we add, remove nodes between two dummy nodes, but never worry about head, tail being null. 
``` 
Node head = new Node(); 
Node tail = new Node(); 
head.next = tail; 
tail.prev = head; 

public void add(Node node){
  node.next = head.next;
  head.next.prev = node; 
  head.next = node; 
}
public void remove(Node node){
  Node next = node.next;
  Node prev = node.prev; 
  prev.next = next; 
  next.prev = prev; 
}
```

## Sliding window: 
### min/max: 
min/max possible via min Queue, or via dp.   
DP: For sliding window of size k divide the array into chunks of Ks. for each chunk calculate the min (or max) up to that element once from left, once from right. Min of sliding window can be easily calculated using these two extra arrays.  
  
size of sliding window min/max array: N-K+1.  
Intuition: For window of size 1, we have N-1+1 elements.  
For window of size N we have N-N+1 = 1 element.  
Another intuition: tail of the window is at point K - 1. From K-1 to N-1 (last element of array), there are N-1 - K-1 + 1 = N-K+1 elements. 

### Min Queue:
problems: 
[shortest subarray with sum at least K](https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/)

## DP

### Memoization to DP: 
write recursion first, then convert to memoization, then dp.   
converting memoization to dp: find function (recursive function) can be used to access the dp array, and memoization base cases can be put in the find function, so base case for array initilaiztion is not needed, and array doesn't need to be one size larger for base case.  

### LIS:
good link for explanation of O(nlog(n)):  
https://algorithmsandme.com/longest-increasing-subsequence-in-onlogn/  
https://leetcode.com/articles/longest-increasing-subsequence/

### DP optimizations: 
- Removing one dimension: When formula for dp[i][j] depends only on the same row and column (and one of them is the row before or after), ie no diagonal, storage can be optimized. One dimension can be removed.   
Example: https://leetcode.com/problems/coin-change-2/

```
class Solution {
    
    //formula: f[i][amount] = find(amount - coins[i], i) + find(amount, i-1)
        
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
            dp[0] = 1;   
        
        for (int coin : coins){
            for (int j = coin; j <= amount; j++){            
                dp[j] += dp[j - coin]; 
            }
        }
        return dp[amount]; 
    }
}
```

## Grids, matrix
#### Print matrix diagonally: 
```
public class MatrixPrint {

    static void printDiag(int[][] matrix, int row, int col){
        String result = "";
        int m = matrix.length;
        int n = matrix[0].length;
        while(row < m && col >= 0){
            result += matrix[row][col] + " ";
            row++;
            col--;
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        int[][] matrix = {
                            {1, 2, 3},
                            {4, 5, 6},
                            {7, 8, 9},
                         };

        int m = matrix.length;
        int n = matrix[0].length;
        // using min/max: 
        for (int i = 0; i < m + n - 1; i++){
            int row = Math.max(0, i - n + 1);
            int col = Math.min(n-1, i);
            printDiag(matrix, row, col);
        }
        // using while loop: 
        int i = 0;
        int j = 0;
        while(i < m){
            printDiag(matrix, i, j);
            if (j < n - 1){
                j++;
            } else{
                i++;
            }

        }
    }
}
```
There are 4 cases for traversing rows or columns:  
- going up then flat: Max
- flat then going up: Max
- going downn then flat at 0: Min
- flat then going down to 0: Min   

Min or Max? Consider the line and flat line, then see if we are choosing the min or max. [See this picture](http://sketchtoy.com/69161196). Flat line at 0 is Max. Flat line at m-1 (or n-1) is Min. 

i sweeps between 0 and m+n-1. Last i is m + n - 2.  
There are m rows and n cols, and one row and col is common between sweeps, so m + n - 1.  

#### Spiral Matrix: 
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order. [leetcode spiral matrix](https://leetcode.com/problems/spiral-matrix/) 
```
Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
```
```
     public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>(); 
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) 
            return res; 
        int left = 0, right = matrix[0].length-1, top = 0, bottom = matrix.length-1; 
        while(left <= right && bottom >= top){
            for (int i = left; i <= right; i++)
                res.add(matrix[top][i]);
            
            for (int i = top+1; i <= bottom; i++)
                res.add(matrix[i][right]);
            
            if (top >= bottom || right <= left)
                break; 
            
            for (int i = right-1; i >= left; i--)
                res.add(matrix[bottom][i]);
            
            for (int i = bottom-1; i > top; i--)
                res.add(matrix[i][left]);
            
            top++; right--; bottom--; left++;
        }
```
General pattern for spiral matrix:  
Use top, bottom, left, right.  
Always work on a **rectangle**. ie don't mix up working on a point that doesn't belong to the current rectangle.  
In each round narrow the boundries.  
While condition: as long as top and bottom don't pass each other, same for left and right.  
The only condition we need to be careful: after printing top row and right column, for square matrix we should be careful for printing bottom and left. So if top and bottom are equal or left and right are equal, we don't need to return over the matrix to print bottom and left.  
With this solution there is no need to track the total number of elements visited.  

#### other versions of spiral matrix: 
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.
[Spiral Matrix II](https://leetcode.com/problems/spiral-matrix-ii/).  
Spiral from inside to outside:  
[Spiral Matrix III](https://leetcode.com/problems/spiral-matrix-iii/)

## Arrays:
problems:  
[maxium ramp width](https://leetcode.com/problems/maximum-width-ramp) Solution with min stack from start and sweeping from end. 

## Intervals: 
#### Find gap between intervals: 
Can sort by start, end, or both start and end. [Here](./Intervals/IntervalGaps.java) is the code.  
Sort by start, end: have a counter, when counter gets to 0 it's a start of a gap, when counter goes to 1 from 0 it's an end of a gap. 

#### Find maximum number of overlaps at the same time in intervals:
Sort by start and end and have a counter (+1 for start, -1 for end). Maximum number of overlaps is the max of the counter. 

<hr/>

## Binary search tree (BST): 
### Traversal:
Traversal is always iterative.   
In traversal, [left, right] starts from [-inf, inf] and it always shrinks toward the target. So if going right, left will shrink. Going left, right bound will shrink. 

## Design questions: 
### LRU Cache:
Data structures: Doubly linked list, Map<key, Node>. Or use Java LinkedHashMap.
Doubly linked list has head, tail.  
```
class Node{
  int key, 
  int value, 
  Node next; 
  Node prev; 
}
```
leetcode problem: https://leetcode.com/problems/lru-cache/

### Randomized set:
leetcode problem: https://leetcode.com/problems/insert-delete-getrandom-o1/
Insert, delete, getRandom: O(1).  
Data structures: Array (in java ArrayList) and HashMap.   
Array is used for returning a random element.  
HashMap is used for insert, delete O(1). value of the hashmap is the index in the array.  
Tricky part is the remove. Removing the element in the array: swapping the last element and removing last element. Trick is in the part that the element removed might be the last element. Can write the code in a way that doesn't matter whether the element is the last one or not. 


