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
    private Integer ADMIN_TAX;
    @Value("${property.store_id_default}")
    private Integer STORE_ID_DEFAULT;
    @Value("${property.volume_level}")
    private Integer VOLUME_LEVEL;
    @Value("${property.insurance_tax}")
    private Double INSURANCE_TAX;

    @Autowired
    private ItemService itemService;


    @Autowired
    private LocationService locationService;

    public double calculate(Integer itemId, Integer storeId, String expected_release) {
        System.out.println("Calculate smth...");
        Item item = itemService.read(itemId);
        Location location = storeId == null?locationService.read(STORE_ID_DEFAULT):locationService.read(storeId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(expected_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.calculate(item, location, new Date(),  date);
    }

    public double calculate(Item item,Location location, Date today, Date date) {
        long noOfDaysBetween = ChronoUnit.DAYS.between(today.toInstant(), date.toInstant());
        return this.calculate(item, location, noOfDaysBetween);
    }

    public double calculate(Item item,Location location, long noOfDaysBetween) {

        double insuranceAmount = item.getValue() * INSURANCE_TAX;
        double storageAmount;

        if (item.getVolume() < VOLUME_LEVEL && !item.getArt()) {
            storageAmount = location.getRatemin();
        }
        else   {
            storageAmount = location.getRatemax();
        }

        double total = Precision.round((storageAmount + insuranceAmount) * noOfDaysBetween + ADMIN_TAX, 2);

        System.out.println("количество дней: "+ noOfDaysBetween);
        System.out.println("insuranceAmount: "+insuranceAmount);
        System.out.println("storageAmount: "+storageAmount);
        System.out.println("ADMIN_TAX: "+ADMIN_TAX);
        System.out.println("value: "+item.getValue());
        System.out.println("volume: "+item.getVolume());
        System.out.println("Art: "+ item.getArt());
        System.out.println("Total: " + total);

        return total;
    }
}

//http://localhost:8080/invoice-preview?itemId=1&storeId=1&expected_release=2020-06-08
//jobId=1
//storeId=1
//expected_release = 2020-06-08
//Calculate smth...
//количество дней: 7
//insuranceAmount: 11.100000000000001
//storageAmount: 2000.0
//ADMIN_TAX: 100
//value: 111
//volume: 11
//Art: true
//Total: 14177.7