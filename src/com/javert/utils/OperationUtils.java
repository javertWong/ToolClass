package com.javert.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OperationUtils {
	public static final String SUM = "sum";
	public static final String SUB = "sub";
	public static final String MUL = "mul";
	public static final String DIV = "div";
	public static final String DIV1 = "div1";
	public static final String DIV2 = "div2";
	public static final String SUM1 = "sum1";
	public static final String SUB1 = "sub1";
	
	/**
	 * 数量金额的计算
	 * 
	 * @param num1
	 * @param num2
	 * @param op
	 * @return
	 */
	public static String getBigDecimalResult(String num1, String num2, String op) {
		num1 = num1 == null ? "0" : num1;
		num2 = num2 == null ? "0" : num2;
		BigDecimal b1 = new BigDecimal(num1);
		BigDecimal b2 = new BigDecimal(num2);
		BigDecimal result = new BigDecimal(0.00);
		switch (op) {
		case SUM:
			result = b1.add(b2).setScale(2, RoundingMode.HALF_UP);
			break;
		case SUM1:
			result = b1.add(b2).setScale(4, RoundingMode.HALF_UP);
			break;
		case SUB:
			result = b1.subtract(b2).setScale(2, RoundingMode.HALF_UP);
			break;
		case SUB1:
			result = b1.subtract(b2).setScale(4, RoundingMode.HALF_UP);
			break;
		case MUL:
			result = b1.multiply(b2).setScale(4, RoundingMode.HALF_UP);
			break;
		case DIV:
			result = b1.divide(b2, 2, RoundingMode.HALF_UP);
			break;
		case DIV1:
			result = b1.divideToIntegralValue(b2);// 取整
			break;
		case DIV2:
			result = b1.divideAndRemainder(b2)[1];// 取余
			break;
		default:
			break;
		}
		return result.toString();

	}
}
