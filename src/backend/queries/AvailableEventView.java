package backend.queries;

import backend.SqlConnector;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AvailableEventView {
    public static final String SQL = "SELECT * FROM AvailableEvents";

    private String eventId;
    private String className;
    private String room;
    private String price;
    private String startTime;
    private String endTime;
    private String instructor;

    public AvailableEventView(String eventId, String className, String room, String price, String startTime, String endTime, String instructor) {
        this.eventId = eventId;
        this.className = className;
        this.room = room;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructor = instructor;
    }

    /**
     * Get columns info to create a display based on this view
     * @return the dbName, varName, and display name of each column
     */
    public static List<ColumnInfoTriple> getColumnInfo() {
        ArrayList<ColumnInfoTriple> info = new ArrayList<>();
//        info.add(new ColumnInfoTriple("event_id", "eventId", "Event ID"));
        info.add(new ColumnInfoTriple("class_name", "className", "Class Name"));
        info.add(new ColumnInfoTriple("room", "room", "Room"));
        info.add(new ColumnInfoTriple("price", "price", "Price"));
        info.add(new ColumnInfoTriple("start_time", "startTime", "Start Time"));
        info.add(new ColumnInfoTriple("end_time", "endTime", "End Time"));
        info.add(new ColumnInfoTriple("instructor_name", "instructor", "Instructor"));
        return info;
    }

    public static void fillList(ObservableList<AvailableEventView> list) {
        ResultSet rs = SqlConnector.runQuery(SQL);
        try {
            while (rs.next()) {
                list.add(new AvailableEventView(
                        rs.getString("event_id"),
                        rs.getString("class_name"),
                        rs.getString("room"),
                        String.format("$%.2f", rs.getDouble("price")),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("instructor_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }

    }

    public String getEventId() {
        return eventId;
    }

    public String getClassName() {
        return className;
    }

    public String getRoom() {
        return room;
    }

    public String getPrice() {
        return price;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getInstructor() {
        return instructor;
    }

}
