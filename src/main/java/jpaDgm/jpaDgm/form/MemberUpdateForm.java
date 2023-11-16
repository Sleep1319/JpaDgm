package jpaDgm.jpaDgm.form;

import jpaDgm.jpaDgm.domain.Role;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class MemberUpdateForm {
    @NotBlank
    private String phone;
    @NotNull
    private String addr;
    @NotNull
    private String detail_addr;
    @NotNull
    private String zipcode;

}
