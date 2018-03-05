package testfrw.jmockit.practice.demo;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Verifications;

public class PartialMockingTest {
	
	@Before
	public void prepareMockOnAClass(){


	}
	
	@Test
	public void matchOnMockInstance(@Mocked final Collaborator mock,  @Mocked Collaborator otherInstance)
	{
	   new Expectations() {{ mock.getValue(); result = 12; }};

	   // Exercise code under test with mocked instance passed from the test:
	   int result = mock.getValue();
	   assertEquals(12, result);

	   // If another instance is created inside code under test...
	   Collaborator another = new Collaborator(); // also mocked without expectations.

	   // ...we won't get the recorded result, but the default one:(mocked ,the default value is 0 instead of real -1)
	   assertEquals(0, another.getValue()); 
	}
	
	@Test
	public void newCollaboratorsWithDifferentBehaviors(@Mocked final Collaborator col1, @Mocked final Collaborator col2)
	{
	   new Expectations() {{
	      // Map separate sets of future instances to separate mock parameters:
	      new Collaborator(1); result = col1;
	      new Collaborator(2); result = col2;

	      // Record different behaviors for each set of instances:
	      col1.doSomething(anyInt); result = 123;
	      col2.doSomething(anyInt); result = 456;
	   }};

	   // Code under test:
	   System.out.println(new Collaborator(1).doSomething(5)); // will return 123	   
	   System.out.println(new Collaborator(2).doSomething(0)); // will retrun 456
	   
	}
	
	
	@Test
	public void matchOnMockInstance_WithReal(@Mocked final Collaborator mock,  @Injectable Collaborator otherInstance)
	{
	   new Expectations() {{ mock.getValue(); result = 12; }};

	   // Exercise code under test with mocked instance passed from the test:
	   int result = mock.getValue();
	   assertEquals(12, result);

	   // If another instance is created inside code under test...
	   Collaborator another = new Collaborator(); // real object, no mocked

	   // ...we won't get the recorded result, not mocked:
	   assertEquals(-1, another.getValue()); 
	}
	
	
	
	
	 @Test
	   public void partiallyMockingAClassAndItsInstances()
	   {
		  final Collaborator anyInstance = new Collaborator();
	      new Expectations(Collaborator.class) {{
	         anyInstance.getValue(); result = 123;
	      }};
	      
	      // Not mocked, as no constructor expectations were recorded:
	      Collaborator c1 = new Collaborator();
	      Collaborator c2 = new Collaborator(150);

	      // Mocked, as a matching method expectation was recorded:
	      assertEquals(123, c1.getValue());
	      assertEquals(123, c2.getValue());
	      assertEquals(123, anyInstance.getValue());

	      // Not mocked:
	      assertTrue(c1.simpleOperation(1, "b", null));
	      assertEquals(45, new Collaborator(45).value);
	   }
	 
	   @Test
	   public void partiallyMockingASingleInstance()
	   {
	      final Collaborator collaborator = new Collaborator(2);

	      new Expectations(collaborator) {{
	         collaborator.getValue(); result = 123;
	         collaborator.simpleOperation(1, "", null); result = false;

	         // Static methods can be dynamically mocked too.
	         Collaborator.doSomething(anyBoolean, "test");
	      }};

	      // Mocked:
	      assertEquals(123, collaborator.getValue());
	      assertFalse(collaborator.simpleOperation(1, "", null));
	      Collaborator.doSomething(true, "test");

	      // Not mocked:
	      assertEquals(2, collaborator.value);
	      assertEquals(45, new Collaborator(45).getValue());
	      assertEquals(-1, new Collaborator().getValue());
	   }
	   
	   
	   @Test
	   public void partiallyMockingAnObjectJustForVerifications()
	   {
	      final Collaborator collaborator = new Collaborator(123);

	      new Expectations(collaborator) {};

	      // No expectations were recorded, so nothing will be mocked.
	      int value = collaborator.getValue(); // value == 123
	      collaborator.simpleOperation(45, "testing", new Date());
	      

	      // Unmocked methods can still be verified:
	      new Verifications() {{ collaborator.simpleOperation(anyInt, anyString, (Date) any); }};
	   }
}
