package de.hhbk.model;

import de.hhbk.model.enums.PersonAnrede;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Person extends ModelTemplate {
    //-------------------------------------------------------------------------
    //  Var(s)
    //-------------------------------------------------------------------------     

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id = -1L;
    protected String vorname = null;
    protected String nachname = null;
    @Enumerated(EnumType.STRING)
    protected PersonAnrede anrede = PersonAnrede.EMPTY;
    protected Adresse adresse = null;
    protected String telefon = null;
    protected String email = null;

    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------    
    public Person() {
        super();
    }

    public Person(String vorname, String nachname, String email) {
        super();
        this.vorname = vorname;
        this.nachname = nachname;
        this.email = email;
    }

    //-------------------------------------------------------------------------
    //  Get / Set
    //-------------------------------------------------------------------------     
    @Override
    public long getId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public void setNachname(String nachname) {
        this.nachname = nachname;
    }

    public PersonAnrede getAnrede() {
        return anrede;
    }

    public void setAnrede(PersonAnrede anrede) {
        this.anrede = anrede;
    }

    public String getGeschlecht() {
        return getAnrede().getGeschlecht();
    }

    public boolean isWeiblich() {
        return (anrede == PersonAnrede.MRS);
    }

    public boolean isMaennlich() {
        return (anrede == PersonAnrede.MR);
    }

    public Adresse getAdresse() {
        if (adresse == null) {
            adresse = new Adresse();
        }
        return adresse;
    }

    public void setAdresse(Adresse adresse) {
        this.adresse = adresse;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
