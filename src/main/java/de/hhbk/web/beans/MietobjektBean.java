package de.hhbk.web.beans;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import de.hhbk.dao.GenericDao;
import de.hhbk.model.Mietobjekt;
import de.hhbk.model.enums.Typ;
import java.util.Arrays;
import java.util.List;

@Named(value = "mietobjekt")
@ViewScoped
public class MietobjektBean extends BeanTemplate<Mietobjekt, GenericDao<Mietobjekt>> {
    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------     

    public MietobjektBean() {
        super(new GenericDao<Mietobjekt>(Mietobjekt.class));
    }

    @PostConstruct
    public void init() {
        resetItem();
    }
    public List getTypOptionen() {
        return Arrays.asList(Typ.values());
    }
}