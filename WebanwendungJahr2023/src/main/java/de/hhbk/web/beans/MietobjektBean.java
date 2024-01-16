package de.hhbk.web.beans;


import de.hhbk.dao.GenericDao;
import de.hhbk.model.Mieter;
import de.hhbk.model.Mietobjekt;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value = "rooms") 
@ViewScoped
public class MietobjektBean extends BeanTemplate<Mietobjekt, GenericDao<Mietobjekt>>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public MietobjektBean() { super(new GenericDao<Mietobjekt>(Mietobjekt.class)); } 

    @PostConstruct
    public void init()
    {   
        resetItem(); 
    }

    
  //-------------------------------------------------------------------------
  //  Link Mietobjekt and Mieter
  //-------------------------------------------------------------------------     
    public void addMieter(Mietobjekt r, Mieter h)
    { 
        try        
        {
            r.addMieter(h); 
            dao.save(r);  
        } 
        catch (Exception e)
        { 
            setErrorMessage("Fehler", e.getMessage());
            updateMessageBox(); 
        } 
    }    
    
    
    
    public void removeMieter(Mietobjekt r, Mieter h)
    { 
         try        
        { 
            r.removeMieter(h); 
            dao.save(r);
            new GenericDao<Mieter>(Mieter.class).save(h); 
        } 
        catch (Exception e)
        { 
            setErrorMessage("Fehler", e.getMessage());
            updateMessageBox(); 
        }
    }    
 

}
