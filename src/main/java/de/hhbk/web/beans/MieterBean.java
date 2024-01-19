package de.hhbk.web.beans;

import de.hhbk.dao.GenericDao;
import de.hhbk.model.Mieter;
import de.hhbk.model.enums.PersonAnrede;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named(value = "mieter")
@ViewScoped
public class MieterBean extends BeanTemplate<Mieter, GenericDao<Mieter>> {
    //-------------------------------------------------------------------------
    //  Constructor(s)
    //-------------------------------------------------------------------------     

    public MieterBean() {
        super(new GenericDao<Mieter>(Mieter.class));
    }

    @PostConstruct
    public void init() {
        resetItem();
    }
    //-------------------------------------------------------------------------
    //  Get / Set
    //-------------------------------------------------------------------------     

    public List getAnredeOptionen() {
        return Arrays.asList(PersonAnrede.values());
    }
}
