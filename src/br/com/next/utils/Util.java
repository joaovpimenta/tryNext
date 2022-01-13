package br.com.next.utils;

import java.util.Scanner;

public class Util {

	public String readConsole() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		String typedText = read.nextLine();

		return typedText;

	}

	// METODO QUE L� O CONSOLE E RETORNA UMA STRING(TEXTO)
	public String readConsole(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(texto);
		String typedText = read.nextLine();

		return typedText;

	}

	public void writeConsole(String texto) {
		System.out.print(texto);
	}

	public Integer readConsoleInt() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		Integer typedText = read.nextInt();

		return typedText;
	}

	public Integer readConsoleInt(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(texto);
		Integer typedText = read.nextInt();

		return typedText;
	}

	public Double readConsoleDouble() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		Double typedText = read.nextDouble();

		return typedText;
	}

	public Double readConsoleDouble(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(texto);
		Double typedText = read.nextDouble();

		return typedText;
	}

	public Double writeConsole(Double saldo) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(saldo);
		double typedText = read.nextDouble();

		return typedText;
	}

}
