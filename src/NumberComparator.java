import java.util.Comparator;

/**
 * The NumberComparator class uses the Comparator interface to compare two BicycleParts by partNumber
 *
 * @author Harrison Crosse
 * @version 1.0
 * @since 2017-09-05
 */

class NumberComparator implements Comparator<BicyclePart> {

  /**
   * Compares the partNumber of Object o1 with Object o2.
   *
   * @param o1 The first BicyclePart
   * @param o2 The second BicyclePart
   * @return An int of the difference between o1's and o2's partNumbers.
   */
  @Override
  public int compare(BicyclePart o1, BicyclePart o2) {
    return o1.getPartNumber() - o2.getPartNumber();
  }
}