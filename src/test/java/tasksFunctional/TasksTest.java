package tasksFunctional;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class TasksTest {

    public WebDriver abrirNavegador() {
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;
    }


    @Test
    public void deveSalvarTarefaComSucesso(){
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
    public void naoDeveSalvarTarefaComSucesso(){
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
