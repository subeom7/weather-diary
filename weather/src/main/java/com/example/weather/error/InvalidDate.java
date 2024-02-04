package com.example.weather.error;

public class InvalidDate extends RuntimeException{
    private static final String MESSAGE="미래의 날짜 입니다";

    public InvalidDate() {
        super(MESSAGE);
    }
}
