package ru.netology;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {

    DataGenerator() {}

    private static Faker faker = new Faker(new Locale("ru"));
    private static String firstName = faker.name().firstName();
    private static String lastName = faker.name().lastName();
    private static String city = faker.address().cityName();
    private static String phone = faker.phoneNumber().phoneNumber();

    static String getCity () {
        return city;
    }

    static String getName() {
        return firstName + " " + lastName;
    }

    static String getPhone () {
        return phone;
    }

    static String getNewDate() {
        final String FORMAT_DATE = "dd.MM.yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.plusDays(5);
        return dateFormatter.format(newDate);
    }

    static String getFutureDate() {
        final String FORMAT_DATE = "dd.MM.yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate localDate = LocalDate.now();
        LocalDate futureDate = localDate.plusDays(30);
        return dateFormatter.format(futureDate);
    }
}
