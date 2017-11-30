package main;

import java.awt.EventQueue;

import grafics.CircuitWindow;

class Window implements Runnable{
	private ProgramResources resources;
	public Window(ProgramResources resource) {
		this.resources=resource;
	}
	@Override
	public void run() {
		try {
			CircuitWindow window = new CircuitWindow(resources);
			window.frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}



public class Main {
	public static ProgramResources resources;

	public static void main(String[] args) {
		EventQueue.invokeLater( new Window(resources));

	}

}
