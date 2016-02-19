package org.dream.common.exception.enums;

import org.dream.common.exception.ErrorCode;

public enum UserCode implements ErrorCode {

	ERR_USER_NOT_FOUND(1401), 
	ERR_USER_EXIST_ALREADY(1402);

	private final int number;

	private UserCode(int number) {
		this.number = number;
	}

	@Override
	public int getNumber() {
		return number;
	}

}
