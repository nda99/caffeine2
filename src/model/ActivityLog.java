package model;
import java.util.logging.*;

public class ActivityLog {
	private static ActivityLog log = new ActivityLog();
	private Logger logger = Logger.getLogger(ActivityLog.class.getName());
	
	private ActivityLog() {
        logger.addHandler(new ConsoleHandler());
	}
	
	public static ActivityLog getInstance() {
		return log;
	}
	
	public void logInfo(String fullName) {
		logger.info(fullName);
		logger.fine(fullName);
		logger.finer(fullName);
		logger.finest(fullName);
	}
	
}
