package backend.queries;

import backend.SqlConnector;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddEvent {
    /**
     *
     * @param class_id
     * @param room
     * @param start_time
     * @param end_time
     * @param seats
     * @return the id of the event row if event add is successful and -1 otherwise
     */
    public static int addEvent(int class_id, String room, String start_time, String end_time, int seats) {
        if (! SqlConnector.runUpdate(String.format("INSERT INTO Events (class_id, room, start_time, end_time, seats) VALUES ('%d', '%s', '%s', '%s', '%d')", class_id, room, start_time, end_time, seats))) {
            return -1;
        }

        ResultSet rs = SqlConnector.runQuery(String.format("SELECT id FROM Events WHERE class_id = '%s' AND room = '%s' AND start_time = '%s', AND end_time = '%s' AND seats = '%d'", class_id, room, start_time, end_time, seats));
        if (rs == null) return -1;
        try {
            if (!rs.next()) return -1;
            return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Couldn't retrieve event details: " + e);
            return -1;
        }
    }
}