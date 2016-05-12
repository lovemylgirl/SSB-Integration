package org.dream.common.typehandler;

public enum UserType implements ValuedEnum {

	CONSUMER(1), ADMIN(2), OWNER(3), CARER(4);

	private int value;

	private UserType(int value) {
		this.value = value;
	}

	@Override
	public int getValue() {
		return value;
	}

}
