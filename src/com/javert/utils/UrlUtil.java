package com.javert.utils;

import javax.servlet.http.HttpServletRequest;

/** url相关方法 */
public class UrlUtil {
	/**
	 * 获取url请求前缀
	 * 
	 * @explain http://localhost:8080/test
	 * @param request对象
	 * @return
	 */
	public static String getRequestPrefix(HttpServletRequest request) {
		// 网络协议
		String networkProtocol = request.getScheme();
		// 网络ip
		String ip = request.getServerName();
		// 端口号
		int port = request.getServerPort();
		String urlPrefix = networkProtocol + "://" + ip + ":" + port;
		return urlPrefix;
	}
}
