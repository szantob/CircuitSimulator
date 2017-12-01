package controller;

import java.io.Serializable;
import java.util.ArrayList;


import graphics.MovableObject;
import main.Main;
import parts.CircuitObject;

public class PartArray implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<MovableObject> graphicalPartList;
	private ArrayList<CircuitObject> locicalPartList;

	void addPart(MovableObject graphical, CircuitObject logical){
		graphicalPartList.add(graphical);
		locicalPartList.add(logical);
	}
	
	public static void addNewPart(MovableObject graphical, CircuitObject logical) {
		Main.partArray.addPart(graphical, logical);
		graphical.connectObject(logical);
		logical.connectObject(graphical);
	}
}
