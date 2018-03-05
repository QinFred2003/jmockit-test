package testfrw.jmockit.practice.demo;

import java.io.InputStream;

import org.junit.Test;
import static org.junit.Assert.*;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Mocked;

public class InputStreamTest {
	
	@Test
	public void concatenateInputStreamsTest(@Injectable final InputStream input1, @Injectable final InputStream input2)
	   throws Exception
	{
	   new Expectations() {{
	      input1.read(); returns(1, 2, -1);
	      input2.read(); returns(3, -1);
	   }};

	   InputStream concatenatedInput = new ConcatenatingInputStream(input1, input2);
	   byte[] buf = new byte[3];
	   concatenatedInput.read(buf);

	   assertArrayEquals(new byte[] {1, 2, 3}, buf);
	}
	
	

	
}
