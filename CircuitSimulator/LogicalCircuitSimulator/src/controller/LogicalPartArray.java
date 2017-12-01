package controller;

import java.io.Serializable;
import java.util.ArrayList;

import parts.CircuitObject;
import parts.SimpleInput;
import parts.SimpleOutput;

public class LogicalPartArray implements Serializable{
	private static final long serialVersionUID = 1L;
	static ArrayList<SimpleInput> inputList;
	static ArrayList<CircuitObject> circuitMidPartList;
	static ArrayList<SimpleOutput> outputList;
	static TokenContainer tokenContainer;
	static int defaultTTL = 256;
	
	public LogicalPartArray(){
		inputList = new ArrayList<SimpleInput>();
		circuitMidPartList = new ArrayList<CircuitObject>();
		outputList = new ArrayList<SimpleOutput>();
		tokenContainer = new TokenContainer();
	}
	public SimpleInput addPart(SimpleInput newInput) {
		inputList.add(newInput);
		return newInput;
	}
	public CircuitObject addPart(CircuitObject newObject) {
		circuitMidPartList.add(newObject);
		return newObject;
	}
	public SimpleOutput addPart(SimpleOutput newOutput) {
		outputList.add(newOutput);
		return newOutput;
	}
	public void startSimulation() {
		for(SimpleInput input : inputList) {
			tokenContainer.addToken(input,defaultTTL);
		}
	}
	
	public static void setSimpleInput(SimpleInput input, CircuitStateEnum state) {
		input.setState(state);
		tokenContainer.addToken(input,defaultTTL);
	}
}
