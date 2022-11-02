package org.homework.repository;

import java.util.Collection;

public interface Repository <ENT> {
    
    ENT load(Long id);
    ENT load(String key);
    
    void save(ENT entity);
    
    Collection<ENT> getAll();
    
}
