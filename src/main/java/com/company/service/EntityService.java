package com.company.service;

import com.company.dao.EntityDao;
import com.company.exception.ItemAlreadyAvailableException;
import com.company.exception.ItemNotFoundException;
import com.company.model.Entity;

import java.util.List;

public abstract class EntityService<T extends Entity, U extends EntityDao> {

    abstract U getDao();

    public List<T> getAllItems(String order) {
        return getDao().getList(order);
    }

    public T getItem(Long id) {
        T item = (T) getDao().get(id);
        if (item == null) {
            throw new ItemNotFoundException("Entity with id " + id + " is not available");
        }
        return item;
    }

    public T addItem(T item) {
        checkIdNegative(item.getNumber());

        if (getDao().get(item.getNumber()) != null) {
            throw new ItemAlreadyAvailableException("Entity with id " + item.getNumber() + " is already available");
        }
        return (T) getDao().add(item);
    }

    private void checkIdNegative(long number) {
        if (number <= 0) {
            throw new RuntimeException("Entity id can not be negative or zero");
        }
    }

    public T deleteItem(Long number) {
        checkIdNegative(number);

        return (T) getDao().remove(getItem(number));
    }

    public T getItemMax() {
        return (T) getDao().getMax();
    }

    public T getItemMin() {
        return (T) getDao().getMin();
    }
}
