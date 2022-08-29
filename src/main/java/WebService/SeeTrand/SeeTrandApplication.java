package WebService.SeeTrand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class SeeTrandApplication {

	public static void main(String[] args) {

		SpringApplication.run(SeeTrandApplication.class, args);
		
	}

}
