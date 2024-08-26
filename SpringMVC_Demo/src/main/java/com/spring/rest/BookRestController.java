package com.spring.rest;

import com.spring.entities.Book;
import com.spring.exceptions.NotFoundException;
import com.spring.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

//@Controller
//@ResponseBody
@RestController
@RequestMapping("/rest/")
public class BookRestController {

    @Autowired
    BookRepository bookRepository;

    //@ResponseBody
    @GetMapping(value =  "/books")
    public List<Book> getBooks(){
        List<Book> books = bookRepository.findAll();
        return books;
    }

    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable("id") Integer id){
        return bookRepository.findById(id).orElse(null);
    }

    @DeleteMapping("/books/{id}")
    public Book deleteBookById(@PathVariable("id") Integer id){
        Book book = bookRepository.findById(id).orElse(null);
        if(book != null) {
            bookRepository.delete(book);
        }
        return book;
    }

    @PostMapping(value =  "/books")
    public Book addBook(@RequestBody Book book){
        bookRepository.save(book);
        return book;
    }

    @PutMapping("/books")
    public ResponseEntity<Book> updateBook(@RequestBody Book bookInput){
        Book book = bookRepository.findById(bookInput.getId()).orElse(null);
        if(book != null) {
            bookRepository.save(bookInput);
            return ResponseEntity.ok(book);
        } else {

            //ResponseEntity<Book> responseEntity = ResponseEntity.notFound().build();
            //ResponseEntity<Book> responseEntity = ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            //TODO update
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("message", "Book not found");
//            return new ResponseEntity<>(null, headers, HttpStatus.NOT_FOUND);
            throw new NotFoundException("Book not found");
            // throw new NotFoundException("Book not found", 404);
        }

    }

}