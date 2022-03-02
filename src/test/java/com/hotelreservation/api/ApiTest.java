package com.hotelreservation.api;

import com.hotelreservation.api.api.AuthApi;
import com.hotelreservation.api.api.BookingApi;
import com.hotelreservation.api.api.PingApi;
import com.hotelreservation.api.payload.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Date;

import static com.hotelreservation.util.Global.DEFAULT_PASSWORD;
import static com.hotelreservation.util.Global.DEFAULT_USER_NAME;
import static org.assertj.core.api.Assertions.assertThat;


public class ApiTest {

    @Test
    public void testHealthCheckReturns201() {
        Response response = PingApi.healthCheck();

        assertThat(response.getStatusCode() == 201).isTrue();
    }

    @Test
    public void testCreateTokenReturns200() {
        Auth auth = new Auth.Builder()
                .setUserName(DEFAULT_USER_NAME)
                .setPassword(DEFAULT_PASSWORD)
                .build();

        Response response = AuthApi.createToken(auth);

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void testGetBookingIdsReturns200() {
        Response response = BookingApi.getBookingIds();

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void testGetBookingReturns200() {
        Response response = BookingApi.getBooking(1, "application/json");

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void testGetBookingWithIncorrectAcceptReturns418() {
        Response response = BookingApi.getBooking(1, "test");

        assertThat(response.getStatusCode() == 418).isTrue();
    }
    @Test
    public void testPartialUpdateBookingWithNameChange() {
        Booking payload = new Booking.Builder()
                .setFirstName("Prasann")
                .setLastName("Singh")
                .setTotalPrice(200)
                .setDepositPaid(true)
                .setAdditionalNeeds("None")
                .build();

        Response response = BookingApi.updateBooking(payload);

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void testCreateBookingReturns200() {
        BookingDates dates = new BookingDates.Builder()
                .setCheckin(new Date())
                .setCheckout(new Date())
                .build();

        Booking payload = new Booking.Builder()
                .setFirstName("Prasann")
                .setLastName("Kumar")
                .setTotalPrice(200)
                .setDepositPaid(true)
                .setBookingDates(dates)
                .setAdditionalNeeds("None")
                .build();

        Response response = BookingApi.createBooking(payload);

        assertThat(response.getStatusCode() == 200).isTrue();
    }

    @Test
    public void testDeleteBookingReturns201() {
        BookingDates dates = new BookingDates.Builder()
                .setCheckin(new Date())
                .setCheckout(new Date())
                .build();

        Booking payload = new Booking.Builder()
                .setFirstName("Test")
                .setLastName("Test")
                .setTotalPrice(100)
                .setDepositPaid(true)
                .setBookingDates(dates)
                .setAdditionalNeeds("None")
                .build();

        BookingResponse bookingResponse = BookingApi.createBooking(payload).as(BookingResponse.class);

        Auth auth = new Auth.Builder()
                .setUserName(DEFAULT_USER_NAME)
                .setPassword(DEFAULT_PASSWORD)
                .build();

        AuthResponse authResponse = AuthApi.createToken(auth).as(AuthResponse.class);

        Response response = BookingApi.deleteBooking(bookingResponse.getBookingId(), authResponse.getToken());

        assertThat(response.getStatusCode() == 201).isTrue();
    }
}

