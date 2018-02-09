/**
 * 
 */
package com.vispractice.fmc.business.entity.sys.workflow.support.subprocess.configuration;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * 通用参数类
 * 
 * @author fuyx
 * 
 */
public class Paramenter {

	private TextValue name;

	private TextValue expression;

	public TextValue getName() {
		return name;
	}

	public void setName(TextValue name) {
		this.name = name;
	}

	public TextValue getExpression() {
		return expression;
	}

	public void setExpression(TextValue expression) {
		this.expression = expression;
	}

	public static List<Paramenter> parseParamenters(JSONArray params) {
		List<Paramenter> list = new ArrayList<Paramenter>();
		for (int i = 0; i < params.size(); i++) {
			Paramenter p = new Paramenter();
			JSONObject param = (JSONObject) params.get(i);

			JSONObject name = (JSONObject) param.get("name");
			TextValue pname = new TextValue((String) name.get("text"),
					(String) name.get("value"));
			p.setName(pname);

			JSONObject expression = (JSONObject) param.get("expression");
			TextValue pexpression = new TextValue((String) expression
					.get("text"), (String) expression.get("value"));
			p.setExpression(pexpression);

			list.add(p);
		}
		return list;
	}
}
