package com.epwk.software.boot.utils;


public class CommonSuccessResponse extends CommonResponse {

	private Object obj;

	private static final long serialVersionUID = -7927296760107523634L;

	public CommonSuccessResponse(Object obj) {
		this.obj = obj;
		this.setCode(Const.RESPONSE_CODE_SUCCESS);
		this.setMsg(Const.RESPONSE_CODE_SUCCESS_MESSAGE);
	}

	public Object getObject() {
		return obj;
	}

	public void setObject(Object obj) {
		this.obj = obj;
	}

}
