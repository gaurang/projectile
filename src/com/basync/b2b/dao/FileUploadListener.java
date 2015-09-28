package com.basync.b2b.dao;
import org.apache.commons.fileupload.ProgressListener;
public class FileUploadListener implements ProgressListener{
	private volatile long bytesRead = 0L, contentLength = 0L, item = 0L;   

	private String url;
	private String thumbnail_url;
	private String name;
	private String type;
	private long size;
	private String delete_url;
	private String delete_type;
	
	public FileUploadListener() {
		super();
	}

	public void update(long aBytesRead, long aContentLength, int anItem) {
		bytesRead = aBytesRead;
		contentLength = aContentLength;
		item = anItem;
		update(url,thumbnail_url,name,type,size,delete_url,delete_type);
	}

	public void update( String url , String thumbnail_url, String name, String type, long size, String delete_url, String delete_type){
		this.url=url;
		this.thumbnail_url=thumbnail_url;
		this.name=name;
		this.type=type;
		this.size=size;
		this.delete_url=delete_url;
		this.delete_type=delete_type;
	}
	public long getBytesRead() {
		return bytesRead;
	}

	public long getContentLength() {
		return contentLength;
	}

	public long getItem() {
		return item;
	}

	public String getUrl() {
		return url;
	}

	public String getThumbnail_url() {
		return thumbnail_url;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public long getSize() {
		return size;
	}

	public String getDelete_url() {
		return delete_url;
	}

	public String getDelete_type() {
		return delete_type;
	}
	
	

}