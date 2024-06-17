package org.example.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import jdk.jfr.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.junit.jupiter.api.*;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.*;

import static com.codeborne.selenide.Selenide.*;

public class MainPageTest {

    @Test
    @Description("Ожидание появления элемента")
    public void waitOfVisibleElementTest() {
        open("https://the-internet.herokuapp.com/add_remove_elements/");
        $x("//*[@id=\"content\"]/div/button").shouldBe(visible).click();
    }

    @Test
    @Description("Ожидание исчезновения элемента")
    public void disappearanceOfElementTest() {
        open("https://the-internet.herokuapp.com/add_remove_elements/");
        $x("//*[@id=\"content\"]/div/button").shouldBe(visible).click();
        $(".added-manually").click();
        $(".added-manually").shouldBe(disappear);
    }

    @Test
    @Description("Ожидание появления текста")
    public void waitOfVisibilityTextTextTest() {
        open("https://the-internet.herokuapp.com/add_remove_elements/");
        $x("//*[@id=\"content\"]/div/button").shouldBe(visible).click();
        $(".added-manually").shouldHave(text("Delete"));
    }

    @Test
    @Description("Ожидание появления значения")
    public void waitOfValueTest() {
        open("https://the-internet.herokuapp.com/inputs");
        $x("//*[@id=\"content\"]/div/div/div/input").sendKeys("1");
        $x("//*[@id=\"content\"]/div/div/div/input").shouldHave(value("1"));
    }

    @Test
    @Description("Ожидание появления атрибута")
    public void waitOfAttributeTest() {
        open("https://the-internet.herokuapp.com/login");
        $(".fa.fa-2x.fa-sign-in").click();
        $("#flash").shouldHave(attribute("data-alert"));
    }

    @Test
    @Description("Таймаут")
    public void testWithTimeOut() throws InterruptedException {
        open("https://the-internet.herokuapp.com/redirector");
        $("#redirect").click();
        Configuration.timeout = 5000;
        $x("//*[@id=\"content\"]/div/ul/li[1]/a").should(visible);
    }

    @Test
    @Description("Ожидание со слипом")
    public void testWithSleep() {
        open("https://the-internet.herokuapp.com/redirector");
        $("#redirect").click();
        sleep(2000);
        $x("//*[@id=\"content\"]/div/ul/li[1]/a").should(visible);
    }

    @Test
    @Description("Ожидание появления элемента")
    public void waitOfVisibleElementTestV2() {
        open("https://the-internet.herokuapp.com/redirector");
        $("#redirect").shouldBe(visible).click();
    }

    @Test
    @Description("Ожидание появления текста")
    public void waitOfVisibilityTextTextTestV2() {
        open("https://the-internet.herokuapp.com/redirector");
        $("#redirect").shouldBe(visible).click();
        $x("//*[@id=\"content\"]/div/ul/li[1]/a").shouldHave(text("200"));
    }

    @Test
    @Description("Ожидание возможности нажатия элемента")
    public void waitOfClickable() {
        open("https://the-internet.herokuapp.com/login");
        $(".fa.fa-2x.fa-sign-in").shouldBe(clickable).click();
    }

    @Test
    @Description("Ожидание перехода на другую страницу")
    public void testRedirect() throws InterruptedException {
        open("https://the-internet.herokuapp.com/redirector");
        $("#redirect").shouldBe(visible).click();
        sleep(2000);
        $x("//p").shouldHave(text("HTTP"));
        $x("//a[@href='status_codes/200']").should(visible).click();
        TimeUnit.SECONDS.sleep(2);
        $x("//p").should(exist);
    }
}
