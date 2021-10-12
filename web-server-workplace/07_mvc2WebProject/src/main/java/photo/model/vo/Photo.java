package photo.model.vo;

public class Photo {

	private int photoNo;
	private String photoWriter;
	private String photoComment;
	private String photoDate;
	private String filepath;

	public Photo(int photoNo, String photoWriter, String photoComment, String photoDate, String filepath) {
		super();
		this.photoNo = photoNo;
		this.photoWriter = photoWriter;
		this.photoComment = photoComment;
		this.photoDate = photoDate;
		this.filepath = filepath;
	}

	public Photo() {
		// TODO Auto-generated constructor stub
	}

	public int getPhotoNo() {
		return photoNo;
	}

	public void setPhotoNo(int photoNo) {
		this.photoNo = photoNo;
	}

	public String getPhotoWriter() {
		return photoWriter;
	}

	public void setPhotoWriter(String photoWriter) {
		this.photoWriter = photoWriter;
	}

	public String getPhotoComment() {
		return photoComment;
	}

	public void setPhotoComment(String photoComment) {
		this.photoComment = photoComment;
	}

	public String getPhotoDate() {
		return photoDate;
	}

	public void setPhotoDate(String photoDate) {
		this.photoDate = photoDate;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	@Override
	public String toString() {
		return "Photo [photoNo=" + photoNo + ", photoWriter=" + photoWriter + ", photoComment=" + photoComment
				+ ", photoDate=" + photoDate + ", filepath=" + filepath + "]";
	}
	
}
