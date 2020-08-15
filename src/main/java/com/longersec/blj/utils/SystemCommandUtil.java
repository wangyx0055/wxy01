package com.longersec.blj.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.InputStreamReader;

/**
 */
public class SystemCommandUtil {

    private static final String DOCKER_SOCK_CMD_PREFIX = "curl -s --unix-socket /var/run/docker.sock http://localhost/";

    public static void main(String[] args) {
        System.out.println(execDockerSockCmd("containers/json"));
    }

    /**
     * 通过docker套接字发送HTTP请求获取数据
     *
     * @param cmd
     * @return
     */
    public static String execDockerSockCmd(String cmd) {
        return execCmd(DOCKER_SOCK_CMD_PREFIX + cmd);
    }

    /**
     * 执行系统命令, 返回执行结果
     *
     * @param cmd 需要执行的命令
     * @return
     */
    public static String execCmd(String cmd) {
        return execCmd(cmd, null);
    }

    /**
     * 执行系统命令, 返回执行结果
     *
     * @param cmd 需要执行的命令
     * @param dir 执行命令的子进程的工作目录, null 表示和当前主进程工作目录相同
     */
    @SuppressWarnings("finally")
	public static String execCmd(String cmd, File dir) {
        StringBuilder result = new StringBuilder();
        Process process = null;
        BufferedReader bufrIn = null;
        BufferedReader bufrError = null;
        try {
            String[] command = {"/bin/sh", "-c", cmd};
            // 执行命令, 返回一个子进程对象（命令在子进程中执行）
            process = Runtime.getRuntime().exec(command, null, dir);
            // 方法阻塞, 等待命令执行完成（成功会返回0）
            process.waitFor();
            // 获取命令执行结果, 有两个结果: 正常的输出 和 错误的输出（PS: 子进程的输出就是主进程的输入）
            bufrIn = new BufferedReader(new InputStreamReader(process.getInputStream(), "UTF-8"));
            bufrError = new BufferedReader(new InputStreamReader(process.getErrorStream(), "UTF-8"));
            // 读取输出
            String line;
            while ((line = bufrIn.readLine()) != null) {
                result.append(line).append('\n');
            }
            while ((line = bufrError.readLine()) != null) {
                result.append(line).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeStream(bufrIn);
            closeStream(bufrError);
            // 销毁子进程
            if (process != null) {
                process.destroy();
            }
            // 返回执行结果
            return result.toString();
        }
    }

    /**
     * 关闭流
     *
     * @param stream
     */
    private static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}