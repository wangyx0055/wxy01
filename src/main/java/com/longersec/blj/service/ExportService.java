package com.longersec.blj.service;

import com.longersec.blj.domain.*;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public interface ExportService {

    File createTempFile_user() throws IOException;

    File createTempFile_group(int type) throws IOException;

    File createTempFile_device() throws IOException;

    File createTempFile_AccessPolicy() throws IOException;

    File createTempFile_DeviceRecord() throws IOException;

    File createTempFile_loginLog() throws IOException;

    File createTempFile_operatorLog() throws IOException;

    File createTempFile_AlertLog() throws IOException;

    File createTempFile_Password() throws IOException;

    File createTempFile_Password2() throws IOException;

    File createTempFile_department() throws IOException;

    File createTempFile_AppubServe()throws IOException;

    File createTempFile_AppubApp()throws IOException;

    File createTempFile_ApppubProgram()throws IOException;

    File createTempFile_operation()throws IOException;
    
    File createTempFile_reportProtocolLoginlog(String interval,String start_date, String end_date) throws IOException;
    
    File createTempFile_reportUserLoginlog(String interval,String start_date, String end_date) throws IOException;
    
    File createTempFile_reportFailLoginlog(String interval,String start_date, String end_date) throws IOException;
    
    File createTempFile_reportRecordlog(String interval,String start_date, String end_date) throws IOException;
    
    File createTempFile_reportTimeLongDeviceRecordlog(String interval,String start_date, String end_date) throws IOException;
    
    File createTempFile_reportTimeLongUserRecordlog(String interval,String start_date, String end_date) throws IOException;
    
    File createTempFile_reportCommandlog(String interval,String start_date, String end_date) throws IOException;
    
    File createTempFile_reportAlertlog(String interval,String start_date, String end_date) throws IOException;
    
    File createTempFile_reportSystemPriority(DeviceAccount deviceAccount) throws IOException;
    
    File createTempFile_reportApppubPriority(ApppubAccount apppubAccount) throws IOException;

    File createTempFile_reportCrontabCommandLog(Integer id) throws IOException;
    
}
