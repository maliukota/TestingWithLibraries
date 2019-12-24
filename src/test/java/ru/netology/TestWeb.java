package ru.netology;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byClassName;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestWeb {
    private static final String WEBSITE = "http://localhost:9999";

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll(){
        SelenideLogger.removeListener("allure");
    }

    @DisplayName("Заполнить поля формы дважды одинаковыми значениями, кроме даты, проверить появление окна с вопросом" +
            " о перепланировке даты встречи ")
    @Test
    void shouldFillForm() {
        open(WEBSITE);

        //первичное заполнение полей
        $("[placeholder='Город']").setValue(DataGenerator.getCity());

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(DataGenerator.getNewDate());

        $("[name='name']").setValue(DataGenerator.getName());
        $("[name='phone']").setValue(DataGenerator.getPhone());

        $("[data-test-id='agreement']").click();
        $(byClassName("button")).click();
        $(withText("Успешно!")).shouldBe(visible);

        //повторное заполнение полей
        $("[placeholder='Город']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[placeholder='Город']").setValue(DataGenerator.getCity());

        $("[placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[placeholder='Дата встречи']").setValue(DataGenerator.getFutureDate()); //новая дата

        $("[name='name']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[name='name']").setValue(DataGenerator.getName());

        $("[name='phone']").sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
        $("[name='phone']").setValue(DataGenerator.getPhone());

        $(byClassName("button")).click();
        $(withText("Необходимо подтверждение")).shouldBe(visible);
        $(withText("Перепланировать")).click();
    }
}
