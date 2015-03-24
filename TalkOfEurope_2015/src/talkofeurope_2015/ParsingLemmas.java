/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkofeurope_2015;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author jmoschon
 */
public class ParsingLemmas {
    
    

    /*xml_search_rule1: VbMnMp without lemma "καν"*/
    public static ArrayList<String> xml_search_rule1(String filePath) throws SAXException {
        File fXmlFile = new File(filePath);
        ArrayList<String> document = new ArrayList<String>();
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
 
        }
            Document doc = null;
        try {
            doc = dBuilder.parse(fXmlFile);
        } catch (IOException ex) {
        }

            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("s");

       
        for (int temp = 0; temp < nList.getLength(); temp++) {  

            Node nNode = nList.item(temp);
            NodeList sList=nNode.getChildNodes();
            Element eElement=(Element) nNode;
            String sentence = "";
            String sent_id= new String(eElement.getAttribute("id"));
            

            boolean found = false;

            for (int tmp2=0; tmp2<sList.getLength(); tmp2++){

                Node sNode=sList.item(tmp2);

                if (sNode.getNodeType()==Node.ELEMENT_NODE){
                    Element sElement= (Element) sNode;

                    sentence = sentence + sElement.getAttribute("lemmas") + " ";

                }
            }

            document.add(sentence);
        }
      return document;
    }    
    
}
