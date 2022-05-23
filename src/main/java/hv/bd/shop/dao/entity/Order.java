package hv.bd.shop.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "\"order\"")
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "clientid")
    private Client clientid;

    @OneToOne
    @JoinColumn(name = "cost", referencedColumnName = "cost")
    private OrderDetails cost;
}
