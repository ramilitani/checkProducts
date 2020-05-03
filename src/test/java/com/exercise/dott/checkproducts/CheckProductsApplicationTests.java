package com.exercise.dott.checkproducts;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest
@Sql({"/data_test.sql"})
class CheckProductsApplicationTests {

}
