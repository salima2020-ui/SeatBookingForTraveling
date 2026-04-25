package com.example.demo.enums;

import java.math.BigDecimal;

public enum SeatClass {
        ECONOMY(new BigDecimal("50.00")),
        BUSINESS(new BigDecimal("150.00")),
        FIRST_CLASS(new BigDecimal("300.00"));

        private final BigDecimal price;

        SeatClass(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getPrice() {
            return price;

    }
}