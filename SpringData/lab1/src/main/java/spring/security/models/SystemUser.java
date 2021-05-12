package spring.security.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SystemUser {
    private String username;
    private String password;
    private int enabled;
}
