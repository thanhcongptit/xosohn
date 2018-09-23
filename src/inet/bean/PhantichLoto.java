/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

/**
 *
 * @author iNET
 */
// item phan tich loto
public class PhantichLoto {
    
    // cap so xuat hien cau
    private String capso;

    public String getCapso() {
        return capso;
    }

    public void setCapso(String capso) {
        this.capso = capso;
    }

    public int getDodai() {
        return dodai;
    }

    public void setDodai(int dodai) {
        this.dodai = dodai;
    }
    // do dai cua cau
    private int dodai;
    private int vitri1;

    public int getVitri1() {
        return vitri1;
    }

    public void setVitri1(int vitri1) {
        this.vitri1 = vitri1;
    }

    public int getVitri2() {
        return vitri2;
    }

    public void setVitri2(int vitri2) {
        this.vitri2 = vitri2;
    }
    private int vitri2;
    private boolean result;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }
    
    private String loto;

    public String getLoto() {
        return loto;
    }

    public void setLoto(String loto) {
        this.loto = loto;
    }
}
