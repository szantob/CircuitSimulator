package controller;

import java.util.ArrayList;

import parts.CircuitObject;

public class TokenContainer extends ArrayList<Token>{
	private static final long serialVersionUID = 1L;
	TokenContainer(){
		super();
	}
	public void addToken(CircuitObject nextObject, int timeToLive) {
		add(new Token(nextObject, timeToLive));
	}
	public void reduceSimSpeed(int factor) {
		for(Token token : this) {
			token.setSpeedReducer(factor);
		}
	}
}
