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
    String city = dataGenerator.city;
    String name = dataGenerator.lastName + " " + dataGenerator.firstName;
    String phone = dataGenerator.phone;

    @DisplayName("Заполнить поля формы дважды одинаковыми значениями, кроме даты, проверить появление окна с вопросом" +
            " о перепланировке даты встречи ")
    @Test
    void shouldFillForm() {
        open(WEBSITE);

        //первичное заполнение полей
        $("[placeholder='Город']").setValue(city);

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(dataGenerator.setNewDate());

        $("[name='name']").setValue(name);
        $("[name='phone']").setValue(phone);

        $("[data-test-id='agreement']").click();
        $(byClassName("button")).click();
        $(withText("Успешно!")).shouldBe(visible);

        //повторное заполнение полей
        $("[placeholder='Город']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[placeholder='Город']").setValue(city);

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(dataGenerator.setFutureDate()); //новая дата

        $("[name='name']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[name='name']").setValue(name);

        $("[name='phone']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[name='phone']").setValue(phone);

        $(byClassName("button")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible);
        $(withText("Перепланировать")).click();
    }
}
