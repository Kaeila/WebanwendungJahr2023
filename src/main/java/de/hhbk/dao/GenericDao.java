package de.hhbk.dao;

import de.hhbk.model.interfaces.IdInterface;
import de.hhbk.utils.HibernateUtil;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.function.Function;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class GenericDao<T extends IdInterface> implements Serializable {
    //-------------------------------------------------------------------------
    //  Constructor(s)
    //------------------------------------------------------------------------- 

    public GenericDao(Class clazz) {
        this.clazz = clazz;
    }

    //-------------------------------------------------------------------------
    // Class
    //------------------------------------------------------------------------- 
    protected Class<T> clazz = null;

    public void setClazz(Class<T> c) {
        this.clazz = c;
    }

    public Class<T> getClazz() {
        return this.clazz;
    }

    //-------------------------------------------------------------------------
    // Some reflect stuff ...
    //------------------------------------------------------------------------- 
    public T getNewEntityInstance() {
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            return null;
        }
    }

    protected final Class<T> getType() {
        ParameterizedType t = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) t.getActualTypeArguments()[0];
    }

    //-------------------------------------------------------------------------
    //  Execute DB-Operations 
    //------------------------------------------------------------------------- 
    protected Object executeQuery(Function<Session, Object> code) throws Exception {
        Object r = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            r = code.apply(session);
        } finally {
            try {
                session.close();
            } catch (Exception e) {
            }
        }
        return r;
    }

    protected Object executeTransaction(Function<Session, Object> code) throws Exception {
        return executeQuery((session)
                -> {
            Object r = null;
            Transaction tx = null;
            try {
                tx = session.beginTransaction();
                r = code.apply(session);
                session.flush();
                tx.commit();
            } catch (Exception e) {
                try {
                    tx.rollback();
                } catch (Exception ex) {
                }
            }
            return r;
        });
    }

    //-------------------------------------------------------------------------
    //  Load
    //-------------------------------------------------------------------------   
    public T getById(long id) throws Exception {
        return (T) executeTransaction((session) -> {
            return session.get(getClazz(), id);
        });
    }

    //-------------------------------------------------------------------------
    //  Save
    //-------------------------------------------------------------------------   
    public long save(T o) throws Exception {
        return (long) executeTransaction((session)
                -> {
            if (!o.hasId()) {
                session.save(o);
            } else {
                session.merge(o);
            }
            return o.getId();
        });
    }

    //-------------------------------------------------------------------------
    //  Merge
    //-------------------------------------------------------------------------   
    public T merge(T o) throws Exception {
        return (T) executeTransaction((session) -> {
            return session.merge(o);
        });
    }

    //-------------------------------------------------------------------------
    //  Evict
    //-------------------------------------------------------------------------   
    public void evict(T o) throws Exception {
        executeQuery((session) -> {
            session.evict(o);
            return null;
        });
    }

    //-------------------------------------------------------------------------
    //  Refresh
    //-------------------------------------------------------------------------   
    public void clear() throws Exception {
        executeQuery((session) -> {
            session.clear();
            return null;
        });
    }

    //-------------------------------------------------------------------------
    //  Delete
    //-------------------------------------------------------------------------   
    public boolean delete(T o) throws Exception {
        return (boolean) executeTransaction((session) -> {
            try {
                session.delete(session.get(getClazz(), o.getId()));
                return true;
            } catch (Exception ex) {
                return false;
            }
        });
    }

    public int deleteAll() throws Exception {
        return (int) executeTransaction((session)
                -> {
            Query query = session.createQuery("delete from " + getClazz().getName());
            return query.executeUpdate();
        });
    }

    //-------------------------------------------------------------------------
    //  List
    //------------------------------------------------------------------------- 
    public List<T> list(int first, int limit) throws Exception {
        return (List<T>) executeQuery((session)
                -> {
            session.clear();
            Query query = session.createQuery("from " + getClazz().getName());
            if (first > 0) {
                query.setFirstResult(first);
            }
            if (limit > 0) {
                query.setMaxResults(limit);
            }
            return query.getResultList();
        });
    }

    public List<T> list() throws Exception {
        return list(-1, -1);
    }
}
