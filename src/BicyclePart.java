public class BicyclePart {

  private String[] strings;
  private String partName;
  private int partNumber;
  private double listPrice;
  private double salePrice;
  private boolean onSale;
  private int quantity;

  public BicyclePart() {
    this.partName = "";
    this.partNumber = 0;
    this.listPrice = 0;
    this.salePrice = 0;
    this.onSale = false;
    this.quantity = 0;
  }

  public BicyclePart(String inventoryString) {
    strings = inventoryString.split(",");
    setInitialValues();
  }

  /** Setters */
  private void setInitialValues() {
    partName = strings[0];
    partNumber = Integer.parseInt(strings[1]);
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
    quantity = Integer.parseInt(strings[5]);
  }

  public void updateValues(String newValues) {
    strings = newValues.split(",");
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
    quantity += Integer.parseInt(strings[5]);
  }

  public boolean decrement() {
    if (quantity >0) {
      quantity--;
      return true;
    }
    else {
      return false;
    }
  }

  /** Getters */
  public String getPartName() {
    return partName;
  }

  public int getPartNumber() {
    return partNumber;
  }

  public double getListPrice() {
    return listPrice;
  }

  public double getSalePrice() {
    return salePrice;
  }

  public boolean getSaleStatus() {
    return onSale;
  }

  public int getQuantity() {
    return quantity;
  }

  /** Other methods */
  public String display() {
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

  @Override
  public String toString() {
    return partName + ',' + partNumber + ',' + listPrice + ',' +salePrice +',' + onSale + ',' + quantity;
  }
}