package Logistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * The Main class launches the JavaFX application and acts as an intermediary between the Controller
 * and the model.
 *
 * @author Harrison Crosse
 * @version 2.0
 */

public class Main extends Application {

  private static Fleet fleet;

  /**
   * Launches the JavaFX application
   *
   * @param primaryStage Stage to be displayed by the application.
   * @throws Exception unknown.
   */
  @Override
  public void start(Stage primaryStage) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("DistributorshipOverview.fxml"));
    primaryStage.setTitle("Bicycle Part Distributorship");
    primaryStage.setScene(new Scene(root, 960, 540));
    primaryStage.show();
  }

  /**
   * Initializes the fleet and calls on start().
   *
   * @param args CLI commands, unsupported.
   */
  public static void main(String[] args) {
    try {
      fleet = new Fleet();
    } catch (FileNotFoundException e) {
      System.out.println("Error: Database File for Main Warehouse Not Found.");
      e.printStackTrace();
      System.exit(1);
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Error: Unable to save Main Warehouse.");
    }
    DistributorshipController.setOWL(fleet.listWarehouses());
    launch(args);
  }

  /**
   * Saves the fleet on close.
   */
  @Override
  public void stop(){
    save();
  }

  /**
   * Saves the fleet into text files.
   */
  private static void save() {
    try {
      fleet.save();
    } catch (IOException e) {
      System.out.println("Error: Unable to save file.");
      e.printStackTrace();
      System.exit(-1);
    }
  }


  //Doesn't handle duplicates
  /**
   * Reads parts into a Warehouse specified by location from an inventory file.
   *
   * @param location String name of the Warehouse parts are being read into.
   * @param invFile String name of the inventory file.
   * @return String success or failure message.
   */
  static String readInventory(String location, String invFile) {
    try {
      fleet.readInventory(location, new File("resources/inventory/" + invFile + ".txt"));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return "Error: Inventory file not found.";
    }
    return ("Files successfully added to " + location);
  }

  /**
   * Allows a part to be manually entered into a Warehouse.
   *
   * @param strings String[] of Part information.
   * @return String success message.
   */
  static String enterPart(String[] strings) {
    fleet.enterPart(strings);
    return (strings[0] + " successfully added to " + strings[6]);
  }

  /**
   * Allows a part to be sold from a location.
   *
   * @param location String name of Warehouse part is being sold from.
   * @param partNumber int partNumber of the part being sold.
   * @return String success or error message.
   */
 static String sell(String location, String partNumber) {
    String info = fleet.sell(location, Integer.parseInt(partNumber));
    if (info == null) {
      return ("Error: Unable to sell part #" + partNumber + " at " + location);
    }
    return info;
  }

  /**
   * Displays information about a specific part.
   *
   * @param partName String name of the part to be displayed.
   * @return String success or error message.
   */
  static String display(String partName){
    String str = fleet.display(partName);
    if (str == null) {
      return ("Error: Part " + partName + " not found.");
    }
    return str;
  }

  //Radio All perm disables other locations
  //When all is selected only prints stuff from Main
  /**
   * Sorts Parts in a Warehouse by name and returns a list of them.
   *
   * @param location String name of Warehouse to be sorted.
   * @return String list of sorted parts.
   */
  static String sortName(String location) {
    return fleet.sortName(location);
  }

  //Radio All perm disables other locations
  //When all is selected only prints stuff from Main
  /**
   * Sorts Parts in a Warehouse by number and returns a list of them.
   *
   * @param location String name of Warehouse to be sorted.
   * @return String list of sorted parts.
   */
  static String sortNumber(String location) {
    return fleet.sortNumber(location);
  }

  /**
   * Moves parts between Warehouses as specified by a move file.
   *
   * @param moveFile String name of the move file.
   * @return String success or failure message.
   */
  static String moveParts(String moveFile) {
    File mov = new File("resources/move/" + moveFile + ".txt");
    int status = 0;
    try {
      status = fleet.moveParts(mov);
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return ("Error: File " + moveFile + " not found.");
    }
    if (status < 0) {
      switch (status) {
        case -1:
          return ("Error: Warehouse not found.");
        case  -2:
          return ("Error: Part not found.");
        case  -3:
          return ("Error: Trying to move too many parts.");
      }
    }
    return ("Parts successfully moved.");
  }

  /**
   * Adds a van to the fleet.
   *
   * @param vanName String name of the van being added.
   * @return String success or failure message.
   */
  static String addVan(String vanName) {
    boolean vanNew = fleet.addVan(vanName);
    if (!vanNew) {
      return ("Error: Van already exists.");
    }
    DistributorshipController.setOWL(fleet.listWarehouses());
    try {
      fleet.save();
    } catch (IOException e) {
      return ("Error: Something went wrong when saving this van.");
    }
    return (vanName + " successfully added.");
  }

}
