/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkofeurope_2015;

 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
 
public class xmlParser {
   
  public static void parseit(ElasticSearchIntegration el, String path){  
      
    try {
      //  ElasticSearchIntegration el=new ElasticSearchIntegration();
        //String delimiter = "\t\n\r\f!@#$%^&*;:'\".,0123456789()_-[]{}<>?|~`+-=/ \'\b«»§΄―—’‘–°· \\� ";
	File fXmlFile = new File(path);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 

	doc.getDocumentElement().normalize();

	NodeList nList = doc.getElementsByTagName("tuple");

	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
                       // eElement.getElementsByTagName("literal");
                        String text=eElement.getElementsByTagName("literal").item(0).getTextContent();
                       
                        String sessionary=eElement.getElementsByTagName("uri").item(0).getTextContent();
                        String firstname=eElement.getElementsByTagName("literal").item(1).getTextContent();
                        String lastname=eElement.getElementsByTagName("literal").item(2).getTextContent();
                        String countr=eElement.getElementsByTagName("literal").item(3).getTextContent();
                        sessionary=sessionary.substring(sessionary.lastIndexOf("/")+1);
			//System.out.println("Salary : " + eElement.getElementsByTagName("literal").item(1).getTextContent());
                        el.sendToElasticSearch_en(cropNcut(text), sessionary, firstname, lastname, countr);
                      //System.out.println(cropNcut(text) +"\n"+sessionary +"\n"+firstname +"\n"+lastname );
		}
	}
    } catch (ParserConfigurationException e) {
	e.printStackTrace();
    } catch (SAXException e) {
        e.printStackTrace();
      } catch (IOException e) {
          e.printStackTrace();
      } catch (DOMException e) {
          e.printStackTrace();
      }
  }
    public static String cropNcut(String str){
                    
        String delimiter = "\t\n\r\f!@#$%^&*;:'\".,0123456789()_-[]{}<>?|~`+-=/ \'\b«»§΄―—’‘–°· \\� ";
        StringTokenizer tok = new StringTokenizer(str, delimiter, true);
        String new_str = " ";          
      
            while (tok.hasMoreTokens()){
                String token = tok.nextToken();
                if(token.length()>1)
                    new_str=new_str + " " + token.toLowerCase();
            }
        return new_str;
    }



}