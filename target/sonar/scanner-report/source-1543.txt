package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.DynamicToken;

public interface DynamicTokenService {

	public boolean addDynamicToken(DynamicToken dynamicToken);

	public boolean editDynamicToken(DynamicToken dynamicToken);

	public boolean delDynamicToken(List<Integer> ids);

	public List<Object> findAll(DynamicToken dynamicToken, int page_start, int page_length);

}

