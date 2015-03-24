/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package talkofeurope_2015;

/**
 *
 * @author smyrgeorge
 */
public class TalkOfEurope_2015 {

    
    public static void loadEnglishDocsToElasticSearch(){
        ElasticSearchIntegration es= new ElasticSearchIntegration();
        String path = "/home/jmoschon/Desktop/talk of Europ/ours/talkofeurope.xml";
        xmlParser.parseit(es, path);
    }
    
    public static void loadGreekDocsToElasticSearch(){
        ElasticSearchIntegration es= new ElasticSearchIntegration();
//        xmlParser.parseit(es);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        loadEnglishDocsToElasticSearch();
    }
    
}
