package upload;
 
import java.io.File;
import java.io.IOException;
 
import org.apache.commons.io.FileUtils;
 
import com.opensymphony.xwork2.ActionSupport;
 
public class FileUploadAction extends ActionSupport {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;
 
    public String doUpload() {
        File saveFilePath = new File("D:/Downloads/" + fileUploadFileName);
        try {
            FileUtils.copyFile(fileUpload, saveFilePath);
        } catch (IOException ex) {
            System.out.println("Couldn't save file: " + ex.getMessage());
        }
        return SUCCESS;
    }
     
    public String getFileUploadContentType() {
        return fileUploadContentType;
    }
 
    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }
 
    public String getFileUploadFileName() {
        return fileUploadFileName;
    }
 
    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }
 
    public File getFileUpload() {
        return fileUpload;
    }
 
    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }
 
}