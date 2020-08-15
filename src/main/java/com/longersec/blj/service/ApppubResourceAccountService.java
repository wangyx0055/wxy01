package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ApppubResourceAccount;

public interface ApppubResourceAccountService {

	public boolean addApppubResourceAccount(ApppubResourceAccount apppubResourceAccount);

	public boolean editApppubResourceAccount(ApppubResourceAccount apppubResourceAccount);

	public boolean delApppubResourceAccount(List<Integer> ids);

	public List<Object> findAll(ApppubResourceAccount apppubResourceAccount, int page_start, int page_length);

}

