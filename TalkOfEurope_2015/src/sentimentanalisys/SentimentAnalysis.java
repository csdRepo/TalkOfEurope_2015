/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sentimentanalisys;

/**
 *
 * @author smyrgeorge
 */
public class SentimentAnalysis {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        StandfordNLP stnlp = new StandfordNLP();
        
        stnlp.sentimentAnalysis("the movie was boring");
        stnlp.sentimentAnalysis("the movie was great");
        
    }
    
}
