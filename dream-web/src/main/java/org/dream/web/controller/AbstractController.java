package org.dream.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.dream.common.ApiCode;
import org.dream.common.exception.EvcharException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;

public abstract class AbstractController {

	/**
	 * com.fasterxml.jackson.databind.ObjectMapper
	 */
	@Resource
	private ObjectMapper jacksonObjectMapper;

	// private static final Logger logger =
	// LoggerFactory.getLogger(AbstractController.class);

	@ExceptionHandler(EvcharException.class)
	@ResponseStatus(HttpStatus.OK)
	@ResponseBody
	public String handleMessageException(HttpServletRequest request, EvcharException ex, HttpServletResponse response) {
		// String requestParam = generateRequestParamStr(request);
		// logger.info("throw EvcharException: " + requestParam);
		return createJsonRespone(ex.getCode(), ex.getData(), ex.getMessage());
	}

	protected String createJsonRespone(String code, Object data, String msg) {
		Map<String, Object> result = new HashMap<String, Object>();
		result.put("code", code);
		result.put("msg", msg);
		result.put("data", data);
		String json = "";
		try {
			json = this.jacksonObjectMapper.writeValueAsString(result);
		} catch (IOException e) {
			// logger.error("Json转换失败", e);
		}
		return json;
	}

	public void handleValidFieldError(Errors errors) {
		if (errors.hasErrors()) {
			FieldError error = (FieldError) errors.getAllErrors().get(0);
			StringBuilder sb = new StringBuilder("参数错误：");
			String errorMessage = sb.append(error.getField()).append(" ").append(error.getDefaultMessage()).toString();
			throw new EvcharException(ApiCode.ERR_WRONG_PARAMS, errorMessage);
		}
	}

	/**
	 * 获取request参数参数列表
	 * 
	 * @param request
	 * @return
	 */
	private String generateRequestParamStr(HttpServletRequest request) {
		StringBuilder sb = new StringBuilder();
		sb.append(request.getRequestURI());
		sb.append(". param: ");
		Map<?, ?> params = request.getParameterMap();
		for (Object key : params.keySet()) {
			Object obj = params.get(key);
			if (obj instanceof String[]) {
				ArrayList<String> list = Lists.newArrayList((String[]) obj);
				Collections.sort(list);
				for (String v : list) {
					sb.append(key).append("=").append(StringUtils.defaultIfEmpty(v, "")).append(";");
				}
			} else {
				String val = "" + params.get(key);
				sb.append(key).append("=").append(StringUtils.defaultIfEmpty(val, "")).append(";");
			}
		}
		String requestParam = sb.toString();
		return requestParam;
	}

	/**
	 * 初始化绑定器 此处解决 参数Date类型
	 * 
	 * @DateTimeFormat 使用此注解替代
	 */
	// @InitBinder
	// public void initBinder(WebDataBinder binder) throws Exception {
	// binder.registerCustomEditor(Date.class, new CustomDateEditor(new
	// SimpleDateFormat("yyyy-MM-dd"), true));
	// }

}
