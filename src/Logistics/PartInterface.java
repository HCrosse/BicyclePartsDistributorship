package Logistics;

/**
 * Specifies the public method signatures for the Part class.
 *
 * @author Harrison Crosse
 * @version 1.0
 */

public interface PartInterface {

  void updateValues(String[] strings);
  String getPartName();
  int getPartNumber();
  double getListPrice();
  double getSalePrice();
  boolean getSaleStatus();
  double getActivePrice();
  String display();
  String toString();
  boolean equals(Object o);

}
