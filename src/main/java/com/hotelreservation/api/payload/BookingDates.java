package com.hotelreservation.api.payload;


import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public class BookingDates {
    @JsonProperty
    private Date checkin;

    @JsonProperty
    private Date checkout;

    private BookingDates(Date checkin, Date checkout) {
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Date getCheckin() {
        return checkin;
    }

    public Date getCheckout() {
        return checkout;
    }

    public static class Builder {
        private Date checkin, checkout;

        public Builder setCheckin(Date checkin) {
            this.checkin = checkin;
            return this;
        }

        public Builder setCheckout(Date checkout) {
            this.checkout = checkout;
            return this;
        }

        public BookingDates build() {
            return new BookingDates(checkin, checkout);
        }
    }
}

