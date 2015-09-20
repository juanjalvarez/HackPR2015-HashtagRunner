
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class GetAvailableTrends {
	public static ArrayList<String> getPopularHashtags() throws FileNotFoundException {
		final long FIFTEEN_MIN_IN_MS = 1800000/2;
		int[] woeid = {23424935, 2459115, 2466256, 2442047, 2379574, 2424766, 44418, 615702};
		ArrayList<String> hashList = new ArrayList<String>();
		File t_file = new File("files/twit_time.txt");
		File h_file = new File("files/tags_list.txt");
		Scanner fr = new Scanner(t_file);
		if(!fr.hasNext() || System.currentTimeMillis() - fr.nextLong() > FIFTEEN_MIN_IN_MS){
			PrintWriter time_writer = new PrintWriter(t_file);
			PrintWriter hash_writer = new PrintWriter(h_file);
			Twitter twitter = new TwitterFactory().getInstance();
	
			// Consumer and Auth Access Token
			twitter.setOAuthConsumer("ab6ro9XNrx3DPUR9Ly1jF380M", "YGkuhQCm1YrqLpdtSKsP3CkWsdWBEfoUyPCIYk4xvlGwBZKZq8");
			twitter.setOAuthAccessToken(new AccessToken("3618212414-01XEpSoMdJwTygWEeNL06ehWvo6xy1bfKO5bWs2",
					"v444cPMjTB6zftnFeqG4uHPxBT7Ia8MdSHJhxzoq9nPyR"));
			
			//Overwriting the old file
			try {
				for(int id : woeid){
				Trends trends = twitter.getPlaceTrends(id);
				//searches for names of trending topics around the world
					String str = trends.toString();
					int hashIndex = 0;
					int aposIndex = 0;
					while (true) {
						hashIndex = str.indexOf("name=", hashIndex);
						if (hashIndex == -1)
							break;
						hashIndex++;
						aposIndex = str.indexOf("'", hashIndex++);
						hashList.add(str.substring(hashIndex+4, str.indexOf("'", aposIndex+1)));
					}
				}
				//Saving time after requests were made to API
				time_writer.println(System.currentTimeMillis());
			//	}
			//Overwriting our hashtag file for our new list of top hashtags
			int count = 0;
			for(String s : hashList){
				if(s.charAt(0) == '#'){
					hash_writer.println(s);
					count++;
					System.out.println(s);
				}
			}
			System.out.println(count);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
			time_writer.close();
			hash_writer.close();
		}
		else{
			System.out.println("Your last request has been less than 15 minutes ago, cannot follow request yet.");
		}
		fr.close();
		return hashList;
	}

}
