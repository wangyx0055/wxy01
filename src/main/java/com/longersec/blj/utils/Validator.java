/**
 * Project Name:dt48_springMVC4
 * File Name:Validator.java
 * Package Name:cn.java.utils
 * Date:下午5:02:25
 * Copyright (c) 2017, bluemobi All Rights Reserved.
 *
*/

package com.longersec.blj.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

/**
 * Description: 数据校验<br/>
 * Date: 下午5:02:25 <br/>
 * 
 * @author dingP
 * @version
 * @see
 */
public class Validator {
    /**
     * Description:校验实体类中的属性是否正确 <br/>
     * @author wxy
     * @param errorResult：BindingResult类型
     * @return：如果返回值为null，则代表数据完全正确；如果不为null，则返回的是一个封装错误信息map集合
     */
    public static Map<String, Object> fieldValidate(BindingResult errorResult) {
        Map<String, Object> errorMap = null;
        boolean flag = errorResult.hasErrors();
        if (flag) {
            errorMap = new HashMap<String, Object>();
            // 将字段对应的错误信息答应出来
            List<FieldError> errorList = errorResult.getFieldErrors();
            for (FieldError fieldError : errorList) {
                // 1、获取实体类中的属性名
                String fieldName = fieldError.getField();
                // 2、当数据不满足匹配规则时，获取错误提示信息
                String errorMessage = fieldError.getDefaultMessage();
                errorMap.put(fieldName, errorMessage);
            }
        }
        return errorMap;
    }
}
