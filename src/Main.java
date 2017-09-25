import java.io.*;
import java.util.*;

/**
 * The Main class manages an inventory of InventoryParts to simulate a warehouse.
 *
 * @author Harrison Crosse
 * @version 1.0
 * @since 2017-09-05
 */

/* TODO
 * Update Docs
 * Test file IO.
 * Test getIndex methods/.equals.
 */

public class Main {

  private static Scanner keyboard = new Scanner(System.in);
  private static Warehouse wh;

  /**
   * Reads the warehouseDB.txt file into an ArrayList, and calls printMenu() and executes the method
   * that the user specifies.
   *
   * @param args CLI Arguments
   */
  public static void main(String[] args) {
    try {
      wh = new Warehouse("warehouseDB.txt");
    } catch (FileNotFoundException e) {
      System.out.println("Error: Database File Not Found.");
      e.printStackTrace();
      System.exit(1);
    }

    boolean needInput = true;
    while (needInput) {
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
    }

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
   * Writes a sorted partArrayList to warehouseDB.txt as a new line, overwriting all previous
   * content.
   */
  private static void save() {
   try {
     wh.save();
   } catch (IOException e) {
     System.out.println("Error: Unable to save file.");
     e.printStackTrace();
     System.exit(1);
   }
  }

  /**
   * Reads inventory.txt and either adds new InventoryParts or updates existing InventoryParts in
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
      String[] strings = readInv.nextLine().split(",");
      int index = wh.getIndex(strings[0]);
      if (index >= 0) {
        wh.getPart(index).updateValues(strings);
      } else {
        wh.addPart(new InventoryPart(strings));
      }
    }
    readInv.close();
  }

  /**
   * Allows for manual addition or updating of a InventoryPart to partArrayList.
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
    String[] strings = newPart.split(",");
    int index = wh.getIndex(strings[0]);
    if (index >= 0) {
      wh.getPart(index).updateValues(strings);
    } else {
      wh.addPart(new InventoryPart(strings));
    }
  }

  /**
   * If the part being sold exists, decrements its quantity by one and removes it if new quantity is
   * zero.
   */
  private static void sellPart() {
    InventoryPart soldPart;
    System.out.println("What is the part's number?");
    int partNumber = Integer.parseInt(keyboard.nextLine());
    int index = wh.getIndex(partNumber);
    if (index >= 0) {
      soldPart = wh.getPart(index);
    } else {
      System.out.println("Error: Part not found.");
      return;
    }
    if (soldPart.getSaleStatus()) {
      System.out.println("Part is on sale.");
    } else {
      System.out.println("Part is not on sale.");
    }
    System.out.println("Part sold " + new Date().toString());
    int successful = soldPart.decrement();
    if (successful < 1) {
      wh.removePart(index);
    }
  }

  /**
   * Prints out the partName + active price if the part exists.
   */
  private static void displayPart() {
    System.out.println("What is the part's name?");
    String partName = keyboard.nextLine();
    int index = wh.getIndex(partName);
    if (index >= 0) {
      System.out.println(wh.getPart(index).display());
    } else {
      System.out.println("Error: Part not found.");
    }

  }

  /**
   * Sorts partArrayList by partName.
   */
  private static void sortName() {
    wh.sortName();
    System.out.print(wh.toString());
  }

  /**
   * Sorts partArrayList by partNumber
   */
  private static void sortNumber() {
    wh.sortNumber();
    System.out.print(wh.toString());
  }

}