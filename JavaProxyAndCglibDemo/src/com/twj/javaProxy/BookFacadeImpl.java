package com.twj.javaProxy;

public class BookFacadeImpl implements BookFacade {

	@Override
	public void addBook() {
		System.out.println("add a book!");
	}

	@Override
	public void updateBook() {
		System.out.println("update a book!");
	}

	@Override
	public void selectBook() {
		System.out.println("select a book!");
		
	}

	@Override
	public void deleteBook() {
		System.out.println("delete a book!");
		
	}
	
	
}
