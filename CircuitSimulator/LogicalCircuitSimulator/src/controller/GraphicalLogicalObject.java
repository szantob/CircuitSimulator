package controller;

import java.util.ArrayList;

import graphicalParts.MovableComponentPort;
import graphicalParts.MovableComponent;
import logicalParts.LogicalObject;
import logicalParts.LogicalObjectPort;

public class GraphicalLogicalObject {
	LogicalObject logicalObject;
	MovableComponent graphicalObject;

	ArrayList<LogicalObjectPort> logicalObjectPortList;
	ArrayList<MovableComponentPort> graphicalObjectPortList;
}
