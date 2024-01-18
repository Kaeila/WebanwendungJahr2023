package de.hhbk.model;

import de.hhbk.model.enums.PersonAnrede;
import java.time.LocalDate;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Mieter extends Person {
    //-------------------------------------------------------------------------
    //  Var(s)
    //-------------------------------------------------------------------------     

    protected String mobilnummer = null;
    protected String iban = null;
    protected String kontoinhaber = null;
    protected String kreditinstitut = null;
    @ManyToOne(fetch = FetchType.EAGER) // cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, 
    @JoinColumn(name = "mietobjekt_id")
    protected Mietobjekt mietobjekt = null;

    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------    
   public Mieter(String vorname, String nachname, String email) 
    { 
        this(PersonAnrede.EMPTY, vorname, nachname, email);  
    } 

    public Mieter(PersonAnrede anrede, String vorname, String nachname, String email) 
    { 
        super(vorname, nachname, email); 
        this.anrede = anrede;
    } 

    public String getMobilnummer() {
        return mobilnummer;
    }

    public void setMobilnummer(String mobilnummer) {
        this.mobilnummer = mobilnummer;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getKontoinhaber() {
        return kontoinhaber;
    }

    public void setKontoinhaber(String kontoinhaber) {
        this.kontoinhaber = kontoinhaber;
    }

    public String getKreditinstitut() {
        return kreditinstitut;
    }

    public void setKreditinstitut(String kreditinstitut) {
        this.kreditinstitut = kreditinstitut;
    }

    public Mietobjekt getMietobjekt() {
        return mietobjekt;
    }

    public void setMietobjekt(Mietobjekt mietobjekt) {
        this.mietobjekt = mietobjekt;
    }

    public boolean hasMietobjekt() {
        return this.mietobjekt != null;
    }
}
