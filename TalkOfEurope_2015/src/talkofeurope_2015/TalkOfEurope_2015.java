/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkofeurope_2015;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author smyrgeorge
 */
public class TalkOfEurope_2015 {

    
    public static void loadEnglishDocsToElasticSearch() throws IOException{
        ElasticSearchIntegration es= new ElasticSearchIntegration();
        String path = "/home/jmoschon/Desktop/talk of Europ/ours/talkofeurope.xml";
        xmlParser.parseit(es, path);
    }
    
    public static void loadGreekDocsToElasticSearch(){
        ElasticSearchIntegration es= new ElasticSearchIntegration();
//        xmlParser.parseit(es);
    }
    
    public static void executeQ() throws SAXException, ParserConfigurationException, IOException{
        ElasticSearchIntegration es= new ElasticSearchIntegration();
        
        System.out.println(GoogleTranslate.translate(ParsingLemmas.xml_search_rule1("docs/test.xml")));
//        GoogleTranslate.translate(ParsingLemmas.xml_search_rule1("docs/test.txt"))
        
        
//        es.executeQuery(es.queryBuilder("madam president honourable member remark inform representative poland lithuania"));
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
//        loadEnglishDocsToElasticSearch();
        executeQ();
    }
    
}
