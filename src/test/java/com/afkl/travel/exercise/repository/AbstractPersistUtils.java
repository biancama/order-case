package com.afkl.travel.exercise.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

public abstract class AbstractPersistUtils {
    @Autowired
    protected TestEntityManager entityManager;

    protected void commit() {
        entityManager.flush();
        entityManager.clear();
    }

}
