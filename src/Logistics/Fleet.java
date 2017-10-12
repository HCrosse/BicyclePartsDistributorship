package Logistics;

import java.io.*;
import java.util.*;

public class Fleet {

  private static Warehouse mwh;
  private static ArrayList<Warehouse> vans = new ArrayList<>();

  /**
   * Initializes the fleet.
   *
   * @throws FileNotFoundException if a file used in Warehouse creation cannot be found.
   * @throws IndexOutOfBoundsException for unknown reason.
   */
  public Fleet() throws FileNotFoundException, IndexOutOfBoundsException {
    initFleet();
  }

  /**
   * Initializes mwh either from an existing file or from scratch, and vans from existing files.
   *
   * @throws FileNotFoundException if a file used in Warehouse creation cannot be found.
   * @throws IndexOutOfBoundsException for unknown reason.
   */
  private void initFleet() throws FileNotFoundException, IndexOutOfBoundsException {
    File mainFile = new File("resources/warehouse/Main.txt");
    if (mainFile.exists()) {
      mwh = new Warehouse(mainFile);
    } else {
      mwh = new Warehouse("Main");
    }
    File[] vanFiles = new File("resources/vans/").listFiles();
    if (vanFiles != null) {
      for (File file : vanFiles) {
        vans.add(new Warehouse(file));
      }
    }
  }

  /**
   * Saves the Main Warehouse and all vans to .txt files.
   *
   * @throws IOException if something goes wrong saving.
   */
  void save() throws IOException {
    mwh.save();
    for (Warehouse v : vans) {
      v.save();
    }
  }

  /**
   * Reads an inventory file into the specified Warehouse.
   *
   * @param file File to be read in.
   * @param choice String name of the Warehouse.
   * @throws FileNotFoundException if the file does not exist.
   */
  void readInventory(File file, String choice) throws FileNotFoundException {
    if (choice.equals("Main")) {
      modifyInv(file, mwh);
    } else {
      for (Warehouse v : vans) {
        if (choice.equals(v.getName())) {
          modifyInv(file, v);
          return;
        }
      }
    }
  }

  /**
   * Adds parts in the file into Warehouse wh.
   *
   * @param file File to be read in.
   * @param wh Warehouse where parts go.
   * @throws FileNotFoundException if the file does not exist.
   */
  private void modifyInv(File file, Warehouse wh) throws FileNotFoundException {
    Scanner in = new Scanner(file);
    while (in.hasNextLine()) {
      String[] strings = in.nextLine().split(",");
      wh.addPart(strings);
    }
  }

  /**
   * Adds the Part specified by strings to the specified Warehouse.
   *
   * @param strings String[] of Part and Warehouse information.
   */
  void enterPart(String[] strings) {
    if (strings[6].equals("Main")) {
      mwh.addPart(strings);
    } else {
      for (Warehouse v : vans) {
        if (strings[6].equals(v.getName())) {
          v.addPart(strings);
        }
      }
    }
  }

  /**
   * Attempts to sell a Part specified by partNumber from the specified Warehouse. Returns sale info
   * if successful.
   *
   * @param choice String of the Warehouse name.
   * @param partNumber int of the partNumber.
   * @return String[] of sale information.
   */
  String[] sell(String choice, int partNumber) {
    String[] info;
    int index;
    if (choice.equals("Main")) {
      index = mwh.getIndex(partNumber);
      if (index >= 0) {
        info = mwh.decrement(index);
        return info;
      } else {
        return null;
      }
    } else {
      for (Warehouse v : vans) {
        index = v.getIndex(partNumber);
        if (index >= 0) {
          info = v.decrement(index);
          return info;
        }
      }
    }
    return null;
  }

  /**
   * Displays information about the Part specified by partName.
   *
   * @param partName String name of the Part.
   * @return String of Part information.
   */
  String display(String partName) {
    for (Part p : Part.existingParts) {
      if (p.getPartName().equals(partName)) {
        return p.display();
      }
    }
    return null;
  }

  /**
   * Sorts Parts in the specified Warehouse by name and returns them as a String.
   *
   * @param choice Warehouse(s) chosen to be sorted.
   * @return String representation of all sorted parts.
   */
  String sortName(String choice) {
    StringBuilder str = new StringBuilder();
    switch (choice) {
      case "All":
        mwh.sortName();
        str.append(mwh.toString());
        for (Warehouse v : vans) {
          v.sortName();
          str.append(v.toString());
        }
        break;
      case "Main":
        mwh.sortName();
        str.append(mwh.toString());
        break;
      default:
        for (Warehouse v : vans) {
          if (choice.equals(v.getName())) {
            v.sortName();
            str.append(v.toString());
            return str.toString();
          }
        }
        break;
    }
    return str.toString();
  }

  /**
   * Sorts Parts in the specified Warehouse by number and returns them as a String.
   *
   * @param choice Warehouse(s) chosen to be sorted.
   * @return String representation of all sorted parts.
   */
  String sortNumber(String choice) {
    StringBuilder str = new StringBuilder();
    switch (choice) {
      case "All":
        mwh.sortNumber();
        str.append(mwh.toString());
        for (Warehouse v : vans) {
          v.sortNumber();
          str.append(v.toString());
        }
        break;
      case "Main":
        mwh.sortNumber();
        str.append(mwh.toString());
        break;
      default:
        for (Warehouse v : vans) {
          if (choice.equals(v.getName())) {
            v.sortNumber();
            str.append(v.toString());
            return str.toString();
          }
        }
        break;
    }
    return str.toString();
  }

  /**
   * Attempts to add a new van to vans, returns success status.
   *
   * @param vanName String name of the van to be added.
   * @return boolean true if successful, false if not.
   */
  boolean addVan(String vanName) {
    for (Warehouse v : vans) {
      if (v.getName().equals(vanName)) {
        return false;
      }
    }
    vans.add(new Warehouse(vanName));
    return true;
  }

  /**
   * Attempts to move parts from one Warehouse to another.
   *
   * @param moveFile File that specifies parts to be moved, and locations they're moved to or from.
   * @return int representation of success status, <1 = failure.
   * @throws FileNotFoundException if moveFile cannot be found.
   */
  int moveParts(File moveFile) throws FileNotFoundException {
    Scanner in = new Scanner(moveFile);
    String[] names = in.nextLine().split(",");
    Warehouse[] locations = new Warehouse[2];
    for (int i = 0; i < locations.length; i++) {
      if (names[0].equals(mwh.getName())) {
        locations[i] = mwh;
      } else {
        boolean exists = false;
        for (Warehouse v : vans) {
          if (names[0].equals(v.getName())) {
            exists = true;
            locations[i] = v;
            break;
          }
        }
        if (!exists) {
          return -1;
        }
      }
    }
    while (in.hasNextLine()) {
      String[] strings = in.nextLine().split(",");
      int index = locations[0].getIndex(strings[0]);
      if (index < 0) {
        return -2;
      }
      int contains = locations[0].sell(index, Integer.parseInt(strings[1]));
      if (contains == 0) {
        locations[0].removePart(index);
      } else if (contains == -1) {
        return -3;
      }
      locations[1].addPart(strings);
    }
    return 1;
  }

}
