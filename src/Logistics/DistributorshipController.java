package Logistics;

import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class DistributorshipController {

  @FXML
  public void exitApplication(ActionEvent event) {
    Platform.exit();
  }

//  @Override
  public void start(Stage primaryStage) {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("Logistics/DistributorshipOverview.fxml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
}
