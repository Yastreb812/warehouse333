package ru.yastrebov.warehouse333.warehouse;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.springframework.beans.factory.annotation.Value;
import ru.yastrebov.warehouse333.warehouse.entity.Item;
import ru.yastrebov.warehouse333.warehouse.entity.Location;
import ru.yastrebov.warehouse333.warehouse.service.CalculateService;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnit4.class)
public class WarehouseApplicationTests {

//    @Value("${property.admin_tax}")
//    private Integer ADMIN_TAX;
//
//    @Value("${property.insurance_tax}")
//    private Double INSURANCE_TAX;

    private Item item;
    private Location location;
    private long number_of_days;
    private int ADMIN_TAX = 100;
    private double expected = 16188.8;

    private CalculateService calculateService;

    @Before
    public void init() {
        System.out.println("init...");
        this.calculateService = new CalculateService();
        this.number_of_days = 8;
//        this.ADMIN_TAX = 100;
//        this.INSURANCE_TAX = 0.1;
        this.item = new Item(1, 11, 111,true);
        this.location = new Location(1, 100, 2000);
    }

    @Test
    public void calculateTest() {
        System.out.println("test...");
        double actual = calculateService.calculate(item,location,number_of_days);

        assertEquals(expected, actual);
    }
}