package org.homework.repository;

public interface Repository<ENT> {

    ENT load(String key);

    void save(ENT entity);

}
