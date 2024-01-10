package de.hhbk.model.interfaces;
  
 
public interface IdInterface
{ 
    
    public long getId();
    
    public void setId(long id);
    
    default public boolean hasId() { return (getId() > 0); }; 
    
    
    
}
