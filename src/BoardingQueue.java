///////////////////////////////////////////////////////////////////////////////
//
// Title: The BoardingQueue class contains a heap of passengers, which are sorted in the heap based
// on the Passenger.compareTo() method. The heap can be peeked at without altering the array andit
// can add and remove passengers. A size variable is used to keep track of how many passengers are
// in the heap.
//
// Course: CS 300 Fall 2023
//
// Author: Remington Reichmann
// Email: rreichmann@wisc.edu
// Lecturer: Mouna Kacem
//
///////////////////////////////////////////////////////////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority boarding queue storing elements of type
 * Passenger. This class guarantees the min-heap invariant, so that:
 * 
 * The Passenger at the root should be the smallest Passenger in the queue, which corresponds to the
 * passenger having the highest priority.
 * 
 * Children always are greater than their parent.
 * 
 * The Passenger at the root of this min-heap priority queue must be dequeued (board the airplane)
 * next, meaning removed and returned by the dequeue method.
 * 
 * The BoardingQueue.peekBest() must return without removing the Passenger at the root of this
 * min-heap queue, if the queue is not empty.
 * 
 * We rely on the Passenger.compareTo() method to compare Passengers.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class BoardingQueue implements PriorityQueueADT<Passenger> {

  // oversize array
  private Passenger[] heap;// array min-heap of Passengers representing this priority queue
  private int size; // size of this priority queue
  // The heap is an oversize array, meaning that the following should be ensured:
  // heap[i] == null for all valid indexes and size == 0 when the heap is EMPTY
  // If the heap is NOT empty:
  // heap[i] != null when i >= 0 && i < size
  // heap[i] == null when i >= size && i < heap.length

  /**
   * Constructs an empty BoardingQueue with the given capacity
   * 
   * @param capacity Capacity of this boarding queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer (greater than zero)
   */
  public BoardingQueue(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException(
          "The capacity of the BoardingQueue must be a positive integer!");
    }
    size = 0;
    heap = new Passenger[capacity];
  }


  /**
   * Returns the capacity of this BoardingQueue
   * 
   * @return the capacity of this BoardingQueue
   */
  public int capacity() {
    return heap.length;
  }



  /**
   * Removes all the elements from this Boarding Queue
   */
  public void clear() {
    // Set all passengers in the heap to null
    for (int i = 0; i < size; i++) {
      heap[i] = null;
    }
    // Reset the size back to 0
    size = 0;
  }


  /**
   * Checks whether this BoardingQueue is full
   * 
   * @return true if this boarding queue is full
   */
  public boolean isFull() {
    return capacity() == size;
  }


  // toArray() method PROVIDED
  /**
   * Returns a deep copy of the array-heap of this BoardingQueue. This method can be used for
   * testing purposes.
   * 
   * This method can be used for testing purposes.
   * 
   * @return a deep copy of the array-heap storing the Passengers in this queue
   * @throws a NullPointerException if the heap array of this BoardingQueue is null
   */
  public Passenger[] toArray() {
    return Arrays.copyOf(this.heap, this.heap.length);
  }


  /**
   * Returns a deep copy of this BoardingQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * Passengers. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this BoardingQueue. The returned new boarding queue (the deep copy) has
   *         the same length and size as this queue.
   */
  public BoardingQueue deepCopy() {
    BoardingQueue deepCopy = new BoardingQueue(capacity());

    for (int i = 0; i < size; i++) {
      deepCopy.heap[i] = heap[i];
      deepCopy.size++;
    }

    return deepCopy;
  }


  /**
   * Returns a String representing this boarding queue, where each Passenger in the queue is listed
   * on a separate line, in order from smallest to greatest, meaning in their boarding order.
   * 
   * @return a String representing this boarding queue, and an empty String "" if this queue is
   *         empty.
   */
  @Override
  public String toString() {
    String s = "";
    BoardingQueue deepCopy = this.deepCopy();
    while (!deepCopy.isEmpty()) {
      s += deepCopy.dequeue().toString() + "\n";
    }
    return s.trim();
  }

  /**
   * Swaps two elements in the heap to maintain the min-heap ordering.
   * 
   * @param i the index of the first Passenger to be swapped
   * @param j the index of the second Passenger to be swapped
   */
  private void swap(int i, int j) {
    Passenger temp = heap[i];
    heap[i] = heap[j];
    heap[j] = temp;
  }

  /**
   * Restores the min-heap of the priority queue by percolating its root down the tree. If the
   * element at the given index does not violate the min-heap ordering property (it is smaller than
   * its smallest child), then this method does not modify the heap. Otherwise, if there is a heap
   * violation, then swap the element with the correct child and continue percolating the element
   * down the heap.
   * 
   * We assume that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate downwards
   */
  protected void percolateDown(int index) {
    // Base case
    if (2 * index + 1 >= size) {
      return;
    }

    Passenger leftChild = heap[2 * index + 1];
    Passenger rightChild = heap[2 * index + 2];
    Passenger min = heap[index];
    int minIndex = index;

    // Find the minimum between the rightChild, leftChild, and parent
    if (leftChild.compareTo(min) < 0) {
      min = leftChild;
      minIndex = 2 * index + 1;
    }
    if (rightChild != null && rightChild.compareTo(min) < 0) {
      min = rightChild;
      minIndex = 2 * index + 2;
    }

    // If the minimum is not the parent, percolate down the min child's subtree
    if (min.compareTo(heap[index]) < 0) {
      swap(minIndex, index);
      percolateDown(minIndex);
    }
  }

  /**
   * Restores the min-heap invariant of this priority queue by percolating a leaf up the heap. If
   * the element at the given index does not violate the min-heap invariant (it is greater than its
   * parent), then this method does not modify the heap. Otherwise, if there is a heap violation,
   * swap the element with its parent and continue percolating the element up the heap. We assume
   * that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate upwards
   */
  protected void percolateUp(int index) {
    // Base case
    if (index <= 0) {
      return;
    }

    // If this child node is less than the parent, swap the two values
    if (heap[index].compareTo(heap[(index - 1) / 2]) < 0) {
      swap(index, (index - 1) / 2);
    }
    percolateUp((index - 1) / 2);
  }

  /**
   * Checks if the heap is empty
   * 
   * @return true if the heap is empty, false otherwise
   */
  public boolean isEmpty() {
    return size == 0;
  }

  /**
   * Returns the number of elements in the heap
   * 
   * @return the number of elements in the heap
   */
  public int size() {
    return size;
  }

  /**
   * Returns without removing the element with the highest priority
   * 
   * @return the element with the highest priority, which is the root node in the heap
   * 
   * @throws NoSuchElementException if the heap is empty
   */
  public Passenger peekBest() {
    if (isEmpty()) {
      throw new NoSuchElementException("The heap is currently empty!");
    }

    return heap[0];
  }

  /**
   * Inserts a new element e into the heap. This method returns false if the heap is already full.
   * 
   * @param passenger the new element to insert into the heap
   * 
   * @return true if the passenger was added to this queue, else false
   * 
   * @throws NullPointerException if the passenger is null
   */
  public boolean enqueue(Passenger passenger) {
    if (passenger == null) {
      throw new NullPointerException("That passenger is null!");
    }
    if (isFull()) {
      return false;
    }

    // Add the passenger, increment the size, and percolate up
    heap[size] = passenger;
    size++;
    percolateUp(size - 1);
    return true;
  }

  /**
   * Removes and returns the element with the highest priority
   * 
   * @return the removed element
   * @throws NoSuchElementException if this heap is empty
   */
  public Passenger dequeue() {
    if (isEmpty()) {
      throw new NoSuchElementException("The heap is currently empty!");
    }
    // If there is one passenger in the heap, clear the heap and return the passenger
    if (size == 1) {
      Passenger returnedPassenger = heap[0];
      clear();
      return returnedPassenger;
    }

    // Bring the element at the tail of the heap to the head, decrement the size, and percolate down
    Passenger returnedPassenger = heap[0];
    heap[0] = heap[size - 1];
    heap[size - 1] = null;
    size--;
    percolateDown(0);

    return returnedPassenger;
  }
}
