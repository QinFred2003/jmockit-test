package testfrw.jmockit.practice.demo;

import org.junit.Test;
import org.junit.runner.RunWith;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Delegate;


public class CUTTest {
	@Test
	public void testCUT(@Mocked final DependencyClass dc ){	
		new Expectations(){	
			{
				dc.helloWord(anyString); 
				result = new Delegate() {
				 void aDelegateMethod(String s) { System.out.println("hello in hacked: "+ s); }
				};
			}
		};				
		
		ClsUnderTest cut = new ClsUnderTest(); //dependency is mocked	
		cut.doSomething("OK");
		
	}
	
	@Test
	public void testCUT2(){			
		ClsUnderTest cut2= new ClsUnderTest();  //dependency is  not mocked 
		cut2.doSomething("OK2");		
	}
	
	@Test
	public void testCUT3(@Mocked final DependencyClass dc ){			
		ClsUnderTest cut3= new ClsUnderTest();  //dependency is   mocked 	with not matched method and act nothing
		cut3.doSomething("OK3");		
	}
	
}
