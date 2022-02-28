package pages;

import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OstrovokMainPage {

    public OstrovokMainPage inputHotel() {
        $("[data-testid=\"destination-input\"]").setValue("Tower Suites by Blue Orchid");
        sleep(2000);
        actions().sendKeys(Keys.ENTER);

        return this;
    }

    public OstrovokMainPage setNumberOfGuests(String testData) {
        $("[data-testid=\"guests-input\"]").click();
        $(".Counter-module__countButton--3e2-F").click();
        int groupAdults = Integer.parseInt($(".Counter-module__value--2akzl").getText());
        while (groupAdults < Integer.parseInt(testData)) {
            $$(".Counter-module__countButton--3e2-F").get(1).click();
            groupAdults = Integer.parseInt($(".Counter-module__value--2akzl").getText());
        }

        return this;
    }

    public OstrovokMainPage clickSearchButton() {
        $(byText("Найти")).click();

        return this;
    }

    public void checkExpectedText(String expectedText) {
        sleep(2000);
        $(".zen-roomspage-hotel").$(withText(expectedText)).shouldBe(visible);
    }
}
