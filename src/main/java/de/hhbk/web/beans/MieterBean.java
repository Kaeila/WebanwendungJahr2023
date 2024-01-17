package de.hhbk.web.beans;

import de.hhbk.dao.GenericDao;
import de.hhbk.model.Drucker;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value = "printer")
@ViewScoped
public class DruckerBean extends BeanTemplate<Drucker, GenericDao<Drucker>>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public DruckerBean() { super(new GenericDao<Drucker>(Drucker.class)); } 

    @PostConstruct
    public void init()
    { 
        resetItem(); 
    }

 

}
