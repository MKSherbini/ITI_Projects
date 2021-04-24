package de.rieckpil.quickstarts.dao;

import net.jodah.typetools.TypeResolver;

import java.util.List;
import java.util.Optional;

public abstract class GenericRepo<T, ID> {
    protected Class<T> persistentClass;
    protected Class<ID> idClass;

    public GenericRepo() {
        Class<?>[] typeArguments = TypeResolver.resolveRawArguments(GenericRepo.class, getClass());
        this.persistentClass = (Class<T>) typeArguments[0];
        this.idClass = (Class<ID>) typeArguments[1];
    }

    public void create(T obj) {
        DatabaseManager.getInstance().runTransaction(session -> session.persist(obj));
    }

    public Optional<T> read(ID id) {
        return DatabaseManager.getInstance().runTransactionWithRet(session -> Optional.ofNullable(session.find(persistentClass, id)));
    }

    public List<T> readAll() {
        return DatabaseManager.getInstance().runTransactionWithRet(session -> (List<T>) session.createQuery("from " + persistentClass.getSimpleName()).getResultList());
    }

    public Optional<T> update(T obj) { // you can never be too careful
        return DatabaseManager.getInstance().runTransactionWithRet(session -> (Optional<T>) Optional.ofNullable(session.merge(obj)));
    }

    public void delete(T obj) {
        DatabaseManager.getInstance().runTransaction(session -> session.remove(obj));
    }
}
