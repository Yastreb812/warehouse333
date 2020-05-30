//package ru.yastrebov.warehouse333.warehouse.service;
//
//import org.junit.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//import static org.mockito.Mockito.times;
//
////public class CalculateServiceTest {
////
////    @Test
////     void calculate() {
////
////        CalculateService calculateService = new CalculateService();
////        double actual =
////
////    }
////
////}
//////@RunWith(MockitoJUnitRunner.class)
//public class CalculateServiceTest {
//    //    @InjectMocks
////    CalculateService calculateService = new CalculateService(ItemService.class, LocationService.class);
//    @Test
//    void calculate() {
//
//        CalculateService calculateService = new CalculateService();
//        CalculateService calculateServiceSpy = spy( calculateService );
//        doReturn( 1.0 ).when(calculateServiceSpy ).calculate( anyInt() );
//        double result = new DatasourceCalculator( dataProviderSpy ).complexCalc( 5 );
//
//        assertEquals( 5, result, 0.1, "complex calc result:" );
//
//        verify( dataProviderSpy, times( 1 ) ).getDataDouble( 1 );
//        verify( dataProviderSpy, times( 1 ) ).getDataDouble( 2 );
//
//    }