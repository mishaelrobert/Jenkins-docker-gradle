import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static io.restassured.RestAssured.when;
public class RestAssuredTestResponse {
    @Test
    public void statusCode()
{  
 RestAssured.baseURI = "http://localhost/web-server/";
 // Get the RequestSpecification of the request to be sent to the server
 RequestSpecification httpRequest = RestAssured.given();

 Response response = httpRequest.get("");

 // Get the status code of the request. 
 //If request is successful, status code will be 200
int statusCode = response.getStatusCode();

 // Assert that correct status code is returned.
Assert.assertEquals(statusCode , 200 , 
  "Correct status code returned");
}
}
public class webApplicationTests {
    @Test
    public void testWebApplication() {
    }

    @Test
    public void testUI(){
        System.setProperty("webdriver.chrome.driver","C:\\Users\\ROBERT\\OneDrive\\Desktop\\chromedriver_win32 (1)\\chromedriver.exe");
        new ChromeDriver();
        ChromeDriver driver;
        ChromeOptions options = new ChromeOptions();
        driver = new ChromeDriver(options);

       String baseURL="http://localhost/web-server/";
        String expectedName="Mishael Robert";
        String actualName="";
        driver.get(baseURL);
        By css = By.cssSelector("#header div.cf>h3>a");
        WebElement element = driver.findElement(css );
       actualName = element.getText();
       assertEquals(actualName, expectedName);
        driver.quit();
    }
}
