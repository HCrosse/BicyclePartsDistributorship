import java.io.*;
import java.util.*;

/**
 * The Warehouse class simulates a warehouse of InventoryParts.
 *
 * @author Harrison Crosse
 * @version 1.0
 */

public class Warehouse {

  private File databaseFile;
  private ArrayList<InventoryPart> partArrayList = new ArrayList<>();

  /**
   * Default constructor, sets databaseFile to null.
   */
  Warehouse() {
    databaseFile = null;
  }

  /**
   * Sets databaseFile to dbFilename, reads databaseFile into partArrayList.
   *
   * @param dbFilename String filename of the database file.
   * @throws FileNotFoundException if databaseFile cannot be found.
   * @throws IndexOutOfBoundsException happened once with an empty database, unsure if it will re-occur.
   */
  Warehouse(String dbFilename) throws FileNotFoundException, IndexOutOfBoundsException {
    databaseFile = new File(dbFilename);
    Scanner readDB = new Scanner(databaseFile);
    while (readDB.hasNextLine()) {
      String line = readDB.nextLine();
      partArrayList.add(new InventoryPart(line.split(",")));
    }
    readDB.close();
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
    for (InventoryPart element : partArrayList) {
      pWriter.println(element);
    }
    pWriter.close();
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
  void addPart (InventoryPart newPart) {
    partArrayList.add(newPart);
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
    Collections.sort(partArrayList, new NumberComparator());
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
  File getDatabaseFile() {
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
  InventoryPart getPart (int index) {
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
      if (partArrayList.get(i).equals(otherPart)) {
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
      if (partArrayList.get(i).equals(otherPart)) {
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
    return str.toString();
  }

}
