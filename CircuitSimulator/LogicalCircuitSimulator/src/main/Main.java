package main;

import java.awt.EventQueue;

import controller.GraphicalPartArray;
import controller.MouseStateMachine;
import controller.LogicalPartArray;
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
	public static GraphicalPartArray graphicParts = new GraphicalPartArray();
	public static LogicalPartArray partArray = new LogicalPartArray();

	public static void main(String[] args) {
		EventQueue.invokeLater( new WindowThread());
	}

}
