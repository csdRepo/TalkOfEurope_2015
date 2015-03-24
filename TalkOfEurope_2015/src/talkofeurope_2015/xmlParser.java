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
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.xml.sax.SAXException;
 
public class xmlParser {
   
  public static void parseit(ElasticSearchIntegration el, String path){  
      
    try {
      //  ElasticSearchIntegration el=new ElasticSearchIntegration();
	File fXmlFile = new File(path);
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = dBuilder.parse(fXmlFile);
 
	//optional, but recommended
	//read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
	doc.getDocumentElement().normalize();

	//System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	NodeList nList = doc.getElementsByTagName("tuple");
 
	//System.out.println("----------------------------");
 
	for (int temp = 0; temp < nList.getLength(); temp++) {
 
		Node nNode = nList.item(temp);
 
		System.out.println("\nCurrent Element :" + nNode.getNodeName());
            
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
 
			Element eElement = (Element) nNode;
                       // eElement.getElementsByTagName("literal");
                        String text=eElement.getElementsByTagName("literal").item(0).getTextContent();
                        String sessionary=eElement.getElementsByTagName("literal").item(1).getTextContent();
                        String firstname=eElement.getElementsByTagName("literal").item(2).getTextContent();
                        String lastname=eElement.getElementsByTagName("literal").item(3).getTextContent();
                        String countr=eElement.getElementsByTagName("literal").item(4).getTextContent();;
			//System.out.println("Salary : " + eElement.getElementsByTagName("literal").item(1).getTextContent());
                        el.sendToElasticSearch_en(text, sessionary, firstname, lastname, countr);
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