package com.mvolpe.test.selenium.newtours;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Tests {



    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://www.despegar.com.ar/");
        driver.manage().window().maximize();

    }

    @Test
    public void test1() throws InterruptedException {

        //Clickeo en vuelos
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("i[title='Vuelos']")).click();
        Thread.sleep(1000);

        //Limpio el valor por defecto del origen
        driver.findElement(By.cssSelector("input[class='input-tag sbox-main-focus sbox-bind-reference-flight-roundtrip-" +
                "origin-input sbox-primary sbox-places-first places-inline'][placeholder='Ingresá desde dónde viajas']")).clear();
        //Ingreso Brasil como origen y despliego las sugerencias
        driver.findElement(By.cssSelector("input[class='input-tag sbox-main-focus sbox-bind-reference-flight-roundtrip-" +
                "origin-input sbox-primary sbox-places-first places-inline'][placeholder='Ingresá desde dónde viajas']")).sendKeys("Brasil");
        //Espero a que el menu de sugerencias se termine de cargar
        WebDriverWait wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Ciudades')]")));
        //Clickeo en la opcion de Brasilia
        driver.findElement(By.xpath("//span[contains(.,'Brasilia, Distrito Federal, Brasil')]")).click();

        //Ingreso Roma en el destino y cargo las sugerencias
        driver.findElement(By.cssSelector("input[class='input-tag sbox-main-focus sbox-bind-reference-flight-roundtrip-" +
                "destination-input sbox-secondary sbox-places-second places-inline'][placeholder='Ingresá hacia dónde viajas']")).sendKeys("Roma");
        //Espero que se desplieguen las sugerencias
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(.,'Ciudades')]")));
        //Clickeo en Roma como destino
        driver.findElement(By.xpath("//span[contains(.,'Roma, Lazio, Italia')]")).click();

        //Selecciono fecha de ida
        driver.findElement(By.xpath("//*[@id=\'searchbox-sbox-box-flights\']/div/div/div[3]/div[2]/div[2]/div[2]/div[1]/div[2]")).click();
        driver.findElement(By.xpath("/html/body/div[4]/div/div[5]/div[1]/div[4]/span[7]")).click();
        Thread.sleep(1000);
        //Selecciono fecha de vuelta
        driver.findElement(By.xpath("/html/body/div[4]/div/div[5]/div[1]/div[4]/span[14]")).click();

        //Buscar vuelos
        driver.findElement(By.xpath("//*[@id=\'searchbox-sbox-box-flights\']/div/div/div[3]/div[2]/div[4]/div/a/em")).click();

        //Espero que termine de cargar vuelos disponibles
        wait = new WebDriverWait(driver, 3);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\'toolbox-tabs-position\']/toolbox-tabs" +
                "/div/tabs/div/div[1]/div/ul/li[1]/tab-header/div/span/span/label")));

    }

    private void ciudadOrigen (String ciudad) {

    }


    @After
    public void tearDown() throws InterruptedException {

        Thread.sleep(5000);
        driver.close();

    }
}
