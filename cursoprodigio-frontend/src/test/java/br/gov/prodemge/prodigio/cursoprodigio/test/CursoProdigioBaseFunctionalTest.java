package br.gov.prodemge.prodigio.cursoprodigio.test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class CursoProdigioBaseFunctionalTest {
		protected WebDriver driver;
		private WebElement elementoEsperado;
		
		
		protected void waitAndClickByXPATH(WebDriver driver,String path){
			WebDriverWait wait = new WebDriverWait(driver,10);
			elementoEsperado = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
			driver.findElement(By.xpath(path)).click();
		}
		
		protected void waitAndClickById(WebDriver driver,String component){
			WebDriverWait wait = new WebDriverWait(driver,10);
			elementoEsperado = wait.until(ExpectedConditions.elementToBeClickable(By.id(component)));
			driver.findElement(By.id(component)).click();
		}

		protected WebElement findElementById(String component) {
			elementoEsperado = null;
			WebDriverWait wait = new WebDriverWait(driver,10);
			elementoEsperado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(component)));
			return elementoEsperado;
		}

		
		protected WebElement findElementByXPath(String path) {
			elementoEsperado= null;
			WebDriverWait wait = new WebDriverWait(driver,10);
			elementoEsperado = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(path)));
			return elementoEsperado;
		}
		
}