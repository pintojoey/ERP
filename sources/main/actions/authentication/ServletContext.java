/**
 * 
 */
package actions.authentication;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.StreamHandler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author Joey
 *
 */
@WebListener()
public class ServletContext implements ServletContextListener{
	private final static Logger LOGGER = Logger.getLogger(ServletContext.class.getName());
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		try {
			settings.database.PostgreSQLConnection.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		
	}

	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		FileHandler fileHandler = null;
		try {
			fileHandler = new FileHandler("ERP.log");
		} catch (SecurityException | IOException e1) {
			// TODO Auto-generated catch block
			LOGGER.log(Level.SEVERE, "Could not create Log File");
		}
		LOGGER.addHandler(fileHandler);
		LOGGER.addHandler(new StreamHandler(System.out, new SimpleFormatter()));
		LOGGER.setLevel(Level.FINEST);
		try {
			settings.database.PostgreSQLConnection.getConnection();
			quartz.CronScheduler.initialize();
		} catch (SQLException e) {
			
			LOGGER.log(Level.SEVERE, "Could not connect to database at startup");
			// TODO Auto-generated catch block
		//	e.printStackTrace();
			
		}
		
	}
}
