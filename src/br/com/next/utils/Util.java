package br.com.next.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Util {

	public String readConsole() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(" » ");
		String typedText = read.nextLine();

		return typedText;

	}

	// METODO QUE LÊ O CONSOLE E RETORNA UMA STRING(TEXTO)
	public String readConsole(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(texto + "\n » ");
		String typedText = read.nextLine();

		return typedText;

	}

	public void writeConsole(String texto) {
		System.out.print(texto);
	}

	public Integer readConsoleInt() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(" » ");
		Integer typedText = read.nextInt();

		return typedText;
	}

	public Integer readConsoleInt(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(texto + "\n » ");
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
		System.out.print(texto + "\n » ");
		Double typedText = read.nextDouble();

		return typedText;
	}

	public Double writeConsole(Double saldo) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(saldo + "\n » ");
		double typedText = read.nextDouble();

		return typedText;
	}

	public Date readConsoleData(String data) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return dataFormat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}

//	public Integer readConsoleInt(Object texto) {
//		@SuppressWarnings("resource")
//		Scanner read = new Scanner(System.in);
//		System.out.print(texto+"\n» ");
//		Integer typedText = read.nextInt();
//
//		return typedText;
//		
//	}

}
