package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ResourceUser;

public interface ResourceUserService {

	public boolean addResourceUser(ResourceUser resourceUser);

	public boolean editResourceUser(ResourceUser resourceUser);

	public boolean delResourceUser(List<Integer> ids);

	public List<Object> findAll(ResourceUser resourceUser, int page_start, int page_length);

}

