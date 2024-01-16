package de.hhbk.web.beans;


import de.hhbk.dao.GenericDao;
import de.hhbk.model.Hardware;
import de.hhbk.model.Raum;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value = "rooms") 
@ViewScoped
public class RaumBean extends BeanTemplate<Raum, GenericDao<Raum>>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public RaumBean() { super(new GenericDao<Raum>(Raum.class)); } 

    @PostConstruct
    public void init()
    {   
        resetItem(); 
    }

    
  //-------------------------------------------------------------------------
  //  Link hardware and rooms
  //-------------------------------------------------------------------------     
    public void addHardware(Raum r, Hardware h)
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
    
    
    
    public void removeHardware(Raum r, Hardware h)
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
