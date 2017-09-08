import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;


@RunWith(Suite.class)
@SuiteClasses({ SingleGraphAddSetterGetterTests.class,
		SingleGraphConstructor.class, SingleGraphRemoveMethodsTests.class,
		SingleGraphEventTests.class })
public class AllSingleGraphTests {

}
