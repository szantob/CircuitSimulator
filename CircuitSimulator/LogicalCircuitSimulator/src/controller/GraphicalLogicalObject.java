package controller;

import java.util.ArrayList;

import graphics.MovableComponentPort;
import graphics.MovableObject;
import parts.CircuitObject;
import parts.CircuitObjectPort;

public class GraphicalLogicalObject {
	CircuitObject logicalObject;
	MovableObject graphicalObject;

	ArrayList<CircuitObjectPort> logicalObjectPortList;
	ArrayList<MovableComponentPort> graphicalObjectPortList;
}
