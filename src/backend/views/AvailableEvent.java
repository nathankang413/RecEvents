package backend.views;

import backend.ColumnInfoTriple;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class AvailableEvent {
    private String eventId;
    private String className;
    private String price;
    private String startTime;
    private String endTime;
    private String instructor;

    public AvailableEvent(String eventId, String className, String price, String startTime, String endTime, String instructor) {
        this.eventId = eventId;
        this.className = className;
        this.price = price;
        this.startTime = startTime;
        this.endTime = endTime;
        this.instructor = instructor;
    }

    public static List<ColumnInfoTriple> getColumnInfo() {
        ArrayList<ColumnInfoTriple> info = new ArrayList<>();
        info.add(new ColumnInfoTriple("event_id", "eventId", "Event ID"));
        info.add(new ColumnInfoTriple("class_name", "className", "Class Name"));
        info.add(new ColumnInfoTriple("price", "price", "Price"));
        info.add(new ColumnInfoTriple("start_time", "startTime", "Start Time"));
        info.add(new ColumnInfoTriple("end_time", "endTime", "End Time"));
        info.add(new ColumnInfoTriple("instructor_name", "instructor", "Instructor"));
        return info;
    }

    public static void fillList(ResultSet rs, ObservableList<AvailableEvent> list) {
        try {
            while (rs.next()) {
                int eventId = rs.getInt("event_id");
                String className = rs.getString("class_name");
                double price = rs.getDouble("price");
                Timestamp startTime = rs.getTimestamp("start_time");
                Timestamp endTime = rs.getTimestamp("end_time");
                String instructor = rs.getString("instructor_name");

                list.add(new AvailableEvent(String.valueOf(eventId), className, String.format("$%.2f", price), startTime.toString(), endTime.toString(), instructor));
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
