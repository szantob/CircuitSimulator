package tests;

import org.junit.Test;

import controller.ObjectContainer;

public class ObjectContainerTest {

	@Test
	public void createNewObjectTest() {
		ObjectContainer container = ObjectContainer.newObjectContainer(0, 0, "AND");
		container.getGraphicalComponent();
	}
}
