/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkofeurope_2015;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import mitos.stemmer.*;
import static talkofeurope_2015.xmlParser.cropNcut;
import static talkofeurope_2015.xmlParser.init_stopWord;

/**
 *
 * @author jmoschon
 */
public class xmlParserGR {
    
    
    
 public static void parseit(ElasticSearchIntegration el, String path) throws IOException{  
    
     // init_stopWord();
    
    try {
	File fXmlFile = new File(path);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
        Stemmer.Initialize();

	doc.getDocumentElement().normalize();
        NodeList rootNode = doc.getChildNodes();
        Element root = (Element) rootNode.item(0);
        String date = root.getAttribute("Date");
        
            String editedDate="";
            String[] splited = date.split(" ");
            // System.out.println(splited[3]);
            System.out.println(date);
            
            switch (splited[3]){
                
                case "Ιανουαρίου":
                    editedDate=splited[4]+"-"+"01"+"-"+splited[2];
                    break;
                case "Φεβρουαρίου":
                    editedDate=splited[4]+"-"+"02"+"-"+splited[2];
                    break;
                case "Μαρτίου":
                    editedDate=splited[4]+"-"+"03"+"-"+splited[2];
                    break;
                case "Απριλίου":
                    editedDate=splited[4]+"-"+"04"+"-"+splited[2];
                    break;
                case "Μαΐου":
                    editedDate=splited[4]+"-"+"05"+"-"+splited[2];
                    break;
                case "Ιουνίου":
                    editedDate=splited[4]+"-"+"06"+"-"+splited[2];
                    break;
                case "Ιουλίου":
                    editedDate=splited[4]+"-"+"07"+"-"+splited[2];
                    break;
                case "Αυγούστου":
                    editedDate=splited[4]+"-"+"08"+"-"+splited[2];
                    break;
                case "Σεπτεμβρίου":
                    editedDate=splited[4]+"-"+"09"+"-"+splited[2];
                    break;
                case "Οκτωβρίου":
                    editedDate=splited[4]+"-"+"10"+"-"+splited[2];
                    break;
                case "Νοεμβίου":
                    editedDate=splited[4]+"-"+"11"+"-"+splited[2];
                    break;
                case "Δεκεμβρίου":
                    editedDate=splited[4]+"-"+"12"+"-"+splited[2];
                    break;
                    
                    
            }
            System.out.println(editedDate);
            NodeList nList = doc.getElementsByTagName("Theme");
            
            //NodeList nList = doc.getElementsByTagName("s");
            // System.out.println(doc.getElementsByTagName("s"));
            
            
            for (int temp = 0; temp < nList.getLength(); temp++) {
                
                Node nNode = nList.item(temp);
                NodeList sList=nNode.getChildNodes();
                Element eElement=(Element) nNode;
                
                String sent_id= eElement.getAttribute("Topic");
                
                for (int tmp2=0; tmp2<sList.getLength(); tmp2++){
                    
                    Node sNode=sList.item(tmp2);
                    
                    if (sNode.getNodeType()==Node.ELEMENT_NODE){
                        Element sElement= (Element) sNode;
                        System.out.println(sElement.getAttribute("topic"));
                        el.sendToElasticSearch_el(sElement.getAttribute("topic")
                                , editedDate,Stemmer.Stem(sElement.getAttribute("topic")));
                    }
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
    
}
