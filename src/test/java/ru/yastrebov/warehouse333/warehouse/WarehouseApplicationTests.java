package ru.yastrebov.warehouse333.warehouse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import ru.yastrebov.warehouse333.warehouse.entity.Item;
import ru.yastrebov.warehouse333.warehouse.entity.Location;
import ru.yastrebov.warehouse333.warehouse.service.CalculateService;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnit4.class)
public class WarehouseApplicationTests {


    private Item item;
    private Location location;
    private long noOfDaysBetween;
    private int admin_tax = 100;
    private int volume_level = 1000;
    private double insurance_tax = 0.1;
    private double expected = 14177.7;

    private CalculateService calculateService;

    @Before
    public void init() {
        System.out.println("init...");
        this.calculateService = new CalculateService();
        this.noOfDaysBetween = 7;
        this.item = new Item(1, 11, 111,true);
        this.location = new Location(1, 100, 2000);
    }

    @Test
    public void calculateTest() {
        System.out.println("test...");
        double actual = calculateService.calculate(item,location,noOfDaysBetween, admin_tax, volume_level, insurance_tax);

        assertEquals(expected, actual);
    }


}
