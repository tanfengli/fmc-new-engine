package com.vispractice.fmc.business.base.utils;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

/**
 * 
 * 编 号：<br/>
 * 名 称：XMLReaderOrCreate<br/>
 * 描 述：用于XML的读取<br/>
 * 完成日期：2017年3月15日 上午9:48:31<br/>
 * 编码作者："ZengCheng"<br/>
 */
public class XMLReaderOrCreate {

	/**
	 * XML转化为DOM
	 */
	public Document xmlToDocument(String xml) {

		StringReader stringReader = new StringReader(xml);
		InputSource inputSource = new InputSource(stringReader);

		// documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		try {
			// 返回documentBuilderFactory对象
			dbf = DocumentBuilderFactory.newInstance();
			// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
			db = dbf.newDocumentBuilder();

			// 得到一个DOM并返回给document对象
			Document dt = db.parse(inputSource);

			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Document 转换为 String(XML)
	 * 
	 * @param doc
	 *            XML的Document对象
	 * @return String
	 */
	public static String doc2String(Document doc) {
		try {
			Source source = new DOMSource(doc);
			StringWriter stringWriter = new StringWriter();
			Result result = new StreamResult(stringWriter);
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.transform(source, result);
			return stringWriter.getBuffer().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 
	 * 实现流程:TODO(获取XML根节点的属性)<br/>
	 * 1.XXX<br/>
	 * 
	 * @Title: getRootElementAttr
	 * @param xml
	 * @return Map<String, String>
	 */
	public Map<String, String> getRootElementAttr(String xml) {
		Element element = null;
		Map<String, String> map = new HashMap<String, String>();
		try {

			// 得到一个DOM并返回给document对象
			Document dt = xmlToDocument(xml);

			// 得到一个elment根元素
			element = dt.getDocumentElement();

			element.getChildNodes();

			if (element.getNodeType() == Node.ELEMENT_NODE) {
				// 遍历当前元素节点的属性
				if (element.hasAttributes()) {
					NamedNodeMap attrs = element.getAttributes();
					for (int i = 0; i < attrs.getLength(); i++) {
						Node attrNode = attrs.item(i);
						// System.out.println(attrNode.getNodeName() +
						// " : "+attrNode.getNodeValue());
						map.put(attrNode.getNodeName(), attrNode.getNodeValue());
					}
				}
			}

			return map;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * 实现流程:通过XML标签名字获取属性列表<br/>
	 * 1.XXX<br/>
	 * 
	 * @Title: getElementAttrByTagName
	 * @param xml
	 * @param tagName
	 * @return List<Map<String,String>>
	 */
	public List<Map<String, String>> getElementAllAttrByTagName(String xml, String tagName) {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();
		try {
			// XML转化为doc
			Document dt = xmlToDocument(xml);

			// 通过标签名获得节点列表
			NodeList nodeList = dt.getElementsByTagName(tagName);

			for (int i = 0; i < nodeList.getLength(); i++) {
				Element node = (Element) nodeList.item(i);
				map = getElementAttribute(node);
				list.add(map);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取当前节点的属性并映射到Map<String, String>
	 */
	public Map<String, String> getElementAttribute(Element element) {

		Map<String, String> map = new HashMap<String, String>();

		if (element.getNodeType() == Node.ELEMENT_NODE) {
			// 遍历当前元素节点的属性
			if (element.hasAttributes()) {
				NamedNodeMap attrs = element.getAttributes();
				for (int i = 0; i < attrs.getLength(); i++) {
					Node attrNode = attrs.item(i);
					// System.out.println("------:" + attrNode.getNodeName()
					// + " : " + attrNode.getNodeValue());
					map.put(attrNode.getNodeName(), attrNode.getNodeValue());
				}
			}
		}

		return map;

	}

	/**
	 * 
	 * 未完成
	 */
	@SuppressWarnings("unused")
	public List<Map<String, String>> getChildNode(String xml, String nodeId) {

		List<Map<String, String>> list = new ArrayList<Map<String, String>>();

		Map<String, String> map = new HashMap<String, String>();
		try {
			// XML转化为doc
			Document dt = xmlToDocument(xml);

			// 得到一个elment根元素
			Element element = dt.getDocumentElement();

			// 得到所有子节点
			NodeList nodeList = element.getChildNodes();

			for (int i = 0; i < nodeList.getLength(); i++) {
				Element node = (Element) nodeList.item(i);
				if (null != node.getAttribute("id") && !node.getAttribute("id").equals(nodeId)) {
					continue;
				} else {

				}
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String updateXmlAttr(String xml, Map<String, String> dataMap) {
		Element element = null;
		StringReader stringReader = new StringReader(xml);
		InputSource inputSource = new InputSource(stringReader);

		Map<String, String> map = new HashMap<String, String>();
		// documentBuilder为抽象不能直接实例化(将XML文件转换为DOM文件)
		DocumentBuilder db = null;
		DocumentBuilderFactory dbf = null;
		try {
			// 返回documentBuilderFactory对象
			dbf = DocumentBuilderFactory.newInstance();
			// 返回db对象用documentBuilderFatory对象获得返回documentBuildr对象
			db = dbf.newDocumentBuilder();

			// 得到一个DOM并返回给document对象
			Document dt = db.parse(inputSource);

			// 得到一个elment根元素
			element = dt.getDocumentElement();

			if (element.getNodeType() == Node.ELEMENT_NODE) {
				// 遍历当前元素节点的属性
				if (element.hasAttributes()) {
					NamedNodeMap attrs = element.getAttributes();
					for (int i = 0; i < attrs.getLength(); i++) {
						Node attrNode = attrs.item(i);
						if (attrNode.getNodeName().equals("rejectReturn")) {
							attrNode.setNodeValue(dataMap.get("rejectReturn"));
						}
						if (attrNode.getNodeName().equals("notifyType")) {
							attrNode.setNodeValue(dataMap.get("notifyType"));
						}
						if (attrNode.getNodeName().equals("notifyOnFinish")) {
							attrNode.setNodeValue(dataMap.get("notifyOnFinish"));
						}
						if (attrNode.getNodeName().equals("notifyDraftOnFinish")) {
							attrNode.setNodeValue(dataMap.get("notifyDraftOnFinish"));
						}
						if (attrNode.getNodeName().equals("dayOfNotifyPrivileger")) {
							attrNode.setNodeValue(dataMap.get("dayOfNotifyPrivileger"));
						}
						if (attrNode.getNodeName().equals("description")) {
							attrNode.setNodeValue(dataMap.get("description"));
						}
						if (attrNode.getNodeName().equals("privilegerIds")) {
							attrNode.setNodeValue(dataMap.get("privilegerIds"));
						}
						if (attrNode.getNodeName().equals("privilegerNames")) {
							attrNode.setNodeValue(dataMap.get("privilegerNames"));
						}
						if (attrNode.getNodeName().equals("description")) {
							attrNode.setNodeValue(dataMap.get("description"));
						}
						if (attrNode.getNodeName().equals("rejectReturn")) {
							attrNode.setNodeValue(dataMap.get("rejectReturn"));
						}
						if (attrNode.getNodeName().equals("recalculateHandler")) {
							attrNode.setNodeValue(dataMap.get("recalculateHandler"));
						}

						map.put(attrNode.getNodeName(), attrNode.getNodeValue());
					}
				}
			}

			// Document转化为String
			String xmlParse = doc2String(dt);

			return xmlParse;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * 
	 * 实现流程:TODO(功能：转换对象为字符串，适合XML字符数据的拼装)<br/>
	 * data：数据对象 nodeName：根对象对应XML的节点名
	 * 
	 * @Title: printXml
	 */
	public String printXml(Map<String, String> map, String nodeName) {
		StringBuffer sb = new StringBuffer();
		sb.append("<" + nodeName);
		sb.append(" nodesIndex=\"6\"");
		sb.append(" linesIndex=\"5\"");
		sb.append(" iconType=\"2\"");
		if (null != map.get("rejectReturn")) {
			sb.append(" rejectReturn=" + "\"" + map.get("rejectReturn") + "\"");
		}
		sb.append(" recalculateHandler=\"true\"");
		if (null != map.get("notifyType")) {
			sb.append(" notifyType=" + "\"" + map.get("notifyType") + "\"");
		}
		if (null != map.get("notifyOnFinish")) {
			sb.append(" notifyOnFinish=" + "\"" + map.get("notifyOnFinish") + "\"");
		}
		if (null != map.get("notifyDraftOnFinish")) {
			sb.append(" notifyDraftOnFinish=" + "\"" + map.get("notifyDraftOnFinish") + "\"");
		}
		if (null != map.get("dayOfNotifyPrivileger")) {
			sb.append(" dayOfNotifyPrivileger=" + "\"" + map.get("dayOfNotifyPrivileger") + "\"");
		}
		if (null != map.get("description")) {
			sb.append(" description=" + "\"" + map.get("description") + "\"");
		}
		if (null != map.get("privilegerIds")) {
			sb.append(" privilegerIds=" + "\"" + map.get("privilegerIds") + "\"");
		}
		if (null != map.get("privilegerNames")) {
			sb.append(" privilegerNames=" + "\"" + map.get("privilegerNames") + "\"");
		}
		if (null != map.get("description")) {
			sb.append(" description=" + "\"" + map.get("description") + "\"");
		}

		sb.append("></" + nodeName + ">");

		return sb.toString();
	}

}