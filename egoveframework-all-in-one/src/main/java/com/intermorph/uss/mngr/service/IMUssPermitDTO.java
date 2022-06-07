/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.mngr.service;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.mngr.service
 * @File    : IMUssPermitDTO.java
 * @Title   : {간단한 프로그램의 명칭을 기록}
 * @date    : 2022. 2. 25
 * @author  : 노성용
 * @descrption :
 * {상세한 프로그램의 용도를 기록}
 */
public class IMUssPermitDTO {
	private String permitStart;
	private String permitEnd;
	private String usePermitYn;
	private String[] permitAcessips;


	public String getPermitStart() {
		return permitStart;
	}

	public void setPermitStart(String permitStart) {
		this.permitStart = permitStart;
	}

	public String getPermitEnd() {
		return permitEnd;
	}

	public void setPermitEnd(String permitEnd) {
		this.permitEnd = permitEnd;
	}

	public String[] getPermitAcessips() {
		if(this.permitAcessips !=null){
			String[] tempData = new String[this.permitAcessips.length];
			System.arraycopy(this.permitAcessips , 0, tempData, 0, this.permitAcessips.length);
			return tempData;
		}else{
			return null;
		}
	}

	public void setPermitAcessips(String[] permitAcessips) {
		if (permitAcessips != null) {
			this.permitAcessips = new String[permitAcessips.length];
			System.arraycopy(permitAcessips, 0, this.permitAcessips, 0, permitAcessips.length);
		} else {
			this.permitAcessips = null;
		}
	}

	public String getUsePermitYn() {
		return usePermitYn;
	}

	public void setUsePermitYn(String usePermitYn) {
		this.usePermitYn = usePermitYn;
	}
	
	
}
