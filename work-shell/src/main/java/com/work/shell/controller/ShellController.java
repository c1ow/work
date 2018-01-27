package com.work.shell.controller;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jcraft.jsch.JSchException;
import com.work.shell.service.ShellService;

@RestController
public class ShellController {
	@Resource
	private ShellService shellsevice;
	
	
	@RequestMapping(value="/exec/shell",method=RequestMethod.POST)
	public void execShell() throws JSchException, IOException, InterruptedException {
		List<String> execShell = shellsevice.execShell();
		for(String str:execShell) {
			System.err.println("数据："+str);
		}
	}
}
