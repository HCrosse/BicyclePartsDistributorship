public class BicyclePart extends Part{

  private double listPrice;
  private double salePrice;
  private boolean onSale;

  /**
   * Default constructor, generates an empty String, -1 for numerical values, and false for booleans.
   */
  BicyclePart() {
    super();
    listPrice = -1;
    salePrice = -1;
    onSale = false;
  }

  /**
   * Splits a string into disparate values, stores those values into global variables.
   *
   * @param strings String array from database file
   */
  BicyclePart(String[] strings) {
    super(strings);
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
  }

  /* Setters */

  /**
   * Updates listPrice, salePrice, onSale, and quantity.
   *
   * @param strings String array from inventory file
   */
  void updateValues(String[] strings) {
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
  }

  /* Getters */

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
    return (super.getPartName() + " costs $" + getActivePrice());
  }

  /**
   * Returns part info as string.
   *
   * @return String of part information
   */
  @Override
  public String toString() {
    return (super.toString() + "," + listPrice + "," + salePrice + "," + onSale);
  }
}
