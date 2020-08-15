package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.Resource;

public interface ResourceService {

	public boolean addResource(Resource resource);

	public boolean editResource(Resource resource);

	public boolean delResource(List<Integer> ids);

	public List<Object> findAll(Resource resource, int page_start, int page_length);

}

