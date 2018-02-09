package com.vispractice.fmc.cluster.adapter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

import org.jgroups.Address;
import org.jgroups.ExtendedReceiverAdapter;
import org.jgroups.Message;
import org.jgroups.View;
import org.springframework.stereotype.Component;

import com.ruiyi.kmss.sys.cluster.dispatcher.DispatcherMessage;


@Slf4j
@Component
public class FmcReceiverAdapter extends ExtendedReceiverAdapter{

	final List<String> state=new LinkedList<String>();
	
	public void viewAccepted(View new_view) {
		List<Address> memberList = new ArrayList<Address>();
		memberList.addAll(new_view.getMembers());
		log.info("集群成员视图发生变化，成员列表如下：");
		for(int i=0; i < memberList.size(); i++){
			log.info("member<" + i + "> =" + memberList.get(i).toString());
		}
		log.info("集群成员视图列表End");
	}

	public void receive(Message msg) {
		log.info("收到集群信息："+ msg.getSrc() + ": " + msg.getObject());
		DispatcherMessage disMsg = (DispatcherMessage) msg.getObject();
//		synchronized (state) {
//			state.add((String) msg.getObject());
//		}
	}

	
	
//	public void getState(OutputStream output) throws Exception {
//	    synchronized(state) {
//	        Util.objectToStream(state, new DataOutputStream(output));
//	    }
//	}
//	
//	public void setState(InputStream input) throws Exception {
//	    List<String> list;
//	    list=(List<String>)Util.objectFromStream(new DataInputStream(input));
//	    synchronized(state) {
//	        state.clear();
//	        state.addAll(list);
//	    }
//	    System.out.println(list.size() + " messages in chat history):");
//	    for(String str: list) {
//	        System.out.println(str);
//	    }
//	}

}
