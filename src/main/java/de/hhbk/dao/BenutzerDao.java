package de.hhbk.dao;

import de.hhbk.model.Benutzer;
import java.math.BigInteger;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;


public class BenutzerDao extends GenericDao<Benutzer>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public BenutzerDao() { super(Benutzer.class); }


  //-------------------------------------------------------------------------
  //  Get User by Email and Password via NamedQuery (see Benutzer class)
  //-------------------------------------------------------------------------     
    public Benutzer getUserByEmailAndPassword(String email, String pwd) throws Exception
    {
        return (Benutzer) executeQuery( (session) -> 
        { 
            Query<Benutzer> q = session.createNamedQuery("findByEmailAndPassword");
            q.setParameter("email", email);
            q.setParameter("passwort", pwd);
            return q.uniqueResult();
        });  
    }
 
    
    
}
