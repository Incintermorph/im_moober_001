/*
 * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.qlfc.service;

import java.util.List;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.issu.service.IMIssuAplyVO;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyVO;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;


/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.service
 * @File : IMMmbrQlfcService.java
 * @Title : 회원자격정보
 * @date : 2022.04.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
public interface IMMmbrQlfcService {

    /**
     * 등록
     * 
     * @param IMMmbrQlfcVO
     * @return int
     * @throws Exception
     */
    int insertMmbrQlfc(IMMmbrQlfcVO vo) throws Exception;

    /**
     * 수정
     * 
     * @param IMMmbrQlfcVO
     * @return int
     * @throws Exception
     */
    int updateMmbrQlfc(IMMmbrQlfcVO vo) throws Exception;
    /**
     * 자격증 최종 처리 결과 업데이트 
     * @param aplcnt
     * @param rsltCodeDvsn
     * @param crsGrdCdv
     * @param crsDvsnCdv
     * @return
     * @throws Exception
     */
    int updateMmbrQlfcCrs(IMCrsAplcntVO aplcnt, String rsltCodeDvsn,String crsGrdCdv,String crsDvsnCdv) throws Exception;

    /**
     * 자격증 최종 처리 결과 업데이트 평가
     * @param aplcnt
     * @param rsltCodeDvsn
     * @param crsGrdCdv
     * @return
     * @throws Exception
     */
    int updateMmbrQlfcWtst(IMWtstAplcntVO aplcnt,String rsltCodeDvsn,String crsGrdCdv) throws Exception;
    
    /**
     * 자격증 발급 코드 
     * @param issuAply
     * @param lcncIssuCode
     * @param crsGrdCdv
     * @param lcncIssuYmd
     * @return
     * @throws Exception
     */
    int updateMmbrQlfcIssue(IMIssuAplyVO issuAply,String lcncIssuCode,String crsGrdCdv,String lcncIssuYmd) throws Exception;
    
    
    /**
     * 유예신청 업데이트 
     * @param pstpndAply
     * @param qlfclcncEndYmd
     * @return
     * @throws Exception
     */
    int updateMmbrQlfcPstpnd(IMPstpndAplyVO pstpndAply,String qlfclcncEndYmd) throws Exception;
    
    
    
    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
    IMMmbrQlfcVO selectListLastMmbrQlfc(String esntlId) throws Exception;
	
    
    /**
     * 취득 정보 (회원)
     * @param condition
     * @return
     * @throws Exception
     */
    List<IMMmbrQlfcVO> selectListUserMmbrQlfc(String esntlId) throws Exception;
    
    
    /**
     * 취득 정보 (회원)
     * @param condition
     * @return
     * @throws Exception
     */
    List<IMMmbrQlfcVO> selectListUserMmbrQlfcPass(String esntlId) throws Exception;
    
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	IMMmbrQlfcVO selectDetailMmbrQlfc(String esntlId,String crsGrdCdv) throws Exception;
	
	
    

    /**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListMmbrQlfc(BaseCondition condition) throws Exception;
   /**
    * 마이그레이션 데이터 (자격증 데이터 )
    * @param vo
    * @return
    * @throws Exception
    */
	int updateMig(IMMmbrHstryVO vo) throws Exception;
}