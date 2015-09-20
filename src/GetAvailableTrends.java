
import java.util.ArrayList;

import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class GetAvailableTrends {
	public static void main(String[] args) {
//yo
		int[] woeid = {23424935, 2459115, 2466256, 2442047, 2379574, 2424766, 44418, 615702};
		ArrayList<String> hashList = null;
		Twitter twitter = new TwitterFactory().getInstance();

		// Consumer and Auth Access Token
		twitter.setOAuthConsumer("ab6ro9XNrx3DPUR9Ly1jF380M", "YGkuhQCm1YrqLpdtSKsP3CkWsdWBEfoUyPCIYk4xvlGwBZKZq8");
		twitter.setOAuthAccessToken(new AccessToken("3618212414-01XEpSoMdJwTygWEeNL06ehWvo6xy1bfKO5bWs2",
				"v444cPMjTB6zftnFeqG4uHPxBT7Ia8MdSHJhxzoq9nPyR"));
		try {
			hashList = new ArrayList<String>();
			for(int id : woeid){
			Trends trends;
			//for(int i=0; i< woeid.length; i++){
				trends = twitter.getPlaceTrends(id);

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
		//	}
			int count = 0;
			for(String s : hashList){
				if(s.charAt(0) == '#')
					count++;
				System.out.println(s);
			}
			System.out.println(count);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
