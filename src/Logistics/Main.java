package Logistics;

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
  /* ALL FOLLOWING ARE TRIGGERED BY BUTTON PRESS ON RESPECTIVE TABS
     AND TAKE INFO FROM DROP-DOWNS/CHECKS/TEXT-FIELDS

  private static void readInventory() {
    //get inventory file from dropdown - as string
    //get location from dropdown - save as string
    try {
      fleet.readInventory(new File(file), location);
    } catch (FileNotFoundException e) {
      //display error somehow
      e.printStackTrace();
    }
  }

  private static void enterPart() {
    String[] strings;
    //get strings from text fields
    fleet.enterPart(strings);
  }

  private static void sell() {
    //String choice from dropdown
    //int partNumber from text field
    String[] info = fleet.sell(choice, partNumber);
    if (info == null) {
      //display error
      return;
    }
    //display info in info
  }

  private static void display(){
    //get partName from text field
    String str = fleet.display(partName);
    if (str == null) {
      //display error
    }
    //display info in str
  }

  private static void sortName() {
    //get choice from dropdown
    String str = fleet.sortName(choice)
    //display str
  }

  private static void sortNumber() {
    //get choice from dropdown
    String str = fleet.sortNumber(choice)
    //display str
  }

  private static void addVan() {
    //get name from somewhere, idk how controller works
    boolean vanNew = fleet.addVan(vanName)
    if (!vanNew) {
      //display error
    }
  }

  private static void moveParts() {
    //get file name/path/file from somewhere
    //resources/move/FILENAME.txt
    int status = fleet.moveParts(moveFile);
    if (status < 0) {
      switch (status) {
        choice -1:
          //display error
          break;
        choice -2:
          //display error
          break;
        choice -3:
          //display error
          break;
        default:
          //display error
          break;
      }
    }
  }
  */
}
