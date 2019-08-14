package com.baidu.producer;

import com.baidu.api.ISayService;

public class SayService implements ISayService {
	public String say(String msg) {
		return "hello " + msg;
	}
}
