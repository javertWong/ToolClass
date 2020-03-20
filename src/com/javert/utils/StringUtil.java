package com.javert.utils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.sf.json.JSONArray;

public class StringUtil {
	/**
	 * 判断字符串为空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(Object s) {
		if (null == s) {
			return true;
		} else {
			return "".equals(s.toString().trim());
		}
	}

	/**
	 * 判断字符串非空
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(Object s) {
		if (null == s) {
			return false;
		} else {
			return !("".equals(s.toString().trim()));
		}
	}

	/**
	 * 判断两对象是否相同
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean isEqual(Object o1, Object o2) {
		if (o1 == null) {
			return o2 == null;
		} else {
			return o1.equals(o2);
		}
	}

	/**
	 * 判断两对象是否不相同
	 * 
	 * @param o1
	 * @param o2
	 * @return
	 */
	public static boolean isNotEqual(Object o1, Object o2) {
		if (o1 == null) {
			return o2 != null;
		} else {
			return !o1.equals(o2);
		}
	}

	/**
	 * 比较两个对象大小
	 * 
	 * @param o1 对象1
	 * @param o2 对象2
	 * @return 比较结果
	 */
	public static int compare(Object o1, Object o2) {
		if (isEmpty(o1) || isEmpty(o2)) {
			return -1;
		}
		String str1 = o1.toString();
		String str2 = o2.toString();
		return str1.compareToIgnoreCase(str2);
	}

	/**
	 * 判断字符串是否是数字
	 * 
	 * @param str 字符串
	 * @return 是否是数字
	 */
	public static boolean isDigital(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (!isNum.matches()) {
			return false;
		}
		return true;
	}

	/**
	 * 字符串连接，使用指定字符连接
	 * 
	 * @param o 数组
	 * @param flag 连接符
	 * @return
	 */
	public static String join(Object[] o, String flag) {
		StringBuffer str_buff = new StringBuffer();
		for (int i = 0, len = o.length; i < len; i++) {
			str_buff.append(String.valueOf(o[i]));
			if (i < len - 1)
				str_buff.append(flag);
		}
		return str_buff.toString();
	}

	/**
	 * 将字符串str中的a替换成b
	 * 
	 * @param str 待处理的字符串
	 * @param a 待替换的字符串
	 * @param b 替换的字符串
	 * @return 处理后的字符串
	 */
	public static String replaceAToB(String str, String a, String b) {
		if (str == null) {
			return null;
		}
		return str.replace(a, b);
	}

	/**
	 * 检查非法字符
	 * 
	 * @param regex 检查的表达式
	 * @param str 待检查的字符串
	 * @return 是否合法
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);

		return matcher.lookingAt();
	}

	/**
	 * 计算数组的hashCode
	 * 
	 * @param stringArr 字符串数组
	 * @return 数组的hashCode
	 */
	public static int hashCodeOfStringArray(String[] stringArr) {
		if (stringArr == null) {
			return 0;
		}
		int hashCode = 17;
		for (int i = 0; i < stringArr.length; i++) {
			String value = stringArr[i];
			hashCode = hashCode * 31 + (value == null ? 0 : value.hashCode());
		}
		return hashCode;
	}
	
	/**
	 * list to string ,用【,】分隔
	 * @param stringList
	 * @return
	 */
	public static String listToString(List<String> stringList){
        if (stringList==null) {
            return null;
        }
        StringBuilder result=new StringBuilder();
        boolean flag=false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            }else {
                flag=true;
            }
            result.append(string);
        }
        return result.toString();
    }
	
	/**
	 * 将多个资源的[名称]用 ","拼接成字符串
	 * @param array 
	 * @param key 在JSONObject中 key必须存在
	 * @return
	 */
	public static String getNames(JSONArray array,String key){
		StringBuffer buffer = new StringBuffer();
		if (array.size()==0) {
			return "";
		}
		for (int i = 0; i < array.size(); i++) {
			if(array.getJSONObject(i).containsKey(key)){
				buffer.append(array.getJSONObject(i).getString(key));
				if (i<array.size()-1) {
					buffer.append(",");
				}
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 将JSONArray转化成用,分割的字符串
	 * @param array
	 * @return
	 */
	public static String jsonFormatString(JSONArray array){
		StringBuffer buffer = new StringBuffer();
		if (array.size()==0) {
			return "";
		}
		for (int i = 0; i < array.size(); i++) {
				buffer.append(array.getString(i));
				if (i<array.size()-1) {
					buffer.append(",");
				}
		}
		return buffer.toString();
	}
	
	/**
	 * 将字符串首个字母大写,主要用于反射
	 * @param str 
	 * @return
	 */
	public static String captureName(String str){
		char[] cs = str.toCharArray();
		cs[0] -= 32;
		return String.valueOf(cs);
	}
	
	/**
	 * 数据格式必须是 [a,b,c,d,e]
	 */
	public static String[] stringToArray(String s){
		String sTemp = s.substring(1, s.length()-1);
		String[] sArray = sTemp.split(",");
		return sArray;
	}
}
