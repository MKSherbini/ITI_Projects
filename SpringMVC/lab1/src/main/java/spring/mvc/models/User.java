package spring.mvc.models;

import lombok.Data;
import org.springframework.lang.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class User {

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 2)
    private String username;
    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 2)
    private String password;
}
