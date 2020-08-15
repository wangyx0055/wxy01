package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.ApppubResourceUser;

public interface ApppubResourceUserService {

	public boolean addApppubResourceUser(ApppubResourceUser apppubResourceUser);

	public boolean editApppubResourceUser(ApppubResourceUser apppubResourceUser);

	public boolean delApppubResourceUser(List<Integer> ids);

	public List<Object> findAll(ApppubResourceUser apppubResourceUser, int page_start, int page_length);

}

