/**
 * 
 */
package controller;

import java.io.Serializable;

import graphicalParts.GraphicalObject;
import logicalParts.LogicalObject;

/**
 * @author Szanto Benedek David
 *
 */
public class ObjectContainer implements Serializable {
	private static final long serialVersionUID = 1L;
	private GraphicalObject graphicalComponent;
	private LogicalObject logicalComponent;
}
