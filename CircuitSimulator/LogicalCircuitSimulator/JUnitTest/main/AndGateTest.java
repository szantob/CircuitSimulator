package main;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import logicalParts.LogicalObject;
import logicalParts.SimpleInput;
import parts.AndGate;

public class AndGateTest {
	SimpleInput inA;
	SimpleInput inB;
	AndGate and;
	boolean temp;
	@Before
	public void initializeInputs() {
		and = new AndGate();
		inA = new SimpleInput();
		inB = new SimpleInput();
		inA.setState("L");
		inB.setState("L");
		inA.Update();
		inB.Update();
		Assert.assertEquals("U",and.toString());
		LogicalObject.Connect(inA.getPort(), and.getPort(0));
		Assert.assertEquals("U",and.toString());
		LogicalObject.Connect(inB.getPort(), and.getPort(1));
		Assert.assertEquals("U",and.toString());
		and.Update();
	}
	@Test
	public void AndGateBehaviorTest() {
		inA.setState("H");
		inA.Update();
		inB.setState("H");
		inB.Update();
		Assert.assertEquals("U",and.toString());
		and.Update();
		Assert.assertEquals("H",and.toString());

		inA.setState("H");
		inA.Update();
		inB.setState("L");
		inB.Update();
		Assert.assertEquals("H",and.toString());
		and.Update();
		Assert.assertEquals("L",and.toString());
		
		inA.setState("L");
		inA.Update();
		inB.setState("H");
		inB.Update();
		and.Update();
		Assert.assertEquals("L",and.toString());
		
		inA.setState("L");
		inA.Update();
		inB.setState("L");
		inB.Update();
		and.Update();
		Assert.assertEquals("L",and.toString());
		
		inA.setState("H");
		inA.Update();
		inB.setState("H");
		inB.Update();
		and.Update();
		Assert.assertEquals("H",and.toString());
	}
	@Test
	public void AndGateUpdateReturnTest() {

		inA.setState("H");
		inA.Update();
		inB.setState("H");
		inB.Update();
		Assert.assertEquals(true,and.Update());
		Assert.assertEquals(false,and.Update());
		

		inA.setState("H");
		inA.Update();
		inB.setState("L");
		inB.Update();
		Assert.assertEquals(true,and.Update());
		Assert.assertEquals(false,and.Update());
		
		inA.setState("L");
		inA.Update();
		inB.setState("H");
		inB.Update();
		Assert.assertEquals(false,and.Update());
		
		inA.setState("L");
		inA.Update();
		inB.setState("L");
		inB.Update();
		Assert.assertEquals(false,and.Update());
		
		inA.setState("H");
		inA.Update();
		inB.setState("H");
		inB.Update();
		Assert.assertEquals(true,and.Update());
		Assert.assertEquals(false,and.Update());
	}

}
