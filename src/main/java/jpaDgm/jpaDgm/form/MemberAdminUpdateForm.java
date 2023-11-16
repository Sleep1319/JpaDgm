package jpaDgm.jpaDgm.form;

import jpaDgm.jpaDgm.domain.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberAdminUpdateForm {
    private Long id;
    @NotBlank
    private String phone;
    @NotNull
    private Role role;
    @NotNull
    private String addr;
    @NotNull
    private String detail_addr;
    @NotNull
    private String zipcode;
}
