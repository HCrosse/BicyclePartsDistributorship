import java.io.*;
import java.util.*;

/**
 * The Main class manages an inventory of BicycleParts to simulate a warehouse.
 *
 * @author Harrison Crosse
 * @version 1.0
 * @since 2017-09-05
 */

/** TODO
 * Test, specifically file IO and getIndex methods/.equals
 */

public class Main {

  private static Scanner keyboard = new Scanner(System.in);
  private static ArrayList<BicyclePart> partArrayList = new ArrayList<>();

  /**
   * Reads the warehouseDB.txt file into an ArrayList, and calls printMenu() and executes the method
   * that the user specifies.
   *
   * @param args CLI Arguments
   */
  public static void main(String[] args) {
    readDB();

    boolean needInput = true;
    do {
      String menuChoice = printMenu();
      switch (menuChoice) {
        case "Read":
          readInventory();
          break;
        case "Enter":
          enterPart();
          break;
        case "Sell":
          sellPart();
          break;
        case "Display":
          displayPart();
          break;
        case "SortName":
          sortName();
          break;
        case "SortNumber":
          sortNumber();
          break;
        case "Quit":
          save();
          needInput = false;
          break;
        default:
          System.out.println("Error: Invalid input entered.");
          break;
      }
    } while (!needInput);

  }

  /**
   * Prints the user menu and returns the user input.
   *
   * @return String of user input
   */
  private static String printMenu() {
    System.out.println("Please select your option from the following menu:");
    System.out.println("Read: Read an inventory delivery file");
    System.out.println("Enter: Enter a part");
    System.out.println("Sell: Sell a part");
    System.out.println("Display: Display a part");
    System.out.println("SortName: Sort parts by part name");
    System.out.println("SortNumber: Sort parts by part number");
    System.out.println("Quit:");
    System.out.println("Enter your choice:");
    return keyboard.nextLine();
  }

  /**
   * Reads warehouseDB.txt line by line, creating a new BicyclePart from each line and adding to
   * partArrayList.
   */
  private static void readDB() {
    File dbFile = new File("warehouseDB.txt");
    Scanner readDB = null;
    try {
      readDB = new Scanner(dbFile);
    } catch (FileNotFoundException e) {
      System.out.println("Error: File not found.");
      System.out.println("Program Terminating.");
      e.printStackTrace();
      System.exit(0);
    }
    while (readDB.hasNextLine()) {
      String line = readDB.nextLine();
      partArrayList.add(new BicyclePart(line));
    }
    readDB.close();
  }

  /**
   * Writes a sorted partArrayList to warehouseDB.txt as a new line, overwriting all previous
   * content.
   */
  private static void save() {
    sortName();
    File dbFile = new File("warehouseDB.txt");
    FileWriter fWriter;
    try {
      fWriter = new FileWriter(dbFile);
    } catch (IOException e) {
      e.printStackTrace();
      return;
    }
    PrintWriter pWriter = new PrintWriter(fWriter);
    for (BicyclePart element : partArrayList) {
      pWriter.println(element);
    }
    pWriter.close();
  }

  /**
   * Reads inventory.txt and either adds new BicycleParts or updates existing BicycleParts in
   * partArrayList.
   */
  private static void readInventory() {
    System.out.println("What is the inventory delivery file's name?");
    File invFile = new File(keyboard.nextLine());
    Scanner readInv;
    try {
      readInv = new Scanner(invFile);
    } catch (FileNotFoundException e) {
      System.out.println("Error: File not found.");
      e.printStackTrace();
      return;
    }
    while (readInv.hasNextLine()) {
      String line = readInv.nextLine();
      int index = getIndex(line);
      if (index >= 0) {
        partArrayList.get(index).updateValues(line);
      } else {
        partArrayList.add(new BicyclePart(line));
      }
    }
    readInv.close();
  }

  /**
   * Allows for manual addition or updating of a BicyclePart to partArrayList.
   */
  private static void enterPart() {
    System.out.println("What is the part's name?");
    String newPart = keyboard.nextLine() + ",";
    System.out.println("What is the part's number?");
    newPart += keyboard.nextLine() + ",";
    System.out.println("What is the part's list price?");
    newPart += keyboard.nextLine() + ",";
    System.out.println("What is the part's sale price?");
    newPart += keyboard.nextLine() + ",";
    System.out.println("Is the part on sale?");
    newPart += keyboard.nextLine() + ",";
    System.out.println("What is the quantity of the part?");
    newPart += keyboard.nextLine();
    int index = getIndex(newPart);
    if (index >= 0) {
      partArrayList.get(index).updateValues(newPart);
    } else {
      partArrayList.add(new BicyclePart(newPart));
    }
  }

  /**
   * If the part being sold exists, decrements its quantity by one and removes it if new quantity is
   * zero.
   */
  private static void sellPart() {
    BicyclePart soldPart;
    System.out.println("What is the part's number?");
    String partNumber = "," + keyboard.nextLine();
    int index = getIndex(partNumber);
    if (index >= 0) {
      soldPart = partArrayList.get(index);
    } else {
      System.out.println("Error: Part not found.");
      return;
    }
    if (soldPart.getSaleStatus()) {
      System.out.println("Part is on sale.");
    } else {
      System.out.println("Part is not on sale.");
    }
    System.out.println("Part sold " + new Date());
    int successful = soldPart.decrement();
    if (successful > 0) {
      partArrayList.set(index, soldPart);
    } else {
      partArrayList.remove(index);
    }
  }

  /**
   * Prints out the partName + active price if the part exists.
   */
  private static void displayPart() {
    System.out.println("What is the part's name?");
    String partName = keyboard.nextLine();
    int index = getIndex(partName);
    if (index >= 0) {
      partArrayList.get(index).display();
    } else {
      System.out.println("Error: Part not found.");
    }
  }

  /**
   * Sorts partArrayList by partName.
   */
  private static void sortName() {
    Collections.sort(partArrayList);
  }

  /**
   * Sorts partArrayList by partNumber
   */
  private static void sortNumber() {
    Collections.sort(partArrayList, new NumberComparator());
  }

  /**
   * Gets the index of the part, if it exists.
   *
   * @param partString String of new part.
   * @return int -1 if part doesn't exist, otherwise the index of the part
   */
  private static int getIndex(String partString) {
    BicyclePart otherPart = new BicyclePart(partString);
    for (int i = 0; i < partArrayList.size(); i++) {
      if (partArrayList.get(i).equals(otherPart)) {
        return i;
      }
    }
    return -1;
  }
}