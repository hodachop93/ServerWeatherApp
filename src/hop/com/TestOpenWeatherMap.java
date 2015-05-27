package hop.com;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class TestOpenWeatherMap {

	public static void main(String[] args) {
		Double lat, lon;
		lat = 16.07;
		lon = 108.22;
		try {
			URL url = new URL("http://api.openweathermap.org/data/2.5/weather?lat="+lat+"&lon="+lon);
			InputStreamReader ISReader = new InputStreamReader(url.openStream(),"UTF-8");
			BufferedReader reader = new BufferedReader(ISReader);
			
			StringBuilder json = new StringBuilder();
			String temp = "";
			while ((temp = reader.readLine()) != null)
				json.append(temp);
			reader.close();
			System.out.println(json.toString());
		}
		catch (Exception e){
			
		}
		

	}

}
