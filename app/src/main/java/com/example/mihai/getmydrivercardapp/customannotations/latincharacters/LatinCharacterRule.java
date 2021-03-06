package com.example.mihai.getmydrivercardapp.customannotations.latincharacters;

import com.mobsandgeeks.saripaar.AnnotationRule;

import java.util.ArrayList;

public class LatinCharacterRule extends AnnotationRule<LatinCharacters, String> {
    
    protected LatinCharacterRule(LatinCharacters latinCharacters) {
        super(latinCharacters);
    }

    @Override
    public boolean isValid(String string) {
        ArrayList<Character> latinCharacters = new ArrayList<>();
        latinCharacters.add('-');
        latinCharacters.add(' ');

        //adds all latin lowercase letters
        for (int i = 65; i <= 90; i++) {
            latinCharacters.add((char)i);
        }

        // adds all latin capital letters in the list
        for (int i = 97; i <= 122; i++) {
            latinCharacters.add((char)i);
        }

        for (char ch : string.toCharArray()) {
            if (!latinCharacters.contains(ch)){
                return false;
            }
        }
        return true;
    }
}
