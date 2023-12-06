package database;

import backend.queries.ColumnInfoTriple;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InstructorEventsView {
    public static final String SQL = "SELECT * FROM InstructorEventsView WHERE instructor_id = ";



    private String instructorId;
    private String eventId;
    private String className;
    private String room;
    private String startTime;
    private String endTime;
    private String signups;
    private String seats;

    public InstructorEventsView(String instructorId, String eventId, String className, String room, String startTime, String endTime, String enrollment, String seats) {
        this.instructorId = instructorId;
        this.eventId = eventId;
        this.className = className;
        this.room = room;
        this.startTime = startTime;
        this.endTime = endTime;
        this.signups = enrollment;
        this.seats = seats;
    }

    /**
     * Get columns info to create a display based on this view
     * @return the dbName, varName, and display name of each column
     */
    public static List<ColumnInfoTriple> getColumnInfo() {
        ArrayList<ColumnInfoTriple> info = new ArrayList<>();
        info.add(new ColumnInfoTriple("instructor_id", "instructorId", "Instructor ID"));
        info.add(new ColumnInfoTriple("event_id", "eventId", "Event ID"));
        info.add(new ColumnInfoTriple("class_name", "className", "Class Name"));
        info.add(new ColumnInfoTriple("room", "room", "Room"));
        info.add(new ColumnInfoTriple("start_time", "startTime", "Start Time"));
        info.add(new ColumnInfoTriple("end_time", "endTime", "End Time"));
        info.add(new ColumnInfoTriple("signups", "signups", "Signups"));
        info.add(new ColumnInfoTriple("seats", "seats", "Seats"));
        return info;
    }

    public static void fillList(ObservableList<InstructorEventsView> list, int instructor_id) {
        ResultSet rs = SqlConnector.runQuery(SQL + instructor_id);
        if (rs == null) {
            System.out.println("Error: ResultSet is null.");
            System.out.println("Ran query: " + SQL + instructor_id);
            return;
        }
        try {
            while (rs.next()) {
                list.add(new InstructorEventsView(
                        rs.getString("instructor_id"),
                        rs.getString("event_id"),
                        rs.getString("class_name"),
                        rs.getString("room"),
                        rs.getString("start_time"),
                        rs.getString("end_time"),
                        rs.getString("signups"),
                        rs.getString("seats")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e);
        }
    }

    public static void removeClass(int event_id) {
        SqlConnector.runUpdate("DELETE FROM Events WHERE id = " + event_id);
        //TODO: CASCADE ON DELETE
    }

    public String getInstructorId() {
        return instructorId;
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

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getSignups() {
        return signups;
    }

    public String getSeats() {
        return seats;
    }
}
