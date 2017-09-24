package org.graphstream.stream.test;
import static org.junit.Assert.*;

import org.junit.Test;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;

public class SingleGraphRemoveMethodsTests {

	/**
	 * Test removing a node with an attached edge from the graph
	 */
	@Test
	public void test_SingleGraph_RemoveNode() {
		SingleGraph sg = new SingleGraph("removeNodeTest");
		sg.addNode("Node_To_Remove");
		sg.addNode("Second_Node");
		sg.addEdge("Simple_Edge", "Node_To_Remove", "Second_Node");
		sg.removeNode("Node_To_Remove");
		int n_cnt = sg.getNodeCount();
		assertEquals("incorrect number of nodes after remove", 1, n_cnt);
		int e_cnt = sg.getEdgeCount();
		assertEquals("incorrect number of edges after node remove", 0, e_cnt);
	}
	/**
	 * Test remove edge by ID (name string)
	 */
	@Test
	public void test_SingleGraph_RemoveEdgeByID() {
		SingleGraph sg = new SingleGraph("removeEdgeTest1");
		sg.addNode("First Node");
		sg.addNode("Second Node");
		sg.addEdge("Edge To Remove", "First Node", "Second Node");
		sg.removeEdge("Edge To Remove");
		int e_cnt = sg.getEdgeCount();
		assertEquals("wrong number of edges after edge remove", 0, e_cnt);
	}
	/**
	 * remove node using node ID (name string)
	 */
	@Test
	public void test_SingleGraph_RemoveEdgeByNodeNames() {
		SingleGraph sg = new SingleGraph("removeEdgeTest2");
		sg.addNode("First Node");
		sg.addNode("Second Node");
		sg.addEdge("Edge To Remove", "First Node", "Second Node");
		sg.removeEdge("First Node","Second Node");
		int e_cnt = sg.getEdgeCount();
		assertEquals("wrong number of edges after edge remove", 0, e_cnt);
	}
	/**
	 * remove edge by node object index value
	 */
	@Test
	public void test_SingleGraph_RemoveEdgeByNodesIdx() {
		SingleGraph sg = new SingleGraph("removeEdgeTest2");
		Node node1 = sg.addNode("First Node");
		Node node2 = sg.addNode("Second Node");
		sg.addEdge("Edge To Remove", "First Node", "Second Node");
		sg.removeEdge(node1.getIndex(),node2.getIndex());
		int e_cnt = sg.getEdgeCount();
		assertEquals("wrong number of edges after edge remove", 0, e_cnt);
	}
	/**
	 * remove edge using node objects
	 */
	@Test
	public void test_SingleGraph_RemoveEdgeByNodeObjects() {
		SingleGraph sg = new SingleGraph("removeEdgeTest2");
		Node node1 = sg.addNode("First Node");
		Node node2 = sg.addNode("Second Node");
		sg.addEdge("Edge To Remove", "First Node", "Second Node");
		sg.removeEdge(node1, node2);
		int e_cnt = sg.getEdgeCount();
		assertEquals("wrong number of edges after edge remove", 0, e_cnt);
	}
	/**
	 * Test the clear function of the graph since it forms a
	 * super-set function that would do removeNode and removeEdge
	 * functions on all elements in the graph.
	 */
	@Test
	public void test_SingleGraph_clear() {
		SingleGraph sg = new SingleGraph("getEdgeTests");
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addNode("Node3");
		sg.addEdge("edge1", "Node1", "Node2");
		sg.addEdge("edge2","Node2","Node3");
		sg.addEdge("edge3", "Node3", "Node1");
		sg.clear();
		assertEquals("clear left nodes in graph",0,sg.getNodeCount());
		assertEquals("clear left edges in graph",0, sg.getEdgeCount());
	}
}
