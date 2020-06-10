package com.plainid.assignment.converter.mapper;

import com.plainid.assignment.dao.Book;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BookRawMapper implements RowMapper<Book> {
    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();
        book.setId(rs.getInt("ID"));
        book.setBookName(rs.getString("BOOK_NAME"));
        book.setBookAuthor(rs.getString("BOOK_AUTHOR"));
        book.setPrice(rs.getFloat("PRICE"));

        return book;

    }
}
