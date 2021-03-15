package models;

public class User {
    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    public User() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String toHtml() {
        return "        <tr>\n" +
                "            <td>" + firstName + "</td>\n" +
                "            <td>" + lastName + "</td>\n" +
                "            <td>" + userName + "</td>\n" +
                "            <td>" + password + "</td>\n" +
                "        </tr>\n";
    }
}
