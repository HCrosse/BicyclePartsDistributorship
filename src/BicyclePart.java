import java.util.Comparator;

public class BicyclePart implements Comparable<BicyclePart>{

  private String[] strings;
  private String partName;
  private int partNumber;
  private double listPrice;
  private double salePrice;
  private boolean onSale;
  private int quantity;

  public BicyclePart() {
    this.partName = "";
    this.partNumber = -1;
    this.listPrice = -1;
    this.salePrice = -1;
    this.onSale = false;
    this.quantity = -1;
  }

  private BicyclePart(String inventoryString) {
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

  private void updateValues(String newValues) {
    strings = newValues.split(",");
    listPrice = Double.parseDouble(strings[2]);
    salePrice = Double.parseDouble(strings[3]);
    onSale = Boolean.parseBoolean(strings[4]);
    quantity += Integer.parseInt(strings[5]);
  }

  public int decrement() {
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

  /** Getters */
  public String getPartName() { return partName; }

  private int getPartNumber() {
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
  private String display() {
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

  /** Returns 0 if name is equal, -1 if other is greater, 1 if other is lesser */
  @Override
  public int compareTo(BicyclePart otherPart) {
    return this.partName.compareTo(otherPart.partName);
  }

  @Override
  public String toString() {
    return partName + ',' + partNumber + ',' + listPrice + ',' +salePrice +',' + onSale + ',' + quantity;
  }
}

class NumberComparator implements Comparator<BicyclePart> {

  @Override
  public int compare(BicyclePart o1, BicyclePart o2) {
    return o1.getPartNumber() - o2.getPartNumber();
  }
}