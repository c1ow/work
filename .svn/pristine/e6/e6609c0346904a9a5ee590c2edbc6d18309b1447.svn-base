package com.work.shell.service.iml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.work.shell.MyUserInfo;
import com.work.shell.service.ShellService;

@Service
public class ShellServiceImpl implements ShellService{
	private  final String IP = "192.168.0.120";
	private  final int PORT = 22;
	private  final String USERNAME = "mysql";
	private  final String PWD = "mysql";
	
	public  List<String> execShell() throws JSchException, IOException, InterruptedException {
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
			//超时
			session.connect(30000);
			//主体内容
			System.err.println("===========");
			//打开通道
			Channel channel = session.openChannel("exec");
			ChannelExec channelExec = (ChannelExec) channel;
			channelExec.setCommand("/data/mysql_plug/mtrans/grep_index.sh");	
			InputStream inputStream = channelExec.getInputStream();
			channelExec.connect();
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
			//接受执行命令结果
			String line = null;
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
			
			return res;
	}	
}
