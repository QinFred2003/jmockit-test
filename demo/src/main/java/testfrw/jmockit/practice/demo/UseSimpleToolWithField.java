package testfrw.jmockit.practice.demo;

public class UseSimpleToolWithField {
     private SimpleTool simpleTool;
     //private SimpleTool anotherSimpleTool;
     
	 public String fun1(String name) {	        
	        return simpleTool.fun1(name);
	    }
	 
	 public String fun2(String name){
		 return " real: fun2 "+name;
	 }
}
