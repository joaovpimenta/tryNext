package br.com.next.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;

public class Util {
	
	public static void writeConsole(String texto) {
		System.out.println("╔══════════════════════════════════════════╗");
		System.out.print("║ ");
		textoTrim(texto);
		System.out.println("╚══════════════════════════════════════════╝");

	}

	public static String readConsole() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print("  » ");
		String typedText = read.nextLine();

		return typedText;

	}

	// METODO QUE LÊ O CONSOLE E RETORNA UMA STRING(TEXTO)
	public static String readConsole(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.println("╔══════════════════════════════════════════╗");
		System.out.print("║ ");
		textoTrim(texto);
		System.out.println("╚══════════════════════════════════════════╝");
		System.out.print("  » ");
		String typedText = read.nextLine();

		return typedText;

	}

	public static Integer readConsoleInt() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print("  » ");
		Integer typedText = read.nextInt();

		return typedText;
	}

	public static Integer readConsoleInt(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.println("╔══════════════════════════════════════════╗");
		System.out.print("║ ");
		textoTrim(texto);
		System.out.println("╚══════════════════════════════════════════╝");
		System.out.print("  » ");
		Integer typedText = read.nextInt();

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
		System.out.println("╔══════════════════════════════════════════╗");
		System.out.print("║ ");
		textoTrim(texto);
		System.out.println("╚══════════════════════════════════════════╝");
		System.out.print("  » ");
		Double typedText = read.nextDouble();

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

	}

	public static String contadorPorcentagem(int porcentagem) {

		String espacamento = (porcentagem < 100) ? (porcentagem < 10) ? "  " : " " : "";
		String retornoString = espacamento + porcentagem + "%";
		
		return retornoString;
	}

	public static void loading(int totalQuadrados) {
		System.out.print("╔══════════════════════════════════════════╗\n");
		
		for (int porcentagem = 0; porcentagem <= 100; porcentagem+= 4.762) {

			Integer quadrados = (int) Math.round(porcentagem*totalQuadrados/100);
			Integer espacos = totalQuadrados-quadrados;
			
			System.out.print((porcentagem < 100) ? "║ Carregando: [" : "║ Concluído:  [");

			repeat(quadrados, "■", 5, 15);
			repeat(espacos, " ", 0, 0);
			
			System.out.print("] " + contadorPorcentagem(porcentagem) + " ║");

			if (quadrados < totalQuadrados) {
				repeat(44, "\b", 0, 0);
			}
			
		}
		System.out.print("\n╚══════════════════════════════════════════╝");
	}
	
	public static void printLoading(int totalQuadrados) {
		System.out.println("╔══════════════════════════════════════════╗");
		loading(totalQuadrados);
		

	}

	public static void textoTrim(String texto) {

		int i = 0;
		int linhas = 1;

		while (i < texto.length()) {

			if ((i > 0) ? ((i / 40) - (i / 40.0) == 0) ? true : false : false) {
				System.out.print(" ║\n║ ");
				linhas++;
			}

			System.out.print(texto.charAt(i));
			i++;
			
			wait(randInt(10, 30));
		}
		
		repeat(((40 * linhas) - i), " ", 0, 0);
		System.out.print(" ║\n");

	}

}
