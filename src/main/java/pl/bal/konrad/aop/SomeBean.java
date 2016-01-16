package pl.bal.konrad.aop;

import org.springframework.stereotype.Component;

@Component
public class SomeBean {
	public String someMethod() {
		return "Some Value";
	}

	public void throwSomeException() {
		throw new RuntimeException();
	}

}
