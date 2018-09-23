/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.request;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import inet.bean.CapSo;
import inet.bean.CapSoJson;
import inet.bean.Lottery;
import inet.bean.LotteryJson;
import inet.bean.LotterySpecial;
import inet.bean.PhantichLoto;
import inet.model.LotteryResultDAO;
import inet.util.Contant;
import inet.util.HttpURLRequest;

/**
 *
 * @author hanhlm
 */
public class ThongKeRequest {
    
    private List<PhantichLoto> fillterPhanTichLoto(List<PhantichLoto> list,int cau){
        if(list==null||list.isEmpty()){return list;}
        List<PhantichLoto> listPhanTichLoto=null;        
        for(int i=0;i<list.size();i++){
            if(list.get(i).getDodai()>=cau){
                if(listPhanTichLoto==null){listPhanTichLoto=new ArrayList<PhantichLoto>();}
                listPhanTichLoto.add(list.get(i));
            }
        }
        
        return listPhanTichLoto;
    }
    
    private List<CapSo> orderLoto(List<CapSo> list,String order){
        //if(order==null||"".equals(order)){return list;}
        if(list==null||list.isEmpty()){return list;}
        CapSo capSo1=null;
        CapSo capSo2=null;       
        int so1=0;
        int so2=0;
        String[] arrStr=null;
        for(int i=0;i<list.size()-1;i++){
            for(int j=i+1;j<list.size();j++){
                capSo1=list.get(i);
                capSo2=list.get(j);
                if("gan_max".equals(order)){
                    if(capSo1.getGancucdai()<capSo2.getGancucdai()){
                        list.set(i, capSo2);
                        list.set(j, capSo1);
                    }
                }else if("ngay_chua_ve".equals(order)){
                    if(capSo1.getSongaychuave()<capSo2.getSongaychuave()){
                        list.set(i, capSo2);
                        list.set(j, capSo1);
                    }
                }else if("lan_xuat_hien".equals(order)){
                    if(capSo1.getSolanxuathien()<capSo2.getSolanxuathien()){
                        list.set(i, capSo2);
                        list.set(j, capSo1);
                    }
                }else if("ngay_ve_gan_nhat".equals(order)){
                    if(capSo1.getDdmmyyyy()==null||"".equals(capSo1.getDdmmyyyy())){break;}
                    else{
                        arrStr=capSo1.getDdmmyyyy().split("/");
                        so1=Integer.parseInt(arrStr[2]+arrStr[1]+arrStr[0]);
                        arrStr=capSo2.getDdmmyyyy().split("/");
                        so2=Integer.parseInt(arrStr[2]+arrStr[1]+arrStr[0]);
                        
                        if(so2<so1){
                            list.set(i, capSo2);
                            list.set(j, capSo1);
                        }
                    }
                }else{
                    if(Integer.parseInt(capSo1.getCapso())>Integer.parseInt(capSo2.getCapso())){
                        list.set(i, capSo2);
                        list.set(j, capSo1);
                    }
                }
            }
        }
        
        return list;
    }
    
    private List<LotterySpecial> subLotterySpecial(List<LotterySpecial> list){
        
        if(list==null||list.isEmpty()){return list;}
        LotterySpecial lotterySpecial=null;
        String loto="";
        for(int i=0;i<list.size();i++){
            lotterySpecial=list.get(i);
            if(!lotterySpecial.getOpenDate().contains("nbsp")){
                loto=lotterySpecial.getSpecial().substring(lotterySpecial.getSpecial().length()-2,lotterySpecial.getSpecial().length());
                lotterySpecial.setSpecial(lotterySpecial.getSpecial().substring(0,lotterySpecial.getSpecial().length()-2)+"<span class=\"red\">"+loto+"</span>");                
            }
        }
        
        return list;
    }
    
    public List<CapSo> addString(List<CapSo> list){
        if(list==null||list.isEmpty()){return list;}
        
        CapSo capSo=null;
        String special="";
        for(int i=0;i<list.size();i++){
            capSo=list.get(i);
            special=capSo.getSpecial();
            special=special.substring(0,3)+"<span class=\"red\">"+special.substring(3, 5)+"</span>";
            capSo.setSpecial(special);
            list.set(i, capSo);
        }
        
        return list;
    }
    
    public List<CapSo> addStringLotterySpecial(List<CapSo> list){
        if(list==null||list.isEmpty()){return list;}
        
        CapSo capSo=null;
        String special="";
        for(int i=0;i<list.size();i++){
            capSo=list.get(i);
            special=capSo.getLotteryResult1().getSpecial();
            special=special.substring(0,3)+"<span class=\"PTLT_text_DD\">"+special.substring(3, 5)+"</span>";
            capSo.getLotteryResult1().setSpecial(special);
            
            special=capSo.getLotteryResult2().getSpecial();
            special=special.substring(0,3)+"<span class=\"PTLT_text_DD\">"+special.substring(3, 5)+"</span>";
            capSo.getLotteryResult2().setSpecial(special);
            
            list.set(i, capSo);
        }
        
        return list;
    }
    
    
    
    public List<Lottery> parserLotteryPhanTichLoTo(){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_LOTTERYPHANTICHLOTO;
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("list")){
                LotteryJson lotteryJson=(LotteryJson)gson.fromJson(string, LotteryJson.class);                
                return lotteryJson.getList();
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserLotteryPhanTichLoTo=======>>"+e.toString());        
        }                
        return null;
    }
    
    
    public List<CapSo> parserThongKeDacBietCapSo(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_DACBIET_CAP_SO;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachcapso(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietCapSo=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeDacBietCapSoHangChuc(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_DAC_BIET_CAP_SO_HANG_CHUC;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachhangchuc(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietCapSoHangChuc=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeDacBietCapSoHangDonVi(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_DAC_BIET_CAP_SO_HANG_DON_VI;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachhangdonvi(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietCapSoHangDonVi=======>>"+e.toString());        
        }                
        return null;
    }
    
    
    public List<CapSo> parserThongKeLotoCapSoHangChuc(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_LO_TO_HANG_CHUC;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachcapso(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeLotoCapSoHangChuc=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeLotoCapSoHangDonVi(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_LO_TO_DON_VI;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachcapso(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeLotoCapSoHangDonVi=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeDacBietTong(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_DAC_BIET_TONG;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachtong(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietTong=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeDacBietHieu(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_DAC_BIET_HIEU;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachhieu(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietHieu=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeDacBietCham(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_DAC_BIET_CHAM;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachcham(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietCham=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeDacBietChiaHetCho3(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_DAC_BIET_CHIA_HET_CHO_3;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachchiahetcho3(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietChiaHetCho3=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeDacBietChanLe(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_DAC_BIET_CAP_SO_CHAN_LE;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return orderLoto(capSoJson.getDanhsachchanle(),order);
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietChanLe=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeLoRoi(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_LO_ROI;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return addStringLotterySpecial(orderLoto(capSoJson.getDanhsachcapso(),order));
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeLoRoi=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeDacBietGiaiSo(String code,String numOfWeek,String order){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_DAC_BIET_GIAI_SO;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachcapso")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return addString(capSoJson.getDanhsachgiaiso());
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeDacBietGiaiSo=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeNgayNayNamTruoc(String code,String numOfWeek){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_NGAY_NAY_NAM_TRUOC;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachngaynaynamxua")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return addString(capSoJson.getDanhsachngaynaynamxua());
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeNgayNayNamTruoc=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<CapSo> parserThongKeNgayNayThangTruoc(String code,String numOfWeek){
        try{
            Gson gson=new Gson();        
            String url=Contant.URL_THONG_KE_NGAY_NAY_THANG_TRUOC;
            url=url.replace("code", code).replace("numofweek", numOfWeek);
            String string=HttpURLRequest.sendGet(url);
            if(string.contains("danhsachngaynaynamxua")){
                CapSoJson capSoJson=(CapSoJson)gson.fromJson(string, CapSoJson.class);                
                return addString(capSoJson.getDanhsachngaynaynamxua());
            }
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeNgayNayThangTruoc=======>>"+e.toString());        
        }                
        return null;
    }
    
    public List<LotterySpecial> parserThongKeTheoThu(String code,String sdate,String edate){
        try{
//            Gson gson=new Gson();        
//            String url=Contant.URL_LOTTERY_RESULT_SPECIAL_START_TO_END;
//            url=url.replace("code", code).replace("sdate", sdate).replace("edate", edate);
//            String string=HttpURLRequest.sendGet(url);
//            if(string.contains("list")){
//                LotterySpecialJson lotterySpecialJson=(LotterySpecialJson)gson.fromJson(string, LotterySpecialJson.class);                
//                return subLotterySpecial(lotterySpecialJson.getList());
//            }
            if(code!=null&&!"".equals(code)&&sdate!=null&&!"".equals(sdate)&&edate!=null&&!"".equals(edate)){
                LotteryResultDAO lotteryResultDAO=new LotteryResultDAO();
                List<LotterySpecial> list=lotteryResultDAO.findLotterySpecial(code, sdate, edate);
                list=subLotterySpecial(list);
                return list;
            }
            
            
        }catch(Exception e){
            System.out.println("-----------API.XOSO.WAP.VN--------------");
            System.out.println("=======ThongKeRequest=======>>parserThongKeTheoThu=======>>"+e.toString());        
        }                
        return null;
    }
}
