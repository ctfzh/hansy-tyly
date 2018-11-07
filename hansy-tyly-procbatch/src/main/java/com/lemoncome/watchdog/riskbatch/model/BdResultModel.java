package com.lemoncome.watchdog.riskbatch.model;

/**
 * @CreateTime:2017年11月2日下午2:57:29
 * @Description:大数据提交返回结果Bo类
 */
public class BdResultModel {
	private int errorCode;
	private String errorMsg;
	private Object data;

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public boolean isSuccess() {
		//orderId重复表示上次发送成功，但是可能返回过程中出错了
		if (errorCode == 200) {
			return true;
		}else if("orderId重复".equals(errorMsg)){
			return true;
		}
		return false;
	}

}
