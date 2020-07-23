package services.videa.webblog;

import java.util.Date;
import java.util.Scanner;

/**
 * Main class to start web blog application.
 * 
 * Use Cases:
 * <ul>
 * <li>Show all available posts
 * <li>Create a new post
 * <li>Add a comment to an existing post
 * </ul>
 * 
 * Further use cases are to implement.
 */
public class WebBlogMain {

	private static final byte ITEM_CREATE = 1;
	private static final byte ITEM_COMMENT = 2;
	private static final byte ITEM_EXIT = 9;

	private static final String[] MENU_ITEMS = { ITEM_CREATE + ": Create Post", ITEM_COMMENT + ": Comment Post",
			ITEM_EXIT + ": Exit Web Blog" };

	/*
	 * Main method starts here which is the entry point of this application.
	 */
	public static void main(String[] args) {

		Post[] posts = initPosts();

		Scanner scanner = new Scanner(System.in);

		int selectedMenuItem = 0; // no menu selected yet
		do {

			// always show all posts prior to do any following action selected from the menu
			for (Post post : posts) {
				System.out.println(post); // print post out to console using toString method
			}

			// posts are followed by available menu items
			drawMenu();

			// user interaction required now
			System.out.print("Please select: ");
			selectedMenuItem = scanner.nextInt(); // throws an exception if no numeric value is put in

			switch (selectedMenuItem) {

			case ITEM_CREATE:
				int id = nextId(posts);
				Post createdPost = createPost(scanner, id);
				posts = appendPost(posts, createdPost); // override current posts with cloned posts
				break;

			case ITEM_COMMENT:
				System.out.print("Select post (its ID): ");
				int selectedPostId = scanner.nextInt();
				Post post = findById(selectedPostId, posts);
				commentPost(scanner, post);
				break;

			case ITEM_EXIT:
				System.out.println("Good Bye ...");
				break;

			default:
				// repeat until valid input
			}

		} while (selectedMenuItem != ITEM_EXIT); // exit webblog application

		// last action before cancel program is closing the scanner
		scanner.close();
	}

	private static void commentPost(Scanner scanner, Post post) {
		int id = nextId(post.getComments());
		Comment comment = new Comment(id);

		System.out.print("Rating (1-5): ");
		int rating = scanner.nextInt();
		comment.setRating(rating);

		System.out.print("Note: ");
		String note = scanner.next();
		comment.setNote(note);

		appendComment(comment, post);
		// post now has previous comments plus the new one

	}

	/*
	 * This methods takes care of creating a post. User interaction is required in
	 * such user shall put in title, content and author name. An instantiated post
	 * set with input values is returned.
	 */
	private static Post createPost(Scanner scanner, int id) {
		Post post = new Post(id);

		System.out.print("Title: ");
		String title = scanner.next();
		post.setTitle(title);

		System.out.print("Content: ");
		String content = scanner.next();
		post.setContent(content);

		System.out.print("Author: ");
		String author = scanner.next();
		post.setAuthor(author);

		// note: externalise redundant code above to a reusable method

		// timestamp is set automatically when post is being created
		post.setTimestamp(new Date());

		return post;
	}

	/*
	 * Put the menu up to the console. Taken menu is the predefined menu of this
	 * webblog application.
	 */
	private static void drawMenu() {
		for (int i = 0; i < MENU_ITEMS.length; i++) {
			System.out.println(MENU_ITEMS[i]);
		}
	}

	/*
	 * This method takes an array of posts, clones it and appends a given additional
	 * post to it. Return value is the cloned post array.
	 */
	private static Post[] appendPost(Post[] currentPosts, Post additionalPost) {
		Post[] extendedPosts = new Post[currentPosts.length + 1];

		for (int i = 0; i < currentPosts.length; i++) {
			extendedPosts[i] = currentPosts[i];
		}
		extendedPosts[extendedPosts.length - 1] = additionalPost;

		return extendedPosts;
	}

	/*
	 * Append a new comment to a given post.
	 */
	private static void appendComment(Comment comment, Post post) {
		Comment[] extendedComments = new Comment[post.getComments().length + 1];

		for (int i = 0; i < post.getComments().length; i++) {
			extendedComments[i] = post.getComments()[i];
		}
		extendedComments[extendedComments.length - 1] = comment;

		post.setComments(extendedComments);
	}

	/*
	 * Ascertain the highest id value from all post ids in array.
	 */
	private static int nextId(Post[] posts) {
		int maxId = -1;

		for (Post post : posts) {
			maxId = post.getId() > maxId ? post.getId() : maxId;
		}

		return ++maxId;
	}

	/*
	 * Ascertain the highest id value from all comment ids in array.
	 */
	private static int nextId(Comment[] comments) {
		int maxId = -1;

		for (Comment comment : comments) {
			maxId = comment.getId() > maxId ? comment.getId() : maxId;
		}

		return ++maxId;
	}

	/*
	 * Find the post in the array which has the exact id as the given one. If no
	 * post is found null is returned.
	 */
	private static Post findById(int id, Post[] posts) {

		for (Post post : posts) {
			if (post.getId() == id) {
				return post;
			}
		}

		return null; // no post with given id found
	}

	/*
	 * For testing purposes put some random posts and comments to the memory.
	 */
	private static Post[] initPosts() {
		Post[] initialPosts = new Post[2];

		Post p1 = new Post(1);
		p1.setTitle("Post 1");
		p1.setContent("Content 1");
		p1.setAuthor("Author 1");

		Comment c1 = new Comment(1);
		c1.setRating(1);
		c1.setNote("sehr gut gemacht");
		Comment[] comments = new Comment[1];
		comments[0] = c1;
		p1.setComments(comments);

		initialPosts[0] = p1;

		Post p2 = new Post(2);
		p2.setTitle("Post 2");
		p2.setContent("Content 2");
		p2.setAuthor("Author 2");

		initialPosts[1] = p2;

		return initialPosts;
	}

}
