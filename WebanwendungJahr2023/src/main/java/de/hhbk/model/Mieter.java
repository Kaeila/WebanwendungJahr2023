package de.hhbk.model;

import de.hhbk.model.enums.PersonAnrede;
import java.time.LocalDate;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Mieter extends ModelTemplate {
    //-------------------------------------------------------------------------
    //  Var(s)
    //-------------------------------------------------------------------------     

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id = -1L;
    @Enumerated(EnumType.STRING)
    protected PersonAnrede anrede = PersonAnrede.EMPTY;
    protected String vorname = null;
    protected String nachname = null;
    protected String strasse = null;
    protected String plz = null;
    protected String ort = null;
    protected String festnetznummer = null;
    protected String mobilnummer = null;
    protected String email = null;
    protected String iban = null;
    protected String kontoinhaber = null;
    protected String kreditinstitut = null;

    protected LocalDate lieferdatum = LocalDate.now();
    @ManyToOne(fetch = FetchType.EAGER) // cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, 
    @JoinColumn(name = "mietobjekt_id")
    protected Mietobjekt mietobjekt = null;

    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------    
    public Mieter() {
        super();
    }

    public Mieter(String vorname, String nachname, String email) {
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

    public PersonAnrede getAnrede() {
        return anrede;
    }

    public void setAnrede(PersonAnrede anrede) {
        this.anrede = anrede;
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

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getFestnetznummer() {
        return festnetznummer;
    }

    public void setFestnetznummer(String festnetznummer) {
        this.festnetznummer = festnetznummer;
    }

    public String getMobilnummer() {
        return mobilnummer;
    }

    public void setMobilnummer(String mobilnummer) {
        this.mobilnummer = mobilnummer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
