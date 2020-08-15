package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ResourceGroup;

public interface ResourceGroupService {

	public boolean addResourceGroup(ResourceGroup resourceGroup);

	public boolean editResourceGroup(ResourceGroup resourceGroup);

	public boolean delResourceGroup(List<Integer> ids);

	public List<Object> findAll(ResourceGroup resourceGroup, int page_start, int page_length);

}

