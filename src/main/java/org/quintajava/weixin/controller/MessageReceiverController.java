package org.quintajava.weixin.controller;

import java.io.IOException;

import org.quintajava.weixin.service.MessageConvertHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

@RestController
@RequestMapping("/zhouyingqi/weixin/receiver")
public class MessageReceiverController<InMessage> {
	private static final Logger LOG=LoggerFactory.getLogger(MessageReceiverController.class);

	@Autowired
	private XmlMapper xmlMapper;
	
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
			@RequestParam String xml) throws JsonParseException, JsonMappingException, IOException{
				LOG.trace("收到的消息原文：\n{}\n----------------------------",xml);

				
//				String type=xml.substring(xml.indexOf("<MsgType><![CDATA[")+18);
//				type=type.substring(0,type.indexOf("]"));
				
//				if(type.equals("text")) {
//					InMessage x = new TextInMessage();
//				}else
				
//				@SuppressWarnings("unchecked")
//				InMessage inMessage = (InMessage) MessageConvertHelper.convert(xml);
				
				InMessage inMessage= convent(xml);
				
				if(inMessage==null) {
					LOG.error("消息无法转换！原文:\n{}\n",xml);
					return "success";
				}
				LOG.debug("转换后的消息对象\n{}\n",inMessage);
	
				
				return "success";

	}
	private InMessage convent(String xml) throws JsonParseException, JsonMappingException, IOException  {
		Class<? extends InMessage> c = (Class<? extends InMessage>) MessageConvertHelper.getClass(xml);
		InMessage msg=xmlMapper.readValue(xml, c);
		return null;
	}
}

