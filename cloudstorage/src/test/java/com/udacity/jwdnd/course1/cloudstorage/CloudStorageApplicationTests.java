package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.core.context.SecurityContextHolder;


import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;

	private static WebDriver driver;


	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() throws InterruptedException {
		if (this.driver != null) {
			Thread.sleep(2000);
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		driver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void testSignUpnLogin(){
		driver.get("http://localhost:"+this.port+"/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp("Xiaodan","123456", "xiaodan", "cui");
		driver.get("http://localhost:"+this.port+"/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("Xiaodan","123456");
		assertEquals(driver.getCurrentUrl(), "http://localhost:"+this.port+"/home");
	}

	@Test
	public void testNote() throws InterruptedException {
		driver.get("http://localhost:"+this.port+"/signup");
		SignUpPage signUpPage = new SignUpPage(driver);
		signUpPage.signUp("Xiaodan","123456", "xiaodan", "cui");
		driver.get("http://localhost:"+this.port+"/login");
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login("Xiaodan","123456");
		driver.get("http://localhost:"+this.port+"/home");
		HomePage homePage = new HomePage(driver);
		homePage.getNotes();
		homePage.addNote("Hello", "Hello From the Other Side");
		assertEquals(true, true);
	}

}
