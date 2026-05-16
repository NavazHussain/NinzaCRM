package HardAssertion;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Demo {

    @Test
    public void testTitle() {

        String expectedTitle = "Facebook – log in or sign up";

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.facebook.com/");
        String actualTitle = driver.getTitle();

        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println("step1");
        System.out.println("step2");

       
    }
}