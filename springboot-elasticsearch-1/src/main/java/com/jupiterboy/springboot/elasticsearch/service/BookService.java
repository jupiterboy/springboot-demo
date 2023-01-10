package com.jupiterboy.springboot.elasticsearch.service;

import com.jupiterboy.springboot.elasticsearch.entity.Book;
import com.jupiterboy.springboot.elasticsearch.repo.ESBookRepository;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

/**
 * @author geng
 * 2020/12/19
 */
@Slf4j
@Service
public class BookService {
    @Autowired
    private ESBookRepository esBookRepository;

    public void addBook(Book book) {

        try {
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            book.setId(uuid);
            esBookRepository.save(book);
        } catch (Exception e) {
            log.error(String.format("保存ES错误！%s", e.getMessage()));
        }
    }

    public List<Book> searchBook(String title, String author) {
        return esBookRepository.findByTitleOrAuthor(title, author);
    }

    public List<Book> searchBook(String keyword) {
        return esBookRepository.findByTitleLike(keyword);
    }

    public Book findById(String id) {
        return esBookRepository.findById(id).orElse(null);
    }

    public List<Book> listAll() {
        Iterator<Book> iter = esBookRepository.findAll().iterator();
        List<Book> list = new ArrayList<Book>();
        while (iter.hasNext()) {
            list.add(iter.next());
        }
        return list;
    }
}