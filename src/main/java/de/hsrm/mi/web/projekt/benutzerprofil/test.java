package de.hsrm.mi.web.projekt.benutzerprofil;

import java.util.Arrays;
import java.util.List;

public class test {

    private static String interessen = "Interesse1, Interesse2, Interesse3";

    public static void main(String[] args) {
        List<String> interessenListe = getInteressenListe(interessen);
        for (String element : interessenListe){
            System.out.println(element);
        } 
    }

    public static List<String> getInteressenListe(String input){
        List<String> interessenListe = Arrays.asList(input.split("\\s*,\\s*"));
        return interessenListe;
    }
    
}
