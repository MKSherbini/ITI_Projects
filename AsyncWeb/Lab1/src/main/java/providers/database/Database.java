package providers.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Database {

    /*
        create connection to database
     */
    public abstract Connection getConnection() throws SQLException;

    /*
        returns List of query results
     */
    public List<QueryResultRow> executeQuery(SqlCommand command) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        List<QueryResultRow> resultList = new ArrayList<>();
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(command.getSql());
            if (command.getParameters() != null) {
                for (int i = 0; i < command.getParameters().size(); i++) {
                    var parameter = command.getParameters().get(i);
                    preparedStatement.setString(i + 1, parameter);
                }
            }
            var rs = preparedStatement.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();

            while (rs.next()) {
                QueryResultRow row = new QueryResultRow();
                for (int i = 1; i <= columnsNumber; i++) {
                    row.addQueryField(i, rsmd.getColumnName(i), rs.getString(i));
                }
                resultList.add(row);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException("Illegal Sql query");
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (con != null)
                    con.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return resultList;
    }

    /*
           returns number of rows affected
     */
    public int executeUpdate(SqlCommand command) {
        Connection con = null;
        PreparedStatement preparedStatement = null;
        int rowsAffected = 0;
        try {
            con = getConnection();
            preparedStatement = con.prepareStatement(command.getSql());
            if (command.getParameters() != null) {
                for (int i = 0; i < command.getParameters().size(); i++) {
                    var parameter = command.getParameters().get(i);
                    preparedStatement.setString(i + 1, parameter);
                }
            }
            rowsAffected = preparedStatement.executeUpdate();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            throw new RuntimeException("Illegal Sql query");
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
                if (con != null)
                    con.close();
            } catch (SQLException throwable) {
                throwable.printStackTrace();
            }
        }
        return rowsAffected;
    }

}
