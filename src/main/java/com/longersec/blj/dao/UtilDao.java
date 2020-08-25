package com.longersec.blj.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UtilDao {
	Map<String,byte[]> AesEncrypt(@Param("decrypt")String encrypt);
	Map<String,Object> AesDecrypt(@Param("encrypt")byte[] encrypt);
}
