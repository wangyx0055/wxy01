package com.longersec.blj.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.longersec.blj.domain.ApppubProgram;

public interface ApppubProgramDao {
	List<ApppubProgram> selectAll();
	boolean editApppubProgram(ApppubProgram apppubProgram);

	boolean addApppubProgram(ApppubProgram apppubProgram);

	boolean delApppubProgram(List<Integer> ids);

	List<Object> findAll(@Param("apppubProgram")ApppubProgram apppubProgram,@Param("sname")String sname,@Param("type")Integer type, @Param("page_start")int page_start, @Param("page_length")int page_length);

	List<Object> queryApppubProgramById(@Param("apppub_server_id") int apppub_server_id, @Param("apppubProgram")ApppubProgram apppubProgram, @Param("page_start")int page_start, @Param("page_length")int page_length);

	int total();
	ApppubProgram getById(@Param("id") Integer id);
    
    ApppubProgram checkAppName(String name);

    ApppubProgram getApppubProgramByName (@Param("name") String name);
}
