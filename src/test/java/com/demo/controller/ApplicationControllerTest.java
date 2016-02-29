package com.demo.controller;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.BookDAO;
import com.demo.model.Book;


@RunWith(MockitoJUnitRunner.class)
public class ApplicationControllerTest {

    @Mock
    BookDAO bookDAO;
    
    @Mock
    HttpServletRequest request;
    
    @Mock
    HttpSession httpSession;

    private ApplicationController applicationController;
    private List<Book> books;
    private Book dummyBook;
    private MockHttpServletRequest validRequest;
    private MockHttpServletRequest inValidRequest;
    
    @Before
    public void setUp() throws Exception {
    	applicationController = new ApplicationController(bookDAO);
    	dummyBook= new Book(1, "book under test", "tdd tester", 100);
    	books = new ArrayList<Book>();
    	books.add(dummyBook);
        when( bookDAO.fetchAllBooks()).thenReturn(books);
        when( request.getSession()).thenReturn(httpSession);
        validRequest = new MockHttpServletRequest();
        inValidRequest = new MockHttpServletRequest();
        validRequest.addParameter("name", "admin");
        validRequest.addParameter("password", "admin");
        inValidRequest.addParameter("name", "admin2");
        inValidRequest.addParameter("password", "admin2");
    }

    @Test
	public void shouldRenderWelcomePage() throws Exception {
		ModelMap model = new ModelMap();
		String redirectedPage = applicationController.renderWelcomePage(model);
        assertThat(model.get("welcomeMessage"), notNullValue());
        assertEquals(model.get("welcomeMessage") , "Following are the list of books :");
        assertThat(model.get("books"), notNullValue());
        assertEquals(model.get("books") , books);
        assertEquals("index" , redirectedPage);
	}

	@Test
	public void shouldRedirectToUpdatePageWhenSessionIsSet() throws Exception {
		when( httpSession.getAttribute("loggedIn")).thenReturn("true");
		ModelAndView logInModelAndView = applicationController.login(request);
		assertEquals(logInModelAndView.getViewName() , "update");
	}

	@Test
	public void shouldRedirectToLogInPageWhenSessionIsSet() throws Exception {
		when( httpSession.getAttribute("loggedIn")).thenReturn("false");
		ModelAndView logInModelAndView = applicationController.login(request);
		assertEquals(logInModelAndView.getViewName() , "login");
	}

	@Test
	public void shouldRedirectToUpdatePageIfValidUser() throws Exception {
		ModelAndView validatedModelAndView = applicationController.validate(validRequest);
		ModelMap model = validatedModelAndView.getModelMap();
		assertEquals(validatedModelAndView.getViewName() , "update");
        assertThat(model.get("logInMessage"), notNullValue());
        assertEquals(model.get("logInMessage") , "Successfully logged in.");
        assertEquals(validRequest.getSession().getAttribute("loggedIn") , "true");
	}

	@Test
	public void shouldRedirectToLoginPageIfInValidUser() throws Exception {
		ModelAndView validatedModelAndView = applicationController.validate(inValidRequest);
		ModelMap model = validatedModelAndView.getModelMap();
		assertEquals(validatedModelAndView.getViewName() , "login");
        assertThat(model.get("logInMessage"), notNullValue());
        assertEquals(model.get("logInMessage") , "Username or password is wrong.");
        assertNull(validRequest.getSession().getAttribute("loggedIn"));
	}

	@Test
	public void shouldInsertBook() throws Exception {
        validRequest.addParameter("id", "1");
        validRequest.addParameter("title", "book under test");
        validRequest.addParameter("author", "tdd tester");
        validRequest.addParameter("price", "100");
		ModelAndView addedBookModelAndView = applicationController.insertBook(validRequest);
		assertEquals(addedBookModelAndView.getViewName() , "index");
		ModelMap model = addedBookModelAndView.getModelMap();
		assertEquals(model.get("welcomeMessage") , "Following are the updated list of books:");
		assertEquals(model.get("books") , books);
	}

}
