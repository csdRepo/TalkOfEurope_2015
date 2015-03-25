/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sentimentanalisys;

import java.io.IOException;

/**
 *
 * @author smyrgeorge
 */
public class SentimentAnalysis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        StandfordNLP stnlp = new StandfordNLP();
        
        xmlParserSe.parseit(stnlp, "docs/2009.xml");
        
//        stnlp.sentimentAnalysis("the movie was boring");
//        stnlp.sentimentAnalysis("the movie was great");
        
    }
    
}
