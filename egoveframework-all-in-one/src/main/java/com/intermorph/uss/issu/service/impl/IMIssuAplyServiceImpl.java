/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.issu.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.exception.IMCmmnStts;
import com.intermorph.cmmn.service.IMCmmnSttsMapper;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.cmmn.service.IMRsltCodeMapper;
import com.intermorph.cmmn.service.IMRsltCodeVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.uss.issu.service.IMIssuAplyMapper;
import com.intermorph.uss.issu.service.IMIssuAplyResultSet;
import com.intermorph.uss.issu.service.IMIssuAplyService;
import com.intermorph.uss.issu.service.IMIssuAplyVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.issu.service.impl
 * @File : IssuAplyServiceImpl.java
 * @Title : 발급신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMIssuAplyService")
public class IMIssuAplyServiceImpl extends EgovAbstractServiceImpl   implements IMIssuAplyService {

    @Resource (name = "IMIssuAplyMapper")
    private IMIssuAplyMapper issuAplyMapper;
    
    @Resource(name = "imIssuAplyIdGnrService")
    private EgovIdGnrService idgenService;


	@Resource (name = "IMCmmnSttsMapper")
	protected IMCmmnSttsMapper cmmnSttsMapper;

	@Resource (name = "IMRsltCodeMapper")
	private IMRsltCodeMapper rsltCodeMapper;
	

	@Resource (name = "IMMmbrQlfcService")
	private IMMmbrQlfcService mmbrQlfcService;
	
    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.issu.service.IMIssuAplyService#insertIssuAply(com.intermorph.uss.issu.vo.IMIssuAplyVO)
     */
    public int insertIssuAply(IMIssuAplyVO vo) throws Exception {
        int success = 0;
        vo.setIssuAplyId(idgenService.getNextStringId());
        success = issuAplyMapper.insert(vo);
        
        for (IMCmmnStts v : IMCmmnStts.values()) {
        	if(v.sttsCompoent.equals("ISSU_APLY")) {
				IMCmmnSttsVO o = new IMCmmnSttsVO();
				o.setTblId("IM_ISSU_APLY");
				o.setTblRefId(vo.getIssuAplyId());
				o.setRefNm(v.sttsKey);
				if(vo.getSttsCdv()==null || "".equals(vo.getSttsCdv())) {
					o.setSttsCdv(v.defaultCode);
				}else {
					o.setSttsCdv(vo.getSttsCdv());
				}
				o.copyAudit(vo);
				cmmnSttsMapper.insert(o);
				cmmnSttsMapper.insertHstry(o);
        	}
		}

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.issu.service.IMIssuAplyService#updateIssuAply(com.intermorph.uss.issu.vo.IMIssuAplyVO)
     */
    public int updateIssuAply(IMIssuAplyVO vo) throws Exception {
        int success = 0;
        IMIssuAplyResultSet detail = (IMIssuAplyResultSet)  issuAplyMapper.selectDetail(vo);
        vo.setEsntlId(detail.getIssuAply().getEsntlId());
      //승인인 경우 발급 번호 생성 처리함 
        if("02".equals(vo.getSttsCdv()) && !"02".equals(detail.getIssuAply().getSttsCdv())) {
    		String num = "";

    		String delimiter = "-";
    		String lcncIssuCode = null;
    		IMRsltCodeVO rsltVO = new IMRsltCodeVO();
    		rsltVO.copyAudit(vo);
    		
    		rsltVO.setRsltTblId("IM_ISSU_APLY");
    		rsltVO.setRsltRefId(vo.getIssuAplyId());
    		rsltVO.setRsltCodeDvsn("LCNC_ISSU_CODE");
    		String nowYYYYMM= IMDateUtil.getImToday("yyyy-MM");
    		String stndrdCode = nowYYYYMM +delimiter +"L";
    		rsltVO.setStndrdCode(stndrdCode);

    		
    		IMRsltCodeVO rsltCodeVO = rsltCodeMapper.selectDetail(rsltVO);
        	if(rsltCodeVO==null) {
        		String preMaxCode = rsltCodeMapper.selectMaxCode(stndrdCode);

				if (preMaxCode == null) {
					num = "001";
				} else {
					String[] arrCode = preMaxCode.split("-");
					int resutNum = Integer.parseInt(arrCode[arrCode.length - 1]) + 1;
					num = String.format("%03d", resutNum);
				}
				lcncIssuCode =  stndrdCode+delimiter+num;
        	}else {
        		lcncIssuCode = rsltCodeVO.getRsltCode();
        	}
        	vo.setLcncIssuCode(lcncIssuCode);
        	mmbrQlfcService.updateMmbrQlfcIssue(vo, lcncIssuCode, detail.getIssuAply().getCrsGrdCdv(), IMDateUtil.getImToday("yyyyMMddHHmmss"));
        }
        
        //승인된 발급 번호가 취소가 되는 경우  처리  
        if(!"02".equals(vo.getSttsCdv()) && "02".equals(detail.getIssuAply().getSttsCdv())) {
        	mmbrQlfcService.updateMmbrQlfcIssue(vo, null, detail.getIssuAply().getCrsGrdCdv(), null);
        }
        
        success = issuAplyMapper.update(vo);
        
        if(!detail.getIssuAply().getSttsCdv().equals(vo.getSttsCdv())) {
        	//변경시 이력 등록  
            for (IMCmmnStts v : IMCmmnStts.values()) {
            	if(v.sttsCompoent.equals("ISSU_APLY")) {
    				IMCmmnSttsVO o = new IMCmmnSttsVO();
    				o.setTblId("IM_ISSU_APLY");
    				o.setTblRefId(vo.getIssuAplyId());
    				o.setRefNm(v.sttsKey);
    				if(vo.getSttsCdv()==null || "".equals(vo.getSttsCdv())) {
    					o.setSttsCdv(v.defaultCode);
    				}else {
    					o.setSttsCdv(vo.getSttsCdv());
    				}
    				o.setSttsRmks(vo.getPrcsRmks());
    				o.copyAudit(vo);
    				cmmnSttsMapper.update(o);
    				cmmnSttsMapper.insertHstry(o);
            	}
    		}
        }
        
        
        
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.issu.service.IMIssuAplyService#updatelistIssuAply(java.util.List)
     */
    public int updatelistIssuAply(List<IMIssuAplyVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMIssuAplyVO vo : voList) {
                success += updateIssuAply(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.issu.service.IMIssuAplyService#deleteIssuAply(com.intermorph.uss.issu.vo.IMIssuAplyVO)
     */
    public int deleteIssuAply(IMIssuAplyVO vo) throws Exception {
        int success = 0;

        success = issuAplyMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.issu.service.IMIssuAplyService#deletelistIssuAply(java.util.List)
     */
    public int deletelistIssuAply(List<IMIssuAplyVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMIssuAplyVO vo : voList) {
                success += issuAplyMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.issu.service.IMIssuAplyService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailIssuAply(IMIssuAplyVO vo) throws Exception {
		return issuAplyMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.issu.service.IMIssuAplyService#selectListIssuAply(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListIssuAply(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = issuAplyMapper.selectListCount(condition);
			
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(issuAplyMapper.selectList(condition), totalCount, condition);
			
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = issuAplyMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
   
}