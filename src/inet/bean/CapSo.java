/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.io.Serializable;

/**
 *
 * @author iNET
 */
public class CapSo implements Serializable{
    String capso;

    public String getChanle() {
        return chanle;
    }

    public void setChanle(String chanle) {
        this.chanle = chanle;
    }

    public String getCapso() {
        return capso;
    }

    public void setCapso(String capso) {
        this.capso = capso;
    }

    public String getDdmmyyyy() {
        return ddmmyyyy;
    }

    public void setDdmmyyyy(String ddmmyyyy) {
        this.ddmmyyyy = ddmmyyyy;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
    String ddmmyyyy;
    String dayOfWeek;
    
    int solanxuathien;

    public int getSolanxuathien() {
        return solanxuathien;
    }

    public void setSolanxuathien(int solanxuathien) {
        this.solanxuathien = solanxuathien;
    }

    public int getSongaychuave() {
        return songaychuave;
    }

    public void setSongaychuave(int songaychuave) {
        this.songaychuave = songaychuave;
    }

    public int getGancucdai() {
        return gancucdai;
    }

    public void setGancucdai(int gancucdai) {
        this.gancucdai = gancucdai;
    }
    int songaychuave;
    int gancucdai;
    String chanle;
    String special;

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }
    
    int numDayOfWeek;

    public int getNumDayOfWeek() {
        return numDayOfWeek;
    }

    public void setNumDayOfWeek(int numDayOfWeek) {
        this.numDayOfWeek = numDayOfWeek;
    }
    
    private String kep;

    public String getKep() {
        return kep;
    }

    public void setKep(String kep) {
        this.kep = kep;
    }
    
    Lottery lotteryResult1;

    public Lottery getLotteryResult1() {
        return lotteryResult1;
    }

    public void setLotteryResult1(Lottery lotteryResult1) {
        this.lotteryResult1 = lotteryResult1;
    }

    public Lottery getLotteryResult2() {
        return lotteryResult2;
    }

    public void setLotteryResult2(Lottery lotteryResult2) {
        this.lotteryResult2 = lotteryResult2;
    }
    Lottery lotteryResult2;
    
    private Lottery lottery;

    public Lottery getLottery() {
        return lottery;
    }

    public void setLottery(Lottery lottery) {
        this.lottery = lottery;
    }
}
