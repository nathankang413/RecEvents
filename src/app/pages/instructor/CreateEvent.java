package app.pages.instructor;

import deprecated.components.TitledPage;
import backend.queries.AddEvent;
import backend.queries.ClassInformation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.time.LocalDateTime;

public class CreateEvent extends TitledPage {
    public CreateEvent(Stage primaryStage, int instructorid) {
        super(primaryStage, "Create New Event");
        GridPane grid = (GridPane) this.getRoot();

        // Error label
        Label errorLabel = new Label("");
        grid.add(errorLabel, 0, 6);
        //newclass button
        Button NewClassButton = new Button("New Class");
        grid.add(NewClassButton, 0, 1);
        NewClassButton.setOnAction(e -> {
            primaryStage.setScene(new CreateClass(primaryStage, instructorid));
        });
        // Full name label and field
        Label nameLabel = new Label("Class Name:");
        grid.add(nameLabel, 0, 2);

        // class dropdown
        ObservableList<ClassInformation> options =
                FXCollections.observableArrayList();
        ClassInformation.fillList(options, instructorid);
        System.out.println(options);
        ComboBox classes = new ComboBox(options);
        grid.add(classes, 1, 2);

        // Username label and field
        Label roomLabel = new Label("Room:");
        grid.add(roomLabel, 0, 3);
        TextField roomField = new TextField();
        grid.add(roomField, 1, 3);

        // Start time end time
        Label startLabel = new Label("Start Date:");
        grid.add(startLabel, 0, 4);
        DatePicker startDate = new DatePicker();
        grid.add(startDate, 1, 4);

        Label startTimeLabel = new Label("Start Time:");
        grid.add(startTimeLabel, 3, 4);
        TextField startTime = new TextField();
        grid.add(startTime, 4, 4);

        // end time
        Label endLabel = new Label("End Date:");
        grid.add(endLabel, 0, 5);
        DatePicker endDate = new DatePicker();
        grid.add(endDate, 1, 5);

        Label endTimeLabel = new Label("End Time:");
        grid.add(endTimeLabel, 3, 5);
        TextField endTime = new TextField();
        grid.add(endTime, 4, 5);

        Label seatsLabel = new Label("Seats:");
        grid.add(seatsLabel, 0, 6);
        TextField seatsField = new TextField();
        grid.add(seatsField, 1, 6);



        Button AddEventButton = new Button("Add Event");
        grid.add(AddEventButton, 0, 7);
        AddEventButton.setOnAction(e -> {
            AddEvent.addEvent(
                    options.get(options.indexOf(classes.getValue())).get_id(),
                    roomField.getText(),
                    LocalDateTime.parse(startDate.getValue().toString()+"T"+startTime.getText()).toString(),
                    LocalDateTime.parse(endDate.getValue().toString()+"T"+endTime.getText()).toString(),
                    Integer.parseInt(seatsField.getText()));

        });

    }
}
