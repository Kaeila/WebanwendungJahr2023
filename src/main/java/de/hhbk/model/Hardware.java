package de.hhbk.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Hardware extends ModelTemplate
{
  //-------------------------------------------------------------------------
  //  Var(s)
  //-------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    protected long id = -1L;
    protected String seriennummer = null;
    protected String modell = null;
    protected String hersteller = null;
    protected String status = null;
    protected int herstellergarantie = 0;
    protected LocalDate lieferdatum = LocalDate.now();
    @ManyToOne(fetch=FetchType.EAGER) // cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH}, 
    @JoinColumn(name="raum_id")
    protected Mietobjekt mietobjekt = null;

   
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public Hardware() { super(); }
 

  //-------------------------------------------------------------------------
  //  Get / Set
  //-------------------------------------------------------------------------  
    @Override
    public long getId() { return this.id; }

    @Override
    public void setId(long id) { this.id = id; }
 
    public String getSeriennummer() { return seriennummer; }

    public void setSeriennummer(String seriennummer) { this.seriennummer = seriennummer; }

    public String getModell() { return modell; }

    public void setModell(String modell) { this.modell = modell; }

    public String getHersteller() { return hersteller; }

    public void setHersteller(String hersteller) { this.hersteller = hersteller; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public int getHerstellergarantie() { return herstellergarantie; }

    public void setHerstellergarantie(int herstellergarantie) { this.herstellergarantie = herstellergarantie; }

    public LocalDate getLieferdatum() { return lieferdatum; }

    public void setLieferdatum(LocalDate lieferdatum) { this.lieferdatum = lieferdatum; }

    public Raum getRaum() { return raum; }

    public void setRaum(Raum raum) { this.raum = raum; }

    public boolean hasRaum() { return this.raum != null; }
    
    
  //-------------------------------------------------------------------------
  //  Method(s)
  //-------------------------------------------------------------------------   
    public LocalDate berechneGarantieende() { return this.lieferdatum.plusMonths(this.herstellergarantie); }
    
    

}
