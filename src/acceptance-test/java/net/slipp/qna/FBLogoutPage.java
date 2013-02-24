package net.slipp.qna;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FBLogoutPage {
    private WebDriver driver;

    public FBLogoutPage(WebDriver driver) {
        this.driver = driver;
        driver.get("http://localhost:8080/fblogout");
    }

    public FBLogoutPage goToLogoutPage() {
        driver.get("http://localhost:8080/fblogout");
        return new FBLogoutPage(driver);
    }

    public void verifyCurrentLoginUser(String nickName) {
        String loginUserNickName = driver.findElement(By.cssSelector("p.nickName")).getText();
        assertThat(loginUserNickName, is(nickName));
    }

    public IndexPage logout() {
        new WebDriverWait(driver, 1000).until(ExpectedConditions.visibilityOfElementLocated(By.id("fbLogoutBtn")));
        driver.findElement(By.id("fbLogoutBtn")).click();
        List<WebElement> logoutLinks = driver.findElements(By.linkText("로그아웃"));
        if (!logoutLinks.isEmpty()) {
            logoutLinks.get(0).click();
        }
        return new IndexPage(driver);
    }
}
