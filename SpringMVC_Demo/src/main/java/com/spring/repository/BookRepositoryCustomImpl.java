package com.spring.repository;

import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Annotation;
import java.util.List;

@Repository
public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<Object[]> reportBook() {
        return List.of();
    }

    @Transactional
    @Override
    public long reportCountBook() {
        String sql = "SELECT COUNT(*) as total FROM Book";
        long count = 0;
        count = (Long) entityManager.createQuery(sql).getSingleResult();
        return count;
    }

}
