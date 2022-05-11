package de.mi.hsrm.web.project.validierung;

public class BuntValidator implements ConstraintValidator<Bunt>{
    
    @Override
    public boolean isValid(String lieblingsfarbe, ConstraintValidatorContext ctx) {
        if(lieblingsfarbe.isEmpty()){
            return false;
        }

        if(lieblingsfarbe == null || lieblingsfarbe.length == 0){
            return true;
        }

        // erstes zeichen # && (länge = 4 || länge = 6 
        if("#".equals(lieblingsfarbe.charAt(0)) && (lieblingsfarbe.length() == 4 || lieblingsfarbe.length() == 6)){
            if(lieblingsfarbe.length() == 4){
                //wertepaare überprüfen
                if(lieblingsfarbe.charAt(1) != lieblingsfarbe.charAt(2) && lieblingsfarbe.charAt(1) != lieblingsfarbe.charAt(3) && lieblingsfarbe.charAt(2) != lieblingsfarbe.charAt(3)){
                    return true;
                }else {
                    return false;
                }
            }else {
                if(!lieblingsfarbe.substring(1,2).equals(lieblingsfarbe.substring(3,4)) && !lieblingsfarbe.substring(1, 2).equals(lieblingsfarbe.substring(5, 6)) && lieblingsfarbe.substring(3, 4).equals(lieblingsfarbe.substring(5, 6))){
                    return true;
                }else {
                    return false;
                }
            }
        } 
        return false;
    }
}
