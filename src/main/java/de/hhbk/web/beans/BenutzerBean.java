package de.hhbk.web.beans;

import de.hhbk.dao.BenutzerDao;
import de.hhbk.model.Benutzer;
import de.hhbk.model.enums.PersonAnrede;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;


@Named(value = "benutzer") 
@SessionScoped
public class BenutzerBean extends BeanTemplate<Benutzer, BenutzerDao>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public BenutzerBean() { super(new BenutzerDao()); } 

    @PostConstruct
    public void init()
    { 
        resetItem(); 
    }

  //-------------------------------------------------------------------------
  //  Login / Logout
  //-------------------------------------------------------------------------     
    protected String benutzername = null;
    protected String passwort = null;

    public String getBenutzername() { return benutzername; }

    public void setBenutzername(String benutzername) { this.benutzername = benutzername; }

    public String getPasswort() { return passwort; }

    public void setPasswort(String passwort) { this.passwort = passwort; }
           
    public String doLogin() 
    {  
        /*
            CHECK USER CREDENTIALS HERE !!!
            If correct, then store the MyLoginObject in the session --> websession.setAttribute("MyLoginObject", true) 
            The de.hhbk.web.filter.BackendAreaFilter will check the MyLoginObject for the backend folder path.
            Remove the MyLoginObject on logout (see doLogout()). 
            Session timeout (see web.xml) will automatically delete all session objects.
        */  
        HttpSession websession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true); 
        websession.setAttribute("MyLoginObject", true); 
        // The return parameter is the navigation path to the next website.
        return "backend/empty.xhtml?faces-redirect=true"; 
    } 
    
    public String doLogout() 
    {  
        HttpSession websession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true); 
        websession.removeAttribute("MyLoginObject"); 
        return "/login.xhtml?faces-redirect=true";
    }  
    
    
  //-------------------------------------------------------------------------
  //  Get / Set
  //-------------------------------------------------------------------------     
    public List getAnredeOptionen() { return Arrays.asList(PersonAnrede.values()); }
    
 
    
}
