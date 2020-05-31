package ru.yastrebov.warehouse333.warehouse.service;
import org.apache.commons.math3.util.Precision;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.yastrebov.warehouse333.warehouse.entity.Item;
import ru.yastrebov.warehouse333.warehouse.entity.Location;
import ru.yastrebov.warehouse333.warehouse.repository.ItemService;
import ru.yastrebov.warehouse333.warehouse.repository.LocationService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

@Service
public class CalculateService {

    @Value("${property.admin_tax}")
    private Integer admin_tax;
    @Value("${property.store_id_default}")
    private Integer store_id_default;
    @Value("${property.volume_level}")
    private Integer volume_level;
    @Value("${property.insurance_tax}")
    private double insurance_tax;

    @Autowired
    private ItemService itemService;

    @Autowired
    private LocationService locationService;

    public double calculate(Integer itemId, Integer storeId, String expected_release) {
        System.out.println("Calculate smth...");
        Item item = itemService.read(itemId);
        Location location = storeId == null ? locationService.read(store_id_default) : locationService.read(storeId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(expected_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long noOfDaysBetween = ChronoUnit.DAYS.between(new Date().toInstant(), date.toInstant());
        return this.calculate(item, location, noOfDaysBetween, admin_tax, volume_level, insurance_tax);

    }
    public double calculate(Item item, Location location, long noOfDaysBetween, Integer admin_tax, Integer volume_level, double insurance_tax) {


        double storageAmount;

        if (item.getVolume() < volume_level && !item.getArt()) {
            storageAmount = location.getRatemin();
        } else {
            storageAmount = location.getRatemax();
        }
        double insuranceAmount = item.getValue() * insurance_tax;
        double total = Precision.round(
                (storageAmount + insuranceAmount) * (noOfDaysBetween) + admin_tax,
                2);

        System.out.println("количество дней: " + noOfDaysBetween);
        System.out.println("insuranceAmount: " + insuranceAmount);
        System.out.println("storageAmount: " + storageAmount);
        System.out.println("ADMIN_TAX: " + admin_tax);
        System.out.println("value: " + item.getValue());
        System.out.println("volume: " + item.getVolume());
        System.out.println("Art: " + item.getArt());
        System.out.println("Total: " + total);

        return total;
    }
}

//http://localhost:8080/invoice-preview?itemId=1&storeId=1&expected_release=2020-06-09
//jobId=1
//storeId=1
//expected_release = 2020-06-09
//Calculate smth...
//количество дней: 8
//insuranceAmount: 11.100000000000001
//storageAmount: 2000.0
//ADMIN_TAX: 100
//value: 111
//volume: 11
//Art: true
//Total: 16188.8