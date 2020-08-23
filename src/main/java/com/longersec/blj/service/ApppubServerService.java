package com.longersec.blj.service;

import java.util.ArrayList;
import java.util.List;
import com.longersec.blj.domain.ApppubServer;
import org.apache.ibatis.annotations.Param;

public interface ApppubServerService {

	public boolean addApppubServer(ApppubServer apppubServer);

	public boolean editApppubServer(ApppubServer apppubServer);

	public boolean delApppubServer(List<Integer> ids);

	boolean delUselessApppubServer(Integer id);

	public List<Object> findAll(ApppubServer apppubServer, String sname,Integer type,int page_start, int page_length,List<Integer> depart_ids);

	public String selectName(Integer id,String name);

	public ApppubServer getById(Integer id);

	ApppubServer checkname(Integer id,String name);

	public boolean insertMore(ArrayList<ApppubServer> listApppubservers);

	public boolean editApppubServerList(ArrayList<ApppubServer> updatelistlistApppubservers);

	public ApppubServer checkip(String ip, Integer id);

	public ApppubServer getApppubServerByName (String name);
}

