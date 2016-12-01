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
	   
	   private int corePoolSize;//管理线程的数量
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
			   //创建线程池
			   /**1、线程池管理线程的个数
			    * 2、如果线程被占完，额外开的线程数
			    * 3、如果线程池没有任务，存留的时间
			    * 4、时间的单位
			    * 5、如果线程池的线程都已启用，剩下的任务临时存到LinkedBlockingQueue中
			    */
			   pool=new ThreadPoolExecutor(corePoolSize, maximumPoolSize, time, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(10));
		   }
		   
		   //调用线程池执行任务
		   pool.execute(runnable);   
	   }
	   
	   //取消线程任务
	   public void cancel(Runnable runnable)
	   {
		   if(pool!=null && !pool.isShutdown() && !pool.isTerminated())
		   {
			   pool.remove(runnable);
		   }
	   }
   }
}
