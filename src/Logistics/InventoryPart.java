package Logistics;

import java.util.Comparator;

/**
 * The BicyclePart class stores information about InventoryParts.
 *
 * @author Harrison Crosse
 * @version 1.0
 */

public class InventoryPart implements Comparable<InventoryPart> {

  private Part part;
  private int quantity;

  /**
   * Default constructor, sets quantity to -1.
   */
  InventoryPart() {
    part = null;
    quantity = -1;
  }

  /**
   * Super(strings), sets quantity based upon strings.
   *
   * @param strings String[] of part information.
   */
  InventoryPart(String[] strings) {
    part = Part.partFactory(strings);
    quantity = Integer.parseInt(strings[5]);
  }

  /**
   * Super(strings), updates quantity.
   *
   * @param strings String[]from inventory file.
   */
  void updateValues(String[] strings) {
    part.updateValues(strings);
    quantity = Integer.parseInt(strings[5]);
  }

  /**
   * Decrements the quantity of the part by 1 if the quantity is greater than 0.
   *
   * @return int -1 if failed, 1 if successful and new quantity greater than 0,
   * 0 if new quantity is 0.
   */
  int decrement() {
    if (quantity > 0) {
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

  int sell(int quantity) {
    if (this.quantity > quantity) {
      this.quantity -= quantity;
      return 1;
    } else if (this.quantity == quantity) {
      this.quantity = 0;
      return 0;
    }
    return -1;
  }

  String getPartName() {return part.getPartName();}

  double getPrice() {return part.getActivePrice();}

  Part getPart() {return part;}

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

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    else if (this == o) {
      return true;
    }
    else {
      InventoryPart other = (InventoryPart)o;
      return (this.getPart().equals(other.getPart()));
    }
  }

  @Override
  public int compareTo(InventoryPart o) {
    return this.getPart().getPartName().toLowerCase().compareTo(
        o.getPart().getPartName().toLowerCase());
  }

  public static final Comparator<InventoryPart> SORT_BY_NUM = (first, second) ->
      first.getPart().getPartNumber() - second.getPart().getPartNumber();
}
