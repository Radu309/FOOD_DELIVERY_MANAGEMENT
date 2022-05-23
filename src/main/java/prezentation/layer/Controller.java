package prezentation.layer;

import business.layer.BaseProduct;
import business.layer.CompositeProduct;
import business.layer.DeliveryService;
import business.layer.MenuItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    //log in window
    @FXML private Text accountMessageText;
    @FXML private TextField usernameTextField;
    @FXML private TextField passwordTextField;
    // register window
    @FXML private Button createAccountButton;
    @FXML private TextField usernameTextFieldRegister;
    @FXML private TextField passwordTextFieldRegister;
    @FXML ChoiceBox<String> logInAsChoiceBox = new ChoiceBox<>();

    // variable
    private static String username;
    private static String password;
    private static String logInAs;

    //INITIALIZE

    public void initialize(URL url, ResourceBundle rb){
        logInAsChoiceBox.setItems(FXCollections.observableArrayList("Administrator","Client","Employee"));
    }
    /// ADMINISTRATOR
    @FXML public void clickOnLogInAsAdministratorButton() throws IOException {
        if(Objects.equals(usernameTextField.getText(), username) &&
                Objects.equals(passwordTextField.getText(), password) &&
                Objects.equals("Administrator", logInAs)){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("AdministratorWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 900, 700);
            Stage stage = new Stage();
            stage.setTitle("Welcome!");
            stage.setScene(scene);
            stage.show();
        }
        else
            accountMessageText.setText("Incorrect Account !!!");
    }

    ////////////////////////////////////////////// CLIENT WINDOWS

    @FXML public void clickOnLogInAsClientButton() throws IOException {
        if(Objects.equals(usernameTextField.getText(), username) &&
                Objects.equals(passwordTextField.getText(), password) &&
                Objects.equals("Client", logInAs)){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ClientWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1400, 750);
            Stage stage = new Stage();
            stage.setTitle("Welcome!");
            stage.setScene(scene);
            stage.show();
        }
        else
            accountMessageText.setText("Incorrect Account !!!");
    }
    ////////////////////////////////////////////// EMPLOYEE WINDOW
    @FXML public void clickOnLogInAsEmployeeButton() throws IOException {
        if(Objects.equals(usernameTextField.getText(), username) &&
                Objects.equals(passwordTextField.getText(), password) &&
                Objects.equals("Employee", logInAs)){
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("EmployeeWindow.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Welcome!");
            stage.setScene(scene);
            stage.show();
        }
        else
            accountMessageText.setText("Incorrect Account !!!");
    }
    ////////////////////////////////////////////// REGISTER WINDOW
    @FXML public void clickOnRegisterWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("RegisterWindow.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 300);
        Stage stage = new Stage();
        stage.setTitle("Welcome!");
        stage.setScene(scene);
        stage.show();
    }
    @FXML public void clickOnRegisterCreateButton(){
        username = usernameTextFieldRegister.getText();
        password = passwordTextFieldRegister.getText();
        logInAs = logInAsChoiceBox.getValue();
        Stage stage = (Stage) createAccountButton.getScene().getWindow();
        stage.close();
    }
}