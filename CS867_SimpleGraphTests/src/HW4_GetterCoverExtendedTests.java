

import java.util.*;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.Before;
import org.graphstream.graph.Edge;
import org.graphstream.graph.IdAlreadyInUseException;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.*;

public class HW4_GetterCoverExtendedTests {
	private SingleGraph sg;
	
	@Before
	public void initGraph() {
		sg = new SingleGraph("TestGraph", false, true, 2, 1);
	}
	
	/**
	 * test getEdge() with an invalid (<0) edge
	 * index number
	 */
	@Test
	public void EdgeGetInvalidIndexTest() {
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addEdge("Edge1", "Node1", "Node2");
		try {
		    sg.getEdge(-1);
		}
		catch(IndexOutOfBoundsException e) {
			;;
		}
		catch(Exception e2) {
			fail("wrong exception returned from negative edge index to getEdge"+e2.getMessage());
		}
		try {
			sg.getEdge(2);
		}
		catch(IndexOutOfBoundsException e3) {
			;;
		}
		catch(Exception e4) {
			fail("wrong exception returned from edge index out-of-bounds "+e4.getMessage());
		}
	}
	/**
	 * test getNode() with an invalid (<0) node
	 * index number
	 */
	@Test
	public void NodeGetInvalidIndexTest() {
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addEdge("Edge1", "Node1", "Node2");
		try {
		    sg.getNode(-1);
		}
		catch(IndexOutOfBoundsException e) {
			;;
		}
		catch(Exception e2) {
			fail("wrong exception returned from negative node index to getNode test "+e2.getMessage());
		}
		try {
			sg.getNode(11);
		}
		catch(IndexOutOfBoundsException e3) {
			;;
		}
		catch(Exception e4) {
			fail("wrong exception returned from out-of-bounds node index to getNode "+e4.getMessage());
		}
	}
	
	@Test
	public void EdgeIteratorNextRemoveOnEmptyTest() {
		Iterator<Edge> e_iter = sg.getEdgeIterator();
		try {
		    e_iter.remove();
		    fail("edge iterator remove succeeded on empty edge set");
		}
		catch(IllegalStateException e) {
			;;
		} catch(Exception e2) {
			fail("wrong exception returned by edge iterator remove on empty: "+e2.getMessage());
		}
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addEdge("Edge1", "Node1", "Node2");
		Iterator<Edge> it = sg.getEdgeIterator();
		while(it.hasNext()) {
			Edge e = it.next();
			it.remove();
		}
		try {
		    Edge error_e = it.next(); // cause exception
		    fail("edge iterator next succeeded after last entry read");
		}
		catch(NoSuchElementException e) {
			;;
		}
		catch(Exception e2) {
			fail("Edge iterator next returned wrong exception on empty: "+e2.getMessage());
		}
	}
	
	@Test
	public void NodeIteratorNextRemoveOnEmptyTest() {
		Iterator<Node> n_iter = sg.getNodeIterator();
		try {
		    n_iter.remove();
		    fail("node iterator remove succeeded on empty node set");
		}
		catch(IllegalStateException e) {
			;;
		} catch(Exception e2) {
			fail("wrong exception returned by node iterator remove on empty: "+e2.getMessage());
		}
		sg.addNode("Node1");
		sg.addNode("Node2");
		n_iter = sg.getNodeIterator();
		while(n_iter.hasNext()) {
			Node n = n_iter.next();
			n_iter.remove();
		}
		try {
		    Node error_n = n_iter.next(); // cause exception
		    fail("node iterator next succeeded after last entry read");
		}
		catch(NoSuchElementException e) {
			;;
		}
		catch(Exception e2) {
			fail("Node iterator next returned wrong exception on empty: "+e2.getMessage());
		}
	}
	
	@Test
	public void EdgeNodeAddSameIdMaxNodeEdgeTests() {
		sg.addNode("Node1");
		try {
			sg.addNode("Node1");
		}
		catch (IdAlreadyInUseException e) {
			;;
		}
		catch (Exception e2) {
			fail("wrong exception thrown from add node w/same id/name test");
		}
		sg.addNode("Node2");
		sg.addNode("Node3");
		sg.addEdge("Edge1", "Node1", "Node2");
		try {
			sg.addEdge("Edge1", "Node2", "Node3");
		}
		catch(IdAlreadyInUseException e3) {
			;;
		}
		catch (Exception e4) {
			fail("wrong exception thrown from add edge w/same id/name test");
		}
		// test node count exceed internal default sizes for nodeArray
		// of AdjacencyListGraph
		// This can be done by exceeding the edgeArray default size which
		// is far above the size of the nodeArray
		sg.clear();
		int edge_cnt = 1025;
		while(edge_cnt > 0) {
			sg.addEdge("Edge"+edge_cnt,"NodeA"+edge_cnt,"NodeB"+edge_cnt);
			edge_cnt--;
		}
	}
}
