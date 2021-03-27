package providers.database;

public class QueryResultField {
    private final int columnIndex;
    private final String columnName;
    private final String value;

    public QueryResultField(int columnIndex, String columnName, String value) {
        this.columnIndex = columnIndex;
        this.columnName = columnName;
        this.value = value;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getValue() {
        return value;
    }
}
