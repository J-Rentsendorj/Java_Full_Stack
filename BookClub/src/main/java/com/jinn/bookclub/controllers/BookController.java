package com.jinn.bookclub.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.jinn.bookclub.models.Book;
import com.jinn.bookclub.models.User;
import com.jinn.bookclub.services.BookService;
import com.jinn.bookclub.services.UserService;

@Controller
public class BookController {
	@Autowired
	private BookService bookServ;
	
	@Autowired
	private UserService userServ;
	
	// CREATE
	
	@GetMapping("/book/new")
	public String newBook(
		HttpSession session,
		@ModelAttribute("bookObj") Book emptyBook
		) {
		// CHECK TO SEE IF USER IS LOGGED IN
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
	
		
		return "new.jsp";
	}
	
	@PostMapping("/book/new")
	public String createBook(
			@Valid @ModelAttribute("bookObj") Book filledBook,
			BindingResult results
	) {
		// CHECK FOR VALIDATIONS
		if(results.hasErrors()) {
			// VALIDATIONS FAILED
			return "new.jsp";
		}
		else {
			// VALIDATIONS PASSED
			bookServ.save(filledBook);
			return "redirect:/dashboard";
		}
	}
	@GetMapping("/dashboard")
	public String dashboard(HttpSession session, Model model) {
		// CHECK TO SEE IF USER IS LOGGED IN
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		// RETRIEVE USER
		Long user_id = (Long) session.getAttribute("user_id");
		User loggedUser = userServ.getOneUser(user_id);
		
//		// RETRIEVE ALL BOOKS
		List<Book> allBooks = bookServ.getAllBooks();
		
		model.addAttribute("user", loggedUser);
		model.addAttribute("books", allBooks);
		
		return "dashboard.jsp";
	}
	@GetMapping("/books/{id}")
	public String oneBook(@PathVariable("id") Long id, Model model, HttpSession session) {
		// CHECK TO SEE IF USER IS LOGGED IN
		if(session.getAttribute("user_id") == null) {
			return "redirect:/";
		}
		// GET ONE BOOK
		Book oneBook = bookServ.getOneBook(id);
				
		model.addAttribute("book", oneBook);
				
		return "books.jsp";
		}

	// ---------------- UPDATE --------------------//
	@GetMapping("/books/{id}/edit")
	public String editBook(@PathVariable("id")Long id, Model model, HttpSession session) {
	// CHECK TO SEE IF USER IS LOGGED IN
	if(session.getAttribute("user_id") == null) {
		return "redirect:/";
		}
	// GET ONE BOOK
	Book oneBook = bookServ.getOneBook(id);
			
	model.addAttribute("bookObj", oneBook);
			
	return "edit.jsp";
		}
		
	@PutMapping("/books/{id}/edit")
		public String updateBook(
		@Valid @ModelAttribute("bookObj") Book filledBook,
		BindingResult results
	) {
			// CHECK FOR VALIDATIONS
		if(results.hasErrors()) {
			// VALIDATIONS FAILED
		return "edit.jsp";
		}
		else {
			// VALIDATIONS PASSED
		bookServ.save(filledBook);
		return "redirect:/dashboard";
			}
		}	
	// ---------------- DELETE --------------------//
	@GetMapping("/books/{id}/delete")
	public String deleteBook(@PathVariable("id")Long id) {
		bookServ.deleteOneBook(id);
		return "redirect:/dashboard";
		}
}

