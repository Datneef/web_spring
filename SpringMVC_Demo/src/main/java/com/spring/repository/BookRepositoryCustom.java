package com.spring.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

public interface BookRepositoryCustom {

    List<Object[]> reportBook();
    long reportCountBook();
}
