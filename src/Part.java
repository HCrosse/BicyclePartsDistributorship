/**
 * The Part class stores information about Parts.
 *
 * @author Harrison Crosse
 * @version 1.0
 */

public class Part implements Comparable<Part> {

  private String partName;
  private int partNumber;

  /**
   * Default constructor, sets partName to "", partNumber to -1.
   */
  Part() {
    partName = "";
    partNumber = -1;
  }

  /**
   * Sets partName and partNumber based upon strings.
   *
   * @param strings String[] of part information.
   */
  Part(String[] strings) {
    partName = strings[0];
    partNumber = Integer.parseInt(strings[1]);
  }

  /**
   * Gets partName.
   *
   * @return String partName.
   */
  String getPartName() {
    return partName;
  }

  /**
   * Gets partNumber
   *
   * @return int partNumber.
   */
  int getPartNumber() {
    return partNumber;
  }

  /**
   * Determines if this part equals o.
   *
   * @param o Other part.
   * @return boolean equals.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    else if (this == o) {
      return true;
    }
    else {
      Part other = (Part)o;
      return (this.partName.equals(other.partName) || this.partNumber == other.partNumber);
    }
  }

  /**
   * Compares this.partName to otherPart.partName.
   *
   * @param otherPart Part this is being compared to.
   * @return int result from String.compareTo.
   */
  @Override
  public int compareTo(Part otherPart) {
    return this.partName.toLowerCase().compareTo(otherPart.partName.toLowerCase());
  }

  /**
   * Returns part info as string.
   *
   * @return String of part information.
   */
  @Override
  public String toString() {
    return (partName + "," + partNumber);
  }
}
