package selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class UserAction {

    private WebDriver driver;

    public UserAction(WebDriver driver) {

        this.driver = driver;

    }

    public void login(String username, String password) {

        driver.get(Lib.Pages.ROOT);

        driver.findElement(By.name("username")).clear();
        driver.findElement(By.name("username")).sendKeys(username);

        driver.findElement(By.name("password")).clear();
        driver.findElement(By.name("password")).sendKeys(password);

        driver.findElement(By.xpath("//*[@id=\"btn-login\"]")).click();

    }

    public void clickHome(){

        driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/div/ul[1]/li[1]/a")).click();

    }

    public void addStore(String name, String type, String addr) {

        driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/div/ul[1]/li[2]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"store-name\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"store-name\"]")).sendKeys(name);

        driver.findElement(By.xpath("//*[@id=\"store-type\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"store-type\"]")).sendKeys(type);

        driver.findElement(By.xpath("//*[@id=\"store-address\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"store-address\"]")).sendKeys(addr);

        driver.findElement(By.xpath("//*[@id=\"btn-store-add\"]")).click();

        clickHome();

    }

    public void addBrand(String name) {

        driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/div/ul[1]/li[4]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"brand-name\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"brand-name\"]")).sendKeys(name);

        driver.findElement(By.xpath("//*[@id=\"btn-brand-add\"]")).click();

        clickHome();

    }

    public void addCategory(String name) {

        driver.findElement(By.xpath("//*[@id=\"nav\"]/nav/div/ul[1]/li[5]/a")).click();

        driver.findElement(By.xpath("//*[@id=\"category-name\"]")).clear();
        driver.findElement(By.xpath("//*[@id=\"category-name\"]")).sendKeys(name);

        driver.findElement(By.xpath("//*[@id=\"btn-category-add\"]")).click();

        clickHome();

    }

}