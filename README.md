  # interview-concepts
### todo: combination, trie, permutation, powerset, quicksort (functions from clr), mergesort, s notes, my notes


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
```
### print array: 
```
String[] array = new String[] {"John", "Mary", "Bob"};
System.out.println(Arrays.toString(array));
Output: [John, Mary, Bob]
```
Map, List can be printed directly: System.out.println(list); 

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
Arrays.binarySearch. 
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

<hr/>

### Exception: 
```
throw new Exception("test");
```
Also add this to function: throws Exception 
```
public void func() throws Exception
```


## String: 

### Iterate over string characters: 
```
int[] chars = new int[26]; //count all letters
        for (char c : text.toCharArray()) {
            chars[c - 'a']++;
        }
```
Also has an example of counting characters using character counter.   

## java map: 
#### foreach: 
```
Map<String, String> map = ...
for (Map.Entry<String, String> entry : map.entrySet()) {
    System.out.println(entry.getKey() + "/" + entry.getValue());
}
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


<hr/>

## Greedy 
### Intervals

Given a collection of intervals, find the maximum number of intersecting intervals. The intervals are inclusive  
Input: (1,100), (2,3), (5, 10), (6,12)  
Output: 3  
Input: (2,5), (3,6), (8,10),(9,12),(12,20)  
Output: 2

[Solution ](./MaxIntersectingIntervals.java)

<hr/>

## Sorting

### quick select 
quick select has average complexity of O(n), regardless of K.  
Quick select: find the Kth smallest or largest element.   
Find median: can be done via quick select in linear time.  

## Tree: 

### serialization: 
pre order or level order, both with null values.   
pre order cons: using stack, stack normally gets consumed faster than heap.   
Level order is with queue, memory coming from heap so it has more memory available.  

## LinkedList
doubly linked list with a dummy head, tail. This way we add, remove nodes between two dummy nodes, but never worry about head, tail being null. 
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


## LRU Cache:
Data structures: Doubly linked list, Map<key, Node>.  
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

## Sliding window: 
### min/max: 
min/max possible via min Queue, or via dp.   
DP: For sliding window of size k divide the array into chunks of Ks. for each chunk calculate the min (or max) up to that element once from left, once from right. Min of sliding window can be easily calculated using these two extra arrays.  
  
size of sliding window min/max array: N-K+1.  
Intuition: For window of size 1, we have N-1+1 elements.  
For window of size N we have N-N+1 = 1 element.  
Another intuition: tail of the window is at point K - 1. From K-1 to N-1 (last element of array), there are N-1 - K-1 + 1 = N-K+1 elements. 

## DP
### LIS:
good link for explanation of O(nlog(n)):  
https://algorithmsandme.com/longest-increasing-subsequence-in-onlogn/
