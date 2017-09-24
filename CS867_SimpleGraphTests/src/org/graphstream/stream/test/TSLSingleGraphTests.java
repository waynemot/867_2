package org.graphstream.stream.test;
import org.graphstream.stream.test.SingleGraphAddSetterGetterTests;
import org.graphstream.stream.test.SingleGraphConstructor;
import org.graphstream.stream.test.SingleGraphEventTests;
import org.graphstream.stream.test.SingleGraphRemoveMethodsTests;
import org.graphstream.stream.test.SingleGraph_TSL_Tests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ SingleGraphAddSetterGetterTests.class,
		SingleGraphConstructor.class, SingleGraphRemoveMethodsTests.class,
		SingleGraphEventTests.class
		, SingleGraph_TSL_Tests.class
		})
public class TSLSingleGraphTests {

}
