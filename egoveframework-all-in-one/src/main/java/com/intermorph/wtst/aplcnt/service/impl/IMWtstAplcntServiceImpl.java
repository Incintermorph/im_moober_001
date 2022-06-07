/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.wtst.aplcnt.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.exception.IMWtstAplcntStts;
import com.intermorph.cmmn.service.IMCmmnSttsMapper;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.cmmn.service.IMRsltCodeMapper;
import com.intermorph.cmmn.service.IMRsltCodeVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntMapper;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntResultSet;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntService;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;
import com.intermorph.wtst.place.service.IMWtstPlaceMapper;
import com.intermorph.wtst.place.service.IMWtstPlaceResultSet;
import com.intermorph.wtst.place.service.IMWtstPlaceVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.wtst.aplcnt.service.impl
 * @File : WtstAplcntServiceImpl.java
 * @Title : 필기시험 신청자
 * @date : 2022.04.04 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMWtstAplcntService")
public class IMWtstAplcntServiceImpl extends EgovAbstractServiceImpl implements IMWtstAplcntService {

	@Resource (name = "IMWtstAplcntMapper")
	private IMWtstAplcntMapper wtstAplcntMapper;

	@Resource (name = "IMWtstPlaceMapper")
	private IMWtstPlaceMapper wtstPlaceMapper;

	@Resource (name = "imWtstAplcntIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource (name = "IMCmmnSttsMapper")
	protected IMCmmnSttsMapper cmmnSttsMapper;

	@Resource (name = "IMCmmnSttsService")
	private IMCmmnSttsService cmmnSttsService;

	@Resource (name = "IMRsltCodeMapper")
	private IMRsltCodeMapper rsltCodeMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#insertWtstAplcnt(com.intermorph.wtst.aplcnt.vo.IMWtstAplcntVO)
	 */
	public int insertWtstAplcnt(IMWtstAplcntVO vo) throws Exception {

		IMWtstPlaceVO voPlace = new IMWtstPlaceVO();
		voPlace.setWtstPlaceId(vo.getWtstPlaceId());

		BaseResultSet wtPlaceResult = wtstPlaceMapper.selectDetail(voPlace);

		if (wtPlaceResult == null) {
			return -1;
		}
		
		IMWtstPlaceResultSet placeInfo = (IMWtstPlaceResultSet)wtPlaceResult;
		// 접수 진행 중
		if(!"Y".equals(vo.getBatchYn())) {
			if (!"I".equals(placeInfo.getRcptProcType())) {
				return -2;
			}
		}

		vo.setWtstId(placeInfo.getWtstPlace().getWtstId());
		// 중복 체크
		if (wtstAplcntMapper.selectOverAplyCount(vo) > 0) {
			return -1000;

		}

		int totalApply = wtstAplcntMapper.selectAplyCount(vo);
		int crslimitApply = Integer.parseInt(placeInfo.getWtstPlace().getPrsnlLmt());
		int crslimit = Integer.parseInt(placeInfo.getWtstPlace().getPrsnl());
		// 신청자 제한 초과
		if (crslimitApply < totalApply) {
			return -1001;
		}

		String applyStatus = "01"; // 접수 완료
		vo.setWtnOrd(0L);
		if (crslimit < totalApply) {
			applyStatus = "0101"; // 접수 대기
			Long wtnOrd = wtstAplcntMapper.selectAplyMAXWaitOrder(vo) + 1L;
			vo.setWtnOrd(wtnOrd); // 대기번호등록처리
		}

		int success = 0;
		vo.setWtstAplcntId(idgenService.getNextStringId());
		success = wtstAplcntMapper.insert(vo);

		// 기본 수강생 상태 등록
		for (IMWtstAplcntStts v : IMWtstAplcntStts.values()) {
			IMCmmnSttsVO o = new IMCmmnSttsVO();
			o.setTblId("IM_WTST_APLCNT");
			o.setTblRefId(vo.getWtstAplcntId());
			o.setRefNm(v.sttsKey);
			if ("APLY".equals(v.sttsKey)) {
				o.setSttsCdv(applyStatus);
			} else {
				o.setSttsCdv(v.defaultCode);
			}
			o.copyAudit(vo);
			cmmnSttsMapper.insert(o);
			cmmnSttsMapper.insertHstry(o);
		}

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#updateWtstAplcnt(com.intermorph.wtst.aplcnt.vo.IMWtstAplcntVO)
	 */
	public int updateWtstAplcnt(IMWtstAplcntVO vo) throws Exception {
		int success = 0;
		success = wtstAplcntMapper.update(vo);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#updateTktstno(com.intermorph.wtst.aplcnt.vo.IMWtstAplcntVO)
	 */
	public synchronized int updateTktstno(IMWtstAplcntVO vo) throws Exception {
		int success = 0;
		String stndrdCode = IMDateUtil.getImToday("yyyy-MM") + "-T";

		success = wtstAplcntMapper.update(vo);

		IMRsltCodeVO rsltVO = new IMRsltCodeVO();
		rsltVO.copyAudit(vo);

		IMWtstAplcntVO savevo = new IMWtstAplcntVO();
		savevo.copyAudit(vo);

		rsltVO.setRsltTblId("IM_WTST_APLCNT");
		rsltVO.setRsltRefId(vo.getWtstAplcntId());
		rsltVO.setRsltCodeDvsn("tktstno");
		rsltVO.setStndrdCode(stndrdCode);
		IMRsltCodeVO rsltCodeVO = rsltCodeMapper.selectDetail(rsltVO);
		if (rsltCodeVO == null) {
			String preMaxCode = rsltCodeMapper.selectMaxCode(stndrdCode);

			String num = "";
			if (preMaxCode == null) {
				num = "0001";
			} else {
				String[] arrCode = preMaxCode.split("-");
				int resutNum = Integer.parseInt(arrCode[arrCode.length - 1]) + 1;
				num = String.format("%04d", resutNum);
			}
			savevo.setTktstno(stndrdCode + "-" + num);

			rsltVO.setRsltCode(savevo.getTktstno());
			rsltCodeMapper.insert(rsltVO);

		} else {
			savevo.setTktstno(rsltCodeVO.getRsltCode());
		}
		savevo.setWtstAplcntId(vo.getWtstAplcntId());
		// 접수 번호 업데이트
		success = wtstAplcntMapper.update(savevo);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#updatelistWtstAplcnt(java.util.List)
	 */
	public int updatelistWtstAplcnt(List<IMWtstAplcntVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (IMWtstAplcntVO vo : voList) {
				success += wtstAplcntMapper.update(vo);
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#deleteWtstAplcnt(com.intermorph.wtst.aplcnt.vo.IMWtstAplcntVO)
	 */
	public int deleteWtstAplcnt(IMWtstAplcntVO vo) throws Exception {
		int success = 0;

		success = wtstAplcntMapper.delete(vo);

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#deletelistWtstAplcnt(java.util.List)
	 */
	public int deletelistWtstAplcnt(List<IMWtstAplcntVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (IMWtstAplcntVO vo : voList) {
				success += wtstAplcntMapper.delete(vo);
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailWtstAplcnt(IMWtstAplcntVO vo) throws Exception {
		return wtstAplcntMapper.selectDetail(vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#selectListWtstAplcnt(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListWtstAplcnt(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = wtstAplcntMapper.selectListCount(condition);
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(wtstAplcntMapper.selectList(condition), totalCount, condition);

		} else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = wtstAplcntMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;

	}
	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#selectListCount(com.intermorph.cmmn.base.BaseCondition)
	 */
	public int selectListCount(BaseCondition condition) throws Exception {
		return wtstAplcntMapper.selectListCount(condition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#selectOverAplyCount(com.intermorph.cmmn.base.BaseVO)
	 */
	public int selectOverAplyCount(IMWtstAplcntVO vo) throws Exception {
		return wtstAplcntMapper.selectOverAplyCount(vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#selectAplyCount(com.intermorph.cmmn.base.BaseVO)
	 */
	public int selectAplyCount(IMWtstAplcntVO vo) throws Exception {
		return wtstAplcntMapper.selectAplyCount(vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#updateAplyOPSECTSRNGtoApply(IMWtstPlaceVO)
	 */
	public int updateAplyOPSECTSRNGtoApply(IMWtstPlaceVO vo) throws Exception {

		List<IMCmmnSttsVO> list = wtstAplcntMapper.selectAplyOPSECTSRNG(vo.getWtstPlaceId());

		String sttsStts = "01";
		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			if ("01".equals(list.get(i).getSttsCdv())) {
				sttsStts = list.get(i).getChageStts(); // 심사전
			} else if ("02".equals(list.get(i).getSttsCdv())) {
				sttsStts = "02"; // 선정
			} else if ("03".equals(list.get(i).getSttsCdv())) {
				sttsStts = "04"; // 미선정
			}
			result += cmmnSttsService.updateCmmnStts("IM_WTST_APLCNT", list.get(i).getTblRefId(), IMWtstAplcntStts.접수상태.sttsKey, sttsStts, vo);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#selectAplyStat(String)
	 */
	public List<IMWtstAplcntVO> selectAplyStat(String mmbrEsntlId) throws Exception {
		return wtstAplcntMapper.selectAplyStat(mmbrEsntlId);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.wtst.aplcnt.service.IMWtstAplcntService#selectOneWtstExcel(IMWtstAplcntVO)
	 */
	public IMWtstAplcntVO selectOneWtstExcel(IMWtstAplcntVO vo) throws Exception {
		return wtstAplcntMapper.selectOneWtstExcel(vo);
	}
}