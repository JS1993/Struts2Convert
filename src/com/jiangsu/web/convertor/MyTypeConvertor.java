package com.jiangsu.web.convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;


/**
 * 需求：
 * 	  把表单中的MM/dd/yyyy格式的数据转成日期类型
 *   把数据库中的本地日期格式，转成MM/dd/yyyy形式输出
 * 
 * 自定义子类转换器：
 * 	第一步：编写一个类，继承自StrutsTypeConverter，实现convertFromString，convertToString抽象方法
 * @author jiangsu
 *
 */

public class MyTypeConvertor extends StrutsTypeConverter {

	
	//定义一个类型转换器
	private SimpleDateFormat smt = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * 把字符串数组中的数据转成日期类型
	 * 
	 * 方法参数详解：
	 * 	Map context：是OGNL的上下文对象，我们暂时不知道，所以暂时也不用
	 *  String[] values：要转换的数据
	 *  Class toClass：目标类型
	 */
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		//1.先看看有没有数据
		if (values.length==0||values==null) return null;
		
		//2.取出数组中的第一个元素
		String date = values[0];
		
		//3.判断目标类型的字节码是不是日期类型
		if (java.util.Date.class == toClass) {
			try {
				return smt.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	/**
	 * 把日期类型的数据转换成字符串
	 * 
	 * 方法参数详解：
	 * 	Map context：是OGNL的上下文对象，我们暂时不知道，所以暂时也不用
	 *  Object o：要转换的数据
	 */
	@Override
	public String convertToString(Map context, Object o) {
		//1.判断object是不是日期类型
		if (o instanceof Date) {
			Date date = (Date)o;
			return smt.format(date);
		}
		return null;
	}

}
