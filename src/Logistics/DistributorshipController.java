package Logistics;

import java.io.File;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class DistributorshipController {

  private static ObservableList<String> whNames;
  private static ObservableList<String> invFiles;
  private static ObservableList<String> moveFiles;

  @FXML
  public void initialize() {
    setInvFiles();
    setMoveFiles();
    invLocation.setItems(whNames);
    invLocation.getSelectionModel().selectFirst();
    invFile.setItems(invFiles);
    invFile.getSelectionModel().selectFirst();
    enterLocation.setItems(whNames);
    enterLocation.getSelectionModel().selectFirst();
    sellLocation.setItems(whNames);
    sellLocation.getSelectionModel().selectFirst();
    sortNameLocation.setItems(whNames);
    sortNameLocation.getSelectionModel().selectFirst();
    sortNumLocation.setItems(whNames);
    sortNumLocation.getSelectionModel().selectFirst();
    moveFile.setItems(moveFiles);
    moveFile.getSelectionModel().selectFirst();
  }

  static void setOWL(ArrayList<String> owl) {
    whNames = FXCollections.observableArrayList(owl);
  }

  private void updateOWL() {
    invLocation.setItems(whNames);
    enterLocation.setItems(whNames);
    sellLocation.setItems(whNames);
    sortNameLocation.setItems(whNames);
    sortNumLocation.setItems(whNames);
  }

  private void setInvFiles() {
    invFiles = FXCollections.observableArrayList();
    File[] files = new File("resources/inventory").listFiles();
    if (files == null || files.length < 1) {
      invOkay.setDisable(true);
      invFiles.add("No Files Found");
      invFile.setDisable(true);
      return;
    }
    for (File f : files) {
      String str = f.getName();
      invFiles.add(str.substring(0, (str.length() - 4)));
    }
  }

  private void setMoveFiles() {
    moveFiles = FXCollections.observableArrayList();
    File[] files = new File("resources/move").listFiles();
    if (files == null || files.length < 1) {
      moveOkay.setDisable(true);
      moveFiles.add("No Files Found");
      moveFile.setDisable(true);
      return;
    }
    for (File f : files) {
      String str = f.getName();
      moveFiles.add(str.substring(0, (str.length() - 4)));
    }
  }

  @FXML
  private void readInventory() {
    invTA.setText(Main.readInventory(invLocation.getValue(), invFile.getValue()));
  }

  @FXML
  private void enterPart() {
    String[] strings = new String[7];
    strings[0] = enterName.getText();
    strings[1] = enterNum.getText();
    strings[2] = enterLP.getText();
    strings[3] = enterSP.getText();
    strings[4] = String.valueOf(enterOnSale.isSelected());
    strings[5] = enterQuantity.getText();
    strings[6] = enterLocation.getValue();
    enterTA.setText(Main.enterPart(strings));
  }

  @FXML
  private void sell() {
    sellTA.setText(Main.sell(sellLocation.getValue(), sellNumber.getText()));
  }

  @FXML
  private void display() {
    displayTA.setText(Main.display(displayName.getText()));
  }

  @FXML
  private void sortName() {
    sortNameTA.setText(Main.sortName(sortNameLocation.getValue()));
  }

  @FXML
  private void sortNumber() {
    sortNumTA.setText(Main.sortNumber(sortNumLocation.getValue()));
  }

  @FXML
  private void moveParts() {
    moveTA.setText(Main.moveParts(moveFile.getValue()));
  }

  @FXML
  private void addVan() {
    addTA.setText(Main.addVan(addVanName.getText()));
    updateOWL();
  }

  @FXML
  public void exitApplication(ActionEvent event) {
    Platform.exit();
  }

  @FXML
  private void sortNameRadioChecked() {
    sortNameLocation.setDisable(true);
  }

  @FXML
  private void sortNumRadioChecked() {
    sortNumLocation.setDisable(true);
  }

  //Read Inventory
  @FXML private ComboBox<String> invLocation = new ComboBox<String>();
  @FXML private ComboBox<String> invFile = new ComboBox<String>();
  @FXML private Button invOkay = new Button();
  @FXML private TextArea invTA = new TextArea();

  //Enter Part
  @FXML private ComboBox<String> enterLocation = new ComboBox<String>();
  @FXML private TextField enterName = new TextField();
  @FXML private TextField enterNum = new TextField();
  @FXML private TextField enterLP = new TextField();
  @FXML private TextField enterSP = new TextField();
  @FXML private TextField enterQuantity = new TextField();
  @FXML private RadioButton enterOnSale = new RadioButton();
  @FXML private Button enterOkay = new Button();
  @FXML private TextArea enterTA = new TextArea();


  //Sell
  @FXML private ComboBox<String> sellLocation = new ComboBox<String>();
  @FXML private TextField sellNumber = new TextField();
  @FXML private Button sellOkay = new Button();
  @FXML private TextArea sellTA = new TextArea();

  //Display
  @FXML private TextField displayName = new TextField();
  @FXML private Button displayOkay = new Button();
  @FXML private TextArea displayTA = new TextArea();

  //Sort Name
  @FXML private RadioButton sortNameAll = new RadioButton();
  @FXML private ComboBox<String> sortNameLocation = new ComboBox<String>();
  @FXML private TextArea sortNameTA = new TextArea();

  //Sort Number
  @FXML private RadioButton sortNumAll = new RadioButton();
  @FXML private ComboBox<String> sortNumLocation = new ComboBox<String>();
  @FXML private TextArea sortNumTA = new TextArea();

  //Move Parts
  @FXML private ComboBox<String> moveFile = new ComboBox<String>();
  @FXML private Button moveOkay = new Button();
  @FXML private TextArea moveTA = new TextArea();

  //Add Van
  @FXML private TextField addVanName = new TextField();
  @FXML private Button addOkay = new Button();
  @FXML private TextArea addTA = new TextArea();

}
