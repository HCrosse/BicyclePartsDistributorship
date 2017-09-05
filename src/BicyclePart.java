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
   * Default constructor.
   */
  public BicyclePart() {
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
    partName = strings[0];
    partNumber = Integer.parseInt(strings[1]);
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
    quantity = Integer.parseInt(strings[5]);
  }

  /* Setters */

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
   * Decrements the quantity of the part by 1 if the quantity is >0.
   *
   * @return -1 if failed, 1 if successful and new quantity >0, 0 if new quantity is 0.
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
  String getPartName() { return partName; }

  int getPartNumber() {
    return partNumber;
  }

  double getListPrice() {
    return listPrice;
  }

  double getSalePrice() {
    return salePrice;
  }

  boolean getSaleStatus() {
    return onSale;
  }

  int getQuantity() {
    return quantity;
  }

  /* Other methods */

  /**
   * Returns partName + active price as string.
   *
   * @return partName + price as string
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
   * Checks if this partName equals other partName.
   *
   * @param otherName Name of other part
   * @return true if equal, false if not
   */
  public boolean eqauls(String otherName) {
    return this.partName.equals(otherName);
  }

  /**
   * Checks if this partNumber equals other PartNumber.
   *
   * @param otherNumber Number of other part
   * @return true if equal, false if not
   */
  public boolean equals(int otherNumber) {
    return (this.partNumber - otherNumber) == 0;
  }

  /**
   * Overrides compareTo, used in .contains and .sort methods of List.
   *
   * @param otherPart Other BicyclePart
   * @return 0 if equal, -1 if other greater, 1 if other lesser
   */
  @Override
  public int compareTo(BicyclePart otherPart) {
    return this.partName.compareTo(otherPart.partName);
  }

  /**
   * Overrides toString, returns part info as string.
   *
   * @return Part information as a String
   */
  @Override
  public String toString() {
    return partName + ',' + partNumber + ',' + listPrice + ',' +salePrice +',' + onSale + ',' + quantity;
  }
}