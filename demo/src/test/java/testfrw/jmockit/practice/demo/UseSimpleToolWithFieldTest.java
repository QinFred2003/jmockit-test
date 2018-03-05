package testfrw.jmockit.practice.demo;

import org.junit.Test;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;

public class UseSimpleToolWithFieldTest {
	@Tested
	UseSimpleToolWithField useSimpleTool;
	
	@Injectable SimpleTool anotherSimpleTool;
	
	@Test
	public void fieldWithInjectableObj(@Injectable final SimpleTool simpleTool){
		new Expectations(){			
			{
				simpleTool.fun1(anyString);
				result = "MOCKED";
			}
			
		};
		
		System.out.println(simpleTool.fun1("param"));
		System.out.println(simpleTool.fun3("param"));
		System.out.println(useSimpleTool.fun1("param"));
       //		MOCKED
       //		null
       //		MOCKED
		
	}
	
	@Test
	public void fieldWithInjectableObj_differentName(@Injectable final SimpleTool simpleTool2){
		new Expectations(){			
			{
				simpleTool2.fun1(anyString);
				result = "MOCKED";
			}
			
		};
		
		System.out.println(simpleTool2.fun1("param"));
		System.out.println(simpleTool2.fun3("param"));
		System.out.println(useSimpleTool.fun1("param"));
       //		MOCKED
       //		null
       //		MOCKED
		
	}
	
	@Test
	public void fieldWithInjectableObj_withMultileInjectObjs_sameFieldName(@Injectable final SimpleTool simpleTool2, @Injectable final SimpleTool simpleTool ){
		new Expectations(){			
			{
				simpleTool.fun1(anyString);
				result = "MOCKED";
			}			
		};
		new Expectations(useSimpleTool){			
			{
				useSimpleTool.fun2(anyString);
				result = "MOCKED on a tested object";
			}			
		};
		
		
		System.out.println(simpleTool.fun1("param"));
		System.out.println(simpleTool.fun3("param"));
		System.out.println(useSimpleTool.fun1("param"));
		System.out.println(useSimpleTool.fun2("param"));
       //		MOCKED
       //		null
       //		MOCKED
		
	}
	
	
	@Test
	public void fieldWithInjectableObj_withMultileInjectObjs_noSameFieldName_1(@Injectable final SimpleTool simpleTool2, @Injectable final SimpleTool simpleTool3 ){
		new Expectations(){			
			{
				simpleTool3.fun1(anyString);
				result = "MOCKED_By_3";
			};
			{
				simpleTool2.fun1(anyString);
				result = "MOCKED_By_2";
			}
			
		};
		
		System.out.println(simpleTool2.fun1("param"));
		System.out.println(simpleTool3.fun1("param"));
		System.out.println(useSimpleTool.fun1("param"));
        //		MOCKED_By_2
        //		MOCKED_By_3
        //		MOCKED_By_2

		
	}
	
	@Test
	public void fieldWithInjectableObj_withMultileInjectObjs_noSameFieldName_2(@Injectable final SimpleTool simpleTool3, @Injectable final SimpleTool simpleTool2 ){
		new Expectations(){			
			{
				simpleTool3.fun1(anyString);
				result = "MOCKED_By_3";
			}
			{
				simpleTool2.fun1(anyString);
				result = "MOCKED_By_2";
			}			
		};
		

		
		System.out.println(simpleTool2.fun1("param"));
		System.out.println(simpleTool3.fun1("param"));
		System.out.println(useSimpleTool.fun1("param"));

        //		MOCKED_By_2
        //		MOCKED_By_3
        //		MOCKED_By_3

		
	}
	
	
}
