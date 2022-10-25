package com.jinn.bookclub.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinn.bookclub.models.Book;
import com.jinn.bookclub.repositories.BookRepository;

@Service
public class BookService {
	@Autowired
	private BookRepository bookRepo;
	
	// CREATE/ UPDATE A BOOK
	public Book save(Book bookObj) {
		return bookRepo.save(bookObj);
	}
	// GET ALL BOOK
	public List<Book> getAllBooks(){
		return bookRepo.findAll();
	}
	// GET ONE BOOK
	public Book getOneBook(Long id) {
		return bookRepo.findById(id).orElse(null);
	}
	// DELETE BOOK
	public void deleteOneBook(Long id) {
		bookRepo.deleteById(id);
	}
}
