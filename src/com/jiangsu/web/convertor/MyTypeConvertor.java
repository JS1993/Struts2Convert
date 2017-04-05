package com.jiangsu.web.convertor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.struts2.util.StrutsTypeConverter;


/**
 * ����
 * 	  �ѱ��е�MM/dd/yyyy��ʽ������ת����������
 *   �����ݿ��еı������ڸ�ʽ��ת��MM/dd/yyyy��ʽ���
 * 
 * �Զ�������ת������
 * 	��һ������дһ���࣬�̳���StrutsTypeConverter��ʵ��convertFromString��convertToString���󷽷�
 * @author jiangsu
 *
 */

public class MyTypeConvertor extends StrutsTypeConverter {

	
	//����һ������ת����
	private SimpleDateFormat smt = new SimpleDateFormat("MM/dd/yyyy");
	
	/**
	 * ���ַ��������е�����ת����������
	 * 
	 * ����������⣺
	 * 	Map context����OGNL�������Ķ���������ʱ��֪����������ʱҲ����
	 *  String[] values��Ҫת��������
	 *  Class toClass��Ŀ������
	 */
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		//1.�ȿ�����û������
		if (values.length==0||values==null) return null;
		
		//2.ȡ�������еĵ�һ��Ԫ��
		String date = values[0];
		
		//3.�ж�Ŀ�����͵��ֽ����ǲ�����������
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
	 * ���������͵�����ת�����ַ���
	 * 
	 * ����������⣺
	 * 	Map context����OGNL�������Ķ���������ʱ��֪����������ʱҲ����
	 *  Object o��Ҫת��������
	 */
	@Override
	public String convertToString(Map context, Object o) {
		//1.�ж�object�ǲ�����������
		if (o instanceof Date) {
			Date date = (Date)o;
			return smt.format(date);
		}
		return null;
	}

}
