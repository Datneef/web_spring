package com.spring.repository;

import com.spring.entities.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, BookRepositoryCustom {

    Page<Book> findAll(Pageable pageable);

    Page<Book> findByTitleLike(String title, Pageable pageable);

//    @Query(value = "SELECT e FROM Book e", countQuery = "SELECT COUNT(e) FROM Book e")
//    Page<Book> readAll(Pageable pageable);

     @Query( value = "SELECT e FROM Book e where e.title LIKE :titleParam")
     List<Book> findName(@Param("titleParam") String name, Pageable pageable);

    @Transactional
    @Modifying
    @Query( value = "DELETE Book e where e.title LIKE :titleParam")
    List<Book> deleteTitle(String title);
}
