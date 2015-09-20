
import twitter4j.Paging;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class GetUserStatus {
	public static void main(String[] args) {

		Twitter twitter = new TwitterFactory().getInstance();

		//	My Applications Consumer and Auth Access Token
		twitter.setOAuthConsumer("ab6ro9XNrx3DPUR9Ly1jF380M", "YGkuhQCm1YrqLpdtSKsP3CkWsdWBEfoUyPCIYk4xvlGwBZKZq8");
		twitter.setOAuthAccessToken(new AccessToken("3618212414-01XEpSoMdJwTygWEeNL06ehWvo6xy1bfKO5bWs2", "v444cPMjTB6zftnFeqG4uHPxBT7Ia8MdSHJhxzoq9nPyR"));
		try {
			ResponseList<Status> a = twitter.getHomeTimeline(new Paging(1,10));

			for(Status b: a) {
				System.out.println(b.getText() + "\n");
			}

		}catch(Exception e ){
				System.out.println(e.getMessage());
		}
	}


}
