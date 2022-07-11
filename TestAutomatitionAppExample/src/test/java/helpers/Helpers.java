package helpers;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Helpers {
	
	public static void SetExplicitWait(WebDriver driver, By by, int timeout) 
	{
		WebDriverWait wait = new WebDriverWait( driver, Duration.ofSeconds( timeout ));
		wait.until(ExpectedConditions.visibilityOfElementLocated(by)); //Esperar que sea VISIBLE EN EL DOM
		return;
	}
	
	public void awaitResponse( int seconds ) {
		try {
			Thread.sleep( seconds * 1000 );
		}catch ( InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public String generateRandomString(int sizeString) {
		int leftLimit = 97; //Desde letra 'a'
		int rightLimit = 122; //Hasta letra 'z'
		String text = "Sel"; //From Selenium
		for ( int i = 0; i < sizeString; i++) {
			Random r = new Random();
			char letter = (char) ((char) r.nextInt( rightLimit - leftLimit ) + leftLimit);
			text = text + letter; 
		}
		return text;
	}
	
	public String generateRandomNumber( int sizeString) {
		String numbers = "";
		for ( int i = 0; i < sizeString; i++) {
			Random r = new Random();
			numbers += r.nextInt(9);
		}
		return numbers;
	}

}
