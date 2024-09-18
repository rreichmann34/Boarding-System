// DO NOT SUBMIT THIS SOURCE FILE TO GRADESCOPE
/**
 * This enumeration defines the set of the predefined constants to determine the boarding group of 
 * a passenger in an airplane boarding system. 
 * 
 * Boarding Group:
 * Three boarding groups are defined: A, B, and C
 * Group A is less than B, and group B is less than group C.
 * Group B has a higher boarding priority compared to group C.
 * Group A has the highest boarding priority.
 * Group C passengers have the lowest boarding priority.
 * 
 * Constants in an enumeration are comparable.
 * References of type Group can be compared using the Comparable.compareTo() method. 
 * For instance: If we consider the following references.
 * Group a = Group.A;
 * Group b = Group.B;
 * Group c = Group.C;
 * The following statements are TRUE: 
 * a.compareTo(b) < 0
 * a.compareTo(c) < 0
 * b.compareTo(c) < 0
 * b.compareTo(a) > 0
 * c.compareTo(a) > 0
 * c.compareTo(b) > 0
 */
public enum Group {
  A, // Group A
  B, // Group B
  C  // Group C

}
