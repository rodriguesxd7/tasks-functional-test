package tasksFunctional;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class TasksTest {

    public WebDriver abrirNavegador() throws MalformedURLException {
        ChromeOptions cap = new ChromeOptions();
        //DesiredCapabilities cap = new DesiredCapabilities("Chrome", "100", Platform.WIN10);
        WebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444"), cap);
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() throws MalformedURLException {
        WebDriver driver = abrirNavegador();

        try {
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Testes Funcionais");
            driver.findElement(By.id("dueDate")).sendKeys("24/10/2025");
            driver.findElement(By.id("saveButton")).click();
            WebElement text = driver.findElement(By.id("message"));
            Assert.assertEquals("Success!", text.getText());
        } finally { //dando certo ou errado, o browser vai fechar de qualquer forma
            driver.quit();
        }


    }

    @Test
    public void naoDeveSalvarTarefaComSucesso() throws MalformedURLException {
        WebDriver driver = abrirNavegador();

        try {
            driver.findElement(By.id("addTodo")).click();
            driver.findElement(By.id("task")).sendKeys("Testes Funcionais");
            driver.findElement(By.id("dueDate")).sendKeys("24/10/2010");
            driver.findElement(By.id("saveButton")).click();
            WebElement text = driver.findElement(By.id("message"));
            Assert.assertEquals("Due date must not be in past", text.getText());
        } finally {
            driver.quit();
        }


    }
}
