package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.SessionAutodelete;

public interface SessionAutodeleteService {

	public boolean addSessionAutodelete(SessionAutodelete sessionAutodelete);

	public boolean editSessionAutodelete(SessionAutodelete sessionAutodelete);

	public boolean delSessionAutodelete(List<Integer> ids);

	public List<Object> findAll(SessionAutodelete sessionAutodelete, int page_start, int page_length);

}

