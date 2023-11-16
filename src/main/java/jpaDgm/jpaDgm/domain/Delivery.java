package jpaDgm.jpaDgm.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "delivery")
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_status")
    private DeliveryStatus status;

    @Embedded
    @Column(name = "delivery_address")
    private Address address;//주서 정보에 대한 값 뭐로하지 //일단 Address 클래스 선언 //상의 필요

    @OneToOne(mappedBy = "delivery", fetch = LAZY)
    private Orders orders;

}
