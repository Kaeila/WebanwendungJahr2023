package de.hhbk.dao;

import de.hhbk.model.Benutzer;
import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;


public class BenutzerDao extends GenericDao<Benutzer>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    private EntityManager entityManager;
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

    public boolean isValidCredentials(String benutzername, String passwort) throws Exception {
        return (boolean) executeQuery( (session) -> 
        { 
            Query<Benutzer> q = session.createNamedQuery("findByUsernameAndPassword");
            q.setParameter("benutzername", benutzername);
            q.setParameter("passwort", passwort);
            return !q.getResultList().isEmpty();
        }); 
    }   
}
