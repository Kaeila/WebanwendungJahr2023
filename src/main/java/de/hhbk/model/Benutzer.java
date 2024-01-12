package de.hhbk.model;

import de.hhbk.model.enums.PersonAnrede;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


@NamedQueries
({
    @NamedQuery(name="findByEmailAndPassword", query="SELECT u FROM Benutzer u WHERE u.email = :email AND u.passwort = :passwort")     
})


@Entity
public class Benutzer extends Person
{
  //-------------------------------------------------------------------------
  //  Var(s)
  //-------------------------------------------------------------------------     
    protected String benutzername;
    protected String passwort;
    protected boolean isAdmin;


  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public Benutzer() { super(); }

    public Benutzer(String vorname, String nachname, String email, String passwort) 
    { 
        this(PersonAnrede.EMPTY, vorname, nachname, email, passwort);  
    } 

    public Benutzer(PersonAnrede anrede, String vorname, String nachname, String email, String passwort) 
    { 
        super(vorname, nachname, email); 
        this.anrede = anrede;
        this.passwort = passwort;
    } 


  //-------------------------------------------------------------------------
  //  Get / Set
  //-------------------------------------------------------------------------     
    public String getPasswort() { return passwort; }

    public void setPasswort(String passwort) { this.passwort = passwort; }



}
