/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkofeurope_2015;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public static ArrayList<String> xml_search_rule1(String filepath) throws ParserConfigurationException, SAXException, IOException {
            ArrayList<String> document = new ArrayList<String>();
            File fXmlFile = new File (filepath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = null;
    
            dBuilder = dbFactory.newDocumentBuilder();
  
            Document doc = null;

            doc = dBuilder.parse(fXmlFile);


         //   System.out.println("searching fo VbMnMp");
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("s");
                // System.out.println(doc.getElementsByTagName("s"));


        for (int temp = 0; temp < nList.getLength(); temp++) {  

            Node nNode = nList.item(temp);
            NodeList sList=nNode.getChildNodes();
            Element eElement=(Element) nNode;

            String sent_id= new String(eElement.getAttribute("id"));
            String sentence = "";

            boolean found = false;

            for (int tmp2=0; tmp2<sList.getLength(); tmp2++){
                int i=0;
                Node sNode=sList.item(tmp2);

                if (sNode.getNodeType()==Node.ELEMENT_NODE){
                    Element sElement= (Element) sNode;
                   // System.out.println(sElement.getAttribute("wor"));
                   
                    if (i < 50){
                     sentence = sentence + sElement.getAttribute("lemma") + " ";
                        System.out.println(sentence);
                     i++;
                    }
                    else{
                        document.add(sentence);
                        sentence= sElement.getAttribute("lemma")+ " ";
                        i=0;
                        }
                   // System.out.println(sentence);
                }
            }

        }
        return document;
    }
}
