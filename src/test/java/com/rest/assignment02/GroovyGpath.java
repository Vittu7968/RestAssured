package com.rest.assignment02;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class GroovyGpath {
	
	
	
	
	public static void main(String[] args) throws JSONException {
		
		try {
            // Specify the path to the JSON file
            File file = new File("src/main/resources/res/Assignment2.json");
            
            // Read the JSON file into a FileInputStream
//            FileInputStream fis = new FileInputStream(file);
            
         // Read the JSON file into a StringBuilder
            StringBuilder jsonStringBuilder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                jsonStringBuilder.append(line);
            }
            reader.close();

            // Convert the StringBuilder to a String
            String jsonString = jsonStringBuilder.toString();

            // Create a JSONTokener to parse the JSON data
            JSONTokener tokener = new JSONTokener(jsonString);
            
            // Create a JSONObject from the JSONTokener
            JSONObject jsonObject = new JSONObject(tokener);
            
            // Get the "names" JSONArray from the JSONObject
            JSONArray namesArray = jsonObject.getJSONArray("names");
            
            // Create an empty StringBuilder to store language names
//            StringBuilder languageNames = new StringBuilder();
            
            List<String> languageNamesList= new ArrayList<String>();
            List<String> urlList= new ArrayList<String>();
            List<String> namesList= new ArrayList<String>();
            
            // Iterate over each object in the "names" array
            for (int i = 0; i < namesArray.length(); i++) {
                // Get the language object at index i
                JSONObject languageObject = namesArray.getJSONObject(i).getJSONObject("language");
                
                // Extract the value of the "name" field from the language object
                String name = languageObject.getString("name");
                
                String url=languageObject.getString("url");
                String nameFieldvalues=namesArray.getJSONObject(i).getString("name");
                
                // Append the name to the StringBuilder
//                languageNames.append(name).append(", ");
                
                languageNamesList.add(name);
                urlList.add(url);
                namesList.add(nameFieldvalues);
            }
            
            // Remove the trailing comma and space from the StringBuilder
//            String result = languageNames.toString().replaceAll(", $", "");
            
            
            // Print the result
//            System.out.println(result);
            
            System.out.println(languageNamesList);
            System.out.println(urlList);
            System.out.println(namesList);
            
            // Close the FileInputStream
//            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
		

}
