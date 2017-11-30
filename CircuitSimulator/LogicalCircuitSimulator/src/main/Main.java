package main;

import java.awt.EventQueue;

import grafics.CircuitWindow;

class Window implements Runnable{

	@Override
	public void run() {
		try {
			CircuitWindow window = new CircuitWindow();
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater( new Window());

	}

}
