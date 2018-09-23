/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.util;

import java.util.LinkedHashMap;

/**
 *
 * @author thangdm
 */
public class XosoUtil {
    
    //follow kq
    public static LinkedHashMap<String, String> hmXosoName = new LinkedHashMap<String, String>();
    static{
        hmXosoName.put("ag", "An Giang");
        hmXosoName.put("bl", "Bạc Liêu");
        hmXosoName.put("btr", "Bến Tre");
        hmXosoName.put("bdu", "Bình Dương");
        hmXosoName.put("bp", "Bình Phước");
        hmXosoName.put("bth", "Bình Thuận");
        hmXosoName.put("cm", "Cà Mau");
        hmXosoName.put("ct", "Cần Thơ");
        hmXosoName.put("dlt", "Đà Lạt");
        hmXosoName.put("dni", "Đồng Nai");
        hmXosoName.put("dt", "Đồng Tháp");
        hmXosoName.put("hg", "Hậu Giang");
        hmXosoName.put("hcm", "Hồ Chí Minh");
        hmXosoName.put("kg", "Kiên Giang");
        hmXosoName.put("la", "Long An");
        hmXosoName.put("st", "Sóc Trăng");
        hmXosoName.put("tn", "Tây Ninh");
        hmXosoName.put("tg", "Tiền Giang");
        hmXosoName.put("tv", "Trà Vinh");
        hmXosoName.put("vl", "Vĩnh Long");
        hmXosoName.put("vt", "Vũng Tàu");
        hmXosoName.put("bdi", "Bình Định");
        hmXosoName.put("dna", "Đà Nẵng");
        hmXosoName.put("dlc", "Đắc Lắc");
        hmXosoName.put("dno", "Đắc Nông");
        hmXosoName.put("gl", "Gia Lai");
        hmXosoName.put("kh", "Khánh Hòa");
        hmXosoName.put("kt", "Kon Tum");
        hmXosoName.put("nt", "Ninh Thuận");
        hmXosoName.put("py", "Phú Yên");
        hmXosoName.put("qb", "Quảng Bình");
        hmXosoName.put("qng", "Quảng Ngãi");
        hmXosoName.put("qna", "Quảng Nam");
        hmXosoName.put("qt", "Quảng Trị");
        hmXosoName.put("tth", "Thừa Thiên Huế");
    }
    
    public static LinkedHashMap<String, String> hmXosoCode = new LinkedHashMap<String, String>();
    static{
        hmXosoCode.put("ag", "AG");
        hmXosoCode.put("bl", "BL");
        hmXosoCode.put("btr", "BTR");
        hmXosoCode.put("bdu", "BD");
        hmXosoCode.put("bp", "BP");
        hmXosoCode.put("bth", "BTH");
        hmXosoCode.put("cm", "CM");
        hmXosoCode.put("ct", "CT");
        hmXosoCode.put("dlt", "DL");
        hmXosoCode.put("dni", "DN");
        hmXosoCode.put("dt", "DT");
        hmXosoCode.put("hg", "HG");
        hmXosoCode.put("hcm", "HCM");
        hmXosoCode.put("kg", "KG");
        hmXosoCode.put("la", "LA");
        hmXosoCode.put("st", "ST");
        hmXosoCode.put("tn", "TN");
        hmXosoCode.put("tg", "TG");
        hmXosoCode.put("tv", "TV");
        hmXosoCode.put("vl", "VL");
        hmXosoCode.put("vt", "VT");
        hmXosoCode.put("bdi", "BDH");
        hmXosoCode.put("dna", "DNG");
        hmXosoCode.put("dlc", "DLK");
        hmXosoCode.put("dno", "DNO");
        hmXosoCode.put("gl", "GL");
        hmXosoCode.put("kh", "KH");
        hmXosoCode.put("kt", "KT");
        hmXosoCode.put("nt", "NT");
        hmXosoCode.put("py", "PY");
        hmXosoCode.put("qb", "QB");
        hmXosoCode.put("qng", "QNI");
        hmXosoCode.put("qna", "QNM");
        hmXosoCode.put("qt", "QT");
        hmXosoCode.put("tth", "TTH");
    }
    
    //follow kqvn
    public static LinkedHashMap<String, String> hmXosoCityCode = new LinkedHashMap<String, String>();
    static{
        hmXosoCityCode.put("An Giang", "AG");
        hmXosoCityCode.put("Bạc Liêu", "BL");
        hmXosoCityCode.put("Bến Tre", "BTR");
        hmXosoCityCode.put("Bình Dương", "BD");
        hmXosoCityCode.put("Bình Phước", "BP");
        hmXosoCityCode.put("Bình Thuận", "BTH");
        hmXosoCityCode.put("Cà Mau", "CM");
        hmXosoCityCode.put("Cần Thơ", "CT");
        hmXosoCityCode.put("Đà Lạt", "DL");
        hmXosoCityCode.put("Đồng Nai", "DN");
        hmXosoCityCode.put("Đồng Tháp", "DT");
        hmXosoCityCode.put("Hậu Giang", "HG");
        hmXosoCityCode.put("Hồ Chí Minh", "HCM");
        hmXosoCityCode.put("Kiên Giang", "KG");
        hmXosoCityCode.put("Long An", "LA");
        hmXosoCityCode.put("Sóc Trăng", "ST");
        hmXosoCityCode.put("Tây Ninh", "TN");
        hmXosoCityCode.put("Tiền Giang", "TG");
        hmXosoCityCode.put("Trà Vinh", "TV");
        hmXosoCityCode.put("Vĩnh Long", "VL");
        hmXosoCityCode.put("Vũng Tàu", "VT");
        hmXosoCityCode.put("Bình Định", "BDH");
        hmXosoCityCode.put("Đà Nẵng", "DNG");
        hmXosoCityCode.put("Đắc Lắc", "DLK");
        hmXosoCityCode.put("Đắc Nông", "DNO");
        hmXosoCityCode.put("Gia Lai", "GL");
        hmXosoCityCode.put("Khánh Hòa", "KH");
        hmXosoCityCode.put("Kon Tum", "KT");
        hmXosoCityCode.put("Ninh Thuận", "NT");
        hmXosoCityCode.put("Phú Yên", "PY");
        hmXosoCityCode.put("Quảng Bình", "QB");
        hmXosoCityCode.put("Quảng Ngãi", "QNI");
        hmXosoCityCode.put("Quảng Nam", "QNM");
        hmXosoCityCode.put("Quảng Trị", "QT");
        hmXosoCityCode.put("Thừa Thiên Huế", "TTH");
    }
} 
