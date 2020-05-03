package com.exercise.dott.checkproducts.services;

import com.exercise.dott.checkproducts.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@SpringBootTest
@Sql(scripts={"/data_test.sql"})
public class CheckProductsApplicationServiceTests {

    @Autowired
    CheckProductsService service;

    @Test
    void whenIntervalIsInvalidThrowsAnException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.checkOrders( new String[] {null, null}));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.checkOrders(new String[] {"2019", ""}));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.checkOrders(new String[] {"", "2019"}));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.checkOrders(new String[] {"2019", "2020"}));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.checkOrders(new String[] {"2020-01-02 00:00:00", "2020-01-01 00:00:00"}));
    }

    @Test
    void whenIntervalsListPassedIsInvalidThrowsAnException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.checkOrders(new String[] {"1900-01-01 00:00:00", "1900-01-02 00:00:00", "1-1,12"}));
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.checkOrders(new String[] {"1900-01-01 00:00:00", "1900-01-02 00:00:00", "a-b"}));
    }

    @Test
    void whenNotFoundOrdersInIntervalReturnNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, () -> service.checkOrders(new String[] {"1900-01-01 00:00:00", "1900-01-02 00:00:00"}));

    }

    @Test
    void whenFindOrdersInTheIntervalReturnValues() {
        LocalDateTime oneYearAgo = LocalDateTime.now().minus(1, ChronoUnit.YEARS);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        service.checkOrders(new String[] {oneYearAgo.format(formatter), LocalDateTime.now().format(formatter)});
        Assertions.assertNotNull(service.orderPlacedInterval);
        Assertions.assertEquals(service.orderPlacedInterval.size(), 3);
        Assertions.assertEquals(service.orderPlacedInterval.get(0).getRanges(), "1-3 months");
        Assertions.assertEquals(service.orderPlacedInterval.get(0).getCount(), 3);
    }
}
