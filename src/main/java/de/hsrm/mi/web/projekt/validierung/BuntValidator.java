package de.hsrm.mi.web.projekt.validierung;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BuntValidator implements ConstraintValidator<Bunt, String>{
    
    //protected String color;
    @Override
    public boolean isValid(String farbe, ConstraintValidatorContext ctx) {
        if(farbe == null || farbe.length() == 0 || farbe.isEmpty()){
            return true;
        }

        //erstes Zeichen #
        if((farbe.charAt(0) != '#')){
            return false;
            //return "erstes Zeichen kein #";
        }

        //drei oder 6 Ziffern nach #
        if(!(farbe.length() == 4 || farbe.length() == 7)){
            return false;
            //return "Farbe nicht 4 oder 7 stellen Lang";
        }

        //gültige hex Zahlen
        if(!(farbe.substring(1, farbe.length()).matches("-?[0-9a-fA-F]+"))){
            return false;
            //return "Farbe enthält ungültige Ziffern";
        }

        //farbe bunt genug
        if(farbe.length() == 4){
            if((farbe.charAt(1) == farbe.charAt(2)) || (farbe.charAt(1) == farbe.charAt(3)) || (farbe.charAt(2) == farbe.charAt(3))){
                return false;
                //return "Die Farbe ist nicht Bunt genug";
            }
        }else{
            if((farbe.substring(1, 3).equals(farbe.substring(3, 5))) || (farbe.substring(1, 3).equals(farbe.substring(5, 7))) || (farbe.substring(3, 5).equals(farbe.substring(5, 7)))) {
                return false;
                //return "Die Farbe ist nicht Bunt genug";
            }
        }
        return true;
    }
}
