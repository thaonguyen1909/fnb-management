package com.ai.demo.util;


import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
    public static String toSlug(String input){
        if(input == null || input.isEmpty()){
            return "";
        }
        String replaced = input
                .replace("đ","d")
                .replace("Đ", "D");
        //B1. Tach dau thanh ra khoi chu cai
        String nomalized = Normalizer.normalize(input,Normalizer.Form.NFD);
        String normalized = Normalizer.normalize(replaced,Normalizer.Form.NFD);

        //B2. Dung regex de xoa cac ky tu dac biet
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");

        return pattern.matcher(normalized).replaceAll("")
                .toLowerCase()
                .replaceAll("[^a-zA-Z0-9]", "-")
                .replaceAll("-+","-")
                .replaceAll("^-|-$","");
    }
}
