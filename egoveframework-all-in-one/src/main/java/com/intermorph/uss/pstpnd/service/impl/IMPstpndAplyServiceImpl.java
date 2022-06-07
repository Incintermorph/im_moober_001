/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.pstpnd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.exception.IMCmmnStts;
import com.intermorph.cmmn.service.IMCmmnSttsMapper;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyMapper;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyResultSet;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyService;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.pstpnd.service.impl
 * @File : PstpndAplyServiceImpl.java
 * @Title : 유예신청
 * @date : 2022.04.14 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMPstpndAplyService")
public class IMPstpndAplyServiceImpl extends EgovAbstractServiceImpl  implements IMPstpndAplyService {

    @Resource (name = "IMPstpndAplyMapper")
    private IMPstpndAplyMapper pstpndAplyMapper;
    
    @Resource(name = "imPstpndAplyIdGnrService")
    private EgovIdGnrService idgenService;

	@Resource (name = "IMCmmnSttsMapper")
	protected IMCmmnSttsMapper cmmnSttsMapper;

	@Resource (name = "IMMmbrQlfcService")
	private IMMmbrQlfcService mmbrQlfcService;

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.pstpnd.service.IMPstpndAplyService#insertPstpndAply(com.intermorph.uss.pstpnd.vo.IMPstpndAplyVO)
     */
    public int insertPstpndAply(IMPstpndAplyVO vo) throws Exception {
        int success = 0;
        vo.setPstpndAplyId(idgenService.getNextStringId());


     	int year=Integer.parseInt(vo.getLcncEndYmd().substring(0,4))+Integer.parseInt(vo.getTrgtYear());
			//취득년 +3 12월 31일
     	String pstpndRndYmd = year+"1231235959";
     	vo.setPstpndRndYmd(pstpndRndYmd);
    	 
        success = pstpndAplyMapper.insert(vo);
        for (IMCmmnStts v : IMCmmnStts.values()) {
        	if(v.sttsCompoent.equals("PSTPND_APLY")) {
				IMCmmnSttsVO o = new IMCmmnSttsVO();
				o.setTblId("IM_PSTPND_APLY");
				o.setTblRefId(vo.getPstpndAplyId());
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
        if("02".equals(vo.getSttsCdv())){
 			//취득년 +3 12월 31일
         	mmbrQlfcService.updateMmbrQlfcPstpnd(vo, pstpndRndYmd);
         }
        		
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.pstpnd.service.IMPstpndAplyService#updatePstpndAply(com.intermorph.uss.pstpnd.vo.IMPstpndAplyVO)
     */
    public int updatePstpndAply(IMPstpndAplyVO vo) throws Exception {
        int success = 0;
        IMPstpndAplyResultSet detail = (IMPstpndAplyResultSet)  pstpndAplyMapper.selectDetail(vo);
        
        String qlfclcncEndYmd =null;
        if("02".equals(vo.getSttsCdv()) && !"02".equals(detail.getPstpndAply().getSttsCdv())) {
        	
        	int year=Integer.parseInt(detail.getPstpndAply().getLcncEndYmd().substring(0,4))+Integer.parseInt(detail.getPstpndAply().getTrgtYear());
			//취득년 +3 12월 31일
        	qlfclcncEndYmd = year+"1231235959";
         	vo.setPstpndRndYmd(qlfclcncEndYmd);
        }
      //승인 후 취소 처리 
        if(!"02".equals(vo.getSttsCdv()) && "02".equals(detail.getPstpndAply().getSttsCdv())) {
        	qlfclcncEndYmd = detail.getPstpndAply().getLcncEndYmd();
         	vo.setPstpndRndYmd(null);
        }
        
        success = pstpndAplyMapper.update(vo);
        
        if(!detail.getPstpndAply().getSttsCdv().equals(vo.getSttsCdv())) {
        	//변경시 이력 등록  
            for (IMCmmnStts v : IMCmmnStts.values()) {
            	if(v.sttsCompoent.equals("PSTPND_APLY")) {
    				IMCmmnSttsVO o = new IMCmmnSttsVO();
    				o.setTblId("IM_PSTPND_APLY");
    				o.setTblRefId(vo.getPstpndAplyId());
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
        //승인시 자격증 만료 기간 업데이트 처리 
        
      
        
        if(!IMStringUtil.isEmpty(qlfclcncEndYmd)) {
        	vo.setEsntlId(detail.getPstpndAply().getEsntlId());
        	vo.setCrsGrdCdv(detail.getPstpndAply().getCrsGrdCdv());
        	mmbrQlfcService.updateMmbrQlfcPstpnd(vo, qlfclcncEndYmd);
        }
        
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.pstpnd.service.IMPstpndAplyService#updatelistPstpndAply(java.util.List)
     */
    public int updatelistPstpndAply(List<IMPstpndAplyVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMPstpndAplyVO vo : voList) {
                success += updatePstpndAply(vo);
            }
        }
        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.pstpnd.service.IMPstpndAplyService#deletePstpndAply(com.intermorph.uss.pstpnd.vo.IMPstpndAplyVO)
     */
    public int deletePstpndAply(IMPstpndAplyVO vo) throws Exception {
        int success = 0;

        success = pstpndAplyMapper.delete(vo);

        return success;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intermorph.uss.pstpnd.service.IMPstpndAplyService#deletelistPstpndAply(java.util.List)
     */
    public int deletelistPstpndAply(List<IMPstpndAplyVO> voList) throws Exception {
        int success = 0;
        if (voList != null) {
            for (IMPstpndAplyVO vo : voList) {
                success += pstpndAplyMapper.delete(vo);
            }
        }
        return success;
    }


	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.pstpnd.service.IMPstpndAplyService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailPstpndAply(IMPstpndAplyVO vo) throws Exception {
		return pstpndAplyMapper.selectDetail(vo);
	}
	

	
	/* (non-Javadoc)
	 * @see com.intermorph.uss.pstpnd.service.IMPstpndAplyService#selectListPstpndAply(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListPstpndAply(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = pstpndAplyMapper.selectListCount(condition);
			
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(pstpndAplyMapper.selectList(condition), totalCount, condition);
			
		}else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = pstpndAplyMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;
		
	}
	
	

}