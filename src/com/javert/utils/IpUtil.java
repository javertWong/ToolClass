package com.javert.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;

public class IpUtil {
	/**
	 * 获取登录用户IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.equals("0:0:0:0:0:0:0:1")) {
			ip = "本地";
		}
		return ip;
	}

	/**
	 * 根据IP地址获取mac地址
	 * 
	 * @param ipAddress
	 *            127.0.0.1
	 * @return
	 * @throws SocketException
	 * @throws UnknownHostException
	 */
	public static String getLocalMac(String ipAddress) throws SocketException,
			UnknownHostException {
		// TODO Auto-generated method stub
		String str = "";
		String macAddress = "";
		final String LOOPBACK_ADDRESS = "127.0.0.1";
		// 如果为127.0.0.1,则获取本地MAC地址。
		if (LOOPBACK_ADDRESS.equals(ipAddress)) {
			InetAddress inetAddress = InetAddress.getLocalHost();
			// 貌似此方法需要JDK1.6。
			byte[] mac = NetworkInterface.getByInetAddress(inetAddress)
					.getHardwareAddress();
			// 下面代码是把mac地址拼装成String
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				if (i != 0) {
					sb.append("-");
				}
				// mac[i] & 0xFF 是为了把byte转化为正整数
				String s = Integer.toHexString(mac[i] & 0xFF);
				sb.append(s.length() == 1 ? 0 + s : s);
			}
			// 把字符串所有小写字母改为大写成为正规的mac地址并返回
			macAddress = sb.toString().trim().toUpperCase();
			return macAddress;
		} else {
			// 获取非本地IP的MAC地址
			try {
				System.out.println(ipAddress);
				Process p = Runtime.getRuntime()
						.exec("nbtstat -A " + ipAddress);
				System.out.println("===process==" + p);
				InputStreamReader ir = new InputStreamReader(p.getInputStream());

				BufferedReader br = new BufferedReader(ir);

				while ((str = br.readLine()) != null) {
					if (str.indexOf("MAC") > 1) {
						macAddress = str.substring(str.indexOf("MAC") + 9,
								str.length());
						macAddress = macAddress.trim();
						System.out.println("macAddress:" + macAddress);
						break;
					}
				}
				p.destroy();
				br.close();
				ir.close();
			} catch (IOException ex) {
			}
			return macAddress;
		}
	}
}
