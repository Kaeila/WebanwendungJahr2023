package de.hhbk.model;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;


@Entity
public class Mietobjekt extends ModelTemplate
{
  //-------------------------------------------------------------------------
  //  Var(s)
  //-------------------------------------------------------------------------     
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
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
    @OneToMany(fetch = FetchType.EAGER, mappedBy="mietobjekt", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    protected Set<Hardware> hardwareList = null;   


  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     

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
 
    

    public Set<Hardware> getHardwareList() 
    { 
        if (hardwareList == null) { hardwareList = new HashSet<>(); }
        return hardwareList; 
    }

    public void setHardwareList(Set<Hardware> hardwareList) { this.hardwareList = hardwareList; }
 
    
  //-------------------------------------------------------------------------
  //  Method(s)
  //-------------------------------------------------------------------------
    public boolean hasHardware() { return (hardwareList != null && !hardwareList.isEmpty()); }
    
    public void addHardware(Hardware h)
    {
        if (h != null && h.hasId())
        {
            getHardwareList().add(h);
            h.setRaum(this);
        }
    }
 
    public void removeHardware(Hardware h)
    {
        if (hasHardware())
        {
            getHardwareList().remove(h);
            h.setRaum(null);
        }
    }
    
    @PreRemove
    public void nullAllHardware()
    {
        if (hasHardware())
        {
            getHardwareList().forEach(h -> h.setRaum(null));
        }
    }
    
    
}
