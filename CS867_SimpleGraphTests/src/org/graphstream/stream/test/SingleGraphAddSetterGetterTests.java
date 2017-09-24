package org.graphstream.stream.test;
import static org.junit.Assert.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.Test;
import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
 
/*
 * This class tests getters and setters of SingleGraph methods
 * It also does minimal tests on getEdgeIterator and getNodeIterator
 * methods.
 */

public class SingleGraphAddSetterGetterTests {
	
	@Test
	public void test_SingleGraphAddEdgeByNodeIndex() {
		SingleGraph sg = new SingleGraph("getNodeTest");
		SingleNode node1 = sg.addNode("Node1");
		Node node2 = sg.addNode("Node2");
		int node1_idx = node1.getIndex();
		int node2_idx = node2.getIndex();
		sg.addEdge("Edge1", node1_idx, node2_idx);
		assertEquals("edge not present after add by node idx",1,sg.getEdgeCount());
		assertTrue("edge name wrong after add by node idx","Edge1".equals(sg.getEdge("Edge1").getId()));
	}
	@Test
	public void test_SingleGraphAddEdgeDirectedByNodeID() {
		SingleGraph sg = new SingleGraph("addEdgeDirected1");
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addEdge("Edge1", "Node1", "Node2", true);
		assertEquals("edge not present after add by node name directed",1,sg.getEdgeCount());
	}
	@Test
	public void test_SingleGraphAddEdgeDirectedByNode() {
		SingleGraph sg = new SingleGraph("addEdgeDirected2");
		SingleNode node1 = sg.addNode("Node1");
		Node node2 = sg.addNode("Node2");
		sg.addEdge("Edge1", node1, node2, true);
		assertEquals("edge not present after add by node name directed",1,sg.getEdgeCount());
	}
	/**
	 * Test adding a node to a SingleGraph	
	 * Test Node getId() and equivalence of getNode() by
	 * name gets original node returned by addNode()
	 * tests that getNodeIterator() returns the node
	 */
	@Test
	public void test_SingleGraph_getNode() {
		String node_name = "TestNode1";
		SingleGraph sg = new SingleGraph("getNodeTest");
		SingleNode node1_Orig = sg.addNode(node_name);
		assertNotNull("node null from addNode",node1_Orig);
		int node1_orig_idx = node1_Orig.getIndex();
		sg.addNode("TestNode2");
		SingleNode node1 = sg.getNode("TestNode1");
		SingleNode node1_i = sg.getNode(node1_orig_idx);
		assertNotNull("node null from getNode by name",node1);
		assertNotNull("node null from getNode by idx", node1_i);
		assertTrue("Orig Node id not correct from getNode",((String)node1.getId()).equals("TestNode1"));
		assertTrue("Orig Node not equal to getNode by name",node1.equals(node1_Orig));
		boolean not_found = true;
		Iterator<SingleNode> n_iter = sg.getNodeIterator();
		while(n_iter.hasNext()) {
			SingleNode tmp_node = n_iter.next();
			if(node1.equals(tmp_node)) {
				not_found = false;
			}
		}
		assertFalse("Node not found in iterator",not_found);
	}
	
	@Test
	public void testSingleGraph_getNodeByIdx() {
		SingleGraph sg = new SingleGraph("getNodeByIdxTest");
		Node node1 = sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addNode("Node3");
		Node n1_tmp = sg.getNode(node1.getIndex());
		assertEquals("node by index not same as created node", node1, n1_tmp);
	}

	/**
	 * test the getEdge() and getEdgeIterator() methods
	*/
	@Test
	public void test_SingleGraph_getEdgeByIdx() {
		SingleGraph sg = new SingleGraph("getEdgeTest");
		String e_name1 = "Edge1";
		String e_name2 = "Edge2";
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addNode("Node3");
		sg.addEdge(e_name1, "Node1", "Node2");
		sg.addEdge(e_name2, "Node2","Node3");
		boolean not_found = true;
		Edge edge_1 = sg.getEdge(e_name1);
		int edge1_idx = edge_1.getIndex();
		Edge edge_1_i = sg.getEdge(edge1_idx);
		assertNotNull("edge null from getEdge by name", edge_1);
		assertNotNull("edge null from getEdge by idx",edge_1_i);
		assertTrue("edge id incorrect from getEdge by name", edge_1.getId().equals(e_name1));
		Iterator<Edge> e_iter = sg.getEdgeIterator();
		// test of equals should compare nodes of edges
		while (e_iter.hasNext()) {
			Edge edge_tmp = e_iter.next();
			if(edge_1.equals(edge_tmp)) {
				not_found = false;
			}
		}
		assertFalse("Edge not found in iterator", not_found);
	}
	/**
	 * Test getNode().getID() and getEdge().getID()
	 * check node order irrelevance to edge definition
	 */
	@Test
	public void test_GetNodeAndEdgeID() {
		SingleGraph sg = new SingleGraph("getNodeAndEdgeIDTest");
		String node1str = "Node1";
		String node2str = "LongerNodeName2";
		String edge1str = "LongEdgeIDName";
		Node node1 = sg.addNode(node1str);
		sg.addNode(node2str);
		Edge edge1 = sg.addEdge(edge1str, node2str, node1str);
		assertEquals("name from Edge.getID mismatch", edge1str, edge1.getId());
		assertEquals("name from sg.getEdge.getId() mismatch",edge1str, sg.getEdge(edge1str).getId());
		assertEquals("name from Node.getID mismatch", node1str, node1.getId());
		assertEquals("name from sg.getNode.getId() mismatch",node2str,sg.getNode(node2str).getId());
	}
	/**
	 * Test the addAttribute() and getAttribute() methods for
	 * graph object relative attribute
	 * Also tests iterator over attributes of graph object
	 * and finally exception on null attribute and
	 * setNullAttributesAreErrors method
	 * along with nullAttributesAreErrors method
	*/
	/*
	@Test(expected = NullAttributeException.class)
	public void test_SingleGraph_AddAttr() {
		int layout_q = 3;
		String lay_q = "layout.quality";
		String lay_stab = "layout.stabilization-limit";
		SingleGraph sg = new SingleGraph("addAttr_Test");
		sg.addAttribute(lay_q, layout_q);
		int layout_q_val = (int)sg.getAttribute("layout.quality");
		assertEquals("layout quality was not returned from graph", layout_q, layout_q_val);
		sg.addAttribute(lay_stab, 0.8);
		int found_cnt = 0;
		int total_cnt = 0;
		Iterator<String> attrs_keys_iter = sg.getAttributeKeyIterator();
		while (attrs_keys_iter.hasNext()) {
			String attr_key_tmp = attrs_keys_iter.next();
			if(attr_key_tmp.equals(lay_q)) found_cnt++;
			if(attr_key_tmp.equals(lay_stab)) found_cnt++;
			total_cnt++;
		}
		assertEquals("not all attributes returned by getAttrKeyIterator", 2, found_cnt);
		assertEquals("unexpected number of attributes returned", 2, total_cnt);
	    assertFalse(sg.nullAttributesAreErrors());
		sg.setNullAttributesAreErrors(true);
		assertTrue(sg.nullAttributesAreErrors());
		sg.getAttribute("layout.nullattr");
	    fail("Null Attribute Exception not thrown");
	}
	*/
	/**
	 * getEdgeSet method test
	 */
	@Test
	public void test_SingleGraph_getEdgeSet() {
		SingleGraph sg = new SingleGraph("getEdgeTests");
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addNode("Node3");
		Edge edge1 = sg.addEdge("edge1", "Node1", "Node2");
		Edge edge2 = sg.addEdge("edge2","Node2","Node3");
		Edge edge3 = sg.addEdge("edge3", "Node3", "Node1");
		Collection<Edge> e_set = sg.getEdgeSet();
		Iterator<Edge> e_iter = e_set.iterator();
		assertEquals("getEdgeSet wrong # edges in collection",3,e_set.size());
		int ecount = 0;
		while(e_iter.hasNext()) {
			Edge curr_edge = e_iter.next();
			if(curr_edge.equals(edge1)) ecount++;
			else if(curr_edge.equals(edge2)) ecount++;
			else if(curr_edge.equals(edge3)) ecount++;
		}
		assertEquals("getEdgeSet not all edges found in set",3,ecount);
	}
	/**
	 * 
	 */
	@Test
	public void test_SingleGraph_getNodeSet() {
		SingleGraph sg = new SingleGraph("getEdgeTests");
		Node node1 = sg.addNode("Node1");
		Node node2 = sg.addNode("Node2");
		Node node3 = sg.addNode("Node3");
		sg.addEdge("edge1", "Node1", "Node2");
		sg.addEdge("edge2","Node2","Node3");
		sg.addEdge("edge3", "Node3", "Node1");
		Collection<Node> e_set = sg.getNodeSet();
		assertEquals("getNodeSet num nodes != num added",3,e_set.size());
		Iterator<Node> n_iter = e_set.iterator();
		int ncount = 0;
		while(n_iter.hasNext()) {
			Node curr_node = n_iter.next();
			if(curr_node.equals(node1)) ncount++;
			else if(curr_node.equals(node2)) ncount++;
			else if(curr_node.equals(node3)) ncount++;
		}
		assertEquals("getNodeSet not all nodes found in set",3,ncount);
	}
	
	/**
	 * IsStrict flag test
	 * not enabled, test case covered by constructor tests -wdm
	 */
	/*@Test
	public void test_SingleGraph_IsStrict() {
		SingleGraph sg = new SingleGraph("StrictOnDefault");
		assertTrue("strict not defaulting to ON",sg.isStrict());
		sg.setStrict(false);
		assertFalse("strict not disabling",sg.isStrict());
	}*/
	/**
	 * Test auto-create flag functions, test of event thrown
	 * already covered by constructor tests so elided here -wdm
	 */
	public void test_SingleGraph_AutoCreateOn() {
		SingleGraph sg = new SingleGraph("autocreateOnSet");
		sg.setAutoCreate(true);
		sg.addNode("Node1");
		sg.addEdge("Edge1", "Node1", "Node2");
		assertEquals("node not added by autocreate",2,sg.getNodeCount());
		assertEquals("node name wrong from autocreate",sg.getNode("Node2").getId().equals("Node2"));
	}
	/*@Test(expected=ElementNotFoundException.class)
	public void test_SingleGraph_AutoCreateOffDefault() {
		SingleGraph sg = new SingleGraph("autocreateOffDefault");
		sg.addNode("Node1");
		sg.addEdge("edge1","Node1","Node2");
	} */
}
