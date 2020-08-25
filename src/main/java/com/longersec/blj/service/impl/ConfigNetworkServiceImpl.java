package com.longersec.blj.service.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

import com.longersec.blj.domain.ApppubServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.longersec.blj.dao.ConfigNetworkDao;
import com.longersec.blj.domain.ConfigAlertLevel;
import com.longersec.blj.domain.ConfigNetwork;
import com.longersec.blj.service.ConfigNetworkService;
import com.longersec.blj.service.ConfigService;


@Transactional
@Service
public class ConfigNetworkServiceImpl implements ConfigNetworkService{

	@Autowired
	private ConfigNetworkDao ConfigNetworkDao;
	
	@Autowired
	private ConfigService configService;

	@Override
	public boolean editConfigNetwork(ConfigNetwork configNetwork) {
		// TODO Auto-generated method stub
		return this.ConfigNetworkDao.editConfigNetwork(configNetwork);
	}

	@Override
	public boolean addConfigNetwork(ConfigNetwork configNetwork) {
		// TODO Auto-generated method stub
		return this.ConfigNetworkDao.addConfigNetwork(configNetwork);
	}

	@Override
	public boolean delConfigNetwork(List<Integer> ids) {
		// TODO Auto-generated method stub
		return this.ConfigNetworkDao.delConfigNetwork(ids);
	}

	@Override
	public List<Object> findAll(ConfigNetwork configNetwork, int page_start, int page_length) {
		return ConfigNetworkDao.findAll(configNetwork, page_start, page_length);
	}

	@Override
	public ConfigNetwork get() {
		return ConfigNetworkDao.get();
	}

	@Override
	public String getNameByInterface(String ethx, String name) {
		// TODO Auto-generated method stub
		String interfaceFile = configService.getByName("interfacePath").getValue()+"/ifcfg-"+ethx;
        File f = new File(interfaceFile);
        String str = "";
        if(f.isFile()&& f.getName().contains("ifcfg-") &&f.getName().substring(0,6).equals("ifcfg-")){
	        FileInputStream inputStream;
			try {
				inputStream = new FileInputStream(f.getAbsoluteFile());

	            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
	            while(( str = bufferedReader.readLine()) != null)
				{
	            	if(str.toLowerCase().contains(name + "=")) {
	    	            bufferedReader.close();
						return str.substring(name.length()+1).trim();
					}
				}
	            bufferedReader.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
		return str;
	}

}
