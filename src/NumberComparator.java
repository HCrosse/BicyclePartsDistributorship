import java.util.Comparator;

/**
 * The NumberComparator class uses the Comparator interface to compare two BicycleParts by partNumber
 *
 * @author Harrison Crosse
 * @version 1.0
 * @since 2017-09-05
 */

class NumberComparator implements Comparator<BicyclePart> {

  @Override
  public int compare(BicyclePart o1, BicyclePart o2) {
    return o1.getPartNumber() - o2.getPartNumber();
  }
}