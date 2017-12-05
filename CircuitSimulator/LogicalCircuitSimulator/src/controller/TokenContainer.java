package controller;

import java.util.ArrayList;

import logicalParts.LogicalObject;

class Token extends Thread {
	TokenContainer container;
	LogicalObject imSittingHere;
	
	private int tokenTTL;
	int speedReducer=1;
	boolean isPaused=false;
	
	public Token(LogicalObject nextObject, int timeToLive, boolean isPaused, int speedReducer) {
		if(timeToLive==0);			//TODO  Cyclic circuit!!!!!!
		this.isPaused=isPaused;
		this.speedReducer=speedReducer;
		tokenTTL=timeToLive;
		imSittingHere=nextObject;
	}
	public void run() {
		try {
			Thread.sleep(imSittingHere.getSleepTime()*speedReducer);
		} catch (InterruptedException e) {
			e.toString();
		}
		while(isPaused) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {			}
		}
		if(imSittingHere.Update()==true) {
			imSittingHere.addTokensToOutputs(tokenTTL-1);
			container.remove(this);
			return;
		}else {
			return;
		}
	}
}

public class TokenContainer extends ArrayList<Token>{
	private static final long serialVersionUID = 1L;

	int speedReducer=1;
	boolean isPaused=false;
	
	public TokenContainer(){
		super();
	}
	public void addToken(LogicalObject nextObject, int timeToLive) {
		Token token = new Token(nextObject, timeToLive, isPaused, speedReducer);
		token.container=this;
		add(token);
		token.start();
	}
	public void reduceSimSpeed(int factor) {
		speedReducer=factor;
		for(Token i : this) {
			i.speedReducer=factor;
		}
	}
	public void startSim() {
		isPaused=false;
		for(Token i : this) {
			i.isPaused=false;
		}
	}
	public void pauseSim() {
		isPaused=true;
		for(Token i : this) {
			i.isPaused=true;
		}
	}
}
