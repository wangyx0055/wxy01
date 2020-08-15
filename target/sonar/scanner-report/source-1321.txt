package com.longersec.blj.dao;

import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.Protocol;

public interface ProtocolDao {

	public boolean editProtocol(Protocol protocol);

	public boolean addProtocol(Protocol protocol);

	public boolean delProtocol(List<Integer> ids);

	public List<Object> findAll(@Param("protocol")Protocol protocol, @Param("page_start")int page_start, @Param("page_length")int page_length);
	
	public Protocol getById(@Param("id")Integer id);

    public ArrayList<Protocol> listType();

    Protocol getByName(@Param("s") String s);
}
