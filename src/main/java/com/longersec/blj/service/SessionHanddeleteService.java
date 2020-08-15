package com.longersec.blj.service;

import java.util.List;
import com.longersec.blj.domain.SessionHanddelete;

public interface SessionHanddeleteService {

	public boolean addSessionHanddelete(SessionHanddelete sessionHanddelete);

	public boolean editSessionHanddelete(SessionHanddelete sessionHanddelete);

	public boolean delSessionHanddelete(List<Integer> ids);

	public List<Object> findAll(SessionHanddelete sessionHanddelete, int page_start, int page_length);

}

