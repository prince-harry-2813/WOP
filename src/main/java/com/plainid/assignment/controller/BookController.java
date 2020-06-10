package com.plainid.assignment.controller;


import com.plainid.assignment.converter.mapper.BookRawMapper;
import com.plainid.assignment.dao.Book;
import com.plainid.assignment.dao.BookList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/books")
public class BookController {

    private

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/list")
    public BookList getBook() {
        List<Book> rows = jdbcTemplate.query("SELECT * from BOOK",new BookRawMapper());
        BookList bookList = new BookList();
        bookList.setBooks(rows);
        return bookList;
    }
}
