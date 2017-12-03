package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import graphicalParts.GraphicalObject;
import graphics.CircuitWindow;
import logicalParts.LogicalObject;
import main.Main;

public class PartArray implements Serializable{
	private static final long serialVersionUID = 1L;
	private static ArrayList<GraphicalObject> graphicalPartList = new ArrayList<GraphicalObject>();
	private static ArrayList<LogicalObject> locicalPartList = new ArrayList<LogicalObject>();
	private static String lastSavePath = "/saves/test";

	void addPart(GraphicalObject graphical, LogicalObject logical){
		graphicalPartList.add(graphical);
		//TODO
		//locicalPartList.add(logical);
	}
	
	public static void addNewPart(GraphicalObject graphical, LogicalObject logical) {
		Main.partArray.addPart(graphical, logical);
		//TODO
		//graphical.connectObject(logical);
		//logical.connectObject(graphical);
	}
	
	static boolean resetWorspace(){
		CircuitWindow.workplace.removeAll();
		for(GraphicalObject i : graphicalPartList) {
			CircuitWindow.workplace.add(i);
		}
		CircuitWindow.frame.repaint();
		CircuitWindow.frame.revalidate();
		return true;
	}
	
	public static String saveAs(String path) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(path +".circ"));
			for(GraphicalObject i : graphicalPartList) {
				out.writeObject(i);
			}
			out.close();
			lastSavePath = path;
			return "true";
		} catch (IOException e) {
			return e.getMessage();
		}
	}
	public static String save() {
		return saveAs(lastSavePath);
	}
	public static String loadAs(String path) {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(path));
			Main.partArray = new PartArray();
			//TODO
			Main.partArray = (PartArray) in.readObject();
			in.close();
			resetWorspace();
			return "true";
		} catch (IOException | ClassNotFoundException e) {
			return e.getMessage();
		}
	}
	
}
