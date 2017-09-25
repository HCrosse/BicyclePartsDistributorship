import java.io.*;
import java.util.*;

public class Warehouse {

  private File databaseFile;
  private ArrayList<InventoryPart> partArrayList;

  Warehouse() {
    partArrayList = new ArrayList<>();
    databaseFile = null;
  }

  Warehouse(String dbFilename) throws FileNotFoundException {
    databaseFile = new File(dbFilename);
    Scanner readDB = new Scanner(databaseFile);
    while (readDB.hasNextLine()) {
      String line = readDB.nextLine();
      partArrayList.add(new InventoryPart(line.split(",")));
    }
    readDB.close();
  }

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

  void removePart(int index) {
    partArrayList.remove(index);
  }

  void addPart (InventoryPart newPart) {
    partArrayList.add(newPart);
  }

  File getDatabaseFile() {
    return databaseFile;
  }

  ArrayList<InventoryPart> getPartArrayList() {
    return partArrayList;
  }

  InventoryPart getPart (int index) {
    return partArrayList.get(index);
  }

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

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();
    for (InventoryPart element : partArrayList) {
      str.append(element.toString());
      str.append("\n");
    }
    return str.toString();
  }

  void sortName() {
    Collections.sort(partArrayList);
  }

  void sortNumber() {
    Collections.sort(partArrayList, new NumberComparator());
  }

  private String[] padString(String partName) {
    return new String[] {partName, "-1", "-1", "-1", "false", "-1"};
  }

  private String[] padString(int partNumber) {
    return new String[] {"", String.valueOf(partNumber), "-1", "-1", "false", "-1"};
  }

}
