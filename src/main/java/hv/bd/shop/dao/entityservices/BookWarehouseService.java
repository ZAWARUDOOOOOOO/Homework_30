package hv.bd.shop.dao.entityservices;

import hv.bd.shop.dao.entity.BookWarehouse;
import hv.bd.shop.dao.repository.BookWarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookWarehouseService {

    private final BookWarehouseRepository bookWarehouseRepository;

    public BookWarehouseService(BookWarehouseRepository bookWarehouseRepository) {
        this.bookWarehouseRepository = bookWarehouseRepository;
    }

    public BookWarehouse createBookWarehouse(BookWarehouse bookWarehouse) {
        return bookWarehouseRepository.save(bookWarehouse);
    }

    public List<BookWarehouse> getBookWarehouses() {
        return bookWarehouseRepository.findAll();
    }

    public BookWarehouse getBookWarehouseById(Long id) {
        return bookWarehouseRepository.getById(id);
    }
}
