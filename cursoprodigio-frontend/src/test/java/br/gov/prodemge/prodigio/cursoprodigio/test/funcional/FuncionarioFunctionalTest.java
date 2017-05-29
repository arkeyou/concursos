package br.gov.prodemge.prodigio.cursoprodigio.test.funcional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import br.gov.prodemge.prodigio.cursoprodigio.test.CursoProdigioBaseFunctionalTest;


public class FuncionarioFunctionalTest extends CursoProdigioBaseFunctionalTest {
	

	  private String baseUrl;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    baseUrl = "http://127.0.0.1:8080/";
	  }
	  
	  @Test
	  public void testFirefox() throws Exception {
		 driver = new FirefoxDriver(); 
		 driver.get(baseUrl + "cursoprodigio-web/");
		 	findElementByXPath("//a/span").click();
		 	findElementByXPath("//div[2]/ul/li/a/span").click();
		    waitAndClickByXPATH(driver, "//button[9]");
		    findElementByXPath("//td/input").clear();
		    findElementByXPath("//td/input").sendKeys("TESTE_FUNCIONAL");
		    findElementByXPath("//tr[7]/td/input").clear();
		    findElementByXPath("//tr[7]/td/input").sendKeys("12345678900");
		    findElementByXPath("//tr[11]/td/input").clear();
		    findElementByXPath("//tr[11]/td/input").sendKeys("0159");		    
		    findElementByXPath("//button[7]").click();		   
		    assertEquals("Registro gravado com sucesso", findElementByXPath("//div[2]/div[2]").getText());		    
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }
	
}