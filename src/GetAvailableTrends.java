
import java.util.ArrayList;

import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class GetAvailableTrends {
	public static void main(String[] args) {

		Twitter twitter = new TwitterFactory().getInstance();

		// Consumer and Auth Access Token
		twitter.setOAuthConsumer("ab6ro9XNrx3DPUR9Ly1jF380M", "YGkuhQCm1YrqLpdtSKsP3CkWsdWBEfoUyPCIYk4xvlGwBZKZq8");
		twitter.setOAuthAccessToken(new AccessToken("3618212414-01XEpSoMdJwTygWEeNL06ehWvo6xy1bfKO5bWs2",
				"v444cPMjTB6zftnFeqG4uHPxBT7Ia8MdSHJhxzoq9nPyR"));
		int[] woeid = {23424935,369300,779199,369301,346215,
				419688,2477171,137884,364497,369302,137883,55894406,
				91862995,56068648,90270522,90331918} ;
		try {
			Trends trends;
			ArrayList<String> hashList = new ArrayList<String>();
			//for(int i=0; i< woeid.length; i++){
				trends = twitter.getPlaceTrends(23424935);

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
		//	}
			for(String s : hashList)
				System.out.println(s);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
