package br.com.next.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Util {

	public static String readConsole() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(" » ");
		String typedText = read.nextLine();

		return typedText;

	}

	// METODO QUE LÊ O CONSOLE E RETORNA UMA STRING(TEXTO)
	public static String readConsole(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(texto + "\n » ");
		String typedText = read.nextLine();

		return typedText;

	}

	public static void writeConsole(String texto) {
		System.out.print(texto);
	}

	public static Integer readConsoleInt() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(" » ");
		Integer typedText = Integer.parseInt(read.nextLine()); // read.nextInt();

		return typedText;
	}

	public static Integer readConsoleInt(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(texto + "\n » ");
		Integer typedText = Integer.parseInt(read.nextLine());

		return typedText;
	}

	public static Double readConsoleDouble() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		Double typedText = read.nextDouble();

		return typedText;
	}

	public static Double readConsoleDouble(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(texto + "\n » ");
		Double typedText = read.nextDouble();

		return typedText;
	}

	public static Double writeConsole(Double saldo) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print(saldo + "\n » ");
		double typedText = read.nextDouble();

		return typedText;
	}

	public static Date readConsoleData(String data) {
		SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
		try {
			return dataFormat.parse(data);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
