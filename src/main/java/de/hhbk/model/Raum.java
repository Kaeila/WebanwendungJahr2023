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
public class Raum extends ModelTemplate
{
  //-------------------------------------------------------------------------
  //  Var(s)
  //-------------------------------------------------------------------------     
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected long id = -1L; 
    protected String bezeichnung = null;
    protected String typ = null;
    @OneToMany(fetch = FetchType.EAGER, mappedBy="raum", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    protected Set<Hardware> hardwareList = null;   


  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public Raum() { super(); }
 
    public Raum(String bezeichnung, String typ) 
    { 
        super(); 
        this.bezeichnung = bezeichnung;
        this.typ = typ;
    }
 

  //-------------------------------------------------------------------------
  //  Get / Set
  //-------------------------------------------------------------------------     
    @Override
    public long getId() { return this.id; }

    @Override
    public void setId(long id) { this.id = id; }
 
    public String getBezeichnung() { return bezeichnung; }

    public void setBezeichnung(String bezeichnung) { this.bezeichnung = bezeichnung; }

    public String getTyp() { return typ; }

    public void setTyp(String typ) { this.typ = typ; }

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
