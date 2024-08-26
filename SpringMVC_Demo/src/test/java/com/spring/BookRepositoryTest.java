package com.spring;

import com.spring.entities.Book;
import com.spring.repository.BookRepository;
import com.spring.repository.BookRepositoryCustom;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    @Qualifier("bookRepositoryCustomImpl")
    BookRepositoryCustom bookRepositoryCustom;

    @Test
    public void testFindAll() {
        Pageable pageable = PageRequest.of(2, 3);
        String search = "%" + "sach" + "%";
        List<Book> books = bookRepository.findName(search, pageable);
        System.out.println(books);
    }

//    @Test
//    public void test_findByTitleLike(){
//        String search = "%sach%";
//        List<Book> pageBooks = bookRepository.findByTitleLike(search);
//        System.out.println(pageBooks);
//        Assertions.assertNotEquals(pageBooks.size(), 0);
//    }
    @Test
    public void test_custom() {
        long count = bookRepositoryCustom.reportCountBook();
        System.out.println(count);
    }

}
