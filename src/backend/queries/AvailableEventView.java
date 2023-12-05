package backend.queries;

import database.SqlConnector;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
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

    /**
     * Potential issues: event is full, student is already booked at that time
     * @param student_id
     * @param event_id
     * @return the error message or null if no error
     */
    public static String addSignup(int student_id, int event_id) {
        // Check if event is full
        String getSignups = "SELECT * FROM EventSignups WHERE event_id = " + event_id;
        ResultSet rs = SqlConnector.runQuery(getSignups);
        try {
            if (rs.next()) {
                int seats = rs.getInt("seats");
                int signups = rs.getInt("signups");
                if (signups >= seats) {
                    return "Event is full";
                }
            } else {
                return "Could not find event";
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return "Error selecting event.";
        }

        // Check if student is booked already
        String getConflicts = String.format("""
                SELECT COUNT(*) AS conflicts FROM StudentSignups
                WHERE student_id = %d
                AND NOT (
                    end_time < (SELECT end_time FROM Events WHERE id = %d)
                    OR start_time > (SELECT start_time FROM Events WHERE id = %d)
                )
                """, student_id, event_id, event_id);
        rs = SqlConnector.runQuery(getConflicts);
        try {
            if (rs.next()) {
                int conflicts = rs.getInt("conflicts");
                if (conflicts > 0) {
                    return "Student is already booked at that time";
                }
            } else {
                return "Could not check for conflicts";
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
            return "Error while checking for conflicts";
        }

        String insertSignup = "INSERT INTO Signups (student_id, event_id) VALUES (" + student_id + ", " + event_id + ")";
        SqlConnector.runUpdate(insertSignup);
        return null;
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
