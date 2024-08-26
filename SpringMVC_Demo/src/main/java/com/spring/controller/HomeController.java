package com.spring.controller;

import com.spring.entities.Book;
import com.spring.model.ProductDTO;
import com.spring.repository.BookRepository;
import com.spring.service.ProductService;
import com.spring.valiation.CreateGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private BookRepository bookRepository;

    final private ProductService productService;


    @Autowired
    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @Transactional
    @RequestMapping(value = "/admin/books", method = {RequestMethod.POST, RequestMethod.GET})
    public String listBook(Model model,
                           @RequestParam(value = "page", defaultValue = "1") Integer pageNumber,
                           @RequestParam(value = "keyword", required = false) String keyword
    ) {

        int pageSizeDefault = 3;
        //Sort sort = Sort.by(Sort.Direction.ASC, "title");
        Sort sort = Sort.by(Sort.Order.desc("price"), Sort.Order.asc("title"));
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSizeDefault, sort);

        Page<Book> page = null;
        if (keyword == null || keyword.isBlank()) {
            page = bookRepository.findAll(pageable);
        } else {
//            int a = 10;
//            int b= 0;
//            int c= a/b;
            page = bookRepository.findByTitleLike("%" + keyword + "%", pageable);
        }

        // calculate list page numbers
        int totalPage = page.getTotalPages();
        List<Integer> pageNums = new ArrayList<>();
        for (int i = 1; i <= totalPage; i++) {
            pageNums.add(i);
        }

        model.addAttribute("pageNums", pageNums);
        model.addAttribute("page", page);
        return "admin/book/list";
    }

    @GetMapping("/admin/books/create")
    public String showUiCreate(Model model) {

        model.addAttribute("book", new Book());
        return "admin/book/create";
    }

    @PostMapping("/admin/books/create")
    public String saveBook(@Validated(CreateGroup.class)
                           @ModelAttribute(name = "book")
                           Book book,
                           BindingResult bindingResult,
                           RedirectAttributes attributes
    ) {
        if(bindingResult.hasErrors()) {
            return "admin/book/create";
        }

        bookRepository.save(book);

        attributes.addFlashAttribute("message", "Create new book successfully!");
        return "redirect:/admin/books";
    }

    @GetMapping(value = {"/home", "/"})
    public String home(Model model) {
        String msg = "Hello Spring MVC Demo";
        model.addAttribute("message", msg);
        return "index";
    }

    @GetMapping("/product")
    public ModelAndView product() {
        ModelAndView m = new ModelAndView("product");
        //TODO remove fake data
        List<ProductDTO> products = productService.getAll();
        m.addObject("productList", products);
        return m;
    }

    @GetMapping("/admin/books/delete/{id}")
    public RedirectView deleteBook(@PathVariable("id") Integer idBooks, RedirectAttributes attributes) {
        RedirectView redirectView = new RedirectView();

        Book book = bookRepository.findById(idBooks).orElse(null);
        if(book != null) {
            bookRepository.delete(book);
        }

//        Properties properties = new Properties();
//        properties.put("message", "Delete book successfully!");
//        redirectView.setAttributes(properties);
        attributes.addFlashAttribute("message", "Delete book successfully!");

        redirectView.setUrl("/admin/books");
        return redirectView;
    }


    @GetMapping("/admin/books/edit")
    public String editBook(
            @RequestParam("id") Integer idBook,
            Model model
    ){
       Book bookDB = bookRepository.findById(idBook).orElse(null);
       model.addAttribute("book", bookDB);
       return "admin/book/edit";
    }

    @PostMapping("/admin/books/update")
    public String updateBook(@ModelAttribute("book") Book book, RedirectAttributes attributes) {

        bookRepository.save(book);

        attributes.addFlashAttribute("message", "Update book successfully!");
        return "redirect:/admin/books";
    }

}
