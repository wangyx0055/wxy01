package com.longersec.blj.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringWriter;

import cn.hutool.core.util.NumberUtil;
import net.sf.json.JSONObject;
import oshi.json.SystemInfo;
import oshi.json.hardware.CentralProcessor;
import oshi.json.hardware.GlobalMemory;
import oshi.json.hardware.HardwareAbstractionLayer;

/**
 * <p>
 * 类说明
 * </p>
 *
 */
public class SystemUsageUtil {

    private static SystemInfo systemInfo = new SystemInfo();
    
    private String network_device;
    
    private long cpu_used ;
    private long cpu_total ;
    private double cpu_userate ;
    
    private long memory_used ;
    private long memory_total ;
    private double memory_userate ;
    
    private long disk_used ;
    private long disk_total ;
    private double disk_userate ;
    
    private long network_send ;
    private long network_receive  ;
    private double network_userate ;
    
    public JSONObject getAllInfo(String network_device) {
    	this.network_device = network_device;
    	JSONObject systeminfo 	= new JSONObject();
    	this.cpu_userate 		= this.getCpuUsage();
    	this.memory_userate 	= this.getMemoryUsage();
    	this.disk_userate 		= this.getDiskUsage();
    	this.network_userate 	= this.getNetUsage();
    	systeminfo.accumulate("cpu_used", cpu_used);
    	systeminfo.accumulate("cpu_total", cpu_total);
    	systeminfo.accumulate("cpu_userate", cpu_userate);
    	systeminfo.accumulate("memory_used", memory_used);
    	systeminfo.accumulate("memory_total", memory_total);
    	systeminfo.accumulate("memory_used_human", this.Tohuman(memory_used));
    	systeminfo.accumulate("memory_total_human", this.Tohuman(memory_total));
    	systeminfo.accumulate("memory_userate", memory_userate);
    	systeminfo.accumulate("disk_used", disk_used);
    	systeminfo.accumulate("disk_total", disk_total);
    	systeminfo.accumulate("disk_used_human", this.Tohuman(disk_used*1024));
    	systeminfo.accumulate("disk_total_human", this.Tohuman(disk_total*1024));
    	systeminfo.accumulate("disk_userate", disk_userate);
    	systeminfo.accumulate("network_receive", network_receive);
    	systeminfo.accumulate("network_send", network_send);
    	systeminfo.accumulate("network_receive_human", this.Tohuman(network_receive));
    	systeminfo.accumulate("network_send_human", this.Tohuman(network_send));
    	systeminfo.accumulate("network_userate", network_userate);
    	return systeminfo;
    }

    /**
     * 获取内存的使用率
     *
     * @return 内存使用率 0.36
     */
    public double getMemoryUsage() {
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        GlobalMemory memory = hal.getMemory();
        long available = memory.getAvailable();
        long total = memory.getTotal();
        double useRate = NumberUtil.div(total-available, total, 2);
        this.memory_used = total-available;
        this.memory_total = total;
        this.memory_userate = useRate;
        return useRate;
    }

    /**
     * 获取CPU的使用率
     *
     * @return CPU使用率 0.36
     */
    public double getCpuUsage() {
        HardwareAbstractionLayer hal = systemInfo.getHardware();
        CentralProcessor processor = hal.getProcessor();
        double useRate = processor.getSystemCpuLoadBetweenTicks();
        this.cpu_userate = NumberUtil.div(useRate, 1, 2);
        this.cpu_total   = 100;
        this.cpu_used    = new Double(this.cpu_userate*100).longValue();
        return this.cpu_userate;
    }

    /**
     * 获取磁盘的使用率
     *
     * @return CPU使用率 0.36
     */
    public double getDiskUsage() {
        return getUnixDiskUsage();
    }


    /**
     * 获取linux 磁盘使用率
     *
     * @return 磁盘使用率
     */
    private double getUnixDiskUsage() {
        String ioCmdStr = "df /";
       // ioCmdStr = "cat /tmp/disk";
        String resultInfo = runCommand(ioCmdStr);
        String[] lines = resultInfo.split("\n");
        String[] data = lines[1].split(" +");
        double total = Double.parseDouble(data[4].replace("%", ""));

        this.disk_used = Long.parseLong(data[2]);
        this.disk_total = Long.parseLong(data[1]);
        this.disk_userate = total / 100;
        
        return total / 100;
    }
    
    public double getNetUsage() {
        double netUsage = 0.0d;
        Process pro1,pro2;
        Runtime r = Runtime.getRuntime();
        try {
            String command = "cat /proc/net/dev";
            //command = "cat /tmp/dev1";
            //第一次采集流量数据
            long startTime = System.currentTimeMillis();
            pro1 = r.exec(command);
            BufferedReader in1 = new BufferedReader(new InputStreamReader(pro1.getInputStream()));
            String line = null;
            long inSize1 = 0, outSize1 = 0;
            while((line=in1.readLine()) != null){    
                line = line.trim();
                if(line.startsWith(this.network_device)){
                    String[] temp = line.split("\\s+"); 
                    //System.out.println("test1:"+temp);
                    inSize1 = Long.parseLong(temp[1])/8;    //Receive bytes,单位为Byte
                    outSize1 = Long.parseLong(temp[9])/8;                //Transmit bytes,单位为Byte
                    break;
                }                
            }    
            in1.close();
            pro1.destroy();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                StringWriter sw = new StringWriter();
                e.printStackTrace(new PrintWriter(sw));
            }
            //第二次采集流量数据
            long endTime = System.currentTimeMillis();

            //command = "cat /tmp/dev2";
            pro2 = r.exec(command);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(pro2.getInputStream()));
            long inSize2 = 0 ,outSize2 = 0;
            while((line=in2.readLine()) != null){    
                line = line.trim();
                if(line.startsWith("eth0")){
                    String[] temp = line.split("\\s+");
                    inSize2 = Long.parseLong(temp[1])/8;
                    outSize2 = Long.parseLong(temp[9])/8;
                    break;
                }                
            }
            
            if(inSize1 != 0 && outSize1 !=0 && inSize2 != 0 && outSize2 !=0){
                float interval = (float)(endTime - startTime)/1000;
                this.network_receive = new Float((inSize2 - inSize1)/interval).longValue();
                this.network_send = new Float((outSize2 - outSize1)/interval).longValue();
                netUsage = NumberUtil.div(this.network_receive/(this.network_receive+this.network_send), 1, 2);
            }                
            in2.close();
            pro2.destroy();
        } catch (IOException e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
        }    
        return netUsage;
    }


    /**
     * 执行系统命令
     *
     * @param CMD 命令
     * @return 字符串结果
     */
    private String runCommand(String CMD) {
        StringBuilder info = new StringBuilder();
        try {
            Process pos = Runtime.getRuntime().exec(CMD);
            pos.waitFor();
            InputStreamReader isr = new InputStreamReader(pos.getInputStream());
            LineNumberReader lnr = new LineNumberReader(isr);
            String line;
            while ((line = lnr.readLine()) != null) {
                info.append(line).append("\n");
            }
        } catch (Exception e) {
            info = new StringBuilder(e.toString());
        }
        return info.toString();
    }
    
    public String Tohuman(long size) {
    	long K = 1024;
    	long M = K * 1024;
    	long G = M * 1024;
    	long T = G * 1024;
    	if(size<=K) {
    		return size + "B";
    	}else if(size <= M) {
    		return size/K + "KB";
    	}else if(size <= G) {
    		return size/M + "MB";
    	}else if(size <= T) {
    		return size/G + "GB";
    	}else {
    		return size/T + "TB";
    	}
    }
}

