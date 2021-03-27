package providers.database;

import java.util.ArrayList;
import java.util.List;

public class QueryResultRow {
    private final List<QueryResultField> queryResultFields;

    public QueryResultRow() {
        queryResultFields = new ArrayList<>();
    }

    public void addQueryField(int index, String name, String value) {
        queryResultFields.add(new QueryResultField(index, name, value));
    }

    public String getString(int index) {
        return queryResultFields.stream().filter(queryResultField -> queryResultField.getColumnIndex() == index)
                .findAny().map(QueryResultField::getValue).orElse(null);
    }

    public String getString(String name) {
        return queryResultFields.stream().filter(queryResultField -> queryResultField.getColumnName().equals(name))
                .findAny().map(QueryResultField::getValue).orElse(null);
    }
}
