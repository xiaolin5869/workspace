package com.example.googleplay.protocol;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.googleplay.bean.UserInfo;

public class UserProtocol extends BaseProtocol<UserInfo>
{

	@Override
	public UserInfo parseJson(String json) {
		
		try {
			
			JSONObject obj=new JSONObject(json);

			String name=obj.getString("name");
			String email=obj.getString("email");
			String url=obj.getString("url");
			
			UserInfo info=new UserInfo(name,email,url);
			
			return info;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "user";
	}

}
