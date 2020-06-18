package com.casestudy.odcw.util;

import org.springframework.stereotype.Component;

@Component
public class ODCWUtils {

	public String prepareId(Integer size,String startwith) {
		
		StringBuilder builder = new StringBuilder();
		builder.append(startwith)
			.append(1000+size);
		
		return builder.toString();
	}
}
