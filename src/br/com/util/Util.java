package br.com.util;

import java.util.Scanner;

public class Util {
	
	// METODO QUE LÊ O CONSOLE E RETORNA UMA STRING(TEXTO)
	public String readConsole(String texto) {
	@SuppressWarnings("resource")
	Scanner read = new Scanner(System.in);
	System.out.print(texto);
	String typedText = read.next();

	return typedText;

	}

	public Integer readConsoleInt(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(texto);
		Integer typedText = read.nextInt();

		return typedText;
	}
	
	
}
