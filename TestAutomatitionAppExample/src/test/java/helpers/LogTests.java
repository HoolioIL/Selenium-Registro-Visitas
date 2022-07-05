package helpers;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogTests {
	
	private Logger logger = Logger.getLogger("MyLog");
	private FileHandler fileHandler;
	
	public LogTests() {
        try {
        	fileHandler = new FileHandler("src/test/java/logs/MyLogFile.txt");
        	logger.addHandler(fileHandler);
	    	 SimpleFormatter simpleFormatter = new SimpleFormatter();
	    	 fileHandler.setFormatter(simpleFormatter);
        }catch(IOException e) {
        	logger.info("IO Exception:" + e.getMessage());
            e.printStackTrace();
        }

	}
	
	public void printInfo( String text) {
		logger.info( text );
	}

}
