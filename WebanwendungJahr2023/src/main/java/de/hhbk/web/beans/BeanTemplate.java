package de.hhbk.web.beans;
 
import de.hhbk.dao.GenericDao;
import de.hhbk.model.ModelTemplate;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;


public abstract class BeanTemplate<T extends ModelTemplate, D extends GenericDao> implements Serializable
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public BeanTemplate(D dao) 
    { 
        super(); 
        this.dao = dao;
    } 

    
  //-------------------------------------------------------------------------
  //  DAO / ItemList
  //-------------------------------------------------------------------------     
    protected D dao = null; 
    
    protected D getDao() { return this.dao; }

    
  //-------------------------------------------------------------------------
  //  Item
  //-------------------------------------------------------------------------     
    protected T item = null; 
    
    public T getItem() { if (item == null) { item = (T) getDao().getNewEntityInstance(); } return item; }

    public void setItem(T item) { this.item = item; } 
    
    public void resetItem() { item = null; }
    

  //-------------------------------------------------------------------------
  //  ItemList
  //-------------------------------------------------------------------------     
    public List<T> getItemList() 
    { 
        try        
        {
            return (dao != null) ? dao.list() : null;
        } 
        catch (Exception e)
        {
            setErrorMessage("Fehler", e.getMessage());
            updateMessageBox();
            return null;
        }        
    }

    public void saveItem() 
    { 
        try        
        {
            if (dao != null) { dao.save(item); }
        } 
        catch (Exception e)
        {
            setErrorMessage("Fehler", e.getMessage());
            updateMessageBox(); 
        } 
    }
  
    public void removeItem(T item) 
    { 
        try        
        {
            if (dao != null) { dao.delete(item); }
        } 
        catch (Exception e)
        {
            setErrorMessage("Fehler", e.getMessage());
            updateMessageBox(); 
        }  
    } 
     

  //-------------------------------------------------------------------------
  //  Else ... 
  //-------------------------------------------------------------------------     
    protected void setMessage(String comonentId, FacesMessage.Severity type, String header, String msg) { FacesContext.getCurrentInstance().addMessage(comonentId, new FacesMessage(type, header, msg)); }    
    
    public void setErrorMessage(String header, String msg) { setMessage(null, FacesMessage.SEVERITY_ERROR, header, msg); }
    
    public void setInfoMessage(String header, String msg) { setMessage(null, FacesMessage.SEVERITY_INFO, header, msg); }
    
    public void updateContentForm() { PrimeFaces.current().ajax().update(":contentForm"); }

    public void updateMessageBox() { PrimeFaces.current().ajax().update(":messageBox"); }
    
    
    
}
