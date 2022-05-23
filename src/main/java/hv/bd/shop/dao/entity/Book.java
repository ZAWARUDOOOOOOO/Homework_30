package hv.bd.shop.dao.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@ToString
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "bookname")
    private String bookName;

    @ManyToOne
    @JoinColumn(name = "authorid")/*,referencedColumnName = "id")*/
    private Author authorid;

    @Column(name = "releaseyear")
    private Integer releaseYear;

    @Column(name = "pagesnumber")
    private int pagesNumber;

    @Column
    private double cost;
}
