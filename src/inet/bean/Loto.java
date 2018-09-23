/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inet.bean;

/**
 *
 * @author hanhlm
 */
public class Loto implements Comparable<Loto> {

    private String loto;
    private int ngaychuave;
    private int ganmax;
    private int solanxuathien;
    private String ngayxuathiengannhat;
    private String ngaybatdauganmax;
    private String ngayketthucganmax;
    private String giaive;
    private String thuve;
    private int songayve;

    public String getLoto() {
        return loto;
    }

    public void setLoto(String loto) {
        this.loto = loto;
    }

    public int getNgaychuave() {
        return ngaychuave;
    }

    public void setNgaychuave(int ngaychuave) {
        this.ngaychuave = ngaychuave;
    }

    public int getGanmax() {
        return ganmax;
    }

    public void setGanmax(int ganmax) {
        this.ganmax = ganmax;
    }

    public int getSolanxuathien() {
        return solanxuathien;
    }

    public void setSolanxuathien(int solanxuathien) {
        this.solanxuathien = solanxuathien;
    }

    public String getNgayxuathiengannhat() {
        return ngayxuathiengannhat;
    }

    public void setNgayxuathiengannhat(String ngayxuathiengannhat) {
        this.ngayxuathiengannhat = ngayxuathiengannhat;
    }

    public String getNgaybatdauganmax() {
        return ngaybatdauganmax;
    }

    public void setNgaybatdauganmax(String ngaybatdauganmax) {
        this.ngaybatdauganmax = ngaybatdauganmax;
    }

    public String getNgayketthucganmax() {
        return ngayketthucganmax;
    }

    public void setNgayketthucganmax(String ngayketthucganmax) {
        this.ngayketthucganmax = ngayketthucganmax;
    }

    public String getGiaive() {
        return giaive;
    }

    public void setGiaive(String giaive) {
        this.giaive = giaive;
    }

    public String getThuve() {
        return thuve;
    }

    public void setThuve(String thuve) {
        this.thuve = thuve;
    }

    public int getSongayve() {
        return songayve;
    }

    public void setSongayve(int songayve) {
        this.songayve = songayve;
    }

	@Override
	public int compareTo(Loto o) {
		
		return o.getNgaychuave() - this.getNgaychuave();
	}
    
}
