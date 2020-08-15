package com.longersec.blj.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UtilDao {
	public Map<String,byte[]> AesEncrypt(@Param("decrypt")String encrypt);
	public Map<String,Object> AesDecrypt(@Param("encrypt")byte[] encrypt);
}
