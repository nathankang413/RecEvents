package database;

import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class EventSignupsView {
    private static final String SQL = "SELECT * FROM EventSignupsView WHERE event_id = ";
    private static final String DetailsSQL = "SELECT %s FROM InstructorEventsView WHERE event_id = %d";

    private String signupId;
    private String eventID;
    private String studentID;
    private String studentName;
    private String studentUsername;

    public EventSignupsView(String signupId, String eventID, String studentID, String studentName, String studentUsername) {
        this.signupId = signupId;
        this.eventID = eventID;
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentUsername = studentUsername;
    }

    public static List<ColumnInfoTriple> getColumnInfo() {
        ArrayList<ColumnInfoTriple> info = new ArrayList<>();
//        info.add(new ColumnInfoTriple("signup_id", "signupId", "Signup ID"));
//        info.add(new ColumnInfoTriple("event_id", "eventID", "Event ID"));
        info.add(new ColumnInfoTriple("student_id", "studentID", "Student ID"));
        info.add(new ColumnInfoTriple("student_name", "studentName", "Student Name"));
        info.add(new ColumnInfoTriple("student_username", "studentUsername", "Student Username"));
        return info;
    }

    public static void fillList(ObservableList<EventSignupsView> list, int eventId) {
        ResultSet rs = SqlConnector.runQuery(SQL + eventId);
        if (rs == null) {
            System.out.println("Error: ResultSet is null.");
            System.out.println("Ran query: " + SQL + eventId);
            return;
        }
        try {
            while (rs.next()) {
                list.add(new EventSignupsView(
                        rs.getString("signup_id"),
                        rs.getString("event_id"),
                        rs.getString("student_id"),
                        rs.getString("student_name"),
                        rs.getString("student_username")
                ));
            }
        } catch (Exception e) {
            System.out.println("Error: Failed to fill list.");
            e.printStackTrace();
        }
    }

    public static String getEventDetail(int event_id, String column) {
        ResultSet rs = SqlConnector.runQuery(String.format(DetailsSQL, column, event_id));
        if (rs == null) {
            System.out.println("Error: ResultSet is null.");
            System.out.println("Ran query: " + String.format(DetailsSQL, column, event_id));
            return null;
        }

        try {
            rs.next();
            return rs.getString(column);
        } catch (Exception e) {
            System.out.println("Error: Failed to get event detail.");
            e.printStackTrace();
            return null;
        }
    }

    public static void removeSignup(int signup_id) {
        SqlConnector.runUpdate("DELETE FROM Signups WHERE id = " + signup_id);
    }

    public static void removeEvent(int event_id) {
        SqlConnector.runUpdate("DELETE FROM Events WHERE id = " + event_id);
    }

    public String getSignupId() {
        return signupId;
    }

    public String getEventID() {
        return eventID;
    }

    public String getStudentID() {
        return studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getStudentUsername() {
        return studentUsername;
    }
}
