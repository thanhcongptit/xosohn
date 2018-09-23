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
public class LotteryNewsDetailJson {
    private int status;
    private LotteryNews list;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LotteryNews getList() {
        return list;
    }

    public void setList(LotteryNews list) {
        this.list = list;
    }
}
