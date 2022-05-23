package hv.bd.shop.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "orderid")
    private Order orderId;

    @OneToMany
    @JoinColumn(name = "bookid")
    private List<Book> bookid;

    @Column(name = "bookscount")
    private int booksCount;

    @Column
    private int cost;
}
