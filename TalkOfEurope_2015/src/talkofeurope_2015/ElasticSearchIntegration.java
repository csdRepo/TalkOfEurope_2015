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
    
    private final String elasticSearchURI_greek = "http://localhost:9200/talkofeurope/greek";
    private final String elasticSearchURI_english= "http://localhost:9200/talkofeurope/english";
    private final String elasticSearchQueryURI = "http://localhost:9200/_all/_search?";
    
    public int length;
    
    public ElasticSearchIntegration(){
        
    }
    
    public void sendToElasticSearch_en(String text, String sessionday, String firstname, String lastname, String country){

        Random rn = new Random();
        int answer = rn.nextInt(1000) + 1;
        
        try {
            String url = elasticSearchURI_english + sessionday + "_" + firstname + "_" + lastname + "_" +answer;

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
    
    public void sendToElasticSearch_el(String text, String sessionday, String firstname, String lastname, String country){

        try {
            String url = elasticSearchURI_greek + sessionday;

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
    
    
    private double executeQuery(String query){

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
    

    
    
    private String queryBuilder(String commentID, int sentenceID, String query){
        String q = "{\"query\":{\"filtered\":{\"query\":{\"bool\":{\"should\":[{\"query_string\":{\"query\":\""+query
                +"\"}}]}},\"filter\":{\"bool\":{\"must\":[{\"fquery\":{\"query\":{\"query_string\":{\"query\":\"comment:(\\\""
                +commentID+"\\\")\"}},\"_cache\":true}},{\"fquery\":{\"query\":{\"query_string\":{\"query\":\"sentence:(\\\""
                +sentenceID+"\\\")\"}},\"_cache\":true}}],\"must_not\":[{\"fquery\":{\"query\":{\"query_string\":{\"query\":\"Qst\"}},\"_cache\":true}}]}}}},"
                +"\"highlight\":{\"fields\":{},\"fragment_size\":2147483647,\"pre_tags\":[\"@start-highlight@\"],\"post_tags\":[\"@end-highlight@\"]},\"size\":500,"
                +"\"sort\":[{\"_score\":{\"order\":\"desc\",\"ignore_unmapped\":true}}]}";
        return q;
    }
}
