package de.hhbk.web.beans;

import de.hhbk.dao.BenutzerDao;
import de.hhbk.model.Benutzer;
import de.hhbk.model.enums.PersonAnrede;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
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
    private String benutzername;
    private String passwort;
    private boolean isAdmin;

    public boolean isIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
    
    
    public String getBenutzername() { return benutzername; }

    public void setBenutzername(String benutzername) { this.benutzername = benutzername; }

    public String getPasswort() { return passwort; }

    public void setPasswort(String passwort) { this.passwort = passwort; }
           
    public String doLogin() throws Exception 
    {
      BenutzerDao benutzerDao = new BenutzerDao();
      Benutzer benutzer = new Benutzer();
      HttpSession websession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
      this.isAdmin = benutzerDao.isAdmin(benutzername);
       
      if (benutzerDao.isValidCredentials(benutzername, passwort)) {
        websession.setAttribute("benutzername", benutzername);
        websession.setAttribute("MyLoginObject", true);
        return "backend/empty.xhtml?faces-redirect=true"; 
      } 
      else {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Fehler", "Benutzername oder Passwort ist falsch");
        FacesContext.getCurrentInstance().addMessage(null, message);
        return "";
      }
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
