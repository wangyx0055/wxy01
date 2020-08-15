package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;
import com.longersec.blj.domain.Protocol;

public interface ProtocolService {

	public boolean addProtocol(Protocol protocol);

	public boolean editProtocol(Protocol protocol);

	public boolean delProtocol(List<Integer> ids);

	public List<Object> findAll(Protocol protocol, int page_start, int page_length);
	
	public String getById(Integer id);

    public ArrayList<Protocol> listType();
}

