/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkofeurope_2015;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import mitos.stemmer.Stemmer;

/**
 *
 * @author smyrgeorge
 */
public class TalkOfEurope_2015 {

    
    public static void loadEnglishDocsToElasticSearch() throws IOException{
        ElasticSearchIntegration es= new ElasticSearchIntegration();
//        String path = "/home/jmoschon/Desktop/talk of Europ/ours/talkofeurope.xml";
        String path = "docs/talk_of_eu_topics_gr.xml";
        xmlParser.parseit(es, path);
    }
    
    public static void loadGreekDocsToElasticSearch() throws IOException{
        final File folder = new File("/home/jmoschon/Desktop/talk of Europ/ours/xmlProcedures");
        ElasticSearchIntegration es= new ElasticSearchIntegration();
        
        for (final File fileEntry : folder.listFiles()) {

                System.out.println(fileEntry.getName());
                xmlParserGR.parseit(es, fileEntry.getAbsolutePath());
            
        }
        //xmlParserGR.parseit(es, "/home/jmoschon/Desktop/talk of Europ/ours/xmlProcedures/xmlParsedes101218.txt.xml");
//        xmlParser.parseit(es);
    }
    
//    public static void executeQ() throws SAXException, ParserConfigurationException, IOException{
//        ElasticSearchIntegration es= new ElasticSearchIntegration();
//        
//        System.out.println(GoogleTranslate.translate(ParsingLemmas.xml_search_rule1("docs/test.xml")));
////        GoogleTranslate.translate(ParsingLemmas.xml_search_rule1("docs/test.txt"))
//        
//        
////        es.executeQuery(es.queryBuilder("madam president honourable member remark inform representative poland lithuania"));
//    }
    
    public static void stemThat ( String str){
        String str1="";
        String splited[]=str.split(" ");
        for (String splited1 : splited) {
            str1=str1+" "+Stemmer.Stem(splited1);
        }
        System.err.println(str1);
    
    }
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
//        loadEnglishDocsToElasticSearch();
        //loadGreekDocsToElasticSearch();
//        executeQ();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stemmer.Initialize();
        String current;
         while((current=br.readLine())!=null){
             stemThat(current);
         }
         
    }
    
}
