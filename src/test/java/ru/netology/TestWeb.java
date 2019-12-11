package ru.netology;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestWeb {
    public static final String WEBSITE = "http://localhost:9999";
    DataGenerator dataGenerator = new DataGenerator();

    @DisplayName("Filling the fields with random values from Faker")
    @Test
    void shouldFillForm() {

        open(WEBSITE);
        $("[placeholder='Город']").setValue(dataGenerator.city);

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(dataGenerator.setNewDate());

        $("[name='name']").setValue(dataGenerator.firstName + " " + dataGenerator.lastName);
        $("[name='phone']").setValue(dataGenerator.phone);

        $("[data-test-id='agreement']").click();
        $(byClassName("button")).click();
        $(withText("Успешно!")).shouldBe(visible);
    }

    @DisplayName("Filling the fields with static values")
    @Test
    void shouldFillFormAgain() {
        open(WEBSITE);
        $("[placeholder='Город']").setValue("Барнаул");

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(dataGenerator.setNewDate());

        $("[name='name']").setValue("ПЕТРОВ ИВАН");
        $("[name='phone']").setValue("+79999999999");

        $("[data-test-id='agreement']").click();
        $(byClassName("button")).click();
        $(withText("Успешно!")).shouldBe(visible);
    }

    @DisplayName("Filling the fields with static values and different date")
    @Test
    void shouldFillFormWithAnotherDate() {
        open(WEBSITE);
        $("[placeholder='Город']").setValue("Барнаул");

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(dataGenerator.setFutureDate());

        $("[name='name']").setValue("ПЕТРОВ ИВАН");
        $("[name='phone']").setValue("+79999999999");

        $("[data-test-id='agreement']").click();
        $(byClassName("button")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible);
        $(withText("Перепланировать")).click();
    }
}
