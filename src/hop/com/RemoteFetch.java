package hop.com;

/**
 * Created by Hop on 10/04/2015.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import org.json.JSONObject;

public class RemoteFetch {
	public static String getJSON(String location) {
		String yql = String
				.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\")",
						location);
		String endpoint = null;
		try {
			endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", URLEncoder.encode(yql, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			URL url = new URL(endpoint);
			URLConnection connection = url.openConnection();
			
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					connection.getInputStream()));
			StringBuilder json = new StringBuilder();
			String temp = "";
			while ((temp = reader.readLine()) != null)
				json.append(temp);
			reader.close();
			return json.toString();
			/* Gia tri tra ve khi ko get duoc du lieu tu server. Loi 404 */
			

		} catch (MalformedURLException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
