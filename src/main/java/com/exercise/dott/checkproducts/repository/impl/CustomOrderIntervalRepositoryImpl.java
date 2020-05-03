package com.exercise.dott.checkproducts.repository.impl;

import com.exercise.dott.checkproducts.dto.CustomInterval;
import com.exercise.dott.checkproducts.models.OrderPlacedInterval;
import com.exercise.dott.checkproducts.repository.CustomOrderIntervalRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CustomOrderIntervalRepositoryImpl implements CustomOrderIntervalRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public List<OrderPlacedInterval> findByCustomInterval(List<CustomInterval> customIntervals, String initialDate, String finalDate) {
        Query q = em.createNativeQuery(getSQL(customIntervals, initialDate, finalDate));
        List<Object[]> orders = q.getResultList();
        return OrderPlacedInterval.converter(orders);
    }

    /**
     * Creates a native query
     * @param customIntervals
     * @param initialDate
     * @param finalDate
     * @return
     */
    private String getSQL(List<CustomInterval> customIntervals, String initialDate, String finalDate) {

        try {
            StringBuilder str = new StringBuilder();
            str.append(
                "SELECT p.ranges, COUNT(*) as count\n" +
                "FROM (\n" +
                "  SELECT \n" +
                "    CASE\n"
            );

            StringBuilder cases = new StringBuilder();
            for (CustomInterval customInterval : customIntervals) {
                String desc = customInterval.getDescription();
                if (!customInterval.isGreaterThan()) {
                    int ini = customInterval.getInitMonth();
                    int fin = customInterval.getFinalMonth();
                    cases.append("WHEN MONTH(creation) BETWEEN " + ini + " AND " + fin + " AND TIMESTAMPDIFF(YEAR,creation,CURRENT_DATE()) = 0 THEN ")
                      .append("'" + desc + "' \n");
                } else {
                    cases.append("ELSE '" + desc + "' \n");
                }
            }

            str.append(cases.toString());
            str.append(
                "    END AS ranges,\n" +
                "       id\n" +
                "     FROM product) p \n" +
                "   INNER JOIN item on p.id = item.product_id\n" +
                "   INNER JOIN order_table_items orderItem on orderItem.items_id = item.id \n" +
                "   INNER JOIN order_table orderTable on orderTable.id = orderItem.order_table_id  \n" +
                "   WHERE orderTable.order_date BETWEEN '" +initialDate+ "' AND '"+finalDate+ "' \n" +
                "   GROUP BY p.ranges"
            );
            return str.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error creating native query");
        }
    }
}
