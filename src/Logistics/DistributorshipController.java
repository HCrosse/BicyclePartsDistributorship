package Logistics;

public class DistributorshipController {

  @FXML
  public void exitApplication(ActionEvent event) {
    Platform.exit();
  }

  @Override
  public void start(Stage primaryStage) {
    try {
      Parent root = FXMLLoader.load(getClass().getResource("Logistics/DistributorshipOverview.fxml"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
