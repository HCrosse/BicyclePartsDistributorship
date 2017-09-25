public class InventoryPart extends BicyclePart {

  private int quantity;

  InventoryPart() {
    super();
    quantity = -1;
  }

  InventoryPart(String[] strings) {
    super(strings);
    quantity = Integer.parseInt(strings[5]);
  }

  @Override
  void updateValues(String[] strings) {
    super.updateValues(strings);
    quantity = Integer.parseInt(strings[5]);
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

  int getQuantity() {
    return quantity;
  }

  @Override
  public String toString() {
    return (super.toString() + "," + quantity);
  }

}
