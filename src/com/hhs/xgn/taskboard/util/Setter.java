package com.hhs.xgn.taskboard.util;

import java.io.File;
import java.io.PrintWriter;

import com.google.gson.Gson;
import com.hhs.xgn.taskboard.type.User;

public class Setter {

	public static synchronized String writeFile(String path,String value){
		try{
			PrintWriter pw=new PrintWriter(path,"utf-8");
			pw.print(value);
			pw.close();
			return "Success";
		}catch(Exception e){
			return "Failed:"+e+"";
		}
	}
	
	public static Gson gs=new Gson();
	
	public static synchronized void addUser(String user, String pass) {
		User u=new User();
		u.username=user;
		u.password=pass;
		
		updateUser(user,u);
	}

	public static synchronized  void updateUser(String user, User u) {

		File f=new File(Getter.getPath()+"/user");
		if(!f.exists()){
			f.mkdirs();
		}
		
		
		writeFile(Getter.getPath()+"/user/"+user,gs.toJson(u));
	}
}
