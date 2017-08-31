import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

  private static String databaseFile = "WarehouseDB.txt";
  private static Scanner keyboard = new Scanner(System.in);
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
          //Save all open BicycleParts to databaseFile
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

  }

  private static void sellPart() {

  }

  private static void displayPart() {

  }

  private static void sortName() {

  }

  private static void sortNumber() {

  }

  private static void addBicyclePart(String line) {

  }

}
