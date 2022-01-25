package br.com.next.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.Random;

public class Util {

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
		writeConsole(texto, 44, "<");
		System.out.print("  » ");
		String typedText = read.nextLine();

		return typedText;

	}

	public static Integer readConsoleInt() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print("  » ");
		Integer typedInt;
		try {
			typedInt = Integer.parseInt(read.nextLine());
		} catch (Exception e) {
			typedInt = -1;
		}
		
		return typedInt;
	}

	public static Integer readConsoleInt(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		writeConsole(texto, 44, "<");
		System.out.print("  » ");
		Integer typedInt;
		try {
			typedInt = Integer.parseInt(read.nextLine());
		} catch (Exception e) {
			typedInt = -1;
		}

		return typedInt;
	}

	public static Double readConsoleDouble() {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		System.out.print("  » ");
		Double typedDouble;
		try {
			typedDouble = Double.parseDouble(read.nextLine());
		} catch (Exception e) {
			typedDouble = -0.0;
		}
		return typedDouble;
	}

	public static Double readConsoleDouble(String texto) {
		@SuppressWarnings("resource")
		Scanner read = new Scanner(System.in);
		writeConsole(texto, 44, "<");
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

	public static String repeat(int repeticoes, String texto, int delayMin, int delayMax) {
		String textToReturn = "";
		for (int pontos = 0; pontos < repeticoes; pontos++) {
			textToReturn += texto;
			wait(randInt(delayMin, delayMax));
		}
		return textToReturn;
	}

	public static String contadorPorcentagem(int porcentagem) {

		String espacamento = (porcentagem < 100) ? (porcentagem < 10) ? "  " : " " : "";
		String retornoString = espacamento + porcentagem + "%";

		return retornoString;
	}

/*	public static void loading(int largura) {

		int totalQuadrados = largura - 23;

		System.out.println("╔" + repeat(largura - 2, "═", 0, 0) + "╗");

		for (int porcentagem = 0; porcentagem <= 100; porcentagem += 4.762) {

			Integer quadrados = (int) Math.round(porcentagem * totalQuadrados / 100);
			Integer espacos = totalQuadrados - quadrados;

			System.out.print((porcentagem < 100) ? "║ Carregando: [" : "║ Concluído:  [");
			System.out.print(repeat(quadrados, "■", 5, 25));
			System.out.print(repeat(espacos, " ", 0, 0) + "] " + contadorPorcentagem(porcentagem) + " ║");

			if (quadrados < totalQuadrados) {
				System.out.print(repeat(largura, "\b", 0, 0));
			}

		}

		System.out.println("\n╚" + repeat(largura - 2, "═", 0, 0) + "╝");

	}
*/
	public static void writeConsole(String texto, int largura, String alinhamento) {

		int i = 0;
		int linhas = 1;
		largura -= 4;
		Double larguraDouble = (double) largura;
		int totalEspacos = (texto.length() % 2 != 0) ? largura-texto.length() + 1 : largura-texto.length();
		int espacosE = totalEspacos/2;
		int espacosD = (texto.length() % 2 != 0) ? (totalEspacos/2)-1 : (totalEspacos/2);

		System.out.print("╔" + repeat(largura + 2, "═", 0, 0) + "╗" + "\n║ ");

		if (alinhamento == "<") {

			while (i < texto.length()) {

				if ((i > 0) ? ((i / largura) - (i / larguraDouble) == 0) ? true : false : false) {
					System.out.print(" ║\n║ ");
					linhas++;
				}

				System.out.print(texto.charAt(i));
				i++;

				wait(randInt(10, 20));
			}
			System.out.print(repeat(((largura * linhas) - i), " ", 0, 0));

		} else if (alinhamento == "-") {
			
			System.out.print(repeat(espacosE, " ", 0, 0));
			
			while (i < texto.length()) {

				System.out.print(texto.charAt(i));
				i++;

				wait(randInt(10, 20));
			}
			
			System.out.print(repeat(espacosD, " ", 0, 0));

		} else {

			System.out.print(repeat(espacosE+espacosD, " ", 0, 0));
			
			while (i < texto.length()) {

				if ((i > 0) ? ((i / largura) - (i / larguraDouble) == 0) ? true : false : false) {
					System.out.print(" ║\n║ ");
					linhas++;
				}

				System.out.print(texto.charAt(i));
				i++;

				wait(randInt(10, 20));
			}
			System.out.print(repeat(((largura * linhas) - i), " ", 0, 0));
		}

		System.out.println(" ║\n" + "╚" + repeat(largura + 2, "═", 0, 0) + "╝");

	}
	
	public static void writeConsoleError(String texto, int largura, String alinhamento) {

		int i = 0;
		int linhas = 1;
		largura -= 4;
		Double larguraDouble = (double) largura;
		int totalEspacos = (texto.length() % 2 != 0) ? largura-texto.length() + 1 : largura-texto.length();
		int espacosE = totalEspacos/2;
		int espacosD = (texto.length() % 2 != 0) ? (totalEspacos/2)-1 : (totalEspacos/2);

		System.err.print("╔" + repeat(largura + 2, "═", 0, 0) + "╗" + "\n║ ");

		if (alinhamento == "<") {

			while (i < texto.length()) {

				if ((i > 0) ? ((i / largura) - (i / larguraDouble) == 0) ? true : false : false) {
					System.err.print(" ║\n║ ");
					linhas++;
				}

				System.err.print(texto.charAt(i));
				i++;

				wait(randInt(10, 20));
			}
			System.err.print(repeat(((largura * linhas) - i), " ", 0, 0));

		} else if (alinhamento == "-") {
			
			System.err.print(repeat(espacosE, " ", 0, 0));
			
			while (i < texto.length()) {

				System.err.print(texto.charAt(i));
				i++;

				wait(randInt(10, 20));
			}
			
			System.err.print(repeat(espacosD, " ", 0, 0));

		} else {

			System.err.print(repeat(espacosE+espacosD, " ", 0, 0));
			
			while (i < texto.length()) {

				if ((i > 0) ? ((i / largura) - (i / larguraDouble) == 0) ? true : false : false) {
					System.err.print(" ║\n║ ");
					linhas++;
				}

				System.err.print(texto.charAt(i));
				i++;

				wait(randInt(10, 20));
			}
			System.err.print(repeat(((largura * linhas) - i), " ", 0, 0));
		}

		System.err.println(" ║\n" + "╚" + repeat(largura + 2, "═", 0, 0) + "╝");

	}

	public static void writeMenu(ArrayList<String> menu, int linhas) {
		
		for (int x = 0; x < menu.size(); x++)
		
		Util.writeConsole(menu.get(x), 44, ">");
		
		}

}
