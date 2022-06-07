/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.cmmn.service.impl;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.intermorph.cmmn.service.IMCmmnAccssHstryDTO;
import com.intermorph.cmmn.service.IMCmmnAccssHstryMapper;
import com.intermorph.cmmn.service.IMCmmnAccssHstryService;
import com.intermorph.cmmn.service.IMCmmnAccssHstryVO;
import com.intermorph.cmmn.util.IMStringUtil;

import egovframework.com.cmm.LoginVO;
import egovframework.com.cmm.util.EgovUserDetailsHelper;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.cmmn.service.impl
 * @File : CmmnAccssHstryServiceImpl.java
 * @Title : 공통접근이력
 * @date : 2022.05.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMCmmnAccssHstryService")
public class IMCmmnAccssHstryServiceImpl extends EgovAbstractServiceImpl  implements IMCmmnAccssHstryService {

    @Resource (name = "IMCmmnAccssHstryMapper")
    private IMCmmnAccssHstryMapper cmmnAccssHstryMapper;
    
    @Resource(name = "imCmmnAccssHstryIdGnrService")
    private EgovIdGnrService idgenService;


    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.cmmn.service.IMCmmnAccssHstryService#insertCmmnAccssHstry(HttpServletRequest request,String tblId,String tblRefId,String refNm)
     */
    public int insertCmmnAccssHstry(HttpServletRequest request,String tblId,String tblRefId,String refNm) throws Exception {
        int success = 0;
        IMCmmnAccssHstryVO vo = new IMCmmnAccssHstryVO();
        vo.setTblId(tblId);
        vo.setTblRefId(tblRefId);
        vo.setRefNm(refNm);
        vo.setDvsInfo(request.getHeader("User-Agent"));
        
        boolean isAuthenticated = EgovUserDetailsHelper.isAuthenticated();

		vo.setFrstRegerIp(IMStringUtil.getRemoteAddr(request));
		vo.setLastMdferIp(IMStringUtil.getRemoteAddr(request));
		IMCmmnAccssHstryDTO accssHstryMemberDTO = new IMCmmnAccssHstryDTO();
		//미민증사용자 체크
		if(isAuthenticated) {
		
			LoginVO user = (LoginVO)EgovUserDetailsHelper.getAuthenticatedUser();
			vo.setFrstRegerId(user.getUniqId());
			vo.setLastMdferId(user.getUniqId());
			vo.setEsntlId(user.getUniqId());
			accssHstryMemberDTO.setId(user.getId());
			accssHstryMemberDTO.setName(user.getName());
			accssHstryMemberDTO.setUniqId(user.getUniqId());
			
		}

		Gson gson = new Gson();
		vo.setMmbrInfo(gson.toJson(accssHstryMemberDTO));
        vo.setCmmnAccssHstryId(idgenService.getNextStringId());
        success = cmmnAccssHstryMapper.insert(vo);

        return success;
    }

   
}