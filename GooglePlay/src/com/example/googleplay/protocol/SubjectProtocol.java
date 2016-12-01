package com.example.googleplay.protocol;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.googleplay.bean.SubjectInfo;

public class SubjectProtocol  extends BaseProtocol<List<SubjectInfo>>
{
	public List<SubjectInfo> parseJson(String json) {
		
		List<SubjectInfo> list=new ArrayList<SubjectInfo>();
		try {
			JSONArray jsarray=new JSONArray(json);
			
			for(int i=0;i<jsarray.length();i++)
			{
				JSONObject obj=jsarray.getJSONObject(i);
				String url=obj.getString("url");
				String des=obj.getString("des");
				
				SubjectInfo info=new SubjectInfo(des, url);
				
				list.add(info);
				
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
	
		return "subject";
	}

	
}
