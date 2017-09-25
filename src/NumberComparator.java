import java.util.Comparator;

/**
 * The NumberComparator class uses the Comparator interface to compare two Parts by partNumber
 *
 * @author Harrison Crosse
 * @version 1.1
 */

public class NumberComparator implements Comparator<Part> {

  /**
   * Compares the partNumber of Object o1 with Object o2.
   *
   * @param o1 The first Part
   * @param o2 The second Part
   * @return int of the difference between o1's and o2's partNumbers.
   */
  @Override
  public int compare(Part o1, Part o2) {
    return o1.getPartNumber() - o2.getPartNumber();
  }
}