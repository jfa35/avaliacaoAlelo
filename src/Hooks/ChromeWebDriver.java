package Hooks;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChromeWebDriver extends EventFiringWebDriver {
	
	private static final WebDriver webdriver;
	private static final Logger logger = LoggerFactory.getLogger(ChromeWebDriver.class);

	private static final Thread CLOSE_THREAD = new Thread() {
		@Override
		public void run() {
			webdriver.close();
		}
	};

	static {
		System.setProperty("webdriver.chrome.driver", " C:/Users/jefferson.alves/avaliacaoAlelo/src/main/resources/chromedriver");
		
		ChromeOptions capabilities = new ChromeOptions();
		webdriver = new ChromeDriver(capabilities);
		
		Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
	}

	public ChromeWebDriver() {
		super(webdriver);
	}

	@Override
	public void close() {
		if (Thread.currentThread() != CLOSE_THREAD) {
			logger.info("You shouldn't close this WebDriver. It's shared and will close when the JVM exits.");
		}
		super.close();
	}
	
}
