package com.longersec.blj.service;

import java.util.Map;

public interface UtilService {
	public byte[] AesEncrypt(String decrypt);
	public String AesDecrypt(byte[] encrypt);
}