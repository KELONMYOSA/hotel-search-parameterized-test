package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.OstrovokMainPage;

import static com.codeborne.selenide.Selenide.*;

public class HotelSearchTest {

    private static final OstrovokMainPage ostrovokPage = new OstrovokMainPage();

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
            "2, Двухместная",
            "6, Похоже, отель пользуется большим спросом"
    })
    @ParameterizedTest(name = "Search for a hotel room for \"{0}\" people")
    void hotelSearchTest(String testData, String expectedText) {
        ostrovokPage
                .inputHotel()
                .setNumberOfGuests(testData)
                .clickSearchButton()
                .checkExpectedText(expectedText);
    }
}
