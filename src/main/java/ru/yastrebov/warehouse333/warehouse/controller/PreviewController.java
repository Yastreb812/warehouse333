package ru.yastrebov.warehouse333.warehouse.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;
import ru.yastrebov.warehouse333.warehouse.service.CalculateService;

@RestController
public class PreviewController {

    @Autowired
    private CalculateService calculateService;

    @GetMapping(value = "invoice-preview")
    public void read(@Param(value = "itemId") Integer itemId,
                     @Param(value = "storeId") Integer storeId,
                     @Param(value = "expected_release") String expected_release) {
        System.out.println( "\njobId=" + itemId +
                "\nstoreId=" + storeId +
                "\nexpected_release = " + expected_release);
        calculateService.calculate(itemId,storeId,expected_release);
    }


}
