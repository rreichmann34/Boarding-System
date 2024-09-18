///////////////////////////////////////////////////////////////////////////////
//
// Title: The BoardingSystemTester class tests the methods from the Passenger and BoardingQueue
// classes. Each tested method prints to the console whether it passed or failed.
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
 * This is a Utility class which implements tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 fall 2023 p10 Airplane Boarding System.
 *
 */
public class BoardingSystemTester {


  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having different boarding groups.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToDifferentGroup() {
    try {
      Passenger.resetPassengerOrder();

      Passenger p1 = new Passenger("Passenger 1", Group.B, true);
      Passenger p2 = new Passenger("Passenger 2", Group.A, true);

      // p2 should be less than p1
      return (p2.compareTo(p1) < 0) && (p1.compareTo(p2) > 0);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having the same boarding group, and different arrival orders.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupDifferentArrival() {
    try {
      Passenger.resetPassengerOrder();

      Passenger p1 = new Passenger("Passenger 1", Group.A, true);
      Passenger p2 = new Passenger("Passenger 2", Group.A, true);

      // p1 should be less than p2
      return (p1.compareTo(p2) < 0) && (p2.compareTo(p1) > 0);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Ensures two passengers having the SAME boarding group and with the SAME order of arrival are
   * equal (compareTo should return 0).
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupSameArrival() {
    try {
      Passenger.resetPassengerOrder();
      Passenger p1 = new Passenger("Passenger 1", Group.A, true);

      Passenger.resetPassengerOrder();
      Passenger p2 = new Passenger("Passenger 2", Group.A, true);

      // p1 and p2 should be equal
      return (p1.compareTo(p2) == 0) && (p2.compareTo(p1) == 0);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Ensures the correctness of the equals() method in the Passenger class. The passengers must be
   * of type Passenger, have the same name, arrival time, and group to be considered equal.
   * 
   * @return true if the two passengers are equal to each other
   */
  private static boolean testPassengerEquals() {
    try {
      Passenger.resetPassengerOrder();
      Passenger p1 = new Passenger("Passenger 1", Group.A, true);

      Passenger.resetPassengerOrder();
      Passenger p2 = new Passenger("Passenger 1", Group.A, true);

      // p1 and p2 should be equal
      return p1.equals(p2) && p2.equals(p1);
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Ensures the correctness of the constructor for BoardingQueue class.
   * 
   * This tester should implement at least the following test cases:
   *
   * - Calling the constructor of the BoardingQueue class with an invalid capacity should throw an
   * IllegalArgumentException - Calling the constructor or the BoardingQueue class with a valid
   * capacity should not throw any errors, and should result in a new BoardingQueue object which is
   * empty, has size 0, a capacity equal to the capacity that was passed as a parameter, and the
   * heap array contains only null references.
   *
   * @return true if the constructor of the BoardingQueue functions properly, false otherwise
   */
  public static boolean testBoardingQueueConstructor() {
    Passenger.resetPassengerOrder();
    try {
      BoardingQueue bq = new BoardingQueue(-1); // This line should throw an
                                                // IllegalArgumentException
    } catch (IllegalArgumentException e) {
    } catch (Exception e) { // Any other caught exceptions are broken and should return false;
      return false;
    }

    Passenger.resetPassengerOrder();
    try {
      BoardingQueue bq = new BoardingQueue(10);

      // bq should be empty, have a size of 0, have a capacity of 10, and contain all null
      // references
      if (!bq.isEmpty()) {
        return false;
      }
      if (bq.size() != 0) {
        return false;
      }
      if (bq.capacity() != 10) {
        return false;
      }
      Passenger[] copy = bq.toArray();
      for (int i = 0; i < bq.capacity(); i++) {
        if (copy[i] != null) {
          return false;
        }
      }
    } catch (Exception e) { // Any caught exceptions are broken and should return false
      return false;
    }

    // If all tests pass, return true
    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on an empty
   * queue and verifying it throws a NoSuchElementException.
   * 
   * @return true if BoardingQueue.peekBest() verifies a correct functionality, false otherwise
   */
  public static boolean testPeekBestEmptyQueue() {
    Passenger.resetPassengerOrder();

    try {
      BoardingQueue bq = new BoardingQueue(10);

      bq.peekBest(); // This line should throw a NoSuchElementException
    } catch (NoSuchElementException e) {
      return true;
    } catch (Exception e) { // Any other caught exceptions are broken and should return false
      return false;
    }

    return false; // If a NoSuchElementException is not caught, the peekBest() method is broken
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on a non-empty
   * queue and ensures it
   * 
   * 1) returns the Passenger having the highest priority (the minimum), and 2) does not remove that
   * Passenger from the boarding queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekBestNonEmptyQueue() {
    Passenger.resetPassengerOrder();

    try {
      BoardingQueue bq = new BoardingQueue(10);
      Passenger passenger = new Passenger("Passenger", Group.A, true);
      bq.enqueue(passenger);

      if (bq.peekBest().compareTo(passenger) != 0) {
        return false;
      }
    } catch (Exception e) { // Any caught exceptions means a broken implementation and false is
                            // returned
      return false;
    }

    // If the tests pass, return true
    return true;
  }

  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on an empty
   * queue and ensuring the method 1) adds the Passenger and 2) increments the size.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToEmptyQueue() {
    Passenger.resetPassengerOrder();

    try {
      BoardingQueue bq = new BoardingQueue(10);
      Passenger passenger = new Passenger("Passenger", Group.A, true);
      bq.enqueue(passenger);

      // bq should contain passenger and have a size of 1
      if (bq.peekBest().compareTo(passenger) != 0) {
        return false;
      }
      if (bq.size() != 1) {
        return false;
      }
    } catch (Exception e) { // Any caught exceptions means a broken enqueue() and false is returned
      return false;
    }

    // If all tests pass, return true
    return true;
  }


  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on a
   * non-empty queue and ensuring it
   * 
   * 1) inserts the Passenger at the proper position of the heap, increments the size by one, and
   * returns true, if the queue was not already full.
   * 
   * 2) returns false, without making any changes to the size of the queue or the array heap, if the
   * method is called on a full queue.
   * 
   * Try adding at least 5 Passengers.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToNonEmptyQueue() {
    Passenger.resetPassengerOrder();

    try {
      BoardingQueue bq = new BoardingQueue(10);
      Passenger p1 = new Passenger("Passenger 1", Group.A, true);
      Passenger p2 = new Passenger("Passenger 2", Group.B, true);
      Passenger p3 = new Passenger("Passenger 3", Group.C, true);
      bq.enqueue(p3);
      bq.enqueue(p1);
      bq.enqueue(p2);

      // bq should contain the elements in the following order: p1,p3,p2. The size should be 3.
      Passenger[] copy = bq.toArray();
      if (bq.size() != 3) {
        return false;
      }
      if (copy[0].compareTo(p1) != 0) {
        return false;
      }
      if (copy[1].compareTo(p3) != 0) {
        return false;
      }
      if (copy[2].compareTo(p2) != 0) {
        return false;
      }
    } catch (Exception e) { // Any caught exceptions means a broken enqueue() and false is returned
      return false;
    }

    // If all tests pass, return true
    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on an empty
   * queue and ensures a NoSuchElementException is thrown in that case.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueEmpty() {
    Passenger.resetPassengerOrder();

    try {
      BoardingQueue bq = new BoardingQueue(10);
      bq.dequeue(); // This line should throw a NoSuchElementException
    } catch (NoSuchElementException e) {
      return true;
    } catch (Exception e) { // Any other caught exceptions means a broken dequeue() and false is
                            // returned
      return false;
    }

    // If a NoSuchElementException is not caught, dequeue() is broken. Return false
    return false;
  }


  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on a queue of
   * size one and ensures the method call returns the correct Passenger, size is zero, and the array
   * heap contains null references, only.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueBoardingQueueSizeOne() {
    Passenger.resetPassengerOrder();

    try {
      BoardingQueue bq = new BoardingQueue(10);
      Passenger passenger = new Passenger("Passenger 1", Group.A, true);
      bq.enqueue(passenger);

      // dequeue should return passenger, the new size should be 0, and bq should contain only null
      // references
      if (bq.dequeue().compareTo(passenger) != 0) {
        return false;
      }
      if (bq.size() != 0) {
        return false;
      }

      Passenger[] copy = bq.toArray();
      for (int i = 0; i < bq.capacity(); i++) {
        if (copy[i] != null) {
          return false;
        }
      }
    } catch (Exception e) { // Any caught exceptions means a broken dequeue(). Return false
      return false;
    }

    // If all tests pass, return true
    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() when called on a non-empty boarding queue.
   * 
   * This tests ensures the dequeue() method removes, and returns the passenger with the highest
   * boarding priority in the queue, the size of the queue must be decremented by one, and the
   * contents of the heap array is as expected.
   * 
   * @return true if PriorityCareAdmissions.dequeue() returns the correct Passenger each time it is
   *         called and size is appropriately decremented, false otherwise
   */
  public static boolean testDequeueBoardingQueueNonEmpty() {
    Passenger.resetPassengerOrder();

    try {
      BoardingQueue bq = new BoardingQueue(10);
      Passenger p1 = new Passenger("Passenger 1", Group.A, true);
      Passenger p2 = new Passenger("Passenger 2", Group.B, true);
      Passenger p3 = new Passenger("Passenger 3", Group.C, true);
      Passenger p4 = new Passenger("Passenger 4", Group.A, true);
      Passenger p5 = new Passenger("Passenger 5", Group.B, true);
      Passenger p6 = new Passenger("Passenger 6", Group.C, true);
      bq.enqueue(p3);
      bq.enqueue(p1);
      bq.enqueue(p4);
      bq.enqueue(p6);
      bq.enqueue(p2);
      bq.enqueue(p5);

      // At this point, bq should contain passengers in the following order: p1,p2,p4,p6,p3,p5
      // Dequeue should dequeue them in the following order: p1,p4,p2,p5,p3,p6. The size should also
      // decrement after every dequeue() call.
      if (bq.dequeue().compareTo(p1) != 0) {
        return false;
      }
      if (bq.size() != 5) {
        return false;
      }
      if (bq.dequeue().compareTo(p4) != 0) {
        return false;
      }
      if (bq.size() != 4) {
        return false;
      }
      if (bq.dequeue().compareTo(p2) != 0) {
        return false;
      }
      if (bq.size() != 3) {
        return false;
      }
      if (bq.dequeue().compareTo(p5) != 0) {
        return false;
      }
      if (bq.size() != 2) {
        return false;
      }
      if (bq.dequeue().compareTo(p3) != 0) {
        return false;
      }
      if (bq.size() != 1) {
        return false;
      }
      if (bq.dequeue().compareTo(p6) != 0) {
        return false;
      }
      if (bq.size() != 0) {
        return false;
      }
    } catch (Exception e) { // Any caught exceptions means dequeue() is broken. Return false
      return false;
    }

    // If all tests pass, return true
    return true;
  }

  /**
   * Tests the functionality of the clear() method. Should implement at least the following
   * scenarios:
   * 
   * - clear can be called on an empty queue with no errors.
   * 
   * - clear can be called on a non-empty queue with no errors.
   * 
   * After calling clear(), this tester ensures that the queue is empty, meaning its size is zero
   * and its heap array contains only null references.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testBoardingQueueClear() {
    try {
      // Clear on an empty queue will still result in bq having a size of 0 and only null references
      BoardingQueue bq = new BoardingQueue(10);
      bq.clear();

      if (bq.size() != 0) {
        return false;
      }

      Passenger[] copy = bq.toArray();
      for (int i = 0; i < bq.capacity(); i++) {
        if (copy[i] != null) {
          return false;
        }
      }
    } catch (Exception e) { // Any caught exceptions means a broken clear(). Return false
      return false;
    }

    try {
      BoardingQueue bq = new BoardingQueue(10);
      Passenger p1 = new Passenger("Passenger 1", Group.A, true);
      Passenger p2 = new Passenger("Passenger 2", Group.B, true);
      Passenger p3 = new Passenger("Passenger 3", Group.C, true);
      Passenger p4 = new Passenger("Passenger 4", Group.A, true);
      Passenger p5 = new Passenger("Passenger 5", Group.B, true);
      Passenger p6 = new Passenger("Passenger 6", Group.C, true);
      bq.enqueue(p3);
      bq.enqueue(p1);
      bq.enqueue(p4);
      bq.enqueue(p6);
      bq.enqueue(p2);
      bq.enqueue(p5);

      bq.clear();

      // bq should now contain only null references and have a size of 0
      if (bq.size() != 0) {
        return false;
      }

      Passenger[] copy = bq.toArray();
      for (int i = 0; i < bq.capacity(); i++) {
        if (copy[i] != null) {
          return false;
        }
      }
    } catch (Exception e) { // Any caught exceptions means a broken clear(). Return false
      return false;
    }

    // If all tests pass, return true
    return true;
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testPassengerCompareToDifferentGroup: "
        + (testPassengerCompareToDifferentGroup() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupDifferentArrival: "
        + (testPassengerCompareToSameGroupDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupSameArrival: "
        + (testPassengerCompareToSameGroupSameArrival() ? "Pass" : "Failed!"));
    System.out.println("testPassengerEquals: " + (testPassengerEquals() ? "Pass" : "Failed!"));
    System.out.println(
        "testBoardingQueueConstructor: " + (testBoardingQueueConstructor() ? "Pass" : "Failed!"));
    System.out
        .println("testPeekBestEmptyQueue: " + (testPeekBestEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testPeekBestNonEmptyQueue: " + (testPeekBestNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out
        .println("testEnqueueToEmptyQueue: " + (testEnqueueToEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testEnqueueToNonEmptyQueue: " + (testEnqueueToNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println("testDequeueEmpty: " + (testDequeueEmpty() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueSizeOne: "
        + (testDequeueBoardingQueueSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueNonEmpty: "
        + (testDequeueBoardingQueueNonEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testBoardingQueueClear: " + (testBoardingQueueClear() ? "Pass" : "Failed!"));
  }

}
