package jpaDgm.jpaDgm.form;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginForm {
    @NotBlank
    private String email;
    @NotBlank
    private String pw;

}
