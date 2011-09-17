package org.org.springframework.security;

public class MyNotAnnotatedServiceImpl implements IMyAnnotatedServiceInterface {
	@SuppressWarnings("unused")
	private String phonyField;
	
	
	public String getResult() {
		return "result";
	}

	
	public String getResult2() {
		return "result2";
	}
}
