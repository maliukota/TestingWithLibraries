package ru.netology;

import com.github.javafaker.Faker;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataGenerator {

    Faker faker = new Faker(new Locale("ru"));
    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String city = faker.address().cityName();
    String phone = faker.phoneNumber().phoneNumber();

    String setNewDate() {
        final String FORMAT_DATE = "dd.MM.yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate localDate = LocalDate.now();
        LocalDate newDate = localDate.plusDays(5);
        return dateFormatter.format(newDate);
    }

    String setFutureDate() {
        final String FORMAT_DATE = "dd.MM.yyyy";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(FORMAT_DATE);
        LocalDate localDate = LocalDate.now();
        LocalDate futureDate = localDate.plusDays(30);
        return dateFormatter.format(futureDate);
    }
}
