package com.mars.ecsheet.utils;


public enum ApiResponseEnum {
	/**API返回封装
	 *  SUCCESS成功 code = 0
	 */
	SUCCESS("0", "成功"),
	/**API返回封装
	 *  ERROR_PARAM参数错误 code = -2
	 */
	ERROR_PARAM("-2", "参数错误"),
	/**API返回封装
	 *  ERROR_DATA_EMPTY无数据 code = -3
	 */
	ERROR_DATA_EMPTY("-3", "无数据"),
	/**API返回封装
	 *  FAIL失败 code = -1
	 */
	FAIL("-1", "失败");
	private String code;
	private String name;
	
	private ApiResponseEnum(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static String getMsg(String code) {
		for (ApiResponseEnum responseEnum : ApiResponseEnum.values()) {
			if (responseEnum.getCode().equals(code)) {
				return responseEnum.getName();
			}
		}
		return null;
	}
}
