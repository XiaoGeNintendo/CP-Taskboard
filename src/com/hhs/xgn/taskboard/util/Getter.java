package com.hhs.xgn.taskboard.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.hhs.xgn.taskboard.type.User;

public class Getter {
	public static boolean isLinux(){
		return System.getProperty("os.name").toLowerCase().contains("linux");
	}
	
	private static String RootPath;
	/**
	 * Without the last slash('/')
	 * @return
	 */
	public static String getPath() {
		if(RootPath==null) {
			if(System.getProperty("os.name").toLowerCase().indexOf("win")>=0){
				//Windows
				javax.swing.filechooser.FileSystemView fsv = javax.swing.filechooser.FileSystemView.getFileSystemView(); 
				RootPath=fsv.getHomeDirectory().getAbsolutePath();
				RootPath+="\\taskboard";
			}
			else if(System.getProperty("os.name").toLowerCase().indexOf("linux")>=0) {
				//Linux
				RootPath="/usr/taskboard";
			}
			else {
				RootPath="taskboard";
			}
//			System.out.println("HHSOJ root folder=\""+RootPath+"\"");
		}
		return RootPath;
	}
	
	public static Gson gs=new Gson();
	
	public static synchronized String readFileFull(String path){
		return readFileFull(new File(path));
	}
	
	public static synchronized String readFileFull(File path){
		try{
			BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(path), "utf-8"));
			String l="",r="";
			while((r=br.readLine())!=null){
				l+=r+"\n";
			}
			br.close();
			return l;
		}catch(Exception e){
			return null;
		}
	}
	
	public static synchronized User getUser(String handle){
		File f=new File(getPath()+"/user/"+handle);
		if(f.exists()){
			return gs.fromJson(readFileFull(f),User.class);
		}else{
			return null;
		}
	}
	
	public static synchronized ArrayList<User> getAllUsers(){
		File f=new File(getPath()+"/user/");
		if(f.exists()){
			ArrayList<User> arr=new ArrayList<>();
			for(String sub:f.list()){
				arr.add(getUser(sub));
			}
			
			return arr;
		}else{
			return new ArrayList<>();
		}
	}
}
