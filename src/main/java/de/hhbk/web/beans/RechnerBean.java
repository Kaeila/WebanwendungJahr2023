package de.hhbk.web.beans;

import de.hhbk.dao.GenericDao;
import de.hhbk.model.Rechner;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


@Named(value = "computer")
@ViewScoped
public class RechnerBean extends BeanTemplate<Rechner, GenericDao<Rechner>>
{
  //-------------------------------------------------------------------------
  //  Constructor(s)
  //-------------------------------------------------------------------------     
    public RechnerBean() { super(new GenericDao<Rechner>(Rechner.class)); } 

    @PostConstruct
    public void init()
    { 
        resetItem(); 
    }

 

}
