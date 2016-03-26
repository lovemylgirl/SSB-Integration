package org.dream.common.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;


public class CustomDateSerializer extends JsonSerializer<Date> {

	public static final String DATE_FORMATE = "yyyy-MM-dd HH:mm:ss";

	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
			throws IOException, JsonProcessingException {
		SimpleDateFormat format = new SimpleDateFormat(DATE_FORMATE);
		String formateDate = format.format(value);
		jgen.writeString(formateDate);
	}
}
