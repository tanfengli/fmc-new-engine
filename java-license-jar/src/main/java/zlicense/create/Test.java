package zlicense.create;

import java.io.InputStream;

public class Test {

	
	public static void main(String[] args){
		InputStream localInputStream = Test.class.getResourceAsStream("privateKeys.store");
	}
}
