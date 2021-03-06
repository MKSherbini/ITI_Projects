package Servlet.Lab4;

public class User {
    public String firstName;
    public String lastName;
    public String username;
    public String password;

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String toHtml() {
        return "        <tr>\n" +
                "            <td>" + firstName + "</td>\n" +
                "            <td>" + lastName + "</td>\n" +
                "            <td>" + username + "</td>\n" +
                "            <td>" + password + "</td>\n" +
                "        </tr>\n";
    }
}
