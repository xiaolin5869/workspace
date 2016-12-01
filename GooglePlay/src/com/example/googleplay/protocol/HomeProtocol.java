package com.example.googleplay.protocol;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.widget.Toast;

import com.example.googleplay.bean.AppInfo;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.FileUtils;
import com.example.googleplay.tools.Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

/**
 * 鑱旂綉鑾峰彇鏁版嵁
 * @author xiaolin
 *
 */
public class HomeProtocol extends BaseProtocol<List<AppInfo>>
{

	private List<String> pictures;
	/**
	 * 解析json
	 */
	public List<AppInfo> parseJson(String json) 
	{
		
		pictures=new ArrayList<String>();
		List<AppInfo> list=new ArrayList<AppInfo>();
		try 
		{
			JSONObject jsobject=new JSONObject(json);
			JSONArray jsarray=jsobject.getJSONArray("list");
			
			//获取viewpager的图片数据
			JSONArray jsarray2=jsobject.getJSONArray("picture");
			for(int i=0;i<jsarray2.length();i++)
			{
				String str=jsarray2.getString(i);
				pictures.add(str);			
			}
			
			for(int i=0;i<jsarray.length();i++)
			{
				JSONObject jsobj=jsarray.getJSONObject(i);
				long id=jsobj.getLong("id");
				String name = jsobj.getString("name");
				String packageName=jsobj.getString("packageName");
				String iconUrl = jsobj.getString("iconUrl");
				float stars=Float.parseFloat(jsobj.getString("stars"));
				long size=jsobj.getLong("size");
				String downloadUrl = jsobj.getString("downloadUrl");
				String des = jsobj.getString("des");
				
				AppInfo app=new AppInfo(id,name,packageName,iconUrl,stars,size,downloadUrl,des);
			
			    list.add(app);
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
		return "home";
	}

	public List<String> getPictures()
	{
		return pictures;
	}


	
}
