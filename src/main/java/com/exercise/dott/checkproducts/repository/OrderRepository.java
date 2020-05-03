package com.exercise.dott.checkproducts.repository;

import com.exercise.dott.checkproducts.models.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends PagingAndSortingRepository<Order, Long>, CustomOrderIntervalRepository {

    String findOrders = "SELECT p.ranges, COUNT(*) as count\n" +
            "FROM (\n" +
            "  SELECT \n" +
            "    CASE\n" +
            "      WHEN MONTH(creation) BETWEEN 1 AND 3 AND TIMESTAMPDIFF(YEAR,creation,CURRENT_DATE()) = 0 THEN '1-3 months'\n" +
            "      WHEN MONTH(creation) BETWEEN 4 AND 6 AND TIMESTAMPDIFF(YEAR,creation,CURRENT_DATE()) = 0 THEN '4-6 months'\n" +
            "      WHEN MONTH(creation) BETWEEN 7 AND 12 AND TIMESTAMPDIFF(YEAR,creation,CURRENT_DATE()) = 0 THEN '7-12 months'\n" +
            "      ELSE '>12 months'\n" +
            "    END AS ranges,\n" +
            "    id\n" +
            "  FROM product) p \n" +
            "INNER JOIN item on p.id = item.product_id\n" +
            "INNER JOIN order_table_items orderItem on orderItem.items_id = item.id \n" +
            "INNER JOIN order_table orderTable on orderTable.id = orderItem.order_table_id  \n" +
            "WHERE orderTable.order_date BETWEEN :initialDate AND :finalDate \n" +
            "GROUP BY p.ranges";

    @Query(value = findOrders, nativeQuery = true)
    List<Object[]> findOrdersPlacedInTheRange(@Param("initialDate") String initialDate, @Param("finalDate") String finalDate);


}
