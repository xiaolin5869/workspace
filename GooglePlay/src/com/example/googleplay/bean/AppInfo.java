package com.example.googleplay.bean;

import java.util.List;

public class AppInfo {
    	 
    private long id;
	private String name;
	private String packageName;
	private String iconUrl;
	private float stars;
	private long size;
	private String downloadUrl;
	private String des;
	
	//-------------  在DetailActivity 额外用到的数据
	private String downloadNum;
	private String version;
	private String date;
	private String author;
	private List<String> screen;
	
	private List<String> safeUrl;
	private List<String> safeDesUrl;
	private List<String> safeDes;
	private List<Integer> safeDesColor; 
	
	
	
	public AppInfo(long id, String name, String packageName, String iconUrl,
			float stars, long size, String downloadUrl, String des,
			String downloadNum, String version, String date, String author,
			List<String> screen, List<String> safeUrl, List<String> safeDesUrl,
			List<String> safeDes, List<Integer> safeDesColor) {
		super();
		this.id = id;
		this.name = name;
		this.packageName = packageName;
		this.iconUrl = iconUrl;
		this.stars = stars;
		this.size = size;
		this.downloadUrl = downloadUrl;
		this.des = des;
		this.downloadNum = downloadNum;
		this.version = version;
		this.date = date;
		this.author = author;
		this.screen = screen;
		this.safeUrl = safeUrl;
		this.safeDesUrl = safeDesUrl;
		this.safeDes = safeDes;
		this.safeDesColor = safeDesColor;
	}
	public String getDownloadNum() {
		return downloadNum;
	}
	public void setDownloadNum(String downloadNum) {
		this.downloadNum = downloadNum;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<String> getScreen() {
		return screen;
	}
	public void setScreen(List<String> screen) {
		this.screen = screen;
	}
	public List<String> getSafeUrl() {
		return safeUrl;
	}
	public void setSafeUrl(List<String> safeUrl) {
		this.safeUrl = safeUrl;
	}
	public List<String> getSafeDesUrl() {
		return safeDesUrl;
	}
	public void setSafeDesUrl(List<String> safeDesUrl) {
		this.safeDesUrl = safeDesUrl;
	}
	public List<String> getSafeDes() {
		return safeDes;
	}
	public void setSafeDes(List<String> safeDes) {
		this.safeDes = safeDes;
	}
	public List<Integer> getSafeDesColor() {
		return safeDesColor;
	}
	public void setSafeDesColor(List<Integer> safeDesColor) {
		this.safeDesColor = safeDesColor;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPackageName() {
		return packageName;
	}
	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
	public String getIconUrl() {
		return iconUrl;
	}
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	public float getStars() {
		return stars;
	}
	public void setStars(float stars) {
		this.stars = stars;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getDownloadUrl() {
		return downloadUrl;
	}
	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	public String getDes() {
		return des;
	}
	public void setDes(String des) {
		this.des = des;
	}
	public AppInfo() {
		super();
	}
	public AppInfo(long id, String name, String packageName, String iconUrl,
			float stars, long size, String downloadUrl, String des) {
		super();
		this.id = id;
		this.name = name;
		this.packageName = packageName;
		this.iconUrl = iconUrl;
		this.stars = stars;
		this.size = size;
		this.downloadUrl = downloadUrl;
		this.des = des;
	}
	@Override
	public String toString() {
		return "AppInfo [id=" + id + ", name=" + name + ", packageName="
				+ packageName + ", iconUrl=" + iconUrl + ", stars=" + stars
				+ ", size=" + size + ", downloadUrl=" + downloadUrl + ", des="
				+ des + "]";
	}
	
	
}
