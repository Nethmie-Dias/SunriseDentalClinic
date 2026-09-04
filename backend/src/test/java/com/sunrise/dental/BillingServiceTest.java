package com.sunrise.dental;

import com.sunrise.dental.model.Bill;
import com.sunrise.dental.service.BillingService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BillingServiceTest {

    // ---------------------------------------------------------
    // TEST 1: Empty appointment number should be rejected
    // ---------------------------------------------------------

    @Test
    void calculateBill_shouldRejectEmptyAppointmentNumber() {

        BillingService billingService = new BillingService();

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService.calculateBill("")
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    // ---------------------------------------------------------
    // TEST 2: Null appointment number should be rejected
    // ---------------------------------------------------------

    @Test
    void calculateBill_shouldRejectNullAppointmentNumber() {

        BillingService billingService = new BillingService();

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService.calculateBill(null)
                );

        assertEquals(
                "Appointment number is required.",
                exception.getMessage()
        );
    }


    // ---------------------------------------------------------
    // TEST 3: Null bill should be rejected
    // ---------------------------------------------------------

    @Test
    void saveBill_shouldRejectNullBill() {

        BillingService billingService = new BillingService();

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService.saveBill(null)
                );

        assertEquals(
                "Bill information is required.",
                exception.getMessage()
        );
    }


    // ---------------------------------------------------------
    // TEST 4: Invalid appointment ID should be rejected
    // ---------------------------------------------------------

    @Test
    void saveBill_shouldRejectInvalidAppointmentId() {

        BillingService billingService = new BillingService();

        Bill bill = new Bill(
                0,
                0,
                5000.00,
                1500.00,
                6500.00,
                null
        );

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService.saveBill(bill)
                );

        assertEquals(
                "Invalid appointment.",
                exception.getMessage()
        );
    }


    // ---------------------------------------------------------
    // TEST 5: Zero bill amount should be rejected
    // ---------------------------------------------------------

    @Test
    void saveBill_shouldRejectZeroAmount() {

        BillingService billingService = new BillingService();

        Bill bill = new Bill(
                0,
                1,
                0.00,
                0.00,
                0.00,
                null
        );

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService.saveBill(bill)
                );

        assertEquals(
                "Bill amount must be greater than zero.",
                exception.getMessage()
        );
    }


    // ---------------------------------------------------------
    // TEST 6: Negative bill amount should be rejected
    // ---------------------------------------------------------

    @Test
    void saveBill_shouldRejectNegativeAmount() {

        BillingService billingService = new BillingService();

        Bill bill = new Bill(
                0,
                1,
                1000.00,
                500.00,
                -1500.00,
                null
        );

        IllegalArgumentException exception =
                assertThrows(
                        IllegalArgumentException.class,
                        () -> billingService.saveBill(bill)
                );

        assertEquals(
                "Bill amount must be greater than zero.",
                exception.getMessage()
        );
    }


    // ---------------------------------------------------------
    // TEST 7: Invalid appointment ID for getBill
    // ---------------------------------------------------------

    @Test
    void getBill_shouldReturnNullForInvalidAppointmentId() {

        BillingService billingService = new BillingService();

        Bill result = billingService.getBill(0);

        assertNull(result);
    }


    // ---------------------------------------------------------
    // TEST 8: Negative appointment ID for getBill
    // ---------------------------------------------------------

    @Test
    void getBill_shouldReturnNullForNegativeAppointmentId() {

        BillingService billingService = new BillingService();

        Bill result = billingService.getBill(-1);

        assertNull(result);
    }
}