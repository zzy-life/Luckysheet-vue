package com.mars.ecsheet.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true) // 开启链式编程
@AllArgsConstructor // 生成全参构造
@NoArgsConstructor // 生成无参构造
public class Result {
	// 相应码
	private String code;
	// 信息
	private String message;
	// 返回数据
	private Object data;

}
