package testfrw.jmockit.practice.demo;

import org.junit.Test;

import mockit.Delegate;
import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;
import mockit.Tested;
import mockit.Verifications;


public class UseSimpleToolTest {

	
	@Test
	public void testExpectations(@Mocked final SimpleTool simpleTool){
		
		// define Expectation with no arguments
		// means the implementation would are replaced by the default mock object.
		// The not-defined method in the expectation would be implemented as default "empty" behaivor
		//未mock函数返回null
		 new Expectations() { 	
		        {
		            simpleTool.fun1(anyString);
		            result = "MOCK";
		        }
	     };	     
	     //defined in expectations
	     System.out.println(simpleTool.fun1("param"));
	     
         //not defined in expectations for mocked obj, return null
	     System.out.println(simpleTool.fun3("param"));
	     
	   //defined in expectations
	     System.out.println(new UseSimpleTool().fun1("param")); 
	     
      //	MOCK
      //    null
      //    MOCK

	}
	
	
	@Test
	public void testExpectations_withClassArgument(@Mocked final SimpleTool simpleTool){
		// define Expectation with Class arguments
		// means the implementation would are replaced by the  mocked object.
		// The not-defined method in the expectation would be implemented as default "real" behaivour
		//未mock函数调用真实的class方法
		 new Expectations(SimpleTool.class) {
		        {
		            simpleTool.fun1(anyString);
		            result = "MOCK";
		        }
	     };	     
	     
	   //defined in expectations
	     System.out.println(simpleTool.fun1("param"));	     
	   //not defined in expectations for mocked obj, call "real" method
	     System.out.println(simpleTool.fun3("param")); 
	   //All the instance of the target class are mocked with defined method in the expectation  
	    System.out.println(new UseSimpleTool().fun1("param"));
	    
      //MOCK
      //real: public String fun3(param)
      //MOCK
	     
	}
	
	@Test
	public void testExpectations_withInstanceArgument(@Mocked final SimpleTool anySimpleTool){
		// define Expectation with Instance arguments
		// means the implementation would are limited  by the  specific mocked object instance.
		// The not-defined method in the expectation would be implemented as default "real" behaivour
		//未mock函数调用真实的class方法
		 new Expectations(anySimpleTool) {
		        {
		        	anySimpleTool.fun1(anyString);
		            result = "MOCK";
		        }
	     };	     
	     
	   //defined in expectations
	     System.out.println(anySimpleTool.fun1("param"));
	     
	   //not defined in expectations for mocked obj, call "real" method
	     System.out.println(anySimpleTool.fun3("param"));
	     
	   //NOT defined in expectations since only anySimpleTool instance are mocked. Any other instance won't be affected
	   //Would call real method  
	    System.out.println(new UseSimpleTool().fun1("param")); 
	 
	    //MOCK
	    //real: public String fun3(param)
	    //real: public String fun1(param)
	    
	}
	
	
	@Test
	public void testExpectations_Injectable(@Injectable final SimpleTool simpleTool){
		
		// define Expectation with no arguments
		// means the implementation would are replaced by the default mock object.
		// The not-defined method in the expectation would be implemented as default "empty" behaivor
		//未mock函数返回null
		 new Expectations() { 	
		        {
		            simpleTool.fun1(anyString);
		            result = "MOCK";
		        }
	     };	     
	     
	     
	     //defined in expectations
	     System.out.println(simpleTool.fun1("param"));
	     
         //not defined in expectations for mocked obj, return null
	     System.out.println(simpleTool.fun3("param"));
	     
	   //Not MOCKED since only 1 instance are mocked by Injectable, it would call real method
	     System.out.println(new UseSimpleTool().fun1("param")); 
	     
      //	MOCK
      //    null
      //    real: public String fun1(param)

	}
	
	@Test
	public void testExpectations_Injectable_withClass(@Injectable final SimpleTool simpleTool){
		
		// define Expectation with no arguments
		// means the implementation would are replaced by the default mock object.
		// The not-defined method in the expectation would be implemented as default "empty" behaivor
		//未mock函数返回null
		 new Expectations(SimpleTool.class) { 	
		        {
		            simpleTool.fun1(anyString);
		            result = "MOCK";
		        }
	     };	     
	     //defined in expectations
	     System.out.println(simpleTool.fun1("param"));
	     
         //not defined in expectations for mocked obj, return null
	     System.out.println(simpleTool.fun3("param"));
	     
	   //Not MOCKED since only 1 instance are mocked by Injectable, it would call real method
	     System.out.println(new UseSimpleTool().fun1("param")); 
	     
      //	MOCK
      //    null
      //    real: public String fun1(param)

	}	
	
	@Test
	public void testExpectations_Injectable_withInstance(@Injectable final SimpleTool simpleTool){
		
		// define Expectation with no arguments
		// means the implementation would are replaced by the default mock object.
		// The not-defined method in the expectation would be implemented as default "empty" behaivor
		//未mock函数返回null
		 new Expectations(simpleTool) { 	
		        {
		            simpleTool.fun1(anyString);
		            result = "MOCK";
		        }
	     };	     
	     //defined in expectations
	     System.out.println(simpleTool.fun1("param"));
	     
         //not defined in expectations for mocked obj, return null
	     System.out.println(simpleTool.fun3("param"));
	     
	   //Not MOCKED since only 1 instance are mocked by Injectable, it would call real method
	     System.out.println(new UseSimpleTool().fun1("param")); 	     
	     
	     
      //MOCK
      //null
      //real: public String fun1(param)

	}	
	
	@Test
	public void testExpectation_WithDelegate(@Mocked final SimpleTool simpleTool) {

	    new Expectations() {
	        {
	            simpleTool.fun1(anyString);
	            result = new Delegate<String>() {
	                @SuppressWarnings("unused")
					public String aDelegateMethod(String str) {
	                    return str.equals("param0") ? "MOCK0" : "MOCK1";
	                }
	            };
	        }
	    };

	    System.out.println(simpleTool.fun1("param0"));
	    System.out.println(simpleTool.fun3("param"));
	    System.out.println(new UseSimpleTool().fun1("param1"));

	    new Verifications() {
	        {
	            simpleTool.fun1(anyString);
	            times = 2;
	        }
	    };
	    
       //	    MOCK0
       //       null
       //	    MOCK1

	}
	
	
	
}
