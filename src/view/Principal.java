package view;
import javax.swing.JOptionPane;
import controller.Servidor;

import java.util.concurrent.Semaphore;

public class Principal {

	public static void main(String[] args) {
		Semaphore semaforo = new Semaphore(1);

		for (int i=1;i<26;i++) {
			Thread servidor = new Servidor(i, semaforo);
			servidor.start();
		}
	}
}
