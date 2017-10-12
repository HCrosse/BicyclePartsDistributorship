package Logistics;

import java.io.*;
import java.util.*;

/**
 * The Warehouse class simulates a warehouse of InventoryParts.
 *
 * @author Harrison Crosse
 * @version 2.0
 */

public class Warehouse {

  private String name;
  private File databaseFile;
  private ArrayList<InventoryPart> partArrayList = new ArrayList<>();

  /**
   * Default constructor, sets databaseFile to null.
   */
  public Warehouse() {
    name = null;
    databaseFile = null;
  }

  /**
   * Sets databaseFile to dbFilename, reads databaseFile into partArrayList.
   *
   * @param databaseFile
   * @throws FileNotFoundException if databaseFile cannot be found.
   * @throws IndexOutOfBoundsException happened once with an empty database, unsure if it will re-occur.
   */
  public Warehouse(File databaseFile) throws FileNotFoundException, IndexOutOfBoundsException {
    this.databaseFile = databaseFile;
    Scanner readDB = new Scanner(databaseFile);
    name = readDB.nextLine();
    while (readDB.hasNextLine()) {
      String line = readDB.nextLine();
      partArrayList.add(new InventoryPart(line.split(",")));
    }
    readDB.close();
  }

  public Warehouse(String whName) {
    this.name = whName;
    databaseFile = new File(name.replaceAll("[^a-zA-Z]+", "").toLowerCase());
  }

  /**
   * Saves partArrayList into the databaseFile.
   *
   * @throws IOException if file write is unsuccessful.
   */
  void save() throws  IOException {
    sortName();
    FileWriter fWriter;
    fWriter = new FileWriter(databaseFile);
    PrintWriter pWriter = new PrintWriter(fWriter);
    pWriter.println(name);
    for (InventoryPart element : partArrayList) {
      pWriter.println(element);
    }
    pWriter.close();
  }

  String getName() {
    return name;
  }

  String[] decrement(int index) {
    InventoryPart soldPart = getPart(index);
    int status = soldPart.decrement();
    if (status < 1) {
      removePart(index);
    }
    if (status < 0) {
      return null;
    }
    String[] str = {soldPart.getPartName(), Double.toString(soldPart.getPrice()),
        new Date().toString()};
    return str;
  }

  /**
   * Removes the part at index from partArrayList.
   *
   * @param index int index of the part to be removed.
   */
  void removePart(int index) {
    partArrayList.remove(index);
  }

  /**
   * Adds a part to partArrayList.
   *
   * @param newPart InventoryPart to be added.
   */
  void addPart (String[] strings) {

    int index = getIndex(strings[0]);
    if (index >= 0) {
      getPart(index).updateValues(strings);
    } else {
      partArrayList.add(new InventoryPart(strings));
    }
  }

  /**
   * Sorts partArrayList by name.
   */
  void sortName() {
    Collections.sort(partArrayList);
  }

  /**
   * Sorts partArrayList by number.
   */
  void sortNumber() {
    Collections.sort(partArrayList, InventoryPart.SORT_BY_NUM);
  }

  /**
   * Pads a partName for creation of a new InventoryPart.
   *
   * @param partName String partName.
   * @return String[] to create a new InventoryPart.
   */
  private String[] padString(String partName) {
    return new String[] {partName, "-1", "-1", "-1", "false", "-1"};
  }

  /**
   * Pads a partNumber for creation of a new InventoryPart.
   *
   * @param partNumber int partNumber.
   * @return String[] to create a new InventoryPart.
   */
  private String[] padString(int partNumber) {
    return new String[] {"", String.valueOf(partNumber), "-1", "-1", "false", "-1"};
  }

  /**
   * Gets the databaseFile.
   *
   * @return File databaseFile.
   */
  File getFile() {
    return databaseFile;
  }

  /**
   * Gets partArrayList
   *
   * @return ArrayList of InventoryParts.
   */
  ArrayList<InventoryPart> getPartArrayList() {
    return partArrayList;
  }

  /**
   * Gets the part at the given index.
   *
   * @param index int index of part.
   * @return InventoryPart.
   */
  InventoryPart getPart(int index) {
    return partArrayList.get(index);
  }

  /**
   * Gets the index of a part with the given partName.
   *
   * @param partName String partName.
   * @return int index of the specified part.
   */
  int getIndex(String partName) {
    String strings[] = padString(partName);
    InventoryPart otherPart = new InventoryPart(strings);
    for (int i = 0; i < partArrayList.size(); i++) {
      if (partArrayList.get(i).getPart().equals(otherPart.getPart())) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Gets the index of a part with the given partNumber.
   *
   * @param partNumber int partNumber.
   * @return int index of the specified part.
   */
  int getIndex(int partNumber) {
    String strings[] = padString(partNumber);
    InventoryPart otherPart = new InventoryPart(strings);
    for (int i = 0; i < partArrayList.size(); i++) {
      if (partArrayList.get(i).getPart().equals(otherPart.getPart())) {
        return i;
      }
    }
    return -1;
  }

  /**
   * Converts all the elements of partArrayList to a string.
   *
   * @return String representation of partArrayList.
   */
  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (InventoryPart element : partArrayList) {
      str.append(element.toString());
      str.append("\n");
    }
    str.append("\n*****\n\n");
    return str.toString();
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    else if (this == o) {
      return true;
    }
    else {
      Warehouse other = (Warehouse) o;
      return (this.name.equals(other.getName()));
    }
  }

}
