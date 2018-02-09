/**
 * 
 */
package com.vispractice.fmc.business.entity.sys.workflow.support.subprocess.configuration;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

/**
 * 启动节点配置类
 * 
 * @author fuyx
 * 
 */
public class StartNodeConfiguration {

	private JSONObject json;

	public StartNodeConfiguration() {
		super();
	}

	public StartNodeConfiguration(JSONObject json) {
		super();
		this.json = json;
	}

	public StartNodeConfiguration(String configuration) {
		json = (JSONObject) JSONValue.parse(configuration);
	}

	/**
	 * 取得配置的流程相关信息
	 * 
	 * @return
	 */
	public SubProcessInfo getSubProcessInfo() {
		SubProcessInfo info = new SubProcessInfo();
		info.subProcess = (JSONObject) json.get("subProcess");
		return info;
	}

	/**
	 * 流程相关信息
	 * 
	 * @author ruben
	 * 
	 */
	public class SubProcessInfo {
		JSONObject subProcess;

		public String getModelName() {
			return (String) subProcess.get("modelName");
		}

		public String getCreateParam() {
			return (String) subProcess.get("createParam");
		}
	}

	/**
	 * 启动数量类型
	 * 
	 * @author ruben
	 * 
	 */
	public enum CountType {
		ONE, DRAFTSMAN;

		public static CountType typeOf(Number value) {
			// int i = Integer.parseInt(value);
			int i = value.intValue();
			switch (i) {
			case 1:
				return ONE;
			case 2:
				return DRAFTSMAN;
			default:
				return null;
			}
		}
	}

	/**
	 * 获得启动数量类型
	 * 
	 * @return
	 */
	public CountType getCountType() {
		return CountType.typeOf((Number) json.get("startCountType"));
	}

	/**
	 * 获取启动人身份
	 * 
	 * @return
	 */
	public Identity getIdentity() {
		Identity ide = new Identity();
		ide.identity = (JSONObject) json.get("startIdentity");
		return ide;
	}

	/**
	 * 启动人身份类型
	 * 
	 * @author fuyx
	 * 
	 */
	public enum IdentityType {
		DRAFTSMAN, PRE_HANDLER, ORG_ELEMENT, FORMULA;

		public static IdentityType typeOf(Number value) {
			// int i = Integer.parseInt(value);
			int i = value.intValue();
			switch (i) {
			case 1:
				return DRAFTSMAN;
			case 2:
				return PRE_HANDLER;
			case 3:
				return ORG_ELEMENT;
			case 4:
				return FORMULA;
			default:
				return null;
			}
		}
	}

	/**
	 * 启动人身份
	 * 
	 * @author fuyx
	 * 
	 */
	public class Identity {

		JSONObject identity;

		public IdentityType getType() {
			return IdentityType.typeOf((Number) identity.get("type"));
		}

		public String getNames() {
			return (String) identity.get("names");
		}

		public String getValues() {
			return (String) identity.get("values");
		}
	}

	/**
	 * 是否跳过起草节点
	 * 
	 * @return
	 */
	public boolean isSkipDraftNode() {
		// return Boolean.parseBoolean((String) json.get("skipDraftNode"));
		return (Boolean) json.get("skipDraftNode");
	}

	/**
	 * 获得启动参数
	 * 
	 * @return
	 */
	public List<Paramenter> getParamenters() {
		return Paramenter.parseParamenters((JSONArray) json
				.get("startParamenters"));
	}

	/**
	 * 子流程出错通知类型
	 * 
	 * @author fuyx
	 * 
	 */
	public enum NotifyType {
		PRIVILEGER, DRAFTSMAN;

		public static NotifyType typeOf(Number value) {
			// int i = Integer.parseInt(value);
			int i = value.intValue();
			switch (i) {
			case 1:
				return DRAFTSMAN;
			case 2:
				return PRIVILEGER;
			default:
				return null;
			}
		}
	}

	/**
	 * 获得出错通知类型
	 * 
	 * @return
	 */
	public List<NotifyType> getOnErrorNotify() {
		JSONArray onErrorNotifys = (JSONArray) json.get("onErrorNotify");
		List<NotifyType> types = new ArrayList<NotifyType>();
		for (int i = 0; i < onErrorNotifys.size(); i++) {
			Number value = (Number) onErrorNotifys.get(i);
			types.add(NotifyType.typeOf(value));
		}
		return types;
	}

}
