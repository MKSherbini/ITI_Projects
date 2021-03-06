package Servlet.Lab4;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class HtmlConverter {
    public static HtmlConverter instance = new HtmlConverter();

    private HtmlConverter() {
    }

    public String convertToTable(ArrayList<User> users) {
        return "\n" +
                "    <table>\n" +
                "        <tr>\n" +
                "            <th>FirstName</th>\n" +
                "            <th>LastName</th>\n" +
                "            <th>Username</th>\n" +
                "            <th>Password</th>\n" +
                "        </tr>\n" +
                users.stream().map(User::toHtml).collect(Collectors.joining(" ")) +
                "    </table>";
    }

    public String convertToTable(ResultSet rs) throws SQLException {
        String data = "\n" +
                "    <table>\n";

        ResultSetMetaData rsmd = rs.getMetaData();

        int columnsNumber = rsmd.getColumnCount();
        data += "        <tr>\n";
        for (int i = 1; i <= columnsNumber; i++) {
            data += "            <th>" + rsmd.getColumnName(i) + "</th>\n";
        }
        data += "        </tr>\n";

        while (rs.next()) {
            data += "        <tr>\n";
            for (int i = 1; i <= columnsNumber; i++) {
                data += "            <th>" + rs.getString(i) + "</th>\n";
            }
            data += "        </tr>\n";
        }
        data += "    </table>";

        return data;
    }

}
