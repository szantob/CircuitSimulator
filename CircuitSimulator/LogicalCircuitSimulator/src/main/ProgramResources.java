package main;

import javax.swing.JFrame;

import controller.MouseStateMachine;

public class ProgramResources{
	public static MouseStateMachine mouseSM;
	public static JFrame jFrame;
	
	ProgramResources(){
		mouseSM = new MouseStateMachine();
	}
	
	public void setFrame(JFrame frame)
	{
		jFrame = frame;
	}
}
