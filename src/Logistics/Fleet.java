package Logistics;

import java.io.*;
import java.util.*;

public class Fleet {

  private static Warehouse mwh;
  private static ArrayList<Warehouse> vans = new ArrayList<Warehouse>();

  public Fleet() throws FileNotFoundException, IndexOutOfBoundsException {
    initFleet();
  }

  private void initFleet() throws FileNotFoundException, IndexOutOfBoundsException {
    File mainFile = new File("resources/warehouse/Main.txt");
    if (mainFile.exists()) {
      try {
        mwh = new Warehouse(mainFile);
      } catch (FileNotFoundException e) {
        e.printStackTrace();
        System.exit(1);
      } catch (IndexOutOfBoundsException e) {
        //unknown cause
      }
    } else {
      mwh = new Warehouse("Main");
    }

    File[] vanFiles = new File("resources/vans/").listFiles();
    if (vanFiles != null) {
      for (File file : vanFiles) {
        try {
          vans.add(new Warehouse(file));
        } catch (FileNotFoundException e) {
          e.printStackTrace();
          System.exit(2);
        } catch (IndexOutOfBoundsException e) {
          //unknown cause
        }
      }
    }
  }

  void save() throws IOException {
    mwh.save();
    for (Warehouse v : vans) {
      v.save();
    }
  }

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

  private void modifyInv(File file, Warehouse wh) throws FileNotFoundException {
    Scanner in = new Scanner(file);
    while (in.hasNextLine()) {
      String[] strings = in.nextLine().split(",");
      wh.addPart(strings);
    }
  }

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

  String[] sell(String choice, int partNumber) {
    String[] info = null;
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

  String display(String partName) {
    for (Part p : Part.existingParts) {
      if (p.getPartName().equals(partName)) {
        return p.display();
      }
    }
    return null;
  }

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

  boolean addVan(String vanName) {
    for (Warehouse v : vans) {
      if (v.getName().equals(vanName)) {
        return false;
      }
    }
    vans.add(new Warehouse(vanName));
    return true;
  }

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
