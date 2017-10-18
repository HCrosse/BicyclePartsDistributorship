package Logistics;

import java.util.Comparator;

/**
 * The BicyclePart class holds a Part and counts its quantity in a Warehouse.
 *
 * @author Harrison Crosse
 * @version 1.0
 */

public class InventoryPart implements Comparable<InventoryPart> {

  private Part part;
  private int quantity;

  /**
   * Default constructor, sets Part to null and quantity to -1.
   */
  InventoryPart() {
    part = null;
    quantity = -1;
  }

  /**
   * Calls Part.partFactory(strings), sets quantity based upon strings.
   *
   * @param strings String[] of part information.
   */
  InventoryPart(String[] strings) {
    part = Part.partFactory(strings);
    quantity = Integer.parseInt(strings[5]);
  }

  /**
   * Updates part values, this.quantity based on strings.
   *
   * @param strings String[]from inventory file.
   */
  void updateValues(String[] strings) {
    part.updateValues(strings);
    quantity += Integer.parseInt(strings[5]);
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

  /**
   * If this.quantity >= quantity, reduces this.quantity by quantity.
   *
   * @param quantity int of this.quantity to be reduced by.
   * @return int status, -1 = failure, 0 = success with new remaining quantity of 0, 1 = success
   * with >0 remaining quantity.
   */
  int remove(int quantity) {
    if (this.quantity > quantity) {
      this.quantity -= quantity;
      return 1;
    } else if (this.quantity == quantity) {
      this.quantity = 0;
      return 0;
    }
    return -1;
  }

  /**
   * Gets Part.
   *
   * @return Part part.
   */
  private Part getPart() {return part;}

  /**
   * Gets part.getPartName().
   *
   * @return String partName.
   */
  String getPartName() {return part.getPartName();}

  /**
   * Gets part.getActivePrice().
   *
   * @return double activePrice.
   */
  double getPrice() {return part.getActivePrice();}


  /**
   * Gets quantity.
   *
   * @return int quantity.
   */
  int getQuantity() {
    return quantity;
  }

  /**
   * Returns part info and quantity as string.
   *
   * @return String of part information.
   */
  @Override
  public String toString() {
    return (part.toString() + "," + quantity);
  }

  /**
   * Tests for equality between two InventoryParts.
   *
   * @param o Object o.
   * @return boolean true if equal, false if not.
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
      InventoryPart other = (InventoryPart)o;
      return (this.getPart().equals(other.getPart()));
    }
  }

  /**
   * Compares this.part.partName with o.part.partName.
   *
   * @param o InventoryPart o
   * @return -1 if o.partName is greater, 0 if o.partName is equal, 1 if o.partName is lesser.
   */
  @Override
  public int compareTo(InventoryPart o) {
    return this.getPart().getPartName().toLowerCase().compareTo(
        o.getPart().getPartName().toLowerCase());
  }

  /**
   * Compares first.part.partNumber with second.part.partNumber.
   */
  static final Comparator<InventoryPart> SORT_BY_NUM = (first, second) ->
      first.getPart().getPartNumber() - second.part.getPartNumber();

}
