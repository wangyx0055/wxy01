package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ResourceHostAccount;

public interface ResourceHostAccountService {

	public boolean addResourceHostAccount(ResourceHostAccount resourceHostAccount);

	public boolean editResourceHostAccount(ResourceHostAccount resourceHostAccount);

	public boolean delResourceHostAccount(List<Integer> ids);

	public List<Object> findAll(ResourceHostAccount resourceHostAccount, int page_start, int page_length);

}

