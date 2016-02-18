package org.dream.common.exception;

import java.util.ResourceBundle;

import org.junit.Test;

public class TestException {

	private static final int MIN_LENGTH = 10;
	

	@Test
	public void example1() {
		try {
			throw new DreamException(UserCode.ERR_USER_NOT_FOUND);
		} catch (DreamException e) {
			if (e.getErrorCode() == UserCode.ERR_USER_NOT_FOUND) {
				System.out.println("User is not found!");
			}
		}
	}

	@Test
	public void example2() {
		validate("email", "abc");
	}

	@Test
	public void example3() {
		System.out.println(getUserText(ValidationCode.VALUE_TOO_SHORT));
	}

	public void validate(String field, String value) {
		if (value == null || value.length() < MIN_LENGTH) {
			throw new DreamException(ValidationCode.VALUE_TOO_SHORT).set("field", field).set("value", value)
					.set("min-length", MIN_LENGTH);
		}
	}

	public static String getUserText(ErrorCode errorCode) {
		if (errorCode == null) {
			return null;
		}
		String key = errorCode.getClass().getSimpleName() + "__" + errorCode;
		ResourceBundle bundle = ResourceBundle.getBundle("org.dream.common.exceptions");
		return bundle.getString(key);
	}
	
}
