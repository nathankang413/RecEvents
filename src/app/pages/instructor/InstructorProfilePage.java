package app.pages.instructor;

import app.components.TitledPage;
import app.pages.student.StudentHomePage;
import backend.queries.*;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import static app.Constants.WINDOW_WIDTH;
import static javafx.collections.FXCollections.observableArrayList;

public class InstructorProfilePage extends TitledPage {
    public InstructorProfilePage(Stage primaryStage, int id) {
        super(primaryStage, "Instructor Profile");
        GridPane grid = (GridPane) this.getRoot();

        InstructorProfile data = new InstructorProfile(id);

        // Error label
        Label errorLabel = new Label("");
        grid.add(errorLabel, 0, 5);

        // Full name label and field
        Label nameLabel = new Label("Full Name:");
        grid.add(nameLabel, 0, 1);
        TextField nameTextField = new TextField();
        nameTextField.setText(data.getFull_name());
        grid.add(nameTextField, 1, 1);
        // Change name button
        Button nameButton = new Button("Change Name");
        grid.add(nameButton, 2, 1);
        nameButton.setOnAction(e -> {
            if (data.changeName(nameTextField.getText())) {
                errorLabel.setText("Successfully changed name");
            } else {
                errorLabel.setText("Error changing name");
            }
        });

        // Username label and field
        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 2);
        TextField usernameTextField = new TextField();
        usernameTextField.setText(data.getUsername());
        grid.add(usernameTextField, 1, 2);
        // Change username button
        Button usernameButton = new Button("Change Username");
        grid.add(usernameButton, 2, 2);
        usernameButton.setOnAction(e -> {
            if (data.changeUsername(usernameTextField.getText())) {
                errorLabel.setText("Successfully changed username");
            } else {
                errorLabel.setText("Error changing username");
            }
        });

        // Change Password label and field
        Label passwordLabel = new Label("Change Password:");
        grid.add(passwordLabel, 0, 3);
        TextField passwordTextField = new PasswordField();
        grid.add(passwordTextField, 1, 3);
        // Confirm Password label and field
        Label confirmPasswordLabel = new Label("Confirm Password:");
        grid.add(confirmPasswordLabel, 0, 4);
        TextField confirmPasswordTextField = new PasswordField();
        grid.add(confirmPasswordTextField, 1, 4);
        // Change password button
        Button passwordButton = new Button("Change Password");
        grid.add(passwordButton, 2, 4);
        passwordButton.setOnAction(e -> {
            if (data.changePassword(passwordTextField.getText(), confirmPasswordTextField.getText())) {
                errorLabel.setText("Successfully changed password");
            } else {
                errorLabel.setText("Error changing password");
            }
        });


        // Signups table
        TableView<InstructorClasses> table = new TableView<>();
        ObservableList<InstructorClasses> list = observableArrayList();
        table.setItems(list);
        table.setPrefWidth(WINDOW_WIDTH);
        grid.add(table, 0, 5, 3, 1);

        // Create columns and populate table
        for (ColumnInfoTriple info : InstructorClasses.getColumnInfo()) {
            TableColumn<InstructorClasses, String> col = new TableColumn<>(info.displayName);
            col.setCellValueFactory(new PropertyValueFactory<>(info.varName));
            table.getColumns().add(col);
        }
        InstructorClasses.fillList(list, id);

        // Back button
        Button backButton = new Button("Back");
        grid.add(backButton, 0, 6);
        backButton.setOnAction(e -> {
            primaryStage.setScene(new InstructorHomePage(primaryStage, id));
        });

        // Handle clicks
        table.setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                InstructorClasses signup = table.getSelectionModel().getSelectedItem();
                System.out.println("Clicked on " + signup.getEventId() + " " + signup.getClassName());
                InstructorClasses.removeClass(Integer.parseInt(signup.getEventId()));
                primaryStage.setScene(new StudentHomePage(primaryStage, id));
            }
        });
    }

}
