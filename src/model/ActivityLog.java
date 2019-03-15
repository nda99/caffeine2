package model;
import java.io.IOException;
import java.util.logging.*;

/**
 * Class to log events happening in the application.
 * Class is set up to be used as a SINGLETON.
 * @author Nathan
 *
 */
public class ActivityLog {
    private FileHandler fileTxt;
    private SimpleFormatter formatterTxt;
    private FileHandler fileHTML;
    private Formatter formatterHTML;
    
	private static ActivityLog log = new ActivityLog();
	private Logger logger = Logger.getLogger(ActivityLog.class.getName());
	Logger logger2 = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	/**
	 * Constructor to initialise the logger and output file
	 */
	private ActivityLog() {
        logger.addHandler(new ConsoleHandler());
        setup();
        logInfo("Caffeine Application has started");
	}
	
	/**
	 * Creates text file to output logs and adds its format as a handler to Logger object.
	 */
	public void setup() {
		logger2.setLevel(Level.INFO);
        try {
			fileTxt = new FileHandler("Logs.txt");
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        formatterTxt = new SimpleFormatter();
        fileTxt.setFormatter(formatterTxt);
        logger.addHandler(fileTxt);
        logger2.addHandler(fileTxt);

	}
	/**
	 * Get the ActivityLog own instance to be used according to the SINGLETON pattern.
	 * @return ActivityLog instance
	 */
	public static ActivityLog getInstance() {
		return log;
	}
	
	/**
	 * Logs the provided String as an information
	 * @param message String to be logged as info
	 */
	public void logInfo(String message) {
		logger.info(message);
	}
	
	/**
	 * Logs the provided message as a warning.
	 * @param message Warning to be logged.
	 */
	public void logWarning(String message) {
		logger.warning(message);
	}
}
