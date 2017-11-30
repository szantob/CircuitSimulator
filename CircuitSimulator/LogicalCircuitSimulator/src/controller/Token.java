package controller;

import parts.CircuitObject;

public class Token extends Thread {
	TokenContainer container;
	CircuitObject imSittingHere;
	int tokenTTL;
	int speedReducer;
	public Token(CircuitObject nextObject, int timeToLive) {
		if(timeToLive==0);			//////////////////  Cyclic circuit!!!!!!
		tokenTTL=timeToLive;
		imSittingHere=nextObject;
	}
	public void run() {
		try {
			sleep(imSittingHere.getSleepTime()*speedReducer);
		} catch (InterruptedException e) {}
		if(imSittingHere.Update()==true) {
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
