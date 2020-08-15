package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ApppubResource;

public interface ApppubResourceService {

	public boolean addApppubResource(ApppubResource apppubResource);

	public boolean editApppubResource(ApppubResource apppubResource);

	public boolean delApppubResource(List<Integer> ids);

	public List<Object> findAll(ApppubResource apppubResource, int page_start, int page_length);

}

