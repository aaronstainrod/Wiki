/** @author Aaron Stainrod
 * 
 * Generates summaries from Wikipedia based on user queries */

/** Imports 
* 
* HTML Parser: https://jsoup.org/
*
*/
import java.net.*;
import java.io.*;

import org.jsoup.*;

public class Wiki {

	/** User input */
	private String topic;
	
	/** Default Constructor */
	public Wiki() {

		try {
			//Prompts user for input
			System.out.print("Type in your Wikipedia.org topic: ");

			//Reads user input
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));		
			this.topic = in.readLine();

			//Prompts user repeatedly for input if blank
			while (topic.isEmpty()) {
				System.out.print("Please enter a topic: ");
				topic = in.readLine();
			} 

			//Begins connection once user input is accepted
			if (!topic.isEmpty()) {
				this.topic = topic.replaceAll(" ", "_");
				initiate(topic);
			}

		} catch (IOException e) {
			System.out.println("An error has occurred: I/O");
		}

	}

	/** Command line Constructor
	 * 
	 *  @param query		 String for command line argument
	 *  */
	//Constructor for command line argument
	public Wiki(String query) {
		initiate(query);
	}
	
	/** Main method, which creates Wiki objects as well as handles command line arguments */
	public static void main(String[] args) {
		//If command line arguments exist...
		if (args.length > 0) {
			StringBuilder topic = new StringBuilder();
			//Creates one word seperated by underscores
			for (String s: args) {
				topic.append(s + "_");
			}
			//Removes last underscore
			String query = topic.substring(0, topic.length() - 1).toString();
			Wiki w = new Wiki(query);
		//If no arguments, prompts user on startup instead
		} else {
			Wiki w = new Wiki();
		}
	}
	
	/** Prints first paragraph from Wikipedia query to console 
	 * 
	 * @param query								Used to generate full URL for Wikipedia query
	 * @exception malformedURLException			Used to process inappropriate URLs
	 * @exception ioException					Used to process IO errors
	 * 
	 * */
	public void initiate(String query) {

		try {
			//Creates a URL, opens a connection, and connects to the URL
			URL url = new URL("https://en.wikipedia.org/wiki/" + query);
			URLConnection connection = url.openConnection();
			connection.connect();

			System.out.println("Connecting to " + url.toString() + "...");

			//Handles input reading
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			//Each line of input is stored here
			String inputLine;

			//Stores each line of input
			StringBuffer s = new StringBuffer();

			//Handles termination of line reading after first paragraph
			int count = 0;

			//Gets first paragraph from Wikipedia
			while ((inputLine = in.readLine()) != null && count < 1) {
				if (inputLine.contains("<p>")) {
					s.append(inputLine + "\n");
					count++;
				}
			}
			in.close();

			//Removes tags from paragraph
			String result = Jsoup.parse(s.toString()).text();

			//Formats console output
			System.out.println("\n" + result + "\n");
		} catch (MalformedURLException malformedURLException) {
			System.out.println("An error has occurred: Page not found");
		} catch (IOException ioException) {
			System.out.println("An error has occurred: I/O");
		}
	}
}