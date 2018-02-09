/**
 * 
 */
package com.vispractice.fmc.business.base.utils;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.apache.commons.betwixt.XMLIntrospector;
import org.apache.commons.betwixt.io.BeanReader;
import org.apache.commons.betwixt.io.BeanWriter;
import org.xml.sax.InputSource;

import com.vispractice.fmc.base.utils.StringUtil;
import com.vispractice.fmc.business.base.exception.WorkflowException;

/**
 * @author fyx
 * 
 */
public class BeanReaderWriter {

	private Class<?> clazz = null;

	public BeanReaderWriter() {

	}

	public BeanReaderWriter(Class<?> clazz) {
		this.clazz = clazz;
		reNewBeanReader(clazz);	
	}

	private XMLIntrospector introspector = null;

	private XMLIntrospector getIntrospector(Class<?> cls) throws Exception {
		if (introspector == null) {
			introspector = new XMLIntrospector();
			introspector.getConfiguration().setAttributesForPrimitives(true);
			introspector.register(new InputSource(cls
					.getResourceAsStream("betwixt-config.xml")));
		}
		return introspector;
	}

	public String write(Object obj) {
		try {
			StringWriter sWriter = new StringWriter();
			BeanWriter beanWriter = new BeanWriter(sWriter);
			beanWriter.setXMLIntrospector(getIntrospector(obj.getClass()));
			beanWriter.getBindingConfiguration().setMapIDs(false);
			beanWriter.write(obj);
			return sWriter.toString();
		} catch (Exception e) {
			throw new WorkflowException(e.getMessage());
		}
	}

	private BeanReader beanReader = null;

	private BeanReader newBeanReader(Class<?> cls) throws Exception {
		BeanReader beanReader = new BeanReader();
		beanReader.setXMLIntrospector(getIntrospector(cls));
		beanReader.registerBeanClass(cls);
		return beanReader;
	}

	private BeanReader getBeanReader(Class<?> cls) throws Exception {
		if (beanReader == null) {
			beanReader = newBeanReader(cls);
		}
		return beanReader;
	}

	private void reNewBeanReader(Class<?> cls) {
		try {
			beanReader = newBeanReader(cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T parse(String xml, String defXml) {
		return (T) parse(xml, defXml, clazz);
	}

	@SuppressWarnings("unchecked")
	public <T> T parse(String xml) {
		return (T) parse(xml, clazz);
	}

	@SuppressWarnings("unchecked")
	public <T> T parse(String xml, Class<T> cls) {
		try {
			// System.out.println("替换前 = -------- \r\n" + xml);
			xml = xml.replaceAll("[\\x00-\\x08\\x0b-\\x0c\\x0e-\\x1f]", "");
			// System.out.println("替换后 = -------- \r\n" + xml);
			Reader reader = new StringReader(xml);
			// reader = new FilterUnsupportedCharReader(reader);
			return (T) getBeanReader(cls).parse(reader);
		} catch (Exception e) {
			reNewBeanReader(cls);
			throw new WorkflowException(e.getMessage());
		}
	}

	public <T> T parse(String xml, String defXml, Class<T> cls) {
		return parse(StringUtil.isNull(xml) ? defXml : xml, cls);
	}

	// 把DictBeanReader中一样的FilterUnsupportedCharReader提取出来作为公共类
	// private static class FilterUnsupportedCharReader extends BufferedReader {
	//
	// public FilterUnsupportedCharReader(Reader reader) {
	// super(reader);
	// }
	//
	// @Override
	// public void close() throws IOException {
	// super.close();
	// }
	//
	// @Override
	// public int read() throws IOException {
	// throw new UnsupportedOperationException();
	// }
	//
	// @Override
	// public int read(java.nio.CharBuffer target) throws IOException {
	// throw new UnsupportedOperationException();
	// }
	//
	// @Override
	// public int read(char[] cbuf) throws IOException {
	// throw new UnsupportedOperationException();
	// }
	//
	// @Override
	// public int read(char cbuf[], int off, int len) throws IOException {
	//
	// int r = super.read(cbuf, off, len);
	// if (r > 0) {
	// int end = r + off;
	// for (int i = off; i < end; i++) {
	// char ch = cbuf[i];
	// // if (ch == 0 && i == r) {
	// // break;
	// // }
	// if ((ch >= 0x00 && ch <= 0x08)
	// || (ch >= 0x0b && ch <= 0x0c)
	// || (ch >= 0x0e && ch <= 0x1f)) {
	// move(cbuf, i, end);
	// r--;
	// }
	// }
	// }
	// return r;
	// }
}
