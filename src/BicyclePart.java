/**
 * The BicyclePart class stores and manipulates information about a BicyclePart.
 *
 * @author Harrison Crosse
 * @version 1.0
 * @since 2017-09-05
 */

public class BicyclePart implements Comparable<BicyclePart>{

  private String partName;
  private int partNumber;
  private double listPrice;
  private double salePrice;
  private boolean onSale;
  private int quantity;

  /**
   * Default constructor, generates an empty String, -1 for numerical values, and false for booleans.
   */
  BicyclePart() {
    this.partName = "";
    this.partNumber = -1;
    this.listPrice = -1;
    this.salePrice = -1;
    this.onSale = false;
    this.quantity = -1;
  }

  /**
   * Splits a string into disparate values, stores those values into global variables.
   *
   * @param inventoryString String from database file
   */
  BicyclePart(String inventoryString) {
    String[] strings = inventoryString.split(",");
    if (strings.length < 6) {
      setComparisonValues(strings);
    }
    else {
      partName = strings[0];
      partNumber = Integer.parseInt(strings[1]);
      listPrice = Double.parseDouble(strings[2]);
      salePrice = Double.parseDouble(strings[3]);
      onSale = Boolean.parseBoolean(strings[4]);
      quantity = Integer.parseInt(strings[5]);
    }
  }

  /* Setters */

  /**
   * Sets partName and partNumber from partial String for equals method.
   *
   * @param strings String.split array of String passed to constructor
   */
  private void setComparisonValues(String[] strings) {
    if (strings.length == 1) {
      this.partName = strings[0];
      this.partNumber = -1;
    }
    else {
      this.partName = strings[0];
      this.partNumber = Integer.parseInt(strings[1]);
    }
  }

  /**
   * Updates listPrice, salePrice, onSale, and quantity.
   *
   * @param newValues String from inventory file
   */
  void updateValues(String newValues) {
    String[] strings = newValues.split(",");
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
    quantity += Integer.parseInt(strings[5]);
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

  /* Getters */

  /**
   * Returns the partName.
   *
   * @return String partName
   */
  String getPartName() { return partName; }

  /**
   * Returns the partNumber.
   *
   * @return int partNumber
   */
  int getPartNumber() {
    return partNumber;
  }

  /**
   * Returns the listPrice.
   *
   * @return double listPrice
   */
  double getListPrice() {
    return listPrice;
  }

  /**
   * Returns the salePrice.
   *
   * @return double salePrice
   */
  double getSalePrice() {
    return salePrice;
  }

  /**
   * Returns the sale status.
   *
   * @return boolean onSale
   */
  boolean getSaleStatus() {
    return onSale;
  }

  /**
   * Returns the quantity.
   *
   * @return int quantity
   */
  int getQuantity() {
    return quantity;
  }

  /**
   * Returns the active price.
   *
   * @return double active price
   */
  double getActivePrice() {
    if (onSale) {
      return salePrice;
    }
    else {
      return listPrice;
    }
  }

  /* Other methods */

  /**
   * Returns partName + active price as string.
   *
   * @return String partName + price
   */
  String display() {
    String displayString = partName + " costs ";
    if (onSale) {
      displayString += salePrice;
      return displayString;
    }
    else {
      displayString += listPrice;
      return displayString;
    }
  }

  /**
   * Checks if this partName or partNumber equals other partName or partNumber.
   *
   * @param o other part
   * @return boolean true if equal, false if not
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
      BicyclePart other = (BicyclePart)o;
      return (this.partName.equals(other.partName) || this.partNumber == other.partNumber);
    }
  }

  /**
   * Returns String .equals for this and other partName, used in .contains and .sort methods of List.
   *
   * @param otherPart Other BicyclePart
   * @return int  if equal, -1 if other greater, 1 if other lesser
   */
  @Override
  public int compareTo(BicyclePart otherPart) {
    return this.partName.compareTo(otherPart.partName);
  }

  /**
   * Returns part info as string.
   *
   * @return String of part information
   */
  @Override
  public String toString() {
    return partName + ',' + partNumber + ',' + listPrice + ',' + salePrice +',' + onSale + ',' + quantity;
  }
}