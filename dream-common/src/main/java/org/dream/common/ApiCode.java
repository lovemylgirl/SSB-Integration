package org.dream.common;

public class ApiCode {

	// ----------------------------通用错误-------------------------
	public static final String SUCCESS = "A00000";// 成功
	public static final String ERR_WRONG_PARAMS = "A00001";// 参数错误
	public static final String ERR_SYSTEM = "A00002";// 系统错误
	public static final String UPLOAD_ERR = "A00005";// 上传错误

	// -----------------------------用户信息错误----------------------
	public static final String ERR_USER_NOT_ONE_BY_WECHATID = "A01411"; // 用户不唯一wechatId
}
