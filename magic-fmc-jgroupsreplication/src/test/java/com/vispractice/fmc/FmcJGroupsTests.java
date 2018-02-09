package com.vispractice.fmc;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.vispractice.fmc.business.service.sys.workflow.IClusterService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes=FmcJGroupsApplication.class)
public class FmcJGroupsTests {

	@Autowired
	private IClusterService clusterService;
	
	@Test
	public void exampleTest() {
		try {
			clusterService.submit(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("test");
	}
	
	
}
