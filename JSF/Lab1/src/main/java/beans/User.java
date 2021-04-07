package beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named
@SessionScoped
public class User implements Serializable {

    private String email;
    private String password;
    private String message;

    public void createMessage() {
        if (email.length() > 0 && password.length() > 0)
            message = "Hello, " + email + "!";
        else
            message = "Enter correct data";
    }

    public String getMessage() {
        return message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}