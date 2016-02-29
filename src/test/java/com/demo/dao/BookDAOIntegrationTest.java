package com.demo.dao;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/test-application-context.xml" })
public class BookDAOIntegrationTest {
	
	@Resource
	private JdbcTemplate jdbcTemplate;
	
	private BookDAO bookDAO;
	private Book dummyBook;
	
    @Before
    public void setUp() throws Exception {
    	bookDAO = new BookDAO(jdbcTemplate);
    	dummyBook= new Book(16, "book under test", "tdd tester", 1000);
    }

    @After
    public void tearDown() throws Exception {
    	bookDAO.deleteBook(dummyBook);
    }

    @Test
	public void shouldAddBook() throws Exception {
    	bookDAO.addBook(dummyBook);
    	List<Book> listOfBooks = bookDAO.fetchAllBooks();
    	List<Integer> bookIds = new ArrayList<Integer>();
    	for(Book book : listOfBooks)
    	{
    		bookIds.add(book.getId());
    	}
    	assertTrue(bookIds.contains(dummyBook.getId()));
	}
   

}
