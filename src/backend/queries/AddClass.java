package backend.queries;

import database.SqlConnector;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AddClass {
    /**
     * @param name
     * @param instructor_id
     * @param price
     * @return the id of the class row if insert is successful and -1 otherwise
     */
    public static int addClass(String name, int instructor_id, double price) {
        if (! SqlConnector.runUpdate(String.format("INSERT INTO Classes (name, instructor_id, price) VALUES ('%s', '%d', '%.2f')", name, instructor_id, price))) {
            return -1;
        }

        ResultSet rs = SqlConnector.runQuery(String.format("SELECT id FROM Classes WHERE name = '%s' AND instructor_id = '%d' AND price = '%.2f'", name, instructor_id, price));
        if (rs == null) return -1;
        try {
            if (!rs.next()) return -1;
            return rs.getInt("id");
        } catch (SQLException e) {
            System.out.println("Couldn't retrieve class details: " + e);
            return -1;
        }
    }
}
