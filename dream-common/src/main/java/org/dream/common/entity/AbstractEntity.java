package org.dream.common.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Transient;

@SuppressWarnings("serial")
public class AbstractEntity implements Serializable {

	@Transient
	private Map<String, Object> paramMap;

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}
}
