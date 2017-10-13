package Logistics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  private static Fleet fleet;

  @Override
  public void start(Stage primaryStage) throws Exception{
    Parent root = FXMLLoader.load(getClass().getResource("DistributorshipOverview.fxml"));
    primaryStage.setTitle("Bicycle Part Distributorship");
    primaryStage.setScene(new Scene(root, 960, 540));
    primaryStage.show();
  }


  public static void main(String[] args) {
    try {
      fleet = new Fleet();
    } catch (FileNotFoundException e) {
      System.out.println("Error: Database File for Main Warehouse Not Found.");
      e.printStackTrace();
      System.exit(1);
    } catch (IndexOutOfBoundsException e) {
      e.printStackTrace();
    }
    DistributorshipController.setOWL(fleet.listWarehouses());
    launch(args);
  }

  @Override
  public void stop(){
    save();
  }

  private static void save() {
    try {
      fleet.save();
    } catch (IOException e) {
      System.out.println("Error: Unable to save file.");
      e.printStackTrace();
      System.exit(-1);
    }
  }

  private static String readInventory(String location, String invFile) {
    try {
      fleet.readInventory(location, new File(invFile));
    } catch (FileNotFoundException e) {
      e.printStackTrace();
      return "Error: Inventory file not found.";
    }
    return ("Files successfully added to " + location);
  }

  private static String enterPart(String[] strings) {
    fleet.enterPart(strings);
    return (strings[0] + " successfully added to " + strings[6]);
  }

  private static String sell(String location, int partNumber) {
    String info = fleet.sell(location, partNumber);
    if (info == null) {
      return ("Error: Unable to sell part #" + partNumber + " at " + location);
    }
    return info;
  }

  private static String display(String partName){
    String str = fleet.display(partName);
    if (str == null) {
      return ("Error: " + partName + " not found.");
    }
    return str;
  }

  private static String sortName(String location) {
    return fleet.sortName(location);
  }

  private static String sortNumber(String location) {
    return fleet.sortNumber(location);
  }

  private static String moveParts(String moveFile) {
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

  private static String addVan(String vanName) {
    boolean vanNew = fleet.addVan(vanName);
    if (!vanNew) {
      return ("Error: Van already exists.");
    }
    DistributorshipController.setOWL(fleet.listWarehouses());
    return (vanName + " successfully added.");
  }

}
