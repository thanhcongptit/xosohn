/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thongke;

import java.util.ArrayList;
import java.util.List;

import com.soicaupro.thongkebacnho.CommonUtil;

import inet.bean.DacBiet;
import inet.bean.Loto;
import inet.bean.LotteryResult;
import inet.bean.TanSuat;
import inet.model.LotteryResultDAO;
import inet.util.DatePro;
import inet.util.StringConvert;

/**
 *
 * @author HanhDung
 */
public class ThongKeDacBiet {

    public List<DacBiet> findBangDacBiet(String code, String sDate, String eDate) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        List<LotteryResult> listLottery = lotteryResultDAO.findLotteryResultAsc(code, sDate, eDate);

        List<DacBiet> list = null;
        DacBiet dacBiet = null;
        LotteryResult lotteryResult = null;
        int tong = 0;
        for (int i = 0; i < listLottery.size(); i++) {
            lotteryResult = listLottery.get(i);
            dacBiet = new DacBiet();
            dacBiet.setSpecial(lotteryResult.getSpecial());
            dacBiet.setNgayve(lotteryResult.getOpenDate());
            dacBiet.setThu(DatePro.convertDayOfWeek(DatePro.getDateOfWeekDDMMYYYY(lotteryResult.getOpenDate())));
            dacBiet.setDau(StringConvert.subRight(dacBiet.getSpecial(), 2).substring(0, 1));
            dacBiet.setDuoi(StringConvert.subRight(dacBiet.getSpecial(), 1));
            if (!CommonUtil.isEmptyString(dacBiet.getDau()) && !CommonUtil.isEmptyString(dacBiet.getDuoi())) {
                tong = Integer.parseInt(dacBiet.getDau()) + Integer.parseInt(dacBiet.getDuoi());
                if (tong > 10) {
                    tong = tong - 10;
                }
                dacBiet.setTong(tong);
                if (list == null) {
                    list = new ArrayList<>();
                }

                list.add(dacBiet);
            }

        }

        return list;
    }

    public List<Loto> findLotoDacbiet(String code, String sDate, String eDate, int opt) {

        BangTanSuat bangTanSuat = new BangTanSuat();
        List<List<TanSuat>> list = bangTanSuat.findTanSuatDacBiet(code, sDate, eDate, opt);

        if (list == null || list.isEmpty()) {
            return null;
        }

        int ganmax = 0;
        int chuave = 0;
        int tonglanve = 0;
        String startGanMax = "";
        String endGanMax = "";
        TanSuat tanSuat = null;
        Loto loto = null;
        List<Loto> listLoto = new ArrayList<>();
        List<TanSuat> listTanSuat = null;
        for (int i = 0; i < 10; i++) {
            ganmax = 0;
            chuave = 0;
            tonglanve = 0;

            loto = new Loto();
            loto.setLoto("" + i);

            for (int t = 0; t < list.size(); t++) {
                listTanSuat = list.get(t);
                for (int j = 0; j < listTanSuat.size(); j++) {
                    tanSuat = listTanSuat.get(j);
                    if (tanSuat.getCapso().equals(loto.getLoto())) {
                        if (tanSuat.getSolanve() > 0) {
                            if (loto.getNgaychuave() == 0) {
                                if (chuave == 0) {
                                    loto.setNgaychuave(-1);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                } else {
                                    loto.setNgaychuave(chuave);
                                    loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                                }
                            }

                            tonglanve = tonglanve + tanSuat.getSolanve();

                            if (ganmax < chuave) {
                                ganmax = chuave;
                                loto.setNgayketthucganmax(endGanMax);
                                loto.setNgaybatdauganmax(startGanMax);
                            }
                            chuave = 0;
                        } else {
                            if (chuave == 0) {
                                endGanMax = tanSuat.getOpenDate();
                            }
                            startGanMax = tanSuat.getOpenDate();
                            chuave++;
                        }
                    }
                }
            }

            if (ganmax < chuave) {
                ganmax = chuave;
            }
            if (loto.getNgaychuave() == 0) {
                loto.setNgaychuave(ganmax);
            }
            if (loto.getNgaychuave() == -1) {
                loto.setNgaychuave(0);
            }

            loto.setSolanxuathien(tonglanve);
            loto.setGanmax(ganmax);

            listLoto.add(loto);
        }
        return listLoto;
    }

    public List<Loto> findLotoDanDacBiet(String code, String sDate, String eDate, String dan) {

        BangTanSuat bangTanSuat = new BangTanSuat();
        List<List<TanSuat>> list = bangTanSuat.findTanSuatTheoDanDacBiet(code, sDate, eDate, dan);

        if (list == null || list.isEmpty()) {
            return null;
        }

        int ganmax = 0;
        int chuave = 0;
        int tonglanve = 0;
        String startGanMax = "";
        String endGanMax = "";
        TanSuat tanSuat = null;
        Loto loto = null;
        List<Loto> listLoto = new ArrayList<>();
        List<TanSuat> listTanSuat = null;

        loto = new Loto();
        loto.setLoto(dan);

        for (int t = 0; t < list.size(); t++) {
            listTanSuat = list.get(t);
            for (int j = 0; j < listTanSuat.size(); j++) {
                tanSuat = listTanSuat.get(j);
                if (tanSuat.getSolanve() > 0) {
                    if (loto.getNgaychuave() == 0) {
                        if (chuave == 0) {
                            loto.setNgaychuave(-1);
                            loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                        } else {
                            loto.setNgaychuave(chuave);
                            loto.setNgayxuathiengannhat(tanSuat.getOpenDate());
                        }
                    }

                    tonglanve = tonglanve + tanSuat.getSolanve();

                    if (ganmax < chuave) {
                        ganmax = chuave;
                        loto.setNgayketthucganmax(endGanMax);
                        loto.setNgaybatdauganmax(startGanMax);
                    }
                    chuave = 0;
                } else {
                    if (chuave == 0) {
                        endGanMax = tanSuat.getOpenDate();
                    }
                    startGanMax = tanSuat.getOpenDate();
                    chuave++;
                }
            }
        }

        if (ganmax < chuave) {
            ganmax = chuave;
        }
        if (loto.getNgaychuave() == 0) {
            loto.setNgaychuave(ganmax);
        }
        if (loto.getNgaychuave() == -1) {
            loto.setNgaychuave(0);
        }

        loto.setSolanxuathien(tonglanve);
        loto.setGanmax(ganmax);

        listLoto.add(loto);
        return listLoto;
    }
    
    public String [][] findBangDacBietThang(String sDate, String eDate) {
        LotteryResultDAO lotteryResultDAO = new LotteryResultDAO();
        return lotteryResultDAO.findLotteryResultMonthAsc(sDate, eDate);

    }
}
