package de.hhbk.model;

import de.hhbk.model.enums.PersonAnrede;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@NamedQueries({
    @NamedQuery(name = "findByUsernameAndPassword", query = "SELECT b.benutzername, b.passwort FROM Benutzer b WHERE b.benutzername = :benutzername AND b.passwort = :passwort"),
    @NamedQuery(name = "checkIfAdmin", query = "SELECT 1 FROM Benutzer b WHERE b.benutzername = :benutzername and b.isAdmin = 1")
})

@Entity
public class Benutzer extends Person {

    //-------------------------------------------------------------------------
    //  Var(s)
    //-------------------------------------------------------------------------     
    public Benutzer() {
        super();
    }
    protected String benutzername = null;
    protected String passwort = null;
    protected boolean isAdmin;

    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------     
    public Benutzer(String vorname, String nachname, String email, String passwort) {
        this(PersonAnrede.EMPTY, vorname, nachname, email, passwort);
    }

    public Benutzer(PersonAnrede anrede, String vorname, String nachname, String email, String passwort) {
        super(vorname, nachname, email);
        this.anrede = anrede;
        this.passwort = passwort;
    }

    //-------------------------------------------------------------------------
    //  Get / Set
    //-------------------------------------------------------------------------     
    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }
}
