package com.longersec.blj.utils;

import java.util.Random;

public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数
     * @return
     */
    public static synchronized String genUniqueKey(){
        Random random = new Random();

        Integer number = random.nextInt(900000)+100000;

        return System.currentTimeMillis()+String.valueOf(number);
    }
    
    public static synchronized String getDigitalCode(Integer len) {
    	char[] arr={'0','1','2','3','4','5','6','7','8','9'};
    	Random random=new Random();
    	StringBuffer stb=new StringBuffer();
    	
    	//需要四个随机数，通过获取随机数的字符获取数组中的字符
    	for(int i=0;i<4;i++){
    		int index=random.nextInt(arr.length);//产生的随机数必须是数组的索引值范围值
    		stb.append(arr[index]);
    	}
    	return stb.toString();
    }

}
