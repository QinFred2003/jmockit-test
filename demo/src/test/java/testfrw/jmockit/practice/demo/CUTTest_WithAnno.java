package testfrw.jmockit.practice.demo;

import org.junit.Test;

import mockit.Delegate;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;

public class CUTTest_WithAnno {
	
	@Mocked 
	DependencyClass dc;
	
	@Tested
	ClsUnderTest cut;
	

	@Test
	public void testCUT( ){	
		new Expectations(){	
			{
				dc.helloWord(anyString); 
				result = new Delegate() {
				 void aDelegateMethod(String s) { System.out.println("hello in hacked: "+ s); }
				};
			}
		};		
	  cut.doSomething("OK");
		
	}
	
	@Test
	public void testCUT2(@Mocked final AnotherDependencyClass adcd){			
		new Expectations(adcd){	
			{
				adcd.calcValue(); 
				result = 15;
			}
		};		
		ClsUnderTest cut2= new ClsUnderTest(); //Dependency is also mocked		
		cut2.setAnotherDependency(adcd); // mocked method with instance		 
		cut2.doSomething("OK2");// No expectation is defined mocked object, the dependency act as default ( do nothing )		
		cut2.getIntValue(); 
	}
	
	@Test
	public void testCUT3(@Mocked final AnotherDependencyClass bc, @Mocked final YetDepenencyClass yc){			
		new Expectations(AnotherDependencyClass.class){	
			{
				bc.calcValue();  // mocked metod with class
				result = 17;
			}
		};
		
		new Expectations(){	
			{
				yc.calcValue();  // mocked metod with instance
				result = 27;
			}
		};
		
		
		ClsUnderTest cut3= new ClsUnderTest();
		cut3.doSomething("OK3");// No expectation is defined mocked object, the dependency act as default ( do nothing )
		cut3.getIntValue(); //class method is mocked in expectation
		cut3.getRealValue(); // invoke real method
		
		cut3.setYdc(yc); //set with mocked instance
		cut3.getAnotherIntValue();
		
		YetDepenencyClass yc2 = new YetDepenencyClass(); // also mocked by @Mocked
		cut3.setYdc(yc2);
		cut3.getAnotherIntValue();
		
	}
	
	@Test
	public void testCUT4(@Mocked final AnotherDependencyClass bc, @Injectable final YetDepenencyClass yc){			
		new Expectations(AnotherDependencyClass.class){	
			{
				bc.calcValue();  // mocked metod with class
				result = 17;
			}
		};
		
		new Expectations(){	
			{
				yc.calcValue();  // mocked metod with instance
				result = 27;
			}
		};
		
		
		ClsUnderTest cut3= new ClsUnderTest();
		cut3.doSomething("OK4");// No expectation is defined mocked object, the dependency act as default ( do nothing )
		cut3.getIntValue(); //class method is mocked in expectation
		cut3.getRealValue(); // invoke real method -- "Real method"
		
		cut3.setYdc(yc); //set with mocked instance
		cut3.getAnotherIntValue(); // "27"
		
		YetDepenencyClass yc2 = new YetDepenencyClass(); // won't mocked by @Injectable
		cut3.setYdc(yc2);
		cut3.getAnotherIntValue(); //"-1"
		
	}
	
	
	
	
	
}
