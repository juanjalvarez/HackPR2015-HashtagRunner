
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class GetAvailableTrends {

	public static final long FIFTEEN_MINUTES = 1800000 / 2;
	public static int[] woeid = { 23424935, 2459115, 2466256, 2442047, 2379574, 2424766, 44418, 615702 };

	public static Trends getTrends(int woeid) throws TwitterException {
		Twitter twitter = new TwitterFactory().getInstance();
		twitter.setOAuthConsumer("ab6ro9XNrx3DPUR9Ly1jF380M", "YGkuhQCm1YrqLpdtSKsP3CkWsdWBEfoUyPCIYk4xvlGwBZKZq8");
		twitter.setOAuthAccessToken(new AccessToken("3618212414-01XEpSoMdJwTygWEeNL06ehWvo6xy1bfKO5bWs2",
				"v444cPMjTB6zftnFeqG4uHPxBT7Ia8MdSHJhxzoq9nPyR"));
		return twitter.getPlaceTrends(woeid);
	}

	public static ArrayList<String> convertToHashtagList(int woeid) throws TwitterException {
		ArrayList<String> hashtagList = new ArrayList<String>();
		Trends trends = getTrends(woeid);
		String str = trends.toString();
		int hashIndex = 0;
		int aposIndex = 0;
		while (true) {
			hashIndex = str.indexOf("name=", hashIndex);
			if (hashIndex == -1)
				break;
			hashIndex++;
			aposIndex = str.indexOf("'", hashIndex++);
			hashtagList.add(str.substring(hashIndex + 4, str.indexOf("'", aposIndex + 1)));
		}
		return hashtagList;
	}

	public static ArrayList<String> callTwitter() throws FileNotFoundException, TwitterException {
		ArrayList<String> hashtagList = new ArrayList<String>();
		File dataFile = new File("twitter.dat");
		PrintWriter pw = new PrintWriter(dataFile);
		pw.println(System.currentTimeMillis());
		for (int id : woeid) {
			ArrayList<String> list = convertToHashtagList(id);
			for (String s : list) {
				String hashtag = s;
				if (!hashtag.startsWith("#"))
					hashtag = "#" + hashtag;
				hashtagList.add(hashtag);
			}
		}
		pw.println(hashtagList.size());
		for (int x = 0; x < hashtagList.size(); x++)
			pw.println(hashtagList.get(x));
		pw.close();
		return hashtagList;
	}

	public static ArrayList<String> getPopularHashtags() throws FileNotFoundException, TwitterException {
		ArrayList<String> hashtagList = new ArrayList<String>();
		File dataFile = new File("twitter.dat");
		if (dataFile.exists()) {
			Scanner fr = new Scanner(dataFile);
			if (System.currentTimeMillis() - fr.nextLong() < FIFTEEN_MINUTES) {
				int amount = fr.nextInt();
				for (int x = 0; x < amount; x++)
					hashtagList.add(fr.nextLine());
				fr.close();
			} else
				hashtagList = callTwitter();
		} else
			hashtagList = callTwitter();
		return hashtagList;
	}

}
