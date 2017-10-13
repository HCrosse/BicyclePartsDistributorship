package Logistics;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DistributorshipController {

  private static ObservableList<String> whNames;
  private static ObservableList<String> moveFiles;
  private static ObservableList<String> invFiles;

  static void setOWL(ObservableList<String> owl) {
    whNames = owl;
  }

  @FXML
  public void exitApplication(ActionEvent event) {
    Platform.exit();
  }

}
