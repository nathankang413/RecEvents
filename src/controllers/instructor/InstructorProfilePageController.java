package controllers.instructor;
import app.ScreenController;
import backend.queries.InstructorProfile;
import controllers.UserPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InstructorProfilePageController implements UserPageController {
    @FXML
    private Button backButton, nameButton, usernameButton, passwordButton;
    @FXML
    InstructorProfile data;
    @FXML
    TextField nameTextField, usernameTextField;
    @FXML
    PasswordField passwordTextField, confirmPasswordTextField;
    @FXML
    Label errorLabel;
    int instructor_id;

    @FXML
    protected void back(ActionEvent e) {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        ScreenController.activate("Instructor Home Page");
    }

    @FXML
    protected void changeName(ActionEvent e){
        if (data.changeName(nameTextField.getText())) {
            errorLabel.setText("Successfully changed name");
        } else {
            errorLabel.setText("Error changing name");
        }
    }

    @FXML
    protected void changeUsername(ActionEvent e){
        if (data.changeUsername(usernameTextField.getText())) {
            errorLabel.setText("Successfully changed username");
        } else {
            errorLabel.setText("Error changing username");
        }
    }

    @FXML
    protected void changePassword(ActionEvent e){
        if (data.changePassword(passwordTextField.getText(), confirmPasswordTextField.getText())) {
            errorLabel.setText("Successfully changed password");
        } else {
            errorLabel.setText("Error changing password");
        }
    }

    @FXML
//    public void initialize() {
//        this.data = new InstructorProfile(Instructor_id);
//        System.out.println("FULL NAME: " + this.data.getFull_name());
//        nameTextField.setText(data.getFull_name());
//        usernameTextField.setText(data.getUsername());
//    }
    public void setUID(int Instructor_id) {
        this.instructor_id = Instructor_id;
        this.data = new InstructorProfile(Instructor_id);
        nameTextField.setText(data.getFull_name());
        usernameTextField.setText(data.getUsername());
    }
}

