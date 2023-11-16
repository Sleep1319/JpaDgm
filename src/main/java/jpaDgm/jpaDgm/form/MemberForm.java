package jpaDgm.jpaDgm.form;

import jpaDgm.jpaDgm.domain.Address;
import jpaDgm.jpaDgm.domain.Role;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm {
    private Long id;
    private String email;
    private String pw;
    private String name;
    private String phone;
    private Role role;
    private Address address;
}
