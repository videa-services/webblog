package services.videa.webblog;

public class Comment {

	private int id = 0;

	private int rating; // 1 to 5
	private String note;

	public Comment(int id) {
		super();
		this.id = id;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", rating=" + rating() + ", note=" + note + "]";
	}

	/**
	 * Return rating as string whereas 1 would be one asterisk, 2 two asterisks and
	 * so on.
	 */
	public String rating() {
		String rating = "";

		switch (this.rating) { // watch out: this must be class attribute

		case 1:
			rating += "*";
			break;

		case 2:
			rating += "**";
			break;

		case 3:
			rating += "***";
			break;

		case 4:
			rating += "****";
			break;

		case 5:
			rating += "*****";
			break;

		default:
			break;
		}

		return rating; // watch out: this must be method variable
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getId() {
		return id;
	}

}
