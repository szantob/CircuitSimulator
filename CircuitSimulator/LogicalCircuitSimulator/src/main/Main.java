package main;

import java.awt.EventQueue;

import controller.MouseStateMachine;
import controller.PartArray;
import grafics.CircuitWindow;

class WindowThread implements Runnable{

	@Override
	public void run() {
		try {
			CircuitWindow.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}

public class Main {
	public static CircuitWindow window = new CircuitWindow();
	public static MouseStateMachine mouseSM = new MouseStateMachine();
	public static PartArray partArray = new PartArray();

	public static void main(String[] args) {
		EventQueue.invokeLater( new WindowThread());
	}

}
