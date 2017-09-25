import java.io.*;
import java.util.*;

/**
 * The Main class provides a user interface for the Warehouse simulation.
 *
 * @author Harrison Crosse
 * @version 1.5
 */

public class Main {

  private static Scanner keyboard = new Scanner(System.in);
  private static Warehouse wh;

  /**
   * Reads warehouseDB.txt into the Warehouse wh, manages console UI.
   *
   * @param args CLI Arguments.
   */
  public static void main(String[] args) {
    try {
      wh = new Warehouse("resources/warehouseDB.txt");
    } catch (FileNotFoundException e) {
      System.out.println("Error: Database File Not Found.");
      e.printStackTrace();
      System.exit(1);
    } catch (IndexOutOfBoundsException e) {
      //thrown by empty warehouseDB
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
   * @return String of user input.
   */
  private static String printMenu() {
    System.out.println("\nPlease select your option from the following menu:");
    System.out.println("Read: Read an inventory delivery file");
    System.out.println("Enter: Enter a part");
    System.out.println("Sell: Sell a part");
    System.out.println("Display: Display a part");
    System.out.println("SortName: Sort parts by part name");
    System.out.println("SortNumber: Sort parts by part number");
    System.out.println("Quit");
    System.out.println("Enter your choice:");
    return keyboard.nextLine();
  }

  /**
   * Saves wh to warehouse.txt.
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
   * wh.
   */
  private static void readInventory() {
    System.out.println("What is the inventory delivery file's name?");
    File invFile = new File("resources/" + keyboard.nextLine());
    Scanner readInv;
    try {
      readInv = new Scanner(invFile);
    } catch (FileNotFoundException e) {
      System.out.println("Error: File not found.");
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
   * Allows for manual addition or updating of a InventoryPart to wh.
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
   * If the part being sold exists, decrements its quantity by one and removes it from wh if the
   * new quantity is zero.
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
   * Sorts wh by partName.
   */
  private static void sortName() {
    wh.sortName();
    System.out.print(wh.toString());
  }

  /**
   * Sorts wh by partNumber
   */
  private static void sortNumber() {
    wh.sortNumber();
    System.out.print(wh.toString());
  }

}