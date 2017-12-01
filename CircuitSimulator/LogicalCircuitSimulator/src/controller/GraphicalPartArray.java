package controller;

import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphicalPartArray implements Serializable{
	private static final long serialVersionUID = 1L;
	private ArrayList<JPanel> partList;

	public JPanel newGraphicalPart(JPanel part) {
		partList.add(part);
		return part;
	}
}
