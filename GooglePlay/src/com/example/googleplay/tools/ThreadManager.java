package com.example.googleplay.tools;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadManager
{
   private static  ThreadManager instance=new ThreadManager();
   private ThreadPoolProxy longpool;
   private ThreadPoolProxy shortpool;
   
   public static ThreadManager getInstance()
   {
	   return instance;
   }
	
   public synchronized ThreadPoolProxy createLongPool()
   {
	   if(longpool==null)
	   {
		   longpool = new ThreadPoolProxy(5, 5, 5000l);
	   }
	 
	   return longpool;
   }
   
   public synchronized ThreadPoolProxy createShortPool()
   {
	   if(shortpool==null)
	   {
		   shortpool = new ThreadPoolProxy(3, 3, 5000l);
	   }
	   
	   return shortpool;
   }
   
   
   public class ThreadPoolProxy
   {
	   private ThreadPoolExecutor pool;
	   
	   private int corePoolSize;//�����̵߳�����
	   private int maximumPoolSize;
	   private long time;
	   
	   public ThreadPoolProxy(int corepoolsize,int  maximumPoolSize,long time )
	   {
		   this.corePoolSize=corepoolsize;
		   this.maximumPoolSize=maximumPoolSize;
		   this.time=time;
		   
	   }
	   
	   public void execute(Runnable runnable)
	   {
		   if(pool==null)
		   {
			   //�����̳߳�
			   /**1���̳߳ع����̵߳ĸ���
			    * 2������̱߳�ռ�꣬���⿪���߳���
			    * 3������̳߳�û�����񣬴�����ʱ��
			    * 4��ʱ��ĵ�λ
			    * 5������̳߳ص��̶߳������ã�ʣ�µ�������ʱ�浽LinkedBlockingQueue��
			    */
			   pool=new ThreadPoolExecutor(corePoolSize, maximumPoolSize, time, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10));
		   }
		   
		   //�����̳߳�ִ������
		   pool.execute(runnable);   
	   }
	   
	   //ȡ���߳�����
	   public void cancel(Runnable runnable)
	   {
		   if(pool!=null && !pool.isShutdown() && !pool.isTerminated())
		   {
			   pool.remove(runnable);
		   }
	   }
   }
}
