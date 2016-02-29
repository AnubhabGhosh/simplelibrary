package com.demo.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.demo.dao.BookDAO;
import com.demo.model.Book;

@Controller
@RequestMapping("/")
public class ApplicationController {

	private BookDAO bookDAO;
	
	private static final String LOGIN_PAGE = "login";
	private static final String WELCOME_PAGE = "index";
	private static final String UPDATION_PAGE = "update";

	@Autowired
	public ApplicationController(final BookDAO bookDAO) {
		this.bookDAO = bookDAO;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String renderWelcomePage(ModelMap model) {
		model.addAttribute("welcomeMessage", "Following are the list of books :");
		model.addAttribute("books", bookDAO.fetchAllBooks());
		return WELCOME_PAGE;
	}

	@RequestMapping(value="/login", method = RequestMethod.GET)
	public ModelAndView login(HttpServletRequest request){
		String sessionParamvalue = (String) request.getSession().getAttribute("loggedIn");
		return new ModelAndView(isUserLoggedIn(sessionParamvalue) ? UPDATION_PAGE : LOGIN_PAGE);
	}
	
	@RequestMapping(value="/validate", method = RequestMethod.POST)
	public ModelAndView validate(HttpServletRequest request) {
		boolean isValidUser = isValidUser(request.getParameter("name"), request.getParameter("password"));
		
		if(isValidUser){
			request.getSession().setAttribute("loggedIn", "true");
		}
		Map<String, String> modelMap = new HashMap<String, String>();
		modelMap.put("logInMessage", isValidUser ? "Successfully logged in." : "Username or password is wrong." );
		return new ModelAndView(isValidUser ? UPDATION_PAGE : LOGIN_PAGE, modelMap);
	}

	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ModelAndView insertBook(HttpServletRequest request){
		bookDAO.addBook(createNewBook(request));
		ModelMap model = new ModelMap();
		model.addAttribute("welcomeMessage", "Following are the updated list of books:");
		model.addAttribute("books", bookDAO.fetchAllBooks());
		return new ModelAndView(WELCOME_PAGE, model);
	}

	private Book createNewBook(HttpServletRequest request) {
		return new Book(Integer.parseInt(request.getParameter("id")), request.getParameter("title"), request.getParameter("author"), Integer.parseInt(request.getParameter("price")));
	}
	
	private boolean isValidUser(String name, String password) {
		return "admin".equalsIgnoreCase(name) && "admin".equalsIgnoreCase(password);
	}
	
	private boolean isUserLoggedIn(String sessionParamvalue) {
		return StringUtils.isNotEmpty(sessionParamvalue) && sessionParamvalue.equals("true");
	}

}
