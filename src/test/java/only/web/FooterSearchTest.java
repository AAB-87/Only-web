package only.web;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FooterSearchTest {
    private WebDriver driver;

    @BeforeEach
    public void setUpTest() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @DisplayName("Находит элемент футера")
    public void shouldFindFooterElement() {
        driver.get("https://only.digital");
        WebElement footer = driver.findElement(By.xpath("//*[@class='text2 Footer_text___ATim']"));
        int deltaY = footer.getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
        String actual = footer.getText();
        String expected = "Создаем digital-продукт на базе\nстратегии, креатива и технологий";
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Находит элемент Презентация компании")
    public void shouldFindCompanyPresentation() {
        driver.get("https://only.digital");
        List<WebElement> footer = driver.findElements(By.xpath("//*[@class='captions Documents_documentsDescription__ARJsa']"));
        footer.get(1);
        int deltaY = footer.get(1).getRect().y;
        new Actions(driver)
                .scrollByAmount(0, deltaY)
                .perform();
        String actual = footer.get(1).getText();
        String expected = "презентация \nкомпании";
        assertEquals(expected, actual);
    }

}