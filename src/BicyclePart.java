/**
 * The BicyclePart class stores information about BicycleParts.
 *
 * @author Harrison Crosse
 * @version 1.5
 */

public class BicyclePart extends Part{

  private double listPrice;
  private double salePrice;
  private boolean onSale;

  /**
   * Default constructor, sets -1 for numerical values, and false for booleans.
   */
  BicyclePart() {
    super();
    listPrice = -1;
    salePrice = -1;
    onSale = false;
  }

  /**
   * Super(strings), sets listPrice, salePrice, onSale values from strings.
   *
   * @param strings String[] of part information.
   */
  BicyclePart(String[] strings) {
    super(strings);
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
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
    return (super.getPartName() + " costs $" + getActivePrice());
  }

  /**
   * Returns part info as string.
   *
   * @return String of part information.
   */
  @Override
  public String toString() {
    return (super.toString() + "," + listPrice + "," + salePrice + "," + onSale);
  }
}
