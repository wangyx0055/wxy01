package com.longersec.blj.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxy
 * @description
 * @data 2020/9/7
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
	private String name;
	private int age;
	private String adress;
}
