//package com.spring.dao;
//
//import com.spring.entities.Book;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Repository
//public class BookDAOImpl implements BookDAO {
//
//    @Autowired
//    private SessionFactory sessionFactory;
//
//    //@Transactional
//    @Override
//    public Book create(Book book) {
//        try(Session session = sessionFactory.openSession()){
//            session.getTransaction().begin();
//            session.persist(book);
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return book;
//    }
//
//    @Override
//    public Book update(Book book) {
//        try(Session session = sessionFactory.openSession()){
//            session.getTransaction().begin();
//
//            Book bookDB = session.find(Book.class, book.getId());
//            if (bookDB != null) {
//                session.merge(book);
//            }
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return book;
//    }
//
//    @Override
//    public Book delete(Book book) {
//        try(Session session = sessionFactory.openSession()){
//            session.getTransaction().begin();
//
//            session.remove(book);
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return book;
//    }
//
//    @Override
//    public Book readOne(Integer id) {
//        Book book = null;
//        try(Session session = sessionFactory.openSession()){
//            session.getTransaction().begin();
//
//            book = session.find(Book.class, id);
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return book;
//    }
//
//    @Override
//    public List<Book> readAll() {
//        List<Book> books = null;
//
//        try(Session session = sessionFactory.openSession()){
//            session.getTransaction().begin();
//
//            books = session.createQuery("FROM Book").list();
//
//            session.getTransaction().commit();
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//        return books;
//    }
//}
