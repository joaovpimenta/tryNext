package br.com.next.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;

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

	public static int randInt(int min, int max) {

		Random rand = new Random();
		int randomNum = rand.nextInt((max - min) + 1) + min;
		return randomNum;

	}

	public static void wait(int ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
	}

	public static void repeat(int repeticoes, String texto, int delayMin, int delayMax) {
		for (int pontos = 0; pontos < repeticoes; pontos++) {
			System.out.print(texto);
			wait(randInt(delayMin, delayMax));
		}
		;
	}

	public static void porcentometro(int repeticoes, String texto, int delayMin, int delayMax) {
		for (int pontos = 0; pontos < 4; pontos++) {
			System.out.print(texto);
			wait(randInt(delayMin, delayMax));
		}
		;
	}

	public static void porcentometr() {
		int porcentagem = 0;
		while (porcentagem <= 100) {
			String porcentagemTextString = (porcentagem < 10) ? "  " + porcentagem
					: (porcentagem < 100) ? " " + porcentagem : "" + porcentagem;
			System.out.print("\b\b\b\b" + porcentagemTextString + "%");
			wait(500);
			porcentagem++;
		}

	}

	public static void porcentometro() {
			
			int quadrados = 0;
			int espacos = 22;
			
			while (quadrados < 22) {
				
				repeat(quadrados, "■", 0, 15);
				repeat(espacos, " ", 0, 0);
				if (quadrados < 21) { repeat(22, "\b", 0, 0); }	

			}
			
		}

		//String porcentagemTextString = (porcentagem < 10) ? "  " + porcentagem
		//		: (porcentagem < 100) ? " " + porcentagem : "" + porcentagem;
		//System.out.print("\b\b\b\b\b]" + porcentagemTextString + "%");
		//wait(500);
		//porcentagem++;
		//}

	public static void loading() {

		System.out.println("╔══════════════════════════════════════════╗");
		System.out.print("║ Carregando: [");
		porcentometro();

		System.out.println("╚══════════════════════════════════════════╝");

	}

}
