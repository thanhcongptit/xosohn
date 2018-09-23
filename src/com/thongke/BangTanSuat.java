/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thongke;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import inet.bean.Loto;
import inet.bean.LotteryResult;
import inet.bean.TanSuat;
import inet.model.LotteryResultDAO;
import inet.util.DatePro;
import inet.util.LotoUtils;
import inet.util.StringConvert;

/**
 *
 * @author hanhlm
 */
public class BangTanSuat {

    public List<List<TanSuat>> findTanSuatDacBiet(String code, String sDate, String eDate, int opt) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResult(code, sDate, eDate);
        List<List<TanSuat>> list = null;

        if (listLottery != null && !listLottery.isEmpty()) {
            List<TanSuat> listTansuat = null;
            TanSuat tanSuat = null;
            String loto = "";
            String lotoResult = "";
            LotteryResult lotteryResult = null;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                lotoResult = StringConvert.subRight(lotteryResult.getSpecial(), 2);
                listTansuat = new ArrayList<>();
                for (int j = 0; j < 10; j++) {
                    loto = "" + j;
                    tanSuat = new TanSuat();
                    tanSuat.setCapso(loto);
                    tanSuat.setOpenDate(lotteryResult.getOpenDate());
                    tanSuat.setSolanve(LotoUtils.countLotoResultDacBiet(lotoResult, loto, opt));
                    listTansuat.add(tanSuat);
                }
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(listTansuat);
            }
        }
        return list;
    }

    public List<List<TanSuat>> findTanSuat(String code, String sDate, String eDate) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResult(code, sDate, eDate);
        List<List<TanSuat>> list = null;

        if (listLottery != null && !listLottery.isEmpty()) {
            List<TanSuat> listTansuat = null;
            TanSuat tanSuat = null;
            String loto = "";
            String lotoResult = "";
            String lotospecial = "";
            LotteryResult lotteryResult = null;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                if (null != lotteryResult) {
                    lotoResult = StringConvert.lotoResult(lotteryResult);
                    lotospecial = StringConvert.subRight(lotteryResult.getSpecial(), 2);
                    listTansuat = new ArrayList<>();
                    for (int j = 0; j < 100; j++) {
                        if (j < 10) {
                            loto = "0" + j;
                        } else {
                            loto = "" + j;
                        }
                        tanSuat = new TanSuat();
                        tanSuat.setCapso(loto);
                        tanSuat.setOpenDate(lotteryResult.getOpenDate());
                        tanSuat.setSolanve(LotoUtils.countLotoResult(lotoResult, loto, false));
                        if (loto.equals(lotospecial)) {
                            tanSuat.setSpecial(true);
                        }
                        listTansuat.add(tanSuat);
                    }
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(listTansuat);
                }
            }
        }
        return list;
    }

    //Danh sach tan suat theo thu trong tuan
    public List<List<TanSuat>> findTanSuatTheoThu(String code, String thu, String sDate, String eDate) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResult(code, sDate, eDate);
        List<List<TanSuat>> list = null;

        if (listLottery != null && !listLottery.isEmpty()) {
            List<TanSuat> listTansuat = null;
            TanSuat tanSuat = null;
            String loto = "";
            String lotoResult = "";
            String lotospecial = "";
            LotteryResult lotteryResult = null;
            String tmpThu = "";
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                tmpThu = DatePro.getDateOfWeek(lotteryResult.getOpenDate());
                if (tmpThu == null || !tmpThu.equalsIgnoreCase(thu)) {
                    continue;
                }
                lotoResult = StringConvert.lotoResult(lotteryResult);
                lotospecial = StringConvert.subRight(lotteryResult.getSpecial(), 2);
                listTansuat = new ArrayList<>();
                for (int j = 0; j < 100; j++) {
                    if (j < 10) {
                        loto = "0" + j;
                    } else {
                        loto = "" + j;
                    }
                    tanSuat = new TanSuat();
                    tanSuat.setCapso(loto);
                    tanSuat.setOpenDate(lotteryResult.getOpenDate());
                    tanSuat.setSolanve(LotoUtils.countLotoResult(lotoResult, loto, false));
                    if (loto.equals(lotospecial)) {
                        tanSuat.setSpecial(true);
                    }
                    listTansuat.add(tanSuat);
                }
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(listTansuat);
            }
        }
        return list;
    }

    public List<List<TanSuat>> findTanSuatTheoCap(String code, String sDate, String eDate) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResult(code, sDate, eDate);
        List<List<TanSuat>> list = null;

        String[] capLoto = {"00-55", "01-10", "02-20", "03-30", "04-40", "05-50", "06-60", "07-70", "08-80", "09-90",
            "11-66", "12-21", "13-31", "14-41", "15-51", "16-61", "17-71", "18-81", "19-91",
            "22-77", "23-32", "24-42", "25-52", "26-62", "27-72", "28-82", "29-92",
            "33-88", "34-43", "35-53", "36-63", "37-73", "38-83", "39-93",
            "44-99", "45-54", "46-64", "47-74", "48-84", "49-94",
            "56-65", "57-75", "58-85", "59-95",
            "67-76", "68-86", "69-96",
            "78-87", "79-97",
            "89-98"};

        if (listLottery != null && !listLottery.isEmpty()) {
            List<TanSuat> listTansuat = null;
            TanSuat tanSuat = null;
            String loto = "";
            String lotoResult = "";
            LotteryResult lotteryResult = null;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                lotoResult = StringConvert.lotoResult(lotteryResult);
                listTansuat = new ArrayList<>();
                for (int j = 0; j < capLoto.length; j++) {
                    loto = capLoto[j];
                    tanSuat = new TanSuat();
                    tanSuat.setCapso(loto);
                    tanSuat.setOpenDate(lotteryResult.getOpenDate());
                    tanSuat.setSolanve(LotoUtils.countLotoResult(lotoResult, loto));
                    listTansuat.add(tanSuat);
                }
                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(listTansuat);
            }
        }
        return list;
    }

    public List<List<TanSuat>> findTanSuatTheoDan(String code, String sDate, String eDate, String dan) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResult(code, sDate, eDate);
        List<List<TanSuat>> list = null;

        if (listLottery != null && !listLottery.isEmpty()) {
            List<TanSuat> listTansuat = null;
            TanSuat tanSuat = null;
            String loto = "";
            String lotoResult = "";
            LotteryResult lotteryResult = null;
            int dem = 0;
            int socap = 0;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                lotoResult = StringConvert.lotoResult(lotteryResult);
                listTansuat = new ArrayList<>();
                dem = 0;
                socap = 0;
                for (StringTokenizer stringTokenizer = new StringTokenizer(dan, ","); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if (LotoUtils.countLotoResult(lotoResult, token) > 0) {
                        dem++;
                    }
                    socap++;
                }
                if (dem == socap) {
                    tanSuat = new TanSuat();
                    tanSuat.setCapso(dan);
                    tanSuat.setOpenDate(lotteryResult.getOpenDate());
                    tanSuat.setSolanve(1);
                    listTansuat.add(tanSuat);
                } else {
                    tanSuat = new TanSuat();
                    tanSuat.setCapso(dan);
                    tanSuat.setOpenDate(lotteryResult.getOpenDate());
                    tanSuat.setSolanve(0);
                    listTansuat.add(tanSuat);
                }

                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(listTansuat);
            }
        }
        return list;
    }

    public List<List<TanSuat>> findTanSuatTheoDanDacBiet(String code, String sDate, String eDate, String dan) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResult(code, sDate, eDate);
        List<List<TanSuat>> list = null;

        if (listLottery != null && !listLottery.isEmpty()) {
            List<TanSuat> listTansuat = null;
            TanSuat tanSuat = null;
            String loto = "";
            String lotoResult = "";
            LotteryResult lotteryResult = null;
            int dem = 0;
            for (int i = 0; i < listLottery.size(); i++) {
                lotteryResult = listLottery.get(i);
                lotoResult = StringConvert.subRight(lotteryResult.getSpecial(), 2);
                listTansuat = new ArrayList<>();
                dem = 0;
                for (StringTokenizer stringTokenizer = new StringTokenizer(dan, ","); stringTokenizer.hasMoreTokens();) {
                    String token = stringTokenizer.nextToken();
                    if (LotoUtils.countLotoResultDacBiet(lotoResult, token, 3) > 0) {
                        dem++;
                    }
                }
                if (dem > 0) {
                    tanSuat = new TanSuat();
                    tanSuat.setCapso(dan);
                    tanSuat.setOpenDate(lotteryResult.getOpenDate());
                    tanSuat.setSolanve(1);
                    listTansuat.add(tanSuat);
                } else {
                    tanSuat = new TanSuat();
                    tanSuat.setCapso(dan);
                    tanSuat.setOpenDate(lotteryResult.getOpenDate());
                    tanSuat.setSolanve(0);
                    listTansuat.add(tanSuat);
                }

                if (list == null) {
                    list = new ArrayList<>();
                }
                list.add(listTansuat);
            }
        }
        return list;
    }

    public List<Loto> findNhipTanSuat(String code, String sDate, String eDate, String dayOfWeek, String capso) {

        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResult(code, sDate, eDate);

        List<Loto> list = null;
        if (listLottery != null && !listLottery.isEmpty()) {
            int nhip = 1;
            Loto loto = null;
            for (int i = listLottery.size() - 1; i > -1; i--) {
                //System.out.println("iiiiiii==="+i+"==="+listLotteryResult.get(i).getFourth()+"ddmmyyy==="+listLotteryResult.get(i).getOpenDate());
                loto = getNhipTuanSuat(listLottery.get(i), capso);
                if (loto != null) {
                    loto.setNgaychuave(nhip);
                    nhip = 1;
                    if (list == null) {
                        list = new ArrayList<>();
                    }
                    list.add(loto);
                } else {
                    nhip++;
                }
            }
        }
        return list;
    }

    private Loto getNhipTuanSuat(LotteryResult lotteryResult, String capso) {
        Loto loto = null;
        if (lotteryResult == null) {
            return loto;
        }
        String strCapso = "";
        String strGiai = "";

        // giai dac biet
        strCapso = StringConvert.subRight(lotteryResult.getSpecial(), 2);
        if (strCapso.equals(capso)) {
            strGiai = "G0";
        }
        // giai nhat
        strCapso = StringConvert.subRight(lotteryResult.getFirst(), 2);
        if (strCapso.equals(capso)) {
            strGiai += ",G11";
        }
        // giai nhi
        if (lotteryResult.getSecond().contains("-")) {
            int i = 1;
            for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSecond(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                String token = stringTokenizer.nextToken();
                if (token.endsWith(capso)) {
                    strGiai += ",G2" + i;
                }
            }
        } else {
            strCapso = StringConvert.subRight(lotteryResult.getSecond(), 2);
            if (strCapso.equals(capso)) {
                strGiai += ",G21";
            }
        }

        // giai ba
        if (lotteryResult.getThird().contains("-")) {
            int i = 1;
            for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getThird(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                String token = stringTokenizer.nextToken();
                if (token.endsWith(capso)) {
                    strGiai += ",G3" + i;
                }
            }
        } else {
            strCapso = StringConvert.subRight(lotteryResult.getThird(), 2);
            if (strCapso.equals(capso)) {
                strGiai += ",G31";
            }
        }

        // giai tu
        if (lotteryResult.getFourth().contains("-")) {
            int i = 1;
            for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getFourth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                String token = stringTokenizer.nextToken();
                if (token.endsWith(capso)) {
                    strGiai += ",G4" + i;
                }
            }
        } else {
            strCapso = StringConvert.subRight(lotteryResult.getFourth(), 2);
            if (strCapso.equals(capso)) {
                strGiai += ",G41";
            }
        }

        // giai nam
        if (lotteryResult.getFifth().contains("-")) {
            int i = 1;
            for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getFifth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                String token = stringTokenizer.nextToken();
                if (token.endsWith(capso)) {
                    strGiai += ",G5" + i;
                }
            }
        } else {
            strCapso = StringConvert.subRight(lotteryResult.getFifth(), 2);
            if (strCapso.equals(capso)) {
                strGiai += ",G51";
            }
        }

        // giai sau
        if (lotteryResult.getSixth().contains("-")) {
            int i = 1;
            for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSixth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                String token = stringTokenizer.nextToken();
                if (token.endsWith(capso)) {
                    strGiai += ",G6" + i;
                }
            }
        } else {
            strCapso = StringConvert.subRight(lotteryResult.getSixth(), 2);
            if (strCapso.equals(capso)) {
                strGiai += ",G61";
            }
        }

        // giai bay
        if (lotteryResult.getSeventh().contains("-")) {
            int i = 1;
            for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getSeventh(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                String token = stringTokenizer.nextToken();
                if (token.endsWith(capso)) {
                    strGiai += ",G7" + i;
                }
            }
        } else {
            strCapso = StringConvert.subRight(lotteryResult.getSeventh(), 2);
            if (strCapso.equals(capso)) {
                strGiai += ",G71";
            }
        }

        //giai tam
        if (lotteryResult.getEighth() != null && !"".equals(lotteryResult.getEighth())) {
            if (lotteryResult.getEighth().contains("-")) {
                int i = 1;
                for (StringTokenizer stringTokenizer = new StringTokenizer(lotteryResult.getEighth(), "-"); stringTokenizer.hasMoreTokens(); i++) {
                    String token = stringTokenizer.nextToken();
                    if (token.endsWith(capso)) {
                        strGiai += ",G8" + i;
                    }
                }
            } else {
                strCapso = StringConvert.subRight(lotteryResult.getEighth(), 2);
                if (strCapso.equals(capso)) {
                    strGiai += ",G81";
                }
            }
        }

        if (!"".equals(strGiai)) {
            if (strGiai.startsWith(",")) {
                strGiai = strGiai.substring(1, strGiai.length());
            }
            loto = new Loto();
            loto.setGiaive(strGiai);
            loto.setLoto(capso);
            loto.setNgayxuathiengannhat(lotteryResult.getOpenDate());
            loto.setThuve(DatePro.convertDayOfWeek(DatePro.getDateOfWeekDDMMYYYY(lotteryResult.getOpenDate())));
        }

        return loto;
    }

    public static void main(String[] args) {
        BangTanSuat bangTanSuat = new BangTanSuat();
        List<List<TanSuat>> list = bangTanSuat.findTanSuatTheoDanDacBiet("XSTD", "2009-07-01", "2015-12-31", "00,01");
        if (list != null && !list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                List<TanSuat> listTanSuat = list.get(i);
                for (TanSuat tanSuat : listTanSuat) {
                }
            }
        }
    }
}
