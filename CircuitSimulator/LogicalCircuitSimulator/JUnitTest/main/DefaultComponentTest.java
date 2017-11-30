package main;

import org.junit.Assert;
import org.junit.Test;
import parts.*;

public class DefaultComponentTest {
	@Test
	public void SimpleInputBehaviorTest(){
		SimpleInput in = new SimpleInput();
		Assert.assertEquals("U",in.toString());
		
		in.setState("H");
		Assert.assertEquals("H",in.toString());
		
		in.setState("L");
		Assert.assertEquals("L",in.toString());
		
		in.setState("U");
		Assert.assertEquals("U",in.toString());
	}
	@Test
	public void SimpleInputUpdateReturnTest(){
		SimpleInput in = new SimpleInput();
		boolean temp;
		
		in.setState("H");
		temp = in.Update();
		Assert.assertEquals(true, temp);
		in.setState("H");
		temp = in.Update();
		Assert.assertEquals(false, temp);
		
		in.setState("L");
		temp = in.Update();
		Assert.assertEquals(true, temp);
		in.setState("L");
		temp = in.Update();
		Assert.assertEquals(false, temp);
		
		in.setState("U");
		temp = in.Update();
		Assert.assertEquals(true, temp);
		temp = in.Update();
		Assert.assertEquals(false, temp);
	}
	@Test
	public void SimpleOutputBehaviorTest(){
		SimpleInput in = new SimpleInput();
		SimpleOutput out = new SimpleOutput();
		Assert.assertEquals("U",out.toString());
		CircuitObject.Connect(in.getPort(), out.getPort());
		Assert.assertEquals("U",out.toString());
		
		in.setState("H");
		in.Update();
		Assert.assertEquals("U",out.toString());
		out.Update();
		Assert.assertEquals("H",out.toString());
		
		in.setState("L");
		in.Update();
		Assert.assertEquals("H",out.toString());
		out.Update();
		Assert.assertEquals("L",out.toString());
		
		in.setState("U");
		in.Update();
		Assert.assertEquals("L",out.toString());
		out.Update();
		Assert.assertEquals("U",out.toString());
	}
	@Test
	public void SimpleOutputUpdateReturnTest(){
		SimpleInput in = new SimpleInput();
		SimpleOutput out = new SimpleOutput();
		CircuitObject.Connect(in.getPort(), out.getPort());

		in.setState("H");
		in.Update();
		Assert.assertEquals(true, out.Update());
		Assert.assertEquals(false, out.Update());

		in.setState("L");
		in.Update();
		Assert.assertEquals(true, out.Update());
		Assert.assertEquals(false, out.Update());

		in.setState("U");
		in.Update();
		Assert.assertEquals(true, out.Update());
		Assert.assertEquals(false, out.Update());
		
	}
	@Test
	public void SimpleWireBehaviorTest() {
		SimpleInput in = new SimpleInput();
		SimpleWire wire = new SimpleWire();
		Assert.assertEquals("U",wire.toString());
		
		CircuitObject.Connect(in.getPort(), wire.getPort(0));
		Assert.assertEquals("U",wire.toString());
		
		in.setState("L");
		Assert.assertEquals("U",wire.toString());
		
		in.Update();
		Assert.assertEquals("U",wire.toString());
		
		wire.Update();
		Assert.assertEquals("L",wire.toString());
	}
	@Test
	public void SimpleWireUpdateReturnTest() {
		SimpleInput in = new SimpleInput();
		SimpleWire wire = new SimpleWire();
		boolean temp;
		CircuitObject.Connect(in.getPort(), wire.getPort(0));
		
		in.setState("L");
		in.Update();
		temp=wire.Update();
		Assert.assertEquals(true,temp);
		in.setState("L");
		in.Update();
		temp=wire.Update();
		Assert.assertEquals(false,temp);
		
		in.setState("H");
		in.Update();
		temp=wire.Update();
		Assert.assertEquals(true,temp);
		
		temp=wire.Update();
		Assert.assertEquals(false,temp);
	}
	@Test
	public void SimpleWireOutputTest(){
		SimpleInput in = new SimpleInput();
		SimpleWire wire = new SimpleWire();
		SimpleOutput out = new SimpleOutput();
		CircuitObject.Connect(in.getPort(), wire.getPort(0));
		CircuitObject.Connect(wire.getPort(0), out.getPort(0));

		in.setState("H");
		in.Update();
		wire.Update();
		out.Update();
		Assert.assertEquals("H",out.toString());

		in.setState("L");
		in.Update();
		wire.Update();
		out.Update();
		Assert.assertEquals("L",out.toString());

		in.setState("U");
		in.Update();
		wire.Update();
		out.Update();
		Assert.assertEquals("U",out.toString());
	}
}
