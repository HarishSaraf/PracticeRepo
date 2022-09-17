package CCC.Amazon.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadUtility {

	
	public static Properties readProperty(String folderName,String fileName) throws Exception
	{
		Properties pr=new Properties();
		FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"//"+folderName+"//"+fileName);
		pr.load(fis);
		return pr;
	}
	
	public static List<HashMap<String, String>> readJsonFile(String folderName,String fileName) throws Exception
	{
		String jsonContent=FileUtils.readFileToString(new File(System.getProperty("user.dir")+"//"+folderName+"//"+fileName),
				StandardCharsets.UTF_8);
		
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<String,String>> data=mapper.readValue(jsonContent, 
				new TypeReference<List<HashMap<String, String>>>(){});
		
		return data;
		
	}

}
