/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkofeurope_2015;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;
import tools.parsing.org.json.JSONException;
import tools.parsing.org.json.JSONObject;

/**
 *
 * @author smyrgeorge
 */
public class ElasticSearchIntegration {
    
    private final String elasticSearchURI_greek = "http://10.20.72.190:9200/talkofeurope/greek/";
    private final String elasticSearchURI_english= "http://10.20.72.190:9200/talkofeurope/eu_topics_gr/";
    private final String elasticSearchQueryURI = "http://10.20.72.190:9200/_all/_search?";
    
    public int length;
    
    public ElasticSearchIntegration(){
        
    }
    
    public void sendToElasticSearch_en(String text, String sessionday){

        Random rn = new Random();
        int random = rn.nextInt(1000) + 1;
        
        try {
            String url = elasticSearchURI_english + sessionday + "_" + random;

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            String data =  "{\"topic\": \""+text+"\","
                    + " \"session_day\": \""+sessionday+"\"}";
            
            try {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(data);
                out.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            String line = "";
            BufferedReader in = new BufferedReader(new 
                                     InputStreamReader(conn.getInputStream()));
            
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void sendToElasticSearch_el(String text, String sessionday, String firstname, String lastname, String country){

        Random rn = new Random();
        int random = rn.nextInt(1000) + 1;
        
        try {
            String url = elasticSearchURI_english + sessionday + "_" + random;

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            String data =  "{\"text\": \""+text+"\","
                    + " \"sessionday\": \""+sessionday+"\","
                    + " \"firstname\": \""+firstname+"\","
                    + " \"lastname\": \""+lastname+"\","
                    + " \"country\": \""+country+"\"}";
            
            try {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(data);
                out.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            String line = "";
            BufferedReader in = new BufferedReader(new 
                                     InputStreamReader(conn.getInputStream()));
            
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    public double executeQuery(String query){

        double score = 0.0;
        
        try {
            String url = elasticSearchQueryURI;

            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            
            
            try {
                OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
                out.write(query);
                out.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }

            String json = "";
            String line = "";
            BufferedReader in = new BufferedReader(new 
                                     InputStreamReader(conn.getInputStream()));
            
            while ((line = in.readLine()) != null) {
                json = json + line + " ";
            }

            System.out.println(json);
            
            JSONObject jsonObj = new JSONObject(json);
            try{
                score = jsonObj.getJSONObject("hits").getDouble("max_score");
            }
            catch (JSONException ex){
                score = 0.0;
            }
            
            System.out.println(score);
            
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(JSONException e){
            e.printStackTrace();
        }
    
        
        return score;
    }
    

    
    
    public String queryBuilder(String query){
        String q = "{\"facets\":{\"terms\":{\"terms\":{\"field\":\"_type\",\"size\":10,\"order\":\"count\","
                + "\"exclude\":[]},\"facet_filter\":{\"fquery\":{\"query\":{\"filtered\":{\"query\":{\"bool\":"
                + "{\"should\":[{\"query_string\":{\"query\":"
                + "\""+query+"\""
                + "}}]}},\"filter\":{\"bool\":{\"must\":[{\"terms\":{\"_type\":[\"english\"]}}]}}}}}}}},\"size\":0}";
        return q;
    }
}
