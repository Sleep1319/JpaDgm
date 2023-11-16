package jpaDgm.jpaDgm.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;


//상의 필요
@Embeddable
@Getter
public class Address {
    @Column(name = "addr")
    private String addr;
    @Column(name = "detail_addr")
    private String detail_addr;
    @Column(name = "zipcode")
    private String zipcode;

    protected Address() {

    }

    public Address(String addr,String detail_addr,String zipcode) {
        this.addr = addr;
        this.detail_addr = detail_addr;
        this.zipcode = zipcode;
    }
}

