package controller;

import java.io.Serializable;
import java.util.ArrayList;

import graphics.CircuitWindow;
import graphics.MovableObject;
import main.Main;
import parts.CircuitObject;

public class PartArray implements Serializable{
	private static final long serialVersionUID = 1L;
	private static ArrayList<MovableObject> graphicalPartList = new ArrayList<MovableObject>();
	private static ArrayList<CircuitObject> locicalPartList = new ArrayList<CircuitObject>();

	void addPart(MovableObject graphical, CircuitObject logical){
		graphicalPartList.add(graphical);
		locicalPartList.add(logical);
	}
	
	public static void addNewPart(MovableObject graphical, CircuitObject logical) {
		Main.partArray.addPart(graphical, logical);
		graphical.connectObject(logical);
		logical.connectObject(graphical);
		CircuitWindow.workplace.add(graphical);
		CircuitWindow.frame.repaint();
		CircuitWindow.frame.revalidate();
	}
}
