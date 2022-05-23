package prezentation.layer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class Employee implements Initializable {
    @FXML TextArea textArea = new TextArea();
    @FXML Button logOutButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        textArea.setText(EmployeeObserver.notification);
    }
    @FXML public void clickLogOutButton(){
        Stage stage = (Stage) logOutButton.getScene().getWindow();
        stage.close();
    }


}
