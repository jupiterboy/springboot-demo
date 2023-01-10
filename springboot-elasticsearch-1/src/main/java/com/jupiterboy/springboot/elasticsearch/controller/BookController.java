package com.jupiterboy.springboot.elasticsearch.controller;

import com.jupiterboy.springboot.elasticsearch.entity.Book;
import com.jupiterboy.springboot.elasticsearch.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.matchPhraseQuery;

/**
 * @author geng
 * 2020/12/20
 */
@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    ElasticsearchTemplate template;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/book")
    public Map<String,String> addBook(){
        Book book = new Book();
        book.setAuthor("Author" +  System.currentTimeMillis());
        book.setTitle("Title" + System.currentTimeMillis());
        book.setPrice(System.currentTimeMillis()*1.0);
        bookService.addBook(book);
        Map<String,String> map = new HashMap<>();
        map.put("msg","ok");
        return map;
    }

    @GetMapping("/book/search/{key}")
    public List<Book> search(@PathVariable String key){
        return bookService.searchBook(key);
    }

    @GetMapping("/book/get/{id}")
    public Book get(@PathVariable String id){
        return bookService.findById(id);
    }

    @GetMapping("/book/listAll")
    public List<Book> listAll() {
        return bookService.listAll();
    }

}