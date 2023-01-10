package com.jupiterboy.springboot.elasticsearch.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;
import java.util.UUID;


/**
 * @author geng
 * 2020/12/18
 */
@Data
@Document(indexName = "book",createIndex = true)
public class Book {
    @Id
    @Field(type = FieldType.Text)
    private String id = UUID.randomUUID().toString().replaceAll("-", "");
    @Field(analyzer="ik_max_word")
    private String title;
    @Field(analyzer="ik_max_word")
    private String author;
    @Field(type = FieldType.Double)
    private Double price;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date createTime = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Field(type = FieldType.Date,format = DateFormat.basic_date_time)
    private Date updateTime = new Date();
}