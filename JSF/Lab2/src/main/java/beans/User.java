package beans;

import jakarta.annotation.ManagedBean;
import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Data;

import java.io.Serializable;

@Named
@SessionScoped
@Data
public class User implements Serializable {

    private boolean hasVoted;
    private String email;
    private String password;
    private String message;
    @Inject
    private VoteSystem voteSystem;
    private String voteCategory;

    public boolean isHasVoted() {
        return hasVoted;
    }

    public void vote() {
        voteSystem.increment(voteCategory);
        hasVoted = true;
        System.out.println("voteCategory = " + voteCategory);
        System.out.println("voteSystem = " + voteSystem);
    }


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