import static org.junit.Assert.*;

import org.junit.Test;
import org.graphstream.graph.implementations.*;

public class SingleGraphConstructor {

	// test SimpleGraph() constructor
	// Verify construction of SimpleGraph() with a
	// string value of any length would be desirable but is
	// ignored for the purposes of this assignment -wdm
	// Ignoring the max string length test here since testing max
	// string length of Java String() object would be memory
	// intensive/abusive and deemed unnecessary by using code inspection
	// methodology used to verify datatype used to encapsulate ID value
	// in base class AbstractElement.java -wdm
	//
	// Along with the testing of the constructors this test minimally
	// tests the addNode, addEdge, getNodeCount and getEdgeCount methods
	// as they relate to settings of the constructor that cause Exceptions
	// or impose pseudo-limits on the number of nodes/edges at creation.
	//
	// Test the SimpleGraph constructor StrictChecking
	// option setting
	// Also tests addNode and addEdge methods minimally
	@Test(expected = org.graphstream.graph.ElementNotFoundException.class)
	public void test_SimpleGraph_StrictCheck() {
		SingleGraph sg = new SingleGraph("StrictChecking", true, false);
		sg.addNode("Node1");
		sg.addEdge("Edge1", "Node1", "Node2");
		fail("failed to exception on node add w/Strict Checking");
	}
	// Test the SimpleGraph constructor AutoCreate
	// option setting, this option creates nodes as
	// needed to complete graph edges defined
	@Test
	public void test_SingleGraph_AutoCreate() {
		SingleGraph sg = new SingleGraph("AutoCreate", false, true);
		sg.addNode("Node1");
		sg.addEdge("Edge1", "Node1", "Node2");
		assertTrue(sg.getNodeCount() == 2);
	}
	// Test constructor with both StrictChecking & AutoComplete
	// set to true, StrictCheck overrides AutoComplete
	// so expect exception to AutoComplete operation
	@Test(expected = org.graphstream.graph.ElementNotFoundException.class)
	public void test_SimpleGraph_Strict_and_AutoCreate() {
		SingleGraph sg = new SingleGraph("StrictChk&AutoCreate", true, true);
		sg.addNode("Node1");
		sg.addEdge("Edge1", "Node1", "Node2");
		fail("failed to exception on node add w/Strict Checking & AutoCreate");
	}
	// test constructor with limit set on number of nodes and
	// add 1 node above limit since limit is not enforced.
	// Also tests getEdgeCount and getNodeCount methods
	@Test
	public void test_SingleGraph_NodeLimited() {
		SingleGraph sg = new SingleGraph("NodeLimited", false, false, 1, 1);
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addEdge("Edge1", "Node1", "Node2");
		// Cover Tests for getEdgeCount/getNodeCount from AdjacencyListGraph 
		// parent
		assertTrue(sg.getEdgeCount() == 1);
		assertTrue(sg.getNodeCount() == 2);
	}
	//
	// test edge limit flag, also tests getNodeCount and getEdgeCount
	// methods
	@Test
	public void test_SingleGraph_EdgeLimited() {
		SingleGraph sg = new SingleGraph("NodeLimited", false, false, 3, 1);
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addNode("Node3");
		sg.addEdge("Edge1", "Node1", "Node2");
		sg.addEdge("Edge2","Node2","Node3");
		// Cover tests for getEdgeCount/getNodeCount from AdjacencyListGraph
		// parent
		assertTrue(sg.getNodeCount() == 3);
		assertTrue(sg.getEdgeCount() == 2);
	}
}
