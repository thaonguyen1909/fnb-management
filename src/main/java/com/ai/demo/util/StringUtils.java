package com.ai.demo.util;


import java.text.Normalizer;
import java.util.regex.Pattern;

public class StringUtils {
    public static String toSlug(String input){
        if(input == null || input.isEmpty()){
            return "";
        }
        //B1. Tach dau thanh ra khoi chu cai
        String nomalized = Normalizer.normalize(input,Normalizer.Form.NFD);

        //B2. Dung regex de xoa cac ky tu dac biet
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        String slug = pattern.matcher(nomalized).replaceAll("");

        return slug.toLowerCase()
                .replaceAll("đ", "d") //Thay the ky tu "đ" bang "d"
                .replaceAll("[^a-zA-Z0-9]", "") //B3. Thay the cac ky tu khong phai chu cai va so bang dau "-"
                .replaceAll("-+", "-") //B4. Xoa cac dau "-" lien tiep
                .replaceAll("^-|-$", "")//B5. Xoa dau "-" o dau va cuoi chuoi
                ;
    }
}
