package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import graphicalParts.GraphicalObjectPort;
import graphics.CircuitWindow;
import main.Main;

public class PartArray implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private ArrayList<ObjectContainer> objectContainerList = new ArrayList<ObjectContainer>();
	
	private static String lastSavePath = "/saves/test";

	public ObjectContainer addPart(ObjectContainer container) {
		objectContainerList.add(container);
		return container;
	}
	public boolean addNewPart(int posX, int posY, String type) {
		try {
			ObjectContainer temp = ObjectContainer.newObjectContainer(posX, posY, type);
			Main.partArray.addPart(temp);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	public static boolean addNewPart(GraphicalObjectPort portA, GraphicalObjectPort portB) {
		try {
			ObjectContainer temp = ObjectContainer.newObjectContainer(portA,portB);
			Main.partArray.addPart(temp);
		} catch (Exception e) {
			return false;
		}
		return true;
	}
	
	boolean resetWorspace(){
		CircuitWindow.workplace.removeAll();
		for(ObjectContainer i : objectContainerList) {
			CircuitWindow.workplace.add(i.getGraphicalComponent());
		}
		CircuitWindow.frame.repaint();
		CircuitWindow.frame.revalidate();
		return true;
	}
	
	public String saveAs(String path) {
		ObjectOutputStream out;
		try {
			out = new ObjectOutputStream(new FileOutputStream(path +".circ"));
			for(ObjectContainer i : objectContainerList) {
				out.writeObject(i);
			}
			out.close();
			lastSavePath = path;
			return null;
		} catch (IOException e) {
			return e.getMessage();
		}
	}
	public String save() {
		return saveAs(lastSavePath);
	}
	public String loadAs(String path) {
		ObjectInputStream in;
		try {
			in = new ObjectInputStream(new FileInputStream(path));
			Main.partArray = (PartArray) in.readObject();
			in.close();
			resetWorspace();
			return null;
		} catch (IOException | ClassNotFoundException e) {
			return e.getMessage();
		}
	}
	public static void newPage() {
		Main.partArray = new PartArray();
		Main.partArray.resetWorspace();
	}
}
