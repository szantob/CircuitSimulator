package controller;

import java.util.ArrayList;

import graphicalParts.GraphicalObject;
import logicalParts.LogicalObject;

class Token extends Thread {
	TokenContainer container;
	LogicalObject imSittingHere;
	GraphicalObject youCanSeeMeHere;
	
	private int tokenTTL;
	private int speedReducer;
	
	public Token(LogicalObject nextObject, int timeToLive) {
		if(timeToLive==0);			//////////////////  Cyclic circuit!!!!!!
		tokenTTL=timeToLive;
		imSittingHere=nextObject;
	}
	public void run() {
		try {
			sleep(imSittingHere.getSleepTime()*speedReducer);
		} catch (InterruptedException e) {}
		if(imSittingHere.Update()==true) {
			youCanSeeMeHere.setColorByState(imSittingHere.getState());
			imSittingHere.addTokensToOutputs(container, tokenTTL-1);
			container.remove(this);
			return;
		}else {
			return;
		}
	}
	public void setSpeedReducer(int factor){
		speedReducer = factor;
	}
}

public class TokenContainer extends ArrayList<Token>{
	private static final long serialVersionUID = 1L;
	public TokenContainer(){
		super();
	}
	public void addToken(LogicalObject nextObject, int timeToLive) {
		add(new Token(nextObject, timeToLive));
	}
	public void reduceSimSpeed(int factor) {
		for(Token token : this) {
			token.setSpeedReducer(factor);
		}
	}
}
