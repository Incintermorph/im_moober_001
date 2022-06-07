/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.crs.aplcnt.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.exception.IMCrsAplcntStts;
import com.intermorph.cmmn.service.IMCmmnDtService;
import com.intermorph.cmmn.service.IMCmmnDtVO;
import com.intermorph.cmmn.service.IMCmmnSttsMapper;
import com.intermorph.cmmn.service.IMCmmnSttsService;
import com.intermorph.cmmn.service.IMCmmnSttsVO;
import com.intermorph.cmmn.service.IMRsltCodeMapper;
import com.intermorph.cmmn.service.IMRsltCodeVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntCondition;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntMapper;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntResultSet;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.crs.crs.service.IMCrsMapper;
import com.intermorph.crs.crs.service.IMCrsResultSet;
import com.intermorph.crs.crs.service.IMCrsVO;
import com.intermorph.uss.hstry.service.IMMmbrHstryMapper;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.idgnr.EgovIdGnrService;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.crs.aplcnt.service.impl
 * @File : CrsAplcntServiceImpl.java
 * @Title : 운영과정신청자
 * @date : 2022.03.03 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMCrsAplcntService")
public class IMCrsAplcntServiceImpl extends EgovAbstractServiceImpl implements IMCrsAplcntService {

	@Resource (name = "IMCrsAplcntMapper")
	private IMCrsAplcntMapper crsAplcntMapper;

	@Resource (name = "imCrsAplcntIdGnrService")
	private EgovIdGnrService idgenService;

	@Resource (name = "IMCmmnDtService")
	private IMCmmnDtService cmmnDtService;

	@Resource (name = "IMMmbrQlfcService")
	private IMMmbrQlfcService mmbrQlfcService;

	@Resource (name = "IMCmmnSttsMapper")
	protected IMCmmnSttsMapper cmmnSttsMapper;

	@Resource (name = "IMCrsMapper")
	private IMCrsMapper crsMapper;

	@Resource (name = "IMRsltCodeMapper")
	private IMRsltCodeMapper rsltCodeMapper;

	@Resource (name = "IMCmmnSttsService")
	private IMCmmnSttsService cmmnSttsService;

	@Resource (name = "IMMmbrHstryMapper")
	private IMMmbrHstryMapper mmbrHstryMapper;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#insertCrsAplcnt(com.intermorph.crs.aplcnt.vo.IMCrsAplcntVO)
	 */
	public int insertCrsAplcnt(IMCrsAplcntVO vo) throws Exception {
		int success = 0;
		IMCrsVO crs = new IMCrsVO();
		crs.setCrsId(vo.getCrsId());

		IMCrsVO crsInfo =null;
		//일괄 등록처리
		if("Y".equals(vo.getBatchYn())) {
			BaseResultSet rs = crsMapper.selectDetail(crs);

			if (rs == null) {
				return -1;
			}else {
				crsInfo = ((IMCrsResultSet)rs).getCrs();
			}
		}else {
			crsInfo = crsMapper.selectDetailChkInfo(crs);
		}
		
		if (crsInfo == null) {
			return -1;
		}

		if (crsAplcntMapper.selectOverAplyCount(vo) > 0) {
			return -1000;
		}

		int totalApply = crsAplcntMapper.selectAplyCount(vo);
		// 신청자 체크

		int crslimitApply = Integer.parseInt(crsInfo.getPrsnlLmt());
		// 신청자 제한 초과
		if (crslimitApply < totalApply) {
			return -1001;
		}
		
		
		vo.setCrsAplcntId(idgenService.getNextStringId());
		success = crsAplcntMapper.insert(vo);
		// 기본 수강생 상태 등록
		for (IMCrsAplcntStts v : IMCrsAplcntStts.values()) {
			IMCmmnSttsVO o = new IMCmmnSttsVO();
			o.setTblId("IM_CRS_APLCNT");
			o.setTblRefId(vo.getCrsAplcntId());
			o.setRefNm(v.sttsKey);
			o.setSttsCdv(v.defaultCode);
			o.copyAudit(vo);
			cmmnSttsMapper.insert(o);
			cmmnSttsMapper.insertHstry(o);
		}

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#updateCrsAplcnt(com.intermorph.crs.aplcnt.vo.IMCrsAplcntVO)
	 */
	public int updateCrsAplcnt(IMCrsAplcntVO vo) throws Exception {
		int success = 0;
		success = crsAplcntMapper.update(vo);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#updateCrsAplcntFnshRslt(com.intermorph.crs.aplcnt.vo.IMCrsAplcntVO,String)
	 */
	public synchronized int updateCrsAplcntRslt(IMCrsAplcntVO vo, String rsltCodeDvsn) throws Exception {

		int success = 0;

		// 수강정보 확인
		IMCrsAplcntResultSet aplcnt = (IMCrsAplcntResultSet)crsAplcntMapper.selectDetail(vo);

		if (aplcnt == null) {
			return -5;
		}

		// 운영과정 정보 확인
		IMCrsVO cvo = new IMCrsVO();
		cvo.setCrsId(aplcnt.getCrsAplcnt().getCrsId());
		IMCrsResultSet chkCrsinfo = (IMCrsResultSet)crsMapper.selectDetail(cvo);
		if (chkCrsinfo == null) {
			return -2;
		}

		// 이력 정보 정보 확인
		IMMmbrHstryVO hisvo = new IMMmbrHstryVO();
		hisvo.setEsntlId(aplcnt.getCrsAplcnt().getMmbrEsntlId());
		IMMmbrHstryResultSet hstryResultSet = (IMMmbrHstryResultSet)mmbrHstryMapper.selectDetail(hisvo);

		if (hstryResultSet == null) {
			return -6;
		}

		HashMap<String, IMCmmnDtVO> dataMap = cmmnDtService.selectListCmmnDtResultMap("IM_CRS", aplcnt.getCrsAplcnt().getCrsId());

		String fnshDVSNcode = "";
		String fnshGrdcode = "";

		String crsGrdCdv = chkCrsinfo.getCrsMstr().getCrsGrdCdv();
		String crsDvsnCdv = chkCrsinfo.getCrsMstr().getCrsDvsnCdv();
		if ("CRS_DVSN_001".equals(crsDvsnCdv)) {
			fnshDVSNcode = "B";
		} else if ("CRS_DVSN_002".equals(crsDvsnCdv)) {
			fnshDVSNcode = "P";
		} else if ("CRS_DVSN_003".equals(crsDvsnCdv)) {
			fnshDVSNcode = "R"; // 보수교육
		}
		if ("CRS_GRD_3".equals(crsGrdCdv)) {
			fnshGrdcode = "3";
		} else if ("CRS_GRD_2".equals(crsGrdCdv)) {
			fnshGrdcode = "2";
		} else if ("CRS_GRD_1".equals(crsGrdCdv)) {
			fnshGrdcode = "1";
		}

		if ("".equals(fnshDVSNcode) || "".equals(fnshGrdcode)) {
			return -1;
		}
		if (chkCrsinfo.getAgncy().getFnshCode() == null || "".equals(chkCrsinfo.getAgncy().getFnshCode())) {
			return -3;
		}

		String num = "";

		String delimiter = "-";

		IMRsltCodeVO rsltVO = new IMRsltCodeVO();
		rsltVO.copyAudit(vo);

		String stndrdCode = null;

		if ("FNSH".equals(rsltCodeDvsn)) {
			stndrdCode = chkCrsinfo.getCrs().getEduYear() + "_" + chkCrsinfo.getCrsMstr().getCrsGrdCdv() + chkCrsinfo.getCrsMstr().getCrsDvsnCdv() + "_"
					+ chkCrsinfo.getCrs().getAgncyId() + "_" + rsltCodeDvsn;
		} else if ("QLFC".equals(rsltCodeDvsn)) {
			stndrdCode = chkCrsinfo.getCrs().getEduYear() + "_" + chkCrsinfo.getCrsMstr().getCrsGrdCdv();
		}
		if (stndrdCode == null) {
			return -7;
		}
		rsltVO.setRsltTblId("IM_CRS_APLCNT");
		rsltVO.setRsltRefId(vo.getCrsAplcntId());
		rsltVO.setRsltCodeDvsn(rsltCodeDvsn);
		rsltVO.setStndrdCode(stndrdCode);

		String fnshCode = "";
		String sttsCode = "";
		if ("FNSH".equals(rsltCodeDvsn)) {
			sttsCode = vo.getFnshSttsCdv();
		} else if ("QLFC".equals(rsltCodeDvsn)) {
			sttsCode = vo.getQlfcSttsCdv();
		}
		// 수료인 경우
		if ("02".equals(sttsCode)) {
			// 기존 수료 코드 확인
			IMRsltCodeVO rsltCodeVO = rsltCodeMapper.selectDetail(rsltVO);

			if (rsltCodeVO == null) {
				String preMaxCode = rsltCodeMapper.selectMaxCode(stndrdCode);

				if (preMaxCode == null) {
					num = "0001";
				} else {
					String[] arrCode = preMaxCode.split("-");
					int resutNum = Integer.parseInt(arrCode[arrCode.length - 1]) + 1;
					num = String.format("%04d", resutNum);
				}
				// 2022-3-0001호

				if ("FNSH".equals(rsltCodeDvsn)) {
					fnshCode = chkCrsinfo.getCrs().getEduYear() + delimiter + fnshGrdcode + fnshDVSNcode + delimiter + chkCrsinfo.getAgncy().getFnshCode()
							+ delimiter + num;
				} else if ("QLFC".equals(rsltCodeDvsn)) {
					fnshCode = chkCrsinfo.getCrs().getEduYear() + delimiter + fnshGrdcode + delimiter + num;
				}

				rsltVO.setRsltCode(fnshCode);
				rsltVO.copyAudit(vo);
				rsltCodeMapper.insert(rsltVO);
			} else {
				fnshCode = rsltCodeVO.getRsltCode();
			}
		}

		if ("FNSH".equals(rsltCodeDvsn)) {
			aplcnt.getCrsAplcnt().setFnshSttsCdv(vo.getFnshSttsCdv());
			if ("02".equals(vo.getFnshSttsCdv())) {
				aplcnt.getCrsAplcnt().setFnshYmd(dataMap.get("fnshYmd").getBgnDt());
				aplcnt.getCrsAplcnt().setFnshRsltCode(fnshCode);
				aplcnt.getCrsAplcnt().setCrsDvsnCdv(crsDvsnCdv);
				if("CRS_DVSN_003".equals(crsDvsnCdv)){
					int year=Integer.parseInt(aplcnt.getCrsAplcnt().getFnshYmd().substring(0,4))+3;
	    			//취득년 +3 12월 31일
					aplcnt.getCrsAplcnt().setQlfcVldYmd(year+"1231235959");
					aplcnt.getCrsAplcnt().setFnshVldYmd(year+"1231235959");
				}else {
					//수료일 이후 2년 
					aplcnt.getCrsAplcnt().setFnshVldYmd(IMDateUtil.addDay(aplcnt.getCrsAplcnt().getFnshYmd().substring(0,8), (365*2) )+"235959");
					//System.out.println("aplcnt.getCrsAplcnt().getFnshVldYmd + " + aplcnt.getCrsAplcnt().getFnshVldYmd()); 
				}
						
			} else {
				aplcnt.getCrsAplcnt().setFnshYmd(null);
				aplcnt.getCrsAplcnt().setFnshRsltCode(null);
				aplcnt.getCrsAplcnt().setQlfcVldYmd(null);
				aplcnt.getCrsAplcnt().setFnshVldYmd(null);
			}

		} else if ("QLFC".equals(rsltCodeDvsn)) {
			aplcnt.getCrsAplcnt().setQlfcSttsCdv(vo.getQlfcSttsCdv());
			if ("02".equals(vo.getQlfcSttsCdv())) {
				aplcnt.getCrsAplcnt().setQlfcAcqsYmd(dataMap.get("olfcfnshYmd").getBgnDt());
				aplcnt.getCrsAplcnt().setQlfcRsltCode(fnshCode);
				int year=Integer.parseInt(aplcnt.getCrsAplcnt().getQlfcAcqsYmd().substring(0,4))+3;
	    			//취득년 +3 12월 31일
	    		aplcnt.getCrsAplcnt().setQlfcVldYmd(year+"1231235959");
			} else {
				aplcnt.getCrsAplcnt().setQlfcAcqsYmd(null);
				aplcnt.getCrsAplcnt().setQlfcRsltCode(null);
				aplcnt.getCrsAplcnt().setQlfcVldYmd(null);
			}
		}
		
		aplcnt.getCrsAplcnt().setBrdt(hstryResultSet.getMmbrHstry().getBrdt());
		aplcnt.getCrsAplcnt().copyAudit(vo);
		aplcnt.getCrsAplcnt().setEduHrs(chkCrsinfo.getCrs().getEduHrs());
		aplcnt.getCrsAplcnt().setAgncyId(chkCrsinfo.getCrs().getAgncyId());
		aplcnt.getCrsAplcnt().setCrsGrdCdv(chkCrsinfo.getCrsMstr().getCrsGrdCdv());
		aplcnt.getCrsAplcnt().setCrsDvsnCdv(chkCrsinfo.getCrsMstr().getCrsDvsnCdv());
		if ("CRS_DVSN_003".equals(crsDvsnCdv)) {
			//보수 교육인 경우 기관,학습 기관 및 보수교육 횟수 업데이트 
			aplcnt.getCrsAplcnt().setCntneduAgncyId(chkCrsinfo.getCrs().getAgncyId());
			aplcnt.getCrsAplcnt().setCntneduBgnDt(dataMap.get("eduTerm").getBgnDt());
			aplcnt.getCrsAplcnt().setCntneduEndDt(dataMap.get("eduTerm").getEndDt());
			
			IMCrsAplcntCondition  aplcntCondition  = new IMCrsAplcntCondition();
			aplcntCondition.setScMmbrEsntlId(aplcnt.getCrsAplcnt().getMmbrEsntlId());
			aplcntCondition.setScCrsGrdCdv(crsGrdCdv);
			aplcntCondition.setScCrsDvsnCdv("CRS_DVSN_003");
			int finshCnt = selectAplyFishCount(aplcntCondition);
			aplcnt.getCrsAplcnt().setCntneduCnt(finshCnt*1L);
		}
		success = crsAplcntMapper.updateRslt(aplcnt.getCrsAplcnt());
		if (success == 0) {
			success = crsAplcntMapper.insertRslt(aplcnt.getCrsAplcnt());
		}
		// 최종 업데이트
		mmbrQlfcService.updateMmbrQlfcCrs(aplcnt.getCrsAplcnt(), rsltCodeDvsn, crsGrdCdv, crsDvsnCdv);

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#updatelistCrsAplcnt(java.util.List)
	 */
	public int updatelistCrsAplcnt(List<IMCrsAplcntVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (IMCrsAplcntVO vo : voList) {
				success += crsAplcntMapper.update(vo);
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#deleteCrsAplcnt(com.intermorph.crs.aplcnt.vo.IMCrsAplcntVO)
	 */
	public int deleteCrsAplcnt(IMCrsAplcntVO vo) throws Exception {
		int success = 0;

		success = crsAplcntMapper.delete(vo);

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#deletelistCrsAplcnt(java.util.List)
	 */
	public int deletelistCrsAplcnt(List<IMCrsAplcntVO> voList) throws Exception {
		int success = 0;
		if (voList != null) {
			for (IMCrsAplcntVO vo : voList) {
				success += crsAplcntMapper.delete(vo);
			}
		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#selectDetail(com.intermorph.cmmn.base.BaseVO)
	 */
	@Override
	public BaseResultSet selectDetailCrsAplcnt(IMCrsAplcntVO vo) throws Exception {
		return crsAplcntMapper.selectDetail(vo);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#selectListCrsAplcnt(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListCrsAplcnt(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = crsAplcntMapper.selectListCount(condition);
			if (totalCount > 0) {
				paginateInfo.adjustPage(totalCount, condition);
				paginateInfo.page(crsAplcntMapper.selectList(condition), totalCount, condition);
			}
		} else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = crsAplcntMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;

	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#selectListCount(com.intermorph.cmmn.base.BaseCondition)
	 */
	public int selectListCount(BaseCondition condition) throws Exception {
		return crsAplcntMapper.selectListCount(condition);
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#updateCrsAlcntRANDOM(IMCrsVO)
	 */
	public int updateCrsAlcntRANDOM(IMCrsVO vo) throws Exception {

		BaseResultSet rs=crsMapper.selectDetail(vo);
		
		
		int result = 0;
		if (rs != null) {
			
			IMCrsResultSet crsResultSet = (IMCrsResultSet)rs;
			IMCrsVO chkCrsinfo = crsResultSet.getCrs();
			List<IMCrsAplcntVO> list = crsAplcntMapper.selectAplyCrsRANDOM(vo.getCrsId());

			int chkOver = Double.valueOf(Math.ceil(Double.parseDouble(chkCrsinfo.getPrsnl()) / 2)).intValue();
			int waitCount = Integer.parseInt(chkCrsinfo.getPrsnl()) + chkOver;
			// 렌덤상태 업데이트
			String sttsStts = "01";
			for (int i = 0; i < list.size(); i++) {
				if (i < Integer.parseInt(chkCrsinfo.getPrsnl())) {
					sttsStts = "02";
				} else if (i >= Integer.parseInt(chkCrsinfo.getPrsnl()) && i < waitCount) {
					sttsStts = "04";
				} else {
					sttsStts = "03";
				}
				result += cmmnSttsService.updateCmmnStts("IM_CRS_APLCNT", list.get(i).getCrsAplcntId(), IMCrsAplcntStts.랜덤상태.sttsKey, sttsStts, vo);
			}
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#updateAplyCrsOPSECTSRNGtoApply(IMCrsVO)
	 */
	public int updateAplyCrsOPSECTSRNGtoApply(IMCrsVO vo) throws Exception {

		List<IMCmmnSttsVO> list = crsAplcntMapper.selectAplyCrsOPSECTSRNG(vo.getCrsId());

		String sttsStts = "01";
		int result = 0;
		for (int i = 0; i < list.size(); i++) {
			if ("01".equals(list.get(i).getSttsCdv())) {
				sttsStts = "01";
			} else if ("02".equals(list.get(i).getSttsCdv())) {
				sttsStts = "02";
			} else if ("03".equals(list.get(i).getSttsCdv())) {
				sttsStts = "04";
			} else if ("04".equals(list.get(i).getSttsCdv())) {
				sttsStts = "05";
			}
			result += cmmnSttsService.updateCmmnStts("IM_CRS_APLCNT", list.get(i).getTblRefId(), IMCrsAplcntStts.신청상태.sttsKey, sttsStts, vo);
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#selectAplyFishCount(com.intermorph.cmmn.base.BaseCondition)
	 */
	public int selectAplyFishCount(BaseCondition condition) throws Exception {
		return crsAplcntMapper.selectAplyFishCount(condition);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#selectDetailResult(String)
	 */
	public IMCrsAplcntVO selectDetailResult(String crsAplcntId) throws Exception  {
		return crsAplcntMapper.selectDetailResult(crsAplcntId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#selectAplyStat(String)
	 */
	public List<IMCrsAplcntVO> selectAplyStat(String mmbrEsntlId) throws Exception  {
		return crsAplcntMapper.selectAplyStat(mmbrEsntlId);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.crs.aplcnt.service.IMCrsAplcntService#selectAplyUserViewHistory(String)
	 */
	public List<IMCrsAplcntVO> selectAplyUserViewHistory(String mmbrEsntlId) throws Exception  {
		return crsAplcntMapper.selectAplyUserViewHistory(mmbrEsntlId);
	}

}
