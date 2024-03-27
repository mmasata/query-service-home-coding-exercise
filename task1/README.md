# Thread Safety Linked List

## Calling the API

For ease of use I have prepared a [swagger file](src/main/resources/swagger.yaml) and also an exported [Insomnia file](src/main/resources/Insomnia.yaml). This way you can easily call the API.
At the same time the swagger serves as documentation for the API.

## Rest Api Client library

To create a simple CRUD REST API to manipulate LinkedList, I tried to find a simple library (it seemed unnecessary to
use Spring for such a basic use case).

After searching I found the [Spark library](https://sparkjava.com/), which is lightweight and simple to use.

## Data Structure implementation

The implementation consists of two main building blocks - the LinkedList class and the Node class. A Node is an object
that holds a specific value and a reference to another record in the Linked List. The LinkedList class holds a reference
to the first Node (head), the last Node (tail) and the number of records in the data structure.

### Thread safety

Thread safety, i.e. that only one thread will perform read/write operations on a given instance at a time, is ensured
via synchronized keyword directly on methods.

If we didn't take care of this and ran multiple threads over it, a race condition could occur and thus we wouldn't be
guaranteed what the data structure would look like.

As an alternative to thread-safety, I also considered volatile keyword or ReentrantLock.

### Operations

| Operation     	 | Asymptotic time 	 | Description                                                                                                                                                                       	 |
|-----------------|-------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| pop()         	 | O(n)            	 | Removes the last record from the data structure                                                                                                                                   	 |
| push()        	 | O(1)            	 | Adds a new record to the end of the data structure.                                                                                                                               	 |
| insertAfter() 	 | O(n)            	 | It must search LinkedList from the beginning until it finds the correct value. Then it "splits" the list into two and adds a new value between them and links them back together. 	 |
| size()        	 | O(1)            	 | Returns the number of records in LinkedList. Since we keep this information in a variable, it is not necessary to go through the whole structure, but just print the value.       	 |

- **pop** - Since I also store the tail (last record) internally, removing as such is O(1), but selecting a new tail
  takes
  O(n) because I have to go through the whole sheet. If I were to use a bidirectional linked sheet, it would take O(1)
  to get to the previous value in the sheet, and thus the total complexity would be O(1)
- **push** - Thanks to the internal tail storing (last record), I optimized from O(n) to O(1) this operation.
- **insertAfter** - If there is a value more than once in the linked list, it adds another value after the first one it
  finds (the one closest to the head).