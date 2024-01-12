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

    public boolean isValidCredentials(String benutzername, String passwort) {
        TypedQuery<Benutzer> query = entityManager.createQuery(
            "SELECT * FROM Benutzer b WHERE b.Benutzername = :benutzername AND b.Passwort = :passwort", Benutzer.class);
        query.setParameter("benutzername", benutzername);
        query.setParameter("passwort", passwort);

        return !query.getResultList().isEmpty();
    }

       public boolean isAdmin(String benutzername) {
        TypedQuery<Boolean> query = entityManager.createQuery(
                "SELECT b.isAdmin FROM Benutzer b WHERE b.Benutzername = :benutzername", Boolean.class);
        query.setParameter("benutzername", benutzername);

        try {
            return query.getSingleResult();
        } catch (NoResultException e) {
            return false;
        }
    }


 
    
    
}
