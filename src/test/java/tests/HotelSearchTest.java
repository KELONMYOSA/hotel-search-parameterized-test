package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class HotelSearchTest {

    @BeforeEach
    void precondition() {
        Configuration.browserSize = "1920x1080";
        open("https://ostrovok.ru");
    }

    @AfterEach
    void closeBrowser() {
        closeWebDriver();
    }

    @CsvSource(value = {
            "2, Двухместная студия Superior",
            "4, Четырёхместный люкс Executive"
    })
    @ParameterizedTest(name = "Search for a hotel room for \"{0}\" people")
    void hotelSearchTest(String testData, String expectedText) {
        $("[data-testid=\"destination-input\"]").setValue("Tower Suites by Blue Orchid");
        sleep(2000);
        actions().sendKeys(Keys.ENTER);
        $("[data-testid=\"guests-input\"]").click();
        $(".Counter-module__countButton--3e2-F").click();
        int groupAdults = Integer.parseInt($(".Counter-module__value--2akzl").getText());
        while (groupAdults < Integer.parseInt(testData)) {
            $$(".Counter-module__countButton--3e2-F").get(1).click();
            groupAdults = Integer.parseInt($(".Counter-module__value--2akzl").getText());
        }
        $(byText("Найти")).click();
        sleep(2000);
        $(".zenroomspagerate-name-title").shouldHave(text(expectedText));
    }
}
