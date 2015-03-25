/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkofeurope_2015;

 
import java.io.BufferedReader;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.StringTokenizer;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
import mitos.stemmer.*;
public class xmlParser {
  
    private static final HashSet<String> stopWords= new HashSet<>();

          
   
  public static void parseit(ElasticSearchIntegration el, String path) throws IOException{  
    
      init_stopWord();
    
    try {
	File fXmlFile = new File(path);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        Stemmer.Initialize();

	doc.getDocumentElement().normalize();

	NodeList nList = doc.getElementsByTagName("tuple");

	for (int temp = 0; temp < nList.getLength(); temp++) {
		Node nNode = nList.item(temp);
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
                       // eElement.getElementsByTagName("literal");
                        String topic=cropNcut(eElement.getElementsByTagName("literal").item(0).getTextContent());
                       
                        String sessionary=eElement.getElementsByTagName("uri").item(0).getTextContent();
          
                        sessionary=sessionary.substring(sessionary.lastIndexOf("/")+1);
                        
                        
                        el.sendToElasticSearch_en(topic, sessionary, Stemmer.Stem(topic));

		}
	}
    } catch (ParserConfigurationException | SAXException | IOException | DOMException e) {
	e.printStackTrace();
    }
  }
    public static String cropNcut(String str) throws FileNotFoundException, IOException{
        String delimiter = "\t\n\r\f!@#$%^&*;:'\".,0123456789()_-[]{}<>?|~`+-=/ \'\b«»§΄―—’‘–°· \\� ";
       

        StringTokenizer tok = new StringTokenizer(str, delimiter, true);
        String new_str = " ";          
      
            while (tok.hasMoreTokens()){
                String token = tok.nextToken();
                if(token.length()>1 && !stopWords.contains(token))
                    new_str=new_str + " " + token.toLowerCase();
            }
        return new_str;
    }

public static void init_stopWord() throws FileNotFoundException, IOException{
        
    try (BufferedReader br = new BufferedReader(new FileReader("words/stopwordsEn.txt"))) {
        String term = br.readLine();            
        stopWords.add(term);
        while (term != null) {
        term = br.readLine();
        stopWords.add(term);
        }
      }
}

}