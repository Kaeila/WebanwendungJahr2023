package de.hhbk.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Mietobjekt extends ModelTemplate {
    //-------------------------------------------------------------------------
    //  Var(s)
    //-------------------------------------------------------------------------     

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id = -1L;
    protected String beschreibung = null;
    protected String strasse = null;
    protected String ort = null;
    protected String plz = null;
    protected Double wohnflaeche = null;
    protected Double quadratmeterpreis = null;
    protected Double nebenkostengesamt = null;
    protected int typId;
    protected int benutzerId;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "mietobjekt", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    protected Set<Mieter> mieterList = null;

    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------     
    public Mietobjekt() {
        super();
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getPlz() {
        return plz;
    }

    public void setPlz(String plz) {
        this.plz = plz;
    }

    public Double getWohnflaeche() {
        return wohnflaeche;
    }

    public void setWohnflaeche(Double wohnflaeche) {
        this.wohnflaeche = wohnflaeche;
    }

    public Double getQuadratmeterpreis() {
        return quadratmeterpreis;
    }

    public void setQuadratmeterpreis(Double quadratmeterpreis) {
        this.quadratmeterpreis = quadratmeterpreis;
    }

    public Double getNebenkostengesamt() {
        return nebenkostengesamt;
    }

    public void setNebenkostengesamt(Double nebenkostengesamt) {
        this.nebenkostengesamt = nebenkostengesamt;
    }

    public int getTypId() {
        return typId;
    }

    public void setTypId(int typId) {
        this.typId = typId;
    }

    public int getBenutzerId() {
        return benutzerId;
    }

    public void setBenutzerId(int benutzerId) {
        this.benutzerId = benutzerId;
    }
    //-------------------------------------------------------------------------
    //  Method(s)
    //-------------------------------------------------------------------------

    public Set<Mieter> getMieterList() {
        if (mieterList == null) {
            mieterList = new HashSet<>();
        }
        return mieterList;
    }

    public void setMieterList(Set<Mieter> mieterList) {
        this.mieterList = mieterList;
    }

    public boolean hasMieter() {
        return (mieterList != null && !mieterList.isEmpty());
    }

    public void addMieter(Mieter h) {
        if (h != null && h.hasId()) {
            getMieterList().add(h);
            h.setMietobjekt(this);
        }
    }

    public void removeMieter(Mieter h) {
        if (hasMieter()) {
            getMieterList().remove(h);
            h.setMietobjekt(null);
        }
    }

    @PreRemove
    public void nullAllMieter() {
        if (hasMieter()) {
            getMieterList().forEach(h -> h.setMietobjekt(null));
        }
    }
}
