package Logistics;

import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class newMain extends Application {

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
    } catch (FileNotFoundException e1) {
      System.out.println("Error: Database File for Main Warehouse Not Found.");
      e1.printStackTrace();
      System.exit(1);
    } catch (IndexOutOfBoundsException e2) {
      e2.printStackTrace();
    }
    launch(args);
  }

  @Override
  public void stop(){
    System.out.println("Stage is closing");
    // Save file
    save();
  }

  private static void save() {
    try {
      fleet.save();
    } catch (IOException e) {
      System.out.println("Error: Unable to save file.");
      e.printStackTrace();
      System.exit(3);
    }
  }

  private static void addVan() {
    //get name from somewhere, idk how controller works
    //boolean vanNew = fleet.addVan(vanName)
    //if (!vanNew) {
    //display error
    //}
  }

  private static void moveParts() {
    //get file name/path/file from somewhere
    //resources/move/FILENAME.txt
    boolean successful = fleet.moveParts(moveFile);
  }
}
