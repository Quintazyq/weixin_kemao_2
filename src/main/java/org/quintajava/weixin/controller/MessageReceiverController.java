package org.quintajava.weixin.controller;

import org.quintajava.weixin.service.MessageConvertHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zhouyingqi/weixin/receiver")
public class MessageReceiverController<InMessage> {
	private static final Logger LOG=LoggerFactory.getLogger(MessageReceiverController.class);

	
	@GetMapping
	public String echo(
			@RequestParam("signature")String signature,
			@RequestParam("timestamp")String timestamp,
			@RequestParam("nonce")String nonce,
			@RequestParam("echostr")String echostr	
			) {
		return echostr;
	}
	@PostMapping
	public String onMessage(
			@RequestParam("signature")String signature,
			@RequestParam("timestamp")String timestamp,
			@RequestParam("nonce")String nonce,
			@RequestParam String xml){
				LOG.trace("收到的消息原文：\n{}\n----------------------------",xml);

				
//				String type=xml.substring(xml.indexOf("<MsgType><![CDATA[")+18);
//				type=type.substring(0,type.indexOf("]"));
				
//				if(type.equals("text")) {
//					InMessage x = new TextInMessage();
//				}else
				
				@SuppressWarnings("unchecked")
				InMessage inMessage = (InMessage) MessageConvertHelper.convert(xml);
				if(inMessage==null) {
					LOG.error("消息无法转换！原文:\n{}\n",xml);
					return "success";
				}
				LOG.debug("转换后的消息对象\n{}\n",inMessage);
	
				
				return "success";

	}
}

