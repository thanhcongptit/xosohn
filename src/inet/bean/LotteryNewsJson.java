/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package inet.bean;

import java.util.List;

/**
 *
 * @author iNET
 */
public class LotteryNewsJson {
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<LotteryNews> getList() {
        return list;
    }

    public void setList(List<LotteryNews> list) {
        this.list = list;
    }
    private List<LotteryNews> list;
}
