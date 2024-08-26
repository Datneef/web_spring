//package com.spring;
//
//import com.spring.dao.BookDAO;
//import com.spring.entities.Book;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.math.BigDecimal;
//
//@SpringBootTest
//class SpringMvcDemoApplicationTests {
//
//    @Autowired
//    BookDAO bookDAO;
//
//    @Test
//    void contextLoads() {
//        Book book = new Book();
//        book.setTitle("Gia kim thuat");
//        book.setImage("/images/giakimthuat.png");
//        book.setPrice(new BigDecimal(1500.6));
//        bookDAO.create(book);
//    }
//
//}
