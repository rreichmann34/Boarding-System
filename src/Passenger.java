///////////////////////////////////////////////////////////////////////////////
//
// Title: The Passenger class contains basic information for a Passenger object, such as the name,
// arrival order, group, and whether or not they are checked in or not. Passengers can also
// be compared based off of their group and arrival order.
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

/**
 * This class models Passenger objects ready to board an airplane.
 *
 */
public class Passenger implements Comparable<Passenger> {

  // Data fields
  private static int orderGen = 1; // generator of arrival orders of passengers
  private String name; // name of this passenger
  private final int ARRIVAL_ORDER; // arrival order of this passenger
  private Group group; // boarding group assigned to this passenger
  private boolean isCheckedIn; // indicates whether this passenger was checked in before boarding
                               // the airplane


  // CONSTRUCTOR - PROVIDED
  /**
   * Constructs a new Passenger given their name, boarding group, and checkedin status
   * 
   * @param name        name to be assigned to this Passenger
   * @param group       boarding group to be assigned to this Passenger
   * @param isCheckedIn indicates whether this passenger was checked in, or not
   * @throws IllegalArgumentException if name is null or blank or if group is null
   */
  public Passenger(String name, Group group, boolean isCheckedIn) {
    if (name == null || name.isBlank()) {
      throw new IllegalArgumentException("name is null or blank!");
    }

    if (group == null) {
      throw new IllegalArgumentException("boarding group is null!");
    }

    this.name = name;
    this.group = group;
    this.isCheckedIn = isCheckedIn;
    this.ARRIVAL_ORDER = nextOrder();
  }

  // nextOrder() PROVIDED
  /**
   * Generates and returns the arrival order to be assigned to the next Passenger object to be
   * created
   * 
   * @return the next generated order
   */
  private static int nextOrder() {
    return orderGen++;
  }

  // resetPassengerOrder() PROVIDED
  /**
   * Resets the arrival order generated to 1. This method can be used for testing purposes, or to
   * reset the system.
   */
  protected static void resetPassengerOrder() {
    orderGen = 1;
  }

  // name() PROVIDED
  /**
   * Gets the name of this passenger
   * 
   * @return the name of this passenger
   */
  public String name() {
    return name;
  }

  // isCheckedIn() PROVIDED
  /**
   * Determines whether this passenger was checked in before boarding the airplane
   * 
   * @return true if this passenger was checked in
   */
  public boolean isCheckedIn() {
    return this.isCheckedIn;
  }


  // toString() PROVIDED
  /**
   * Returns a String representation of this Passenger in the following format:
   * 
   * <name> from Group <group>
   * 
   * @return a String representation of this passenger
   */
  public String toString() {
    return this.name + " from Group " + this.group;
  }



  /**
   * Indicates whether some other object is "equal to" this Passenger.
   * 
   * @param obj the reference object with which to compare.
   * @return true if obj is an instance of Passenger and has the exact same name, group, and arrival
   *         order as this Passenger, otherwise return false.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Passenger) { // Checks that the obj is a Passenger first
      Passenger compare = (Passenger) obj;
      // Same name, group, arrival
      if (this.name().equals(compare.name()) && this.group.equals(compare.group)
          && this.ARRIVAL_ORDER == compare.ARRIVAL_ORDER) {
        return true;
      }
    }

    return false;
  }

  /**
   * Passengers are compared according to their boarding groups and then by their arrival order.
   * 
   * If this passenger is in a group less than the input passenger, a negative int is returned. If
   * this passenger is in a group greater than the input passenger, return a positive int. Ex/ This
   * passenger(p1) group is Group.A Input passenger(p2) group is Group.B p1.compareTo(p2) returns -1
   * p2.compareTo(p1) returns 1
   * 
   * If the passengers have the same group, they are compared by arrival time. Ex/ This
   * passenger(p1) arrival time is 3 Input passenger(p2) arrival time is 7 p1.compareTo(p2) returns
   * -1 p2.compareTo(p1) returns 1
   * 
   * If both passengers have the same group and arrival time, return 0.
   * 
   * @param passenger the passenger that this object will be compared to
   * 
   * @return -1 if this passenger is less than the parameter passenger, 1 if this passenger is
   *         greater than the parameter passenger, 0 if this passenger and parameter passenger have
   *         same group and arrival time.
   */
  public int compareTo(Passenger passenger) {
    // This passenger is less than parameter passenger
    if (this.group.compareTo(passenger.group) < 0) {
      return -1;
    }
    // This passenger is greater than parameter passenger
    else if (this.group.compareTo(passenger.group) > 0) {
      return 1;
    }

    // If the passenger groups are equal, compare arrival times

    // This passenger is less than parameter passenger
    if (this.ARRIVAL_ORDER < passenger.ARRIVAL_ORDER) {
      return -1;
    } 
    // This passenger is greater than parameter passenger
    else if (this.ARRIVAL_ORDER > passenger.ARRIVAL_ORDER) {
      return 1;
    }
    // Else runs if the group and arrival time are the same
    else {
      return 0;
    }
  }


}
