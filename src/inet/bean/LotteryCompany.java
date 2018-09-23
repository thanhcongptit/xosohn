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
public class LotteryCompany implements Serializable{

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompanyLink() {
        return companyLink;
    }

    public void setCompanyLink(String companyLink) {
        this.companyLink = companyLink;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getOpendate() {
        return opendate;
    }

    public void setOpendate(String opendate) {
        this.opendate = opendate;
    }

    public String getCodeLowerCase() {
        return codeLowerCase;
    }

    public void setCodeLowerCase(String codeLowerCase) {
        this.codeLowerCase = codeLowerCase;
    }

    public String getCodeReverseLowerCase() {
        return codeReverseLowerCase;
    }

    public void setCodeReverseLowerCase(String codeReverseLowerCase) {
        this.codeReverseLowerCase = codeReverseLowerCase;
    }
    
    public String getLinkKq247() {
		if(this.companyLink.contains("ho-chi-minh")) {
			this.companyLink = "tphcm";
		}
		
		return this.companyLink;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public void setLinkKq247(String linkKq247) {
		this.linkKq247 = linkKq247;
	}

	private String region;
    private String company;
    private String companyLink;
    private String code;
    private String opendate;
    private String codeLowerCase;
    private String codeReverseLowerCase;
    private String linkKq247;
    private String province;
}
