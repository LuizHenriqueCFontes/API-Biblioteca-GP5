package com.biblioteca.gp5.exception.user;

//Uma classe que ira extender para uma exception
public class UserNotFoundException extends RuntimeException {
	
	//Crio um construtor para passar uma message para a RuntimeException
	public UserNotFoundException(String message) {
		super(message);
	}
	
	/*Criei outro construtor que ira passa uma message, e tambem uma throwable, que é a super classe raiz de todos os erros,
	 Utiizo ela para poder lançar a cause em alguns erros lançados*/
	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
