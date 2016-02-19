package org.dream.common.util;

import java.util.HashMap;
import java.util.Set;

import com.cloopen.rest.sdk.CCPRestSDK;

public class MessageUtil {

	private static String messageServerAddress = "app.cloopen.com";
	private static String messageServerPort = "8883";
	private static String appID = "8a48b55150dc87820150dd92ad0707f9";
	private static String accountCode = "8a48b55150b86ee80150bdff4f6b112c";
	private static String passWordKey = "ad753b07065342acbcdd6690712ce930";
	private static CCPRestSDK ccpRestSDK;

	static {
		/**
		 * 初始化SDK
		 */
		ccpRestSDK = new CCPRestSDK();

		/**
		 * 初始化服务器地址和端口，格式如下，服务器地址不需要写https://
		 * 
		 * 沙盒环境（用于应用开发调试）：restAPI.init("sandboxapp.cloopen.com", "8883");
		 * 
		 * 生产环境（用户应用上线使用）：restAPI.init("app.cloopen.com", "8883");
		 */
		ccpRestSDK.init(messageServerAddress, messageServerPort);

		/**
		 * 
		 * 初始化主帐号名称和主帐号令牌,对应官网开发者主账号下的ACCOUNT SID和AUTH TOKEN
		 * 
		 * ACOUNT SID和AUTH TOKEN在登陆官网后，在“应用-管理控制台”中查看开发者主账号获取
		 * 
		 * 参数顺序：第一个参数是ACOUNT SID，第二个参数是AUTH TOKEN
		 */
		ccpRestSDK.setAccount(accountCode, passWordKey);

		/**
		 * 初始化应用ID
		 * 
		 * 测试开发可使用“测试Demo”的APP ID，正式上线需要使用自己创建的应用的App ID
		 * 
		 * 应用ID的获取：登陆官网，在“应用-应用列表”，点击应用名称，看应用详情获取APP ID
		 * 
		 */
		ccpRestSDK.setAppId(appID);
	}

	/**
	 * 发送信息
	 */
	public static String sendMessageCode(String phone, String timeOut) {
		HashMap<String, Object> result = null;
		String x = Integer.toString((int) (Math.random() * 1000000));

		/**
		 * phone : 手机号
		 * 
		 * "48076" : 短信模板编号
		 * 
		 * x: --> {0}
		 * 
		 * timeOut: --> {1}
		 * 
		 */
		result = ccpRestSDK.sendTemplateSMS(phone, "48076", new String[] { x, timeOut });
		if ("000000".equals(result.get("statusCode"))) {
			return x;
		} else {

			/**
			 * 异常返回输出错误码和错误信息
			 */
			System.out.println("错误码：" + result.get("statusCode") + " 错误信息： " + result.get("statusMsg"));

			return null;
		}
	}

	/**
	 * 获取主账户信息
	 */
	@SuppressWarnings("unchecked")
	public static String queryAccountInfo() {
		HashMap<String, Object> result = null;
		result = ccpRestSDK.queryAccountInfo();
		System.out.println("accountInfo : " + result);
		if ("000000".equals(result.get("statusCode"))) {
			HashMap<String, Object> data = (HashMap<String, Object>) result.get("data");
			Set<String> keySet = data.keySet();
			for (String key : keySet) {
				Object object = data.get(key);
				System.out.println(key + " = " + object);
			}
		} else {
			/**
			 * 异常返回输出错误码和错误信息
			 */
			System.out.println("错误码=" + result.get("statusCode") + " 错误信息= " + result.get("statusMsg"));
		}
		return null;
	}

	public static void main(String[] args) {
		// System.out.println(sendMessageCode("15888836739", "10"));
		// queryAccountInfo();
	}
}
