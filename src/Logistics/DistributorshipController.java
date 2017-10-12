package Logistics;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class DistributorshipController {

  @FXML
  public void exitApplication(ActionEvent event) {
    Platform.exit();
  }

}
