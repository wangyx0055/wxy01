package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ConfigLdapAd;
import org.apache.ibatis.annotations.Param;

public interface ConfigLdapAdService {

	public boolean addConfigLdapAd(ConfigLdapAd configLdapAd);

	public boolean editConfigLdapAd(ConfigLdapAd configLdapAd);

	public boolean delConfigLdapAd(List<Integer> ids);

	public List<Object> findAll(ConfigLdapAd configLdapAd, int page_start, int page_length);

	public ConfigLdapAd getConfigLdapById(int id);

}

