/**
 * The BicyclePart class stores information about InventoryParts.
 *
 * @author Harrison Crosse
 * @version 1.0
 */

public class InventoryPart extends BicyclePart {

  private int quantity;

  /**
   * Default constructor, sets quantity to -1.
   */
  InventoryPart() {
    super();
    quantity = -1;
  }

  /**
   * Super(strings), sets quantity based upon strings.
   *
   * @param strings String[] of part information.
   */
  InventoryPart(String[] strings) {
    super(strings);
    quantity = Integer.parseInt(strings[5]);
  }

  /**
   * Super(strings), updates quantity.
   *
   * @param strings String[]from inventory file.
   */
  @Override
  void updateValues(String[] strings) {
    super.updateValues(strings);
    quantity = Integer.parseInt(strings[5]);
  }

  /**
   * Decrements the quantity of the part by 1 if the quantity is greater than 0.
   *
   * @return int -1 if failed, 1 if successful and new quantity greater than 0, 0 if new quantity is 0.
   */
  int decrement() {
    if (quantity >0) {
      quantity--;
      if (quantity >0) {
        return 1;
      }
      else return 0;
    }
    else {
      return -1;
    }
  }

  /**
   * Gets quantity.
   *
   * @return int quantity.
   */
  int getQuantity() {
    return quantity;
  }

  /**
   * Returns part info as string.
   *
   * @return String of part information.
   */
  @Override
  public String toString() {
    return (super.toString() + "," + quantity);
  }

}
