package testfrw.jmockit.practice.demo;

public class ClsUnderTest {
	
	private AnotherDependencyClass adc = new AnotherDependencyClass() ;	
	private DependencyClass dc = new DependencyClass();	
	public YetDepenencyClass getYdc() {
		return ydc;
	}

	public void setYdc(YetDepenencyClass ydc) {
		this.ydc = ydc;
	}

	private YetDepenencyClass ydc ; // not initial with instance
	public void doSomething(String str){
		System.out.println("doSomething : " +str);
		dc.helloWord(str);
	}
	
	public void setAnotherDependency(AnotherDependencyClass adc){
		this.adc = adc;
	}
	
	public void getIntValue(){
		System.out.println(this.adc.calcValue());
	}
	
	public void getRealValue(){
		System.out.println("RealValue");
	}
	
	public void getAnotherIntValue(){
		System.out.println(this.ydc.calcValue());
	}
}
