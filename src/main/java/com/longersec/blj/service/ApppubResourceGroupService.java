package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ApppubResourceGroup;

public interface ApppubResourceGroupService {

	public boolean addApppubResourceGroup(ApppubResourceGroup apppubResourceGroup);

	public boolean editApppubResourceGroup(ApppubResourceGroup apppubResourceGroup);

	public boolean delApppubResourceGroup(List<Integer> ids);

	public List<Object> findAll(ApppubResourceGroup apppubResourceGroup, int page_start, int page_length);

}

