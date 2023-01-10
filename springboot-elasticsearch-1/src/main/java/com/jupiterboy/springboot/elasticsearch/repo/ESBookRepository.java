package com.jupiterboy.springboot.elasticsearch.repo;

import com.jupiterboy.springboot.elasticsearch.entity.Book;
import org.elasticsearch.search.SearchHits;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ES Book repository
 *
 * @author geng
 * 2020/12/19
 */
@Repository
public interface ESBookRepository extends ElasticsearchRepository<Book, String> {

    List<Book> findByTitleOrAuthor(String title, String author);

//    @Highlight(fields = {
//            @HighlightField(name = "title"),
//            @HighlightField(name = "author")
//    })
    @Query("{\"match\":{\"title\":\"?0\"}}")
    SearchHits find(String keyword);

    @Query("{\"match\":{\"title\":\"?0\"}}")
    List<Book> findByTitleLike(String title);

}