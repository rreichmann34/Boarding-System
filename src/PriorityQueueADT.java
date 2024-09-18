// DO NOT SUBMIT THIS SOURCE FILE TO GRADESCOPE
/**
 * This generic interface represents the priority queue abstract data type
 *
 * @param <T> type parameter bounded by the Comparable interface. It represents the type of elements
 *            held in this queue
 */
public interface PriorityQueueADT<T extends Comparable<T>> {

  /**
   * checks if the priority queue is empty
   * 
   * @return true if the priority queue is empty, false otherwise
   */
  public boolean isEmpty();

  /**
   * Returns the number of elements in this priority queue
   * 
   * @return the number of elements in this priority queue
   */
  public int size();

  /**
   * Returns without removing the element with the highest priority
   * 
   * @return the element with the highest priority
   * @throws NoSuchElementException if this priority queue is empty
   */
  public T peekBest();

  /**
   * Inserts a new element e into this priority queue. This method returns false if the queue is
   * already full.
   * 
   * @param e the new element to insert into this priority queue
   * 
   * @return true if the element e was added to this queue, else false
   * @throws NullPointerException if the element e is null
   */
  public boolean enqueue(T e);


  /**
   * Removes and returns the element with the highest priority
   * 
   * @return the removed element
   * @throws NoSuchElementException if this priority queue is empty
   */
  public T dequeue();


}
