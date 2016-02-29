package com.demo.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import com.demo.model.Book;

@Controller
public class BookDAO {
	
	private JdbcTemplate jdbcTemplate;  
	  

	@Autowired
	public BookDAO(final JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public int addBook(Book book){  
	    String query="insert into books values ("
	    			+ "'"+ book.getId()+ "',"
	    			+ "'"+ book.getTitle()+ "',"
	    			+ "'"+ book.getAuthor()+ "',"
	    			+ "'"+ book.getPrice()+ "');";  
	    return jdbcTemplate.update(query);  
	} 
	
	public int deleteBook(Book book) {
		String query = "delete from books where id='" + book.getId() + "' ";
		return jdbcTemplate.update(query);
	}
	
	public List<Book> fetchAllBooks() {
		String query = "select * from books";
		return jdbcTemplate.query(query, new BeanPropertyRowMapper(Book.class));
	}
	
}
