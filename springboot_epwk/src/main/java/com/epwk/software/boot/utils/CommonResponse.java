package com.epwk.software.boot.utils;

import java.io.Serializable;

public class CommonResponse implements Serializable {

	private static final long serialVersionUID = -981834386298206820L;

	private String code;
	private String msg;

	public CommonResponse() {
	}

	public CommonResponse(String code, String message) {
		this.code = code;
		this.msg = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
