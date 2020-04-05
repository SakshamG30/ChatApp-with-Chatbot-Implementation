package download;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadFileAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private InputStream inputStream;
	private String fileName;
	private long contentLength;

	public String execute() throws FileNotFoundException {
		
		File fileToDownload = new File("D:/Downloads/Untitled.png");

		inputStream = new FileInputStream(fileToDownload);
		fileName = fileToDownload.getName();
		contentLength = fileToDownload.length();
		
		return SUCCESS;
	}
	
	public long getContentLength() {
		return contentLength;
	}

	public String getFileName() {
		return fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}	
}