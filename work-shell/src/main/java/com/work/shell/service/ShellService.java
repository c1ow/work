package com.work.shell.service;

import java.io.IOException;
import java.util.List;

import com.jcraft.jsch.JSchException;

public interface ShellService {
	public List<String> execShell() throws JSchException, IOException, InterruptedException;
}
