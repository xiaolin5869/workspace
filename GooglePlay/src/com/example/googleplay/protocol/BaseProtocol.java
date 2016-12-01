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
import com.example.googleplay.http.HttpHelper;
import com.example.googleplay.http.HttpHelper.HttpResult;
import com.example.googleplay.tools.Consts;
import com.example.googleplay.tools.FileUtils;
import com.example.googleplay.tools.Utils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.ResponseStream;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public abstract class BaseProtocol<T> {

	/**
	 * 
	 */
	
	public T applist;
	
	public T load(int index)
	{
		//�ӱ��ؼ���json����
		String json=loadLocal(index);
	
		if(json==null)
		{
			json=loadServer(index);	
			
			//���浽����
			saveLocal(json, index);
		}
		  applist=parseJson(json);	
		
		return applist;
	}

	/**
	 * ����json
	 * @param json
	 * @return
	 */
	public abstract T parseJson(String json);
	
	/**
	 * ������ַ��url�Ĺؼ���
	 * @return
	 */
	public abstract String getKey();

	    /**
	     *�ӷ�������ȡ����
	     * @param index
	     * @return
	     */
		private String loadServer(int index) 
		{
			HttpResult httpResult = HttpHelper.get(HttpHelper.URL +getKey()
					+ "?index=" + index+getParams()); 
			
			if(httpResult!=null)
			{
			   String json = httpResult.getString();
			    return json;
			}else
			{
				return null;
			}
		}

		/**
		 * ��Ҫ���������Ӷ����������,һ������·��ؿ�
		 * @return
		 */
	public  String getParams() 
	{
		
			return "";
	}

	/**
	 * �ӱ��ؼ�������
	 * @param index
	 * @return
	 */
	public  String loadLocal(int index) {
		
		 File dir=FileUtils.getDirCache();
			
	     File file=new File(dir,getKey()+"_"+index+getParams());
   
//	     System.out.println(file.getAbsolutePath());
	
	     FileReader fr=null;
	 	 BufferedReader br=null;
	 	 
	     try 
	     {
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			
			long outdate=Long.parseLong(br.readLine());
			
			if(outdate>System.currentTimeMillis())
			{
				//�������ݹ���
				return null;
			}
			
			String str=null;
			StringWriter sw=new StringWriter();
			
			while((str=br.readLine())!=null)
			{
				sw.write(str);
			}
			
			return sw.toString();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}finally
         {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/**
	 * �������ݵ�����
	 * @param json
	 * @param index
	 */
	private void saveLocal(String json, int index) 
	{
        File dir=FileUtils.getDirCache();
		
		File file=new File(dir,getKey()+"_"+index+getParams());
		
//		System.out.println(file.toString());
		
		FileWriter fw=null;
		BufferedWriter bw=null;
		try {
			
			fw = new FileWriter(file);
		    bw=new BufferedWriter(fw);
			
			bw.write(System.currentTimeMillis()+100*1000+"");
			bw.newLine();
			
			bw.write(json);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			if(bw!=null)
			{
				try {
					bw.flush();
					bw.close();
					fw.close();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
	}

}
