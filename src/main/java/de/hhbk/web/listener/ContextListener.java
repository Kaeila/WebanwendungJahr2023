package de.hhbk.web.listener;

import de.hhbk.utils.HibernateUtil;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("---------------------------------------------");
        System.out.println(">>> Application-Start");
        System.out.println("---------------------------------------------");
        System.out.println("Database --> " + HibernateUtil.getDbVersion());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("---------------------------------------------");
        System.out.println(">>> Application-Shutdown");
        System.out.println("---------------------------------------------");
    }
}
