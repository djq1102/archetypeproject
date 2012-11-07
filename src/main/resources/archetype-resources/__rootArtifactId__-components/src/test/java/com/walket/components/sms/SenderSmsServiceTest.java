package com.walket.components.sms;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.walket.components.sms.SenderSmsService;

@ContextConfiguration(locations={"classpath:components/applicationContext-sms.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SenderSmsServiceTest {
	
	private static Logger logger = Logger.getLogger(SenderSmsServiceTest.class);

	@Autowired
	private SenderSmsService senderSmsService;
	
	@Test
	public void testSend(){
		logger.info(senderSmsService.send("13222222222", "lasdkhuqwb阿桑机顶盒"));
	}
}
