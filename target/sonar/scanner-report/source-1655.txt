package com.longersec.blj.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.longersec.blj.dao.UtilDao;
import com.longersec.blj.service.UtilService;

@Transactional
@Service
public class UtilServiceImpl implements UtilService{
	
	@Autowired
	private UtilDao utilDao;
	
	public byte[] AesEncrypt(String decrypt) {
		// TODO Auto-generated method stub
		Map<String,byte[]> _decrypt = this.utilDao.AesEncrypt(decrypt);
		return _decrypt.get("encrypt");
	}
	
	public String AesDecrypt(byte[] encrypt) {
		// TODO Auto-generated method stub
		Map<String,Object> _decrypt = this.utilDao.AesDecrypt(encrypt);
		return new String((byte[])_decrypt.get("decrypt"));
	}
}
