package ru.yastrebov.warehouse333.warehouse.service;

import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Integer ADMIN_TAX = 100;
    private static final Double INSURANCE = 0.1;

    @Autowired
    private ItemService itemService;
    @Autowired
    private LocationService locationService;


    public double calculate(Integer itemId, Integer storeId, String expected_release) {

        Item item = itemService.read(itemId);
        Location location = locationService.read(storeId);

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        Date date = null;
        try {
            date = formatter.parse(expected_release);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return this.calculate(item, location, new Date(),  date);
    }

    public double calculate(Item item, Location location, Date today, Date date) {

        double storageAmount;

        if ((item.getVolume() < 1000) && !item.getArt()) {
            storageAmount = location.getRatemin();
        } else {
            storageAmount = location.getRatemax();

        }
        long noOfDaysBetween = ChronoUnit.DAYS.between(today.toInstant(), date.toInstant());

        double insuranceAmount = item.getValue() * INSURANCE;
        double total = (storageAmount + insuranceAmount) * (noOfDaysBetween + 1) + ADMIN_TAX;
        System.out.println("number of days: " + noOfDaysBetween);
        System.out.println("insuranceAmount:" + insuranceAmount);
        System.out.println("storageAmount:" + storageAmount);
        System.out.println("ADMIN_TAX:" + ADMIN_TAX);
        System.out.println("value:" + item.getValue());
        System.out.println("volume:" + item.getVolume());
        System.out.println("Art:" + item.getArt());
        System.out.println("insuranceAmount:" + insuranceAmount);
        System.out.println("total: " + total);

        return total;
    }

}

//http://localhost:8080/invoice-preview?itemId=1&storeId=1&expected_release=2020-06-07
//jobId=1
//storeId=1
//expected_release = 2020-06-07
//number of days: 7
//insuranceAmount:11.100000000000001
//storageAmount:2000.0
//ADMIN_TAX:100
//value:111
//volume:11
//Art:true
//insuranceAmount:11.100000000000001
//total: 16188.8