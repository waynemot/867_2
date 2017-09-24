package org.graphstream.stream.test;
import static org.junit.Assert.*;

import org.junit.Test;

import java.util.Collection;
import java.util.Iterator;

import org.graphstream.graph.implementations.*;
import org.graphstream.graph.*;
import org.graphstream.stream.ElementSink;

public class SingleGraphEventTests {
	private boolean edgeAddDetected;
	private boolean nodeAddDetected;
	private boolean edgeRemoveDetected;
	private boolean nodeRemoveDetected;
	private boolean graphClearDetected;
	private boolean stepBeginDetected;
	
	@Test
	public void test_SimpleGraphAddNodeEvent() {
		SingleGraph sg = new SingleGraph("addNodeEventTest");
		nodeAddDetected = false;
		sg.addElementSink(new SimpleGraphEventSink());
		sg.addNode("Node1");
		assertTrue("Element sink did not detect node add",nodeAddDetected);
	}
	@Test
	public void test_SimpleGraphAddEdgeEvent() {
		SingleGraph sg = new SingleGraph("addNodeEventTest");
		edgeAddDetected = false;
		sg.addElementSink(new SimpleGraphEventSink());
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addEdge("Edge1", "Node1", "Node2");
		assertTrue("Element sink did not detect edge add",edgeAddDetected);
	}
	@Test
	public void test_SimpleGraphDeleteNodeEvent() {
		SingleGraph sg = new SingleGraph("addNodeEventTest");
		nodeRemoveDetected = false;
		sg.addElementSink(new SimpleGraphEventSink());
		sg.addNode("Node1");
		assertEquals("node count wrong in graph after add",1,sg.getNodeCount());
		sg.removeNode("Node1");
		assertTrue("Element sink did not detect node rmv",nodeRemoveDetected);
	}
	
	@Test
	public void test_SimpleGraphDeleteEdgeEvent() {
		SingleGraph sg = new SingleGraph("addNodeEventTest");
		edgeRemoveDetected = false;
		sg.addElementSink(new SimpleGraphEventSink());
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addEdge("Edge1", "Node1", "Node2");
		assertEquals("edge count wrong in graph after add",1,sg.getEdgeCount());
		sg.removeEdge("Edge1");
		assertTrue("Element sink did not detect edge remove",edgeRemoveDetected);
	}
	@Test
	public void test_SimpleGraphClearEvent() {
		SingleGraph sg = new SingleGraph("addNodeEventTest");
		graphClearDetected = false;
		sg.addElementSink(new SimpleGraphEventSink());
		sg.addNode("Node1");
		sg.addNode("Node2");
		sg.addEdge("Edge1", "Node1", "Node2");
		assertEquals("edge count wrong in graph after add",1,sg.getEdgeCount());
		assertEquals("node count wrong in graph after add",2,sg.getNodeCount());
		sg.clear();
		assertTrue("graph clear event not detected",graphClearDetected);
	}
	/**
	 * 
	 * test stepBegin event, this test doesn't work because the
	 * javadocs about what a stepBegin is are overly cryptic
	 * and examples are lacking.
	 */
	/*@Test
	public void test_SimpleGraphStepBeginEvent() {
		SingleGraph sg = new SingleGraph("addNodeEventTest");
		stepBeginDetected = false;
		sg.addNode("Node1");
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			fail("sleep failed in step begin event test");
		}
		sg.addNode("Node2");
		assertTrue("step begin event not detected", stepBeginDetected);
	} */

	class SimpleGraphEventSink implements ElementSink {

		@Override
		public void edgeAdded(String arg0, long arg1, String arg2, String arg3,
				String arg4, boolean arg5) {
			edgeAddDetected = true;
			
		}

		@Override
		public void edgeRemoved(String arg0, long arg1, String arg2) {
			edgeRemoveDetected = true;
			
		}

		@Override
		public void graphCleared(String arg0, long arg1) {
			graphClearDetected = true;
			
		}

		@Override
		public void nodeAdded(String arg0, long arg1, String arg2) {
			nodeAddDetected = true;
			
		}

		@Override
		public void nodeRemoved(String arg0, long arg1, String arg2) {
			nodeRemoveDetected = true;
			
		}

		@Override
		public void stepBegins(String arg0, long arg1, double arg2) {
			stepBeginDetected = true;
			
		}
		
	}
}
