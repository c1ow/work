package com.work.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.sql.rowset.serial.SerialArray;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.UserInfo;

public class LinkLinux {
	private static final String IP = "192.168.0.120";
	private static final int PORT = 22;
	private static final String USERNAME = "test";
	private static final String PWD = "test";
	
	public static void execShell() throws JSchException, IOException, InterruptedException {
		Thread.sleep(2000);
		JSch jSch = new JSch();
		MyUserInfo myUserInfo = new MyUserInfo();
//		try {
			//创建连接
			Session session = jSch.getSession(USERNAME,IP);
			session.setPassword(PWD);
			session.setConfig("PreferredAuthentications", "publickey,keyboard-interactive,password");
			session.setConfig("StrictHostKeyChecking", "no");  
			session.setUserInfo(myUserInfo);
			session.connect(30000);
			//主体内容
			System.err.println("===========");
			//打开通道
			Channel channel = session.openChannel("exec");
			ChannelExec channelExec = (ChannelExec) channel;
			channelExec.setCommand("./test.sh");
			
//			channelExec.setInputStream(null);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(channelExec.getInputStream()));
			channelExec.connect();
			
			//接受执行命令结果
			String line;
			List<String> res = new ArrayList<String>();
			while((line = bufferedReader.readLine())!=null) {
				res.add(line);
			}
			System.err.println("xxxxxxxxxxxx");
			if (channelExec.isClosed()) {
				int rescode = channelExec.getExitStatus();
				System.err.println("执行结果： "+rescode);
			}
			
			bufferedReader.close();
			channelExec.disconnect();
			session.disconnect();
			
//		} catch (Exception e) {
//			System.err.println(e.getMessage());
//		}
	}
	
	public static void main(String[] args) throws JSchException, IOException, InterruptedException {
		execShell();
	}
}
