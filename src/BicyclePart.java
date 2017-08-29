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



  /** Getters */
  //get

  /** Other methods */
  public boolean matches(String inventoryString) {
    String otherPartName = inventoryString.split(",")[0];
    return  (this.partName.equals(otherPartName));
  }

  @Override
  public String toString() { //WORK
    return super.toString();
  }
}
