package org.dream.common.exception;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

public class DreamException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public static DreamException warp(Throwable exception, ErrorCode errorCode) {
		if (exception instanceof DreamException) {
			DreamException de = (DreamException) exception;
			if (errorCode != null && errorCode != de.getErrorCode()) {
				return new DreamException(exception.getMessage(), exception, errorCode);
			}
			return de;
		} else {
			return new DreamException(exception.getMessage(), exception, errorCode);
		}
	}

	public static DreamException warp(Throwable exception) {
		return warp(exception, null);
	}

	private ErrorCode errorCode;
	private final Map<String, Object> properties = new TreeMap<String, Object>();

	public DreamException(ErrorCode errorCode) {
		this.setErrorCode(errorCode);
	}

	public DreamException(String message, ErrorCode errorCode) {
		super(message);
		this.setErrorCode(errorCode);
	}

	public DreamException(Throwable cause, ErrorCode errorCode) {
		super(cause);
		this.setErrorCode(errorCode);
	}

	public DreamException(String message, Throwable cause, ErrorCode errorCode) {
		super(message, cause);
		this.setErrorCode(errorCode);
	}

	public ErrorCode getErrorCode() {
		return errorCode;
	}

	public DreamException setErrorCode(ErrorCode errorCode) {
		this.errorCode = errorCode;
		return this;
	}

	public Map<String, Object> getProperties() {
		return properties;
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String name) {
		return (T) properties.get(name);
	}

	public DreamException set(String name, Object value) {
		properties.put(name, value);
		return this;
	}

	@Override
	public void printStackTrace(PrintStream s) {
		synchronized (s) {
			printStackTrace(new PrintWriter(s));
		}
	}

	@Override
	public void printStackTrace(PrintWriter s) {
		synchronized (s) {
			s.println(this);
			s.println("\t-------------------------------");
			if (errorCode != null) {
				s.println("\t" + errorCode + ":" + errorCode.getClass().getName());
			}
			for (String key : properties.keySet()) {
				s.println("\t" + key + "=[" + properties.get(key) + "]");
			}
			s.println("\t-------------------------------");
			StackTraceElement[] trace = getStackTrace();
			for (int i = 0; i < trace.length; i++)
				s.println("\tat " + trace[i]);

			Throwable ourCause = getCause();
			if (ourCause != null) {
				ourCause.printStackTrace(s);
			}
			s.flush();
		}
	}
}
