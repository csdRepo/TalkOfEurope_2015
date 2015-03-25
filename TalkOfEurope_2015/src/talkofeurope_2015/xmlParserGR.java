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
 

	doc.getDocumentElement().normalize();
        NodeList rootNode = doc.getChildNodes();
        Element root = (Element) rootNode.item(0);
        String date = root.getAttribute("Date");
        String[] splited = date.split(" ");
        if (splited[2]=="Ιανουαρίου"){
        
        }
        else if (splited[2]=="Φεβρουαρίου"){
        
        }
        else if (splited[2]==:)
        
        System.out.println(root.getAttribute("Date"));
        
        
//        if (rootNode.getNodeType()==Node.ELEMENT_NODE){
//            Element rootElement = (Element) rootNode;
//            
//            System.out.println(rootElement.getAttribute("Date"));
//                    
//        }
	//NodeList nList = doc.getElementsByTagName("Root");

//	for (int temp = 0; temp < nList.getLength(); temp++) {
//		Node nNode = nList.item(temp);
//		System.out.println("\nCurrent Element :" + nNode.getNodeName());
//		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
// 
//			Element eElement = (Element) nNode;
//                       // eElement.getElementsByTagName("literal");
//                        String topic=cropNcut(eElement.getElementsByTagName("literal").item(0).getTextContent());
//                       
//                        String sessionary=eElement.getElementsByTagName("uri").item(0).getTextContent();
//          
//                        sessionary=sessionary.substring(sessionary.lastIndexOf("/")+1);
//                        
//                        
//                        el.sendToElasticSearch_en(topic, sessionary);
//
//		}
//	}
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
