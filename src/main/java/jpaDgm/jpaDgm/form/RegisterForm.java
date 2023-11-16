package jpaDgm.jpaDgm.form;

import jpaDgm.jpaDgm.domain.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterForm {
    @NotBlank
    private String email;
    @NotBlank
    private String pw;
    @NotBlank
    private String name;
    @NotBlank
    private String phone;
    private Role role;
    @NotNull
    private String addr;
    @NotNull
    private String detail_addr;
    @NotNull
    private String zipcode;

}
