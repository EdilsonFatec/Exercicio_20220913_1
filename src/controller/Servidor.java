package controller;

import java.util.concurrent.Semaphore;

public class Servidor extends Thread {
	private int ThreadID;
	private double tempo;
	private int operacao;
	private Semaphore semaforo;
	
	public Servidor(int ThreadID, Semaphore semaforo) {
			this.ThreadID = ThreadID;
			this.semaforo = semaforo;
		}
		
	public void run() {
		if ((ThreadID % 3) == 1) {
			for (int i=0;i<2;i++) {
				do {
					tempo=Math.random() * 1.1;
				} while (tempo < 0.2 || tempo > 1);
				System.out.println("Thread "+ThreadID+" realizando cálculos");
				Calculos(tempo);
				try {
					semaforo.acquire();
					System.out.println("Thread "+ThreadID+" realizando transações de BD");
					BD(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
				semaforo.release();
				}
			}
		}
		if ((ThreadID % 3) == 2) {
			for (int i=0;i<3;i++) {
				do {
					tempo=Math.random() * 1.6;
				} while (tempo < 0.5 || tempo > 1.5);
				System.out.println("Thread "+ThreadID+" realizando cálculos");
				Calculos(tempo);
				try {
					semaforo.acquire();
					System.out.println("Thread "+ThreadID+" realizando transações de BD");
					BD(1.5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}
		} 
		if ((ThreadID % 3) == 0) {
			for (int i=0;i<3;i++) {
				do {
					tempo=Math.random() * 2;
				} while (tempo < 1 || tempo > 2);
				System.out.println("Thread "+ThreadID+" realizando cálculos");
				Calculos(tempo);
				try {
					semaforo.acquire();
					System.out.println("Thread "+ThreadID+" realizando transações de BD");
					BD(1.5);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					semaforo.release();
				}
			}
		}
	}
	
	public void Calculos(double tempo) {
		long tempolong = (long) (tempo * 1000);
		try {
			sleep(tempolong);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void BD(double tempo) {
		long tempolong = (long) (tempo * 1000);
		try {
			sleep(tempolong);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
		
}