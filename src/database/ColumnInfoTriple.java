package database;

public class ColumnInfoTriple {
    public String dbName;
    public String varName;
    public String displayName;
    public ColumnInfoTriple() {}

    public ColumnInfoTriple(String dbName, String varName, String displayName) {
        this.dbName = dbName;
        this.varName = varName;
        this.displayName = displayName;
    }
}
