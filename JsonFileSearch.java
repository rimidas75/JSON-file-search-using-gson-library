

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class JsonFileSearch {

	static FileWriter fw = null;
	static BufferedWriter bw = null;
	static int statusCount = 0;
	

	public static void main(String[] args) {

		
		String tweetString = null;
		String lang = null;
		String topic = null;
		File file = new File("F:\\eclipse\\tweets_search_file12.json");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try {
			fw = new FileWriter(file.getAbsoluteFile());

			bw = new BufferedWriter(fw);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		// FileReader reader = new FileReader("F:\\eclipse\\tweets_Stream1.json");
		JsonParser parser = new JsonParser();
		
		File inputFile = new File("F:\\eclipse\\tweets_Stream.json");
		Scanner input;
		JSONArray jsonArray = null ;
		StringBuilder jsonObjs = new StringBuilder();
		try {
			input = new Scanner(inputFile);
		
		  
		while(input.hasNextLine()) {  
		    jsonObjs.append(input.nextLine());  
		}  
		//System.out.println(jsonObjs.toString()); 
	//Status stat = gson1.fromJson(jsonObjs.toString(), Status.class);
		
		System.out.println("done");
		jsonArray = new JSONArray(jsonObjs.toString()); 
		} catch (JsonIOException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (JsonSyntaxException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		} catch (FileNotFoundException e3) {
			// TODO Auto-generated catch block
			e3.printStackTrace();
		}
		
		
		
		
		for( Object j :jsonArray)
			
			{
			
			JSONObject statusJsonObj = (JSONObject)j;
			
				Gson gson = new GsonBuilder().disableHtmlEscaping().create();
				JsonClass jsonObj = new JsonClass();
				String emojis = "";
				lang = statusJsonObj.getString("lang");
				

		
			
				topic = getTopic(statusJsonObj.getString("text"));
				
				/*for(SymbolEntity s1 : status.getSymbolEntities())
				{
					System.out.println("sym   " +s1.getText());
				}
				*/
				
				byte ptext[];
				

				
				String jsonTweet = gson.toJson(jsonObj);
				// writeToFile(tweetString);
				try {
					bw.write(jsonTweet);
					
					statusCount++;
				
				} catch (IOException e) {
					
					e.printStackTrace();
				}
			}

		try {
			bw.close();
			fw.close();
			System.exit(0);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

				

		
	

	}

	private static String getTopic(String text) {
		String topic = "tech";
		if (text.contains("#iphone7") || text.contains("#Appleevent"))
			topic = "tech";
		else if (text.contains("#CivilWar")|| text.contains("#Syria")||(text.contains("#Civil War"))|| (text.contains("#CivilWarMap") )|| (text.contains("#refugees"))|| (text.contains("#jordan")))
			topic = "news";
		else if (text.contains("#USOpen") || text.contains("#USOpen2016"))
			topic = "sports";
		else if (text.contains("#GameofThrones"))
			topic = "entertainment";
		else if (text.contains("#USElection 2016") || text.contains("#USElection"))
			topic = "politics";


		return topic;
	}

	// The factory instance is re-useable and thread safe.
	// Twitter twitter = TwitterFactory.getSingleton();
	/*
	 * List<Status> statuses = null; try { statuses = twitter.getHomeTimeline();
	 * } catch (TwitterException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } System.out.println("Showing home timeline."); for
	 * (Status status : statuses) {
	 * System.out.println(status.getUser().getName() + ":" + status.getText());
	 * } }
	 */
}

