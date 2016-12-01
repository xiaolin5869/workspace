package com.example.googleplay.protocol;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.googleplay.bean.AppInfo;

public class AppProtocol  extends BaseProtocol<List<AppInfo>>
{

	@Override
	public List<AppInfo> parseJson(String json) {
		
		try {
			JSONArray array=new JSONArray(json);
			List<AppInfo> list=new ArrayList<AppInfo>();
			
			for(int i=0;i<array.length();i++)
			{
				JSONObject object=array.getJSONObject(i);
				long id = object.getLong("id");
				
				String name = object.getString("name");
				String packageName=object.getString("packageName");
				String iconUrl = object.getString("iconUrl");
				float stars=Float.parseFloat(object.getString("stars"));
				long size=object.getLong("size");
				String downloadUrl = object.getString("downloadUrl");
				String des = object.getString("des");
				
				AppInfo appinfo=new AppInfo(id,name,packageName,iconUrl,stars,size,downloadUrl,des);
				list.add(appinfo);
				
			}
			
			return list;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			return null;
		}
		
	}

	@Override
	public String getKey() {
		// TODO Auto-generated method stub
		return "app";
	}

}
