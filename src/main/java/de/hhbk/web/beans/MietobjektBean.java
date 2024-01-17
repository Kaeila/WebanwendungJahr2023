package de.hhbk.web.beans;


import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import de.hhbk.dao.GenericDao;
import de.hhbk.model.Hardware;
import de.hhbk.model.Mietobjekt;


@Named(value = "accommodation") 
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
  //  Link hardware and rooms
  //-------------------------------------------------------------------------     
    public void addHardware(Mietobjekt r, Hardware h)
    { 
        try        
        {
            r.addHardware(h); 
            dao.save(r);  
        } 
        catch (Exception e)
        { 
            setErrorMessage("Fehler", e.getMessage());
            updateMessageBox(); 
        } 
    }    
    
    
    
    public void removeHardware(Mietobjekt r, Hardware h)
    { 
         try        
        { 
            r.removeHardware(h); 
            dao.save(r);
            new GenericDao<Hardware>(Hardware.class).save(h); 
        } 
        catch (Exception e)
        { 
            setErrorMessage("Fehler", e.getMessage());
            updateMessageBox(); 
        }
    }    
 

}
