package services.videa.webblog;

import java.util.Arrays;
import java.util.Date;

/**
 * Blog class containing all relevant information that reflect a web blog entry.
 */
public class Post {

	private int id = 0;

	private String title;
	private String content;
	private Date timestamp;
	private String author;
	
	private Comment[] comments = new Comment[0];
	

	public Post(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", content=" + content + ", timestamp=" + timestamp + ", author="
				+ author + ", comments=" + Arrays.toString(comments) + "]";
	}

	public int getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Comment[] getComments() {
		return comments;
	}

	public void setComments(Comment[] comments) {
		this.comments = comments;
	}

	
}
