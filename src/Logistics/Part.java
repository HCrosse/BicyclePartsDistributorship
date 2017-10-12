package Logistics;

import java.util.ArrayList;

/**
 * The Part class stores information about Parts.
 *
 * @author Harrison Crosse
 * @version 2.0
 */

public class Part {

  private String partName;
  private int partNumber;
  private double listPrice;
  private double salePrice;
  private boolean onSale;

  static ArrayList<Part> existingParts;

  /**
   * Initializes part fields based upon strings.
   *
   * @param strings String[] of part information.
   */
  public Part(String[] strings) {
    partName = strings[0];
    partNumber = Integer.parseInt(strings[1]);
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
  }

  /**
   * Returns an existing Part if it exists or a new Part if it doesn't.
   *
   * @param strings String[] of part information.
   * @return Part either existing or new Part.
   */
  static Part partFactory(String[] strings) {
    if (existingParts == null) {
      existingParts = new ArrayList<>();
    }
    for (Part p : existingParts) {
      if (strings[0].equals(p.getPartName()) ||
          Integer.parseInt(strings[1]) == (p.getPartNumber())) {
        return p;
      }
    }
    if (strings[0].equals("") || strings[1].equals("-1")) {
      return new Part(strings);
    }
    Part newPart = new Part(strings);
    existingParts.add(newPart);
    return newPart;
  }

  /**
   * Updates listPrice, salePrice, and onSale.
   *
   * @param strings String[] from inventory file.
   */
  void updateValues(String[] strings) {
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
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
   * Returns the listPrice.
   *
   * @return double listPrice.
   */
  double getListPrice() {
    return listPrice;
  }

  /**
   * Gets the salePrice.
   *
   * @return double salePrice.
   */
  double getSalePrice() {
    return salePrice;
  }

  /**
   * Gets the sale status.
   *
   * @return boolean onSale.
   */
  boolean getSaleStatus() {
    return onSale;
  }

  /**
   * Gets the active price.
   *
   * @return double active price.
   */
  double getActivePrice() {
    return onSale ? salePrice : listPrice;
  }

  /**
   * Returns partName + active price as string.
   *
   * @return String partName + price.
   */
  String display() {
    return (partName + " costs $" + getActivePrice());
  }

  /**
   * Returns part info as string.
   *
   * @return String of part information.
   */
  @Override
  public String toString() {
    return (partName + "," + partNumber + "," + listPrice + "," + salePrice + "," + onSale);
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
      return (this.partName.equals(other.getPartName()) ||
          this.partNumber == other.getPartNumber());
    }
  }

}
