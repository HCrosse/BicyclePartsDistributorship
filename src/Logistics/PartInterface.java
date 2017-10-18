package Logistics;


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
