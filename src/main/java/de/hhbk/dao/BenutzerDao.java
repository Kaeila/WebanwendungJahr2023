package de.hhbk.dao;

import org.hibernate.query.Query;

import de.hhbk.model.Benutzer;


public class BenutzerDao extends GenericDao<Benutzer>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------
    public BenutzerDao() { super(Benutzer.class); }


  //-------------------------------------------------------------------------
  //  Get User by Email and Password via NamedQuery (see Benutzer class)
  //-------------------------------------------------------------------------     
    /*public Benutzer getUserByEmailAndPassword(String email, String pwd) throws Exception
    {
        return (Benutzer) executeQuery( (session) -> 
        { 
            Query<Benutzer> q = session.createNamedQuery("findByEmailAndPassword");
            q.setParameter("email", email);
            q.setParameter("passwort", pwd);
            return q.uniqueResult();
        });  
    }*/
    
    public boolean isValidCredentials(String benutzername, String passwort) throws Exception {
        return (boolean) executeQuery( (session) -> 
        { 
            Query<Benutzer> q = session.createNamedQuery("findByUsernameAndPassword");
            q.setParameter("benutzername", benutzername);
            q.setParameter("passwort", passwort);
            return !q.getResultList().isEmpty();
        }); 
    }   

       public boolean isAdmin(String benutzername) throws Exception {
         return (boolean) executeQuery( (session) -> 
        { 
            Query<Benutzer> q = session.createNamedQuery("checkIfAdmin");
            q.setParameter("benutzername", benutzername);
            return !q.getResultList().isEmpty();
        }); 
    }
}
