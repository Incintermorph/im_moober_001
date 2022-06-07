/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service.impl;

import java.net.Inet4Address;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.IMGlobals;
import com.intermorph.cmmn.service.IMInfoInqHstryMapper;
import com.intermorph.cmmn.service.IMInfoInqHstryService;
import com.intermorph.cmmn.service.IMInfoInqHstryVO;
import com.intermorph.cmmn.util.IMStringUtil;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service.impl
 * @File : InfoInqHstryServiceImpl.java
 * @Title : 정보조회이력
 * @date : 2022.03.16 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMInfoInqHstryService")
public class IMInfoInqHstryServiceImpl  implements IMInfoInqHstryService {

    @Resource (name = "IMInfoInqHstryMapper")
    private IMInfoInqHstryMapper infoInqHstryMapper;
    
    @Resource(name = "imInfoInqHstryIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.service.IMInfoInqHstryService#insertInfoInqHstry(String  ,String ,String  , String ,  HttpServletRequest )
     */
    public int insertInfoInqHstry(String  tblId,String tblRefId,String  refNm, String inqRsn,  HttpServletRequest req) throws Exception {
    	int success = 0;
    	IMInfoInqHstryVO vo = new IMInfoInqHstryVO();
    	LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
    	vo.setFrstRegerId(user.getUniqId());
		vo.setFrstRegerIp(IMStringUtil.getRemoteAddr(req));
		vo.setLastMdferId(user.getUniqId());
		vo.setLastMdferIp(IMStringUtil.getRemoteAddr(req));
		vo.setTblId(tblId);
		vo.setTblRefId(tblRefId);
		vo.setRefNm(refNm);
		vo.setInqRsn(inqRsn);
		//목록 조회 경우 
		if(IMGlobals.IM_INFOINQ_S.equals(refNm)) {
			vo.setRefNm("SELECTNOID");
		}
		vo.setInqUrl(req.getRequestURI());
		 
		if(req.getAttribute("nowMenuNo")!=null) {
			vo.setMenuNo((String)req.getAttribute("nowMenuNo"));
		}
		
    	vo.setInfoInqHstryId(idgenService.getNextStringId());
    	success = infoInqHstryMapper.insert(vo);
    	return success;
    }

   
}