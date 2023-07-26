package com.cst438;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class E2E_test {

    private static final String CHROME_DRIVER_FILE_LOCATION = "/Users/daviddelacalzadaii/Desktop/chromedriver_mac_arm64/chromedriver";
    private static final String URL = "http://localhost:3000";

    private WebDriver wd;
    

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_FILE_LOCATION);
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);//implicit wait for 10 seconds
        wd.get(URL);
    }

    @AfterEach
    public void tearDown() {
        wd.quit();
    }

    @Test
    public void testAddStudent() {
    	
        //locate necessary elements name, email and button
        WebElement nameInput = wd.findElement(By.name("name"));
        WebElement emailInput = wd.findElement(By.name("email"));
        WebElement addButton = wd.findElement(By.id("add"));

        //test student info
        String testName = "Another Student";
        String testEmail = "another_student@example.com";
        
        //use sendKeys to fill in info
        nameInput.sendKeys(testName);
        emailInput.sendKeys(testEmail);

        addButton.click();

        //pause for response
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //find message element by id
        WebElement messageElement = wd.findElement(By.id("result"));

        String success = "Student added";
        
        //running test multiple times without changing testName and testEmail will cause fail
        //if wish to run multiple times, change testName and testEmail to pass
        assertEquals(success, messageElement.getText(), "Email exsists, student not added");
    }
    
}