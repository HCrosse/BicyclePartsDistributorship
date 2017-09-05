/** STATUS
 * Variables     : Unfinished
 * Main          : Done
 * PrintMenu     : Done
 * ReadDB        : Unfinished
 * Save          : Unfinished
 * ReadInventory : Unfinished
 * EnterPart     : Unfinished
 * SellPart      : Unfinished
 * Display       : Unfinished
 * SortName      : Done
 * SortNumber    : Done
 * addBicyclePart: Unfinished
 */

/** TODO
 * Define databaseFile so that it's usable for file IO
 * Read and write to databaseFile: readDB() and save()
 * Read inventoryFile and add content if not contained, or update if contained
 * Figure our .contains in Enter/Read/Display/Sell
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

  private static String databaseFile = "WarehouseDB.txt";
  private static Scanner keyboard = new Scanner(System.in);
  /** Consider switching to set/hashset if can find a way to sort
   *  See if sorting can be done implicitly or through converting to arraylist, etc.
   * */
  private static ArrayList<BicyclePart> partArrayList;

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

  private static void readDB() {
    //for every line in databaseFile, initialize a new BicyclePart and delete said line (or delete at end)

  }

  private static void save() {

  }

  private static void readInventory() {

  }

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
    String partName = newPart.split(",")[0];
    int index = partExists(partName);
    if (index >= 0) {
      partArrayList.get(partArrayList.indexOf(index)).updateValues(newPart);
    }
    else {
      partArrayList.add(new BicyclePart(newPart));
    }
  }

  private static void sellPart() {

  }

  private static void displayPart() {
    System.out.println("What is the part's name?");
    String partName = keyboard.nextLine();
    int index = partExists(partName); //if exists return index, otherwise return -1
    if (index >= 0) {
      partArrayList.get(partArrayList.indexOf(index)).display();
    }
    else {
      System.out.println("Error: Part not found.");
    }
  }

  private static void sortName() {
    Collections.sort(partArrayList);
  }

  private static void sortNumber() {
    Collections.sort(partArrayList, new NumberComparator());
  }

  private static void addBicyclePart(String line) {

  }

}
