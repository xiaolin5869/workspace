package com.example.googleplay.tools;

import java.io.File;

import android.os.Environment;

public class FileUtils 
{
  public  static final String CACHE="cache";
  public  static final String ROOT="GooglePlay";
  public  static final String ICON="icon";
  
  public static File getIconDir()
  {
	  return getDir(ICON);
  }
  
  public static File getDir(String str)
  {
	  StringBuffer path=new StringBuffer();
	  File file=null;
	  
	  if(isSdAvaiable())
	  {
		  //得到sd卡的绝对路径
		  path.append(Environment.getExternalStorageDirectory().getAbsolutePath());
		  
		  //加入分割符即斜线
		  path.append(File.separator);
		  
		  path.append(ROOT);
		  
		  path.append(File.separator);
		  
		  path.append(str);
		  
		 
	  }else
	  {
		  File filedir=Utils.getContext().getCacheDir();
		  path.append(filedir.getAbsolutePath());
		  path.append(File.separator);
		  path.append(str);
	  }
	  
//	  System.out.println(path.toString());
	  
	  file = new File(path.toString());
	  if(!file.exists() || !file.isDirectory())
	  {
		  file.mkdirs();
	  }
	  
	  return file;
	  
  }

  /**
   * 判断sd卡是否存在
   * @return
   */
    private static boolean isSdAvaiable() 
    {
    	if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED))
    	{
    		return true;
    	}else
    	{
    		return false;
    	}
	
	  
    }
    
    public static File getDirCache()
    {
    	return getDir(CACHE);
    }
}

  
  
  

