/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sentimentanalisys;

import com.gtranslate.Language;
import com.gtranslate.Translator;

/**
 *
 * @author smyrgeorge
 */
public class GoogleTranslate {
    private final String text;
    
    public GoogleTranslate(String text){
        this.text=text;
    }
    
    public String translate(){
        Translator translate = Translator.getInstance();
        String translatedtext = translate.translate(this.text, Language.GREEK, Language.ENGLISH);
        return translatedtext;
    }
    
    public static String translate(String text){
        Translator translate = Translator.getInstance();
        String translatedtext = translate.translate(text, Language.GREEK, Language.ENGLISH);
        return translatedtext;
    }
}
