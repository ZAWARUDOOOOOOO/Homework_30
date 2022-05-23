package hv.bd.shop.controller.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookFilter {

    private Long minCostBound;
    private Long maxCostBound;
}
