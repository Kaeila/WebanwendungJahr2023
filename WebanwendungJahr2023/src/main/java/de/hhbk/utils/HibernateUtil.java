package de.hhbk.utils;
 
import de.hhbk.model.Adresse;
import de.hhbk.model.Benutzer;
import de.hhbk.model.Drucker;
import de.hhbk.model.Hardware;
import de.hhbk.model.Person;
import de.hhbk.model.Raum;
import de.hhbk.model.Rechner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

 
public class HibernateUtil 
{
  //-------------------------------------------------------------------------
  //  Hibernate-SessionFactory
  //-------------------------------------------------------------------------     
    private static SessionFactory sessionFactory = null;

    public static SessionFactory getSessionFactory()
    {
        if (sessionFactory == null) 
        {
            sessionFactory = new Configuration()
                    .setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect")
                    .setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver")
                    .setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/hhbk?zeroDateTimeBehavior=convertToNull")
                    .setProperty("hibernate.connection.username", "root")
                    .setProperty("hibernate.hbm2ddl.auto", "update")
                    .setProperty("hibernate.enable_lazy_load_no_trans", "true")
                    .setProperty("show_sql", "true")
                    .setProperty("format_sql", "true")
                    .setProperty("use_sql_comments", "true")
                    .addAnnotatedClass(Adresse.class)
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Benutzer.class)
                    .addAnnotatedClass(Hardware.class)
                    .addAnnotatedClass(Rechner.class)
                    .addAnnotatedClass(Drucker.class)
                    .addAnnotatedClass(Raum.class)
                    .buildSessionFactory();
        }
        return sessionFactory; 
    }
    

  //-------------------------------------------------------------------------
  //  DB-Version
  //-------------------------------------------------------------------------  
    public static String getDbVersion()
    { 
        String r = "";
        Session session = null;
        try
        {
            session = getSessionFactory().openSession();
            Query query = session.createNativeQuery("SELECT Version()");
            r = (String) query.getSingleResult(); 
        }
        catch (Exception e) { r = null; }
        finally  
        { 
            try { session.flush(); } catch(Exception e) { }
            try { session.close(); } catch(Exception e) { }
        }
        return r;
    } 
    
    
    
}
