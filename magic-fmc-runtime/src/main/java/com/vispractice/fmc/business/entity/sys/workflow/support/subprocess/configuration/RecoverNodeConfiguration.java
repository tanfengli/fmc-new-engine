/**
 * 
 */
package com.vispractice.fmc.business.entity.sys.workflow.support.subprocess.configuration;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * 回收节点参数配置类
 * 
 * @author fuyx
 * 
 */
public class RecoverNodeConfiguration {

	private JSONObject json;

	public RecoverNodeConfiguration() {
		super();
	}

	public RecoverNodeConfiguration(JSONObject json) {
		super();
		this.json = json;
	}

	public RecoverNodeConfiguration(String configuration) {
		json = (JSONObject) JSONValue.parse(configuration);
	}

	/**
	 * 取得需要回收的启动节点的节点ID
	 * 
	 * @return
	 */
	public String getSubProcessNode() {
		return (String) json.get("subProcessNode");
	}

	/**
	 * 变量回收范围
	 * 
	 * @author fuyx
	 * 
	 */
	public enum VariableScope implements ValueEquals {
		PASS_ALL(2), DISCARD_ALL(3), ALL(1);

		private Integer value;

		VariableScope(Integer v) {
			this.value = v;
		}

		static Set<VariableScope> set = EnumSet.allOf(VariableScope.class);

		public boolean valueEquals(Object value) {
			return this.value.equals(value);
		}

		public static VariableScope typeOf(Number value) {
			Integer i = value.intValue();
			return (VariableScope) EnumUtils.findByValueEqual(set, i);
		}
	}

	/**
	 * 取得回收变量范围
	 * 
	 * @return
	 */
	public VariableScope getVariableScope() {
		return VariableScope.typeOf((Number) json.get("variableScope"));
	}

	/**
	 * 回收规则类型
	 * 
	 * @author fuyx
	 * 
	 */
	public enum RecoverRuleType implements ValueEquals {

		ANY(2), FORMULA(3), ALL(1);

		private Integer value;

		RecoverRuleType(Integer v) {
			this.value = v;
		}

		static Set<RecoverRuleType> set = EnumSet.allOf(RecoverRuleType.class);

		public boolean valueEquals(Object value) {
			return this.value.equals(value);
		}

		public static RecoverRuleType typeOf(Number value) {
			Integer i = value.intValue();
			return (RecoverRuleType) EnumUtils.findByValueEqual(set, i);
		}
	}

	/**
	 * 回收规则
	 * 
	 * @author fuyx
	 * 
	 */
	public class RecoverRule {

		JSONObject rule;

		public RecoverRuleType getType() {
			return RecoverRuleType.typeOf((Number) rule.get("type"));
		}

		public TextValue getExpression() {
			JSONObject expression = (JSONObject) rule.get("expression");
			TextValue pexpression = new TextValue((String) expression
					.get("text"), (String) expression.get("value"));
			return pexpression;
		}
	}

	/**
	 * 取得回收规则
	 * 
	 * @return
	 */
	public RecoverRule getRecoverRule() {
		RecoverRule rule = new RecoverRule();
		rule.rule = (JSONObject) json.get("recoverRule");
		return rule;
	}

	/**
	 * 取得回收参数
	 * 
	 * @return
	 */
	public List<Paramenter> getParamenters() {
		return Paramenter.parseParamenters((JSONArray) json
				.get("recoverParamenters"));
	}
}
