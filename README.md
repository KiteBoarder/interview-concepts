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

