package Users;

import Logistics.Warehouse;
import java.io.FileNotFoundException;

public class WarehouseManager extends LoginAccount {

  private String warehouseName;
  private Warehouse wh;

  public WarehouseManager() {
    warehouseName = null;
    wh = null;
  }

  public WarehouseManager(String[] strings, String un, String pw, String whName)
      throws FileNotFoundException, IndexOutOfBoundsException {
    super(strings, un, pw);
    warehouseName = whName;
    wh = new Warehouse("resources/" + warehouseName + ".txt");
  }

  String whToString() {
    return wh.toString();
  }

}
