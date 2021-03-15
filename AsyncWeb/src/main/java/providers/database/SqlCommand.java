package providers.database;

import java.util.Arrays;
import java.util.List;

public class SqlCommand {
    private final String sql;
    private final List<String> parameters;

    public SqlCommand(String sql) {
        this.sql = sql;
        parameters = null;
    }

    public SqlCommand(String sql, List<String> parameters) {
        this.sql = sql;
        this.parameters = parameters;
    }

    public SqlCommand(String sql, String... parameters) {
        this.sql = sql;
        this.parameters = Arrays.asList(parameters.clone());
    }

    public String getSql() {
        return sql;
    }

    public List<String> getParameters() {
        return parameters;
    }
}
