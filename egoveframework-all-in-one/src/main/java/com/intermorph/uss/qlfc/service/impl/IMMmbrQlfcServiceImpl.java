/*
 *  * Copyright (c) 2022 Intermorph Inc. All right reserved.
 * This software is the proprietary information of Intermorph Inc.
 *
 */
package com.intermorph.uss.qlfc.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;
import com.intermorph.cmmn.service.IMCmmnDescService;
import com.intermorph.cmmn.service.IMCmmnDescVO;
import com.intermorph.cmmn.util.IMDateUtil;
import com.intermorph.cmmn.util.IMStringUtil;
import com.intermorph.crs.agncy.service.IMAgncyCondition;
import com.intermorph.crs.agncy.service.IMAgncyResultSet;
import com.intermorph.crs.agncy.service.IMAgncyService;
import com.intermorph.crs.aplcnt.service.IMCrsAplcntVO;
import com.intermorph.uss.hstry.service.IMMmbrHstryMapper;
import com.intermorph.uss.hstry.service.IMMmbrHstryResultSet;
import com.intermorph.uss.hstry.service.IMMmbrHstryVO;
import com.intermorph.uss.issu.service.IMIssuAplyVO;
import com.intermorph.uss.objc.service.IMObjcAplyDtlDTO;
import com.intermorph.uss.pstpnd.service.IMPstpndAplyVO;
import com.intermorph.uss.qlfc.service.IMLcncHstryMapper;
import com.intermorph.uss.qlfc.service.IMLcncHstryVO;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcMapper;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcService;
import com.intermorph.uss.qlfc.service.IMMmbrQlfcVO;
import com.intermorph.wtst.aplcnt.service.IMWtstAplcntVO;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

/**
 * @Project : egoveframework-all-in-one
 * @Package : com.intermorph.uss.qlfc.service.impl
 * @File : MmbrQlfcServiceImpl.java
 * @Title : 회원자격정보
 * @date : 2022.04.13 generated
 * @author : sungyong2
 * @descrption : {상세한 프로그램의 용도를 기록}
 */
@Service ("IMMmbrQlfcService")
public class IMMmbrQlfcServiceImpl extends EgovAbstractServiceImpl implements IMMmbrQlfcService {

	@Resource (name = "IMMmbrQlfcMapper")
	private IMMmbrQlfcMapper mmbrQlfcMapper;

	@Resource (name = "IMCmmnDescService")
	private IMCmmnDescService cmmnDescService;

	@Resource (name = "IMMmbrHstryMapper")
	private IMMmbrHstryMapper mmbrHstryMapper;

	@Resource (name = "IMLcncHstryMapper")
	private IMLcncHstryMapper lcncHstryMapper;

	@Resource (name = "IMAgncyService")
	private IMAgncyService agncyService;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#insertMmbrQlfc(com.intermorph.uss.qlfc.vo.IMMmbrQlfcVO)
	 */
	public int insertMmbrQlfc(IMMmbrQlfcVO vo) throws Exception {
		int success = 0;
		success = mmbrQlfcMapper.insert(vo);

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#updateMig(com.intermorph.uss.hstry.service.IMMmbrHstryVO)
	 */
	public int updateMig(IMMmbrHstryVO vo) throws Exception {
		int success = 0;

		if (IMStringUtil.isEmpty(vo.getEsntlId())) {
			return success;
		}

		IMMmbrHstryResultSet rs = (IMMmbrHstryResultSet)mmbrHstryMapper.selectDetail(vo);

		if (rs != null) {
			List<IMLcncHstryVO> list = null;
			if (!IMStringUtil.isEmpty(rs.getMmbrHstry().getBrdt()) && !IMStringUtil.isEmpty(rs.getMmbrHstry().getMmbrNm())
					&& !IMStringUtil.isEmpty(rs.getMmbrHstry().getEsntlId())) {
				list = lcncHstryMapper.selectListUser(rs.getMmbrHstry().getBrdt(), rs.getMmbrHstry().getMmbrNm());

				if (list != null) {
					for (IMLcncHstryVO lcncHstryVO : list) {
						IMMmbrQlfcVO qlfcVO = new IMMmbrQlfcVO();
						qlfcVO.copyAudit(vo);
						qlfcVO.setEsntlId(vo.getEsntlId());
						if ("1급".equals(lcncHstryVO.getCrsGrd().trim())) {
							qlfcVO.setCrsGrdCdv("CRS_GRD_1");
						} else if ("2급".equals(lcncHstryVO.getCrsGrd().trim())) {
							qlfcVO.setCrsGrdCdv("CRS_GRD_2");
						} else if ("3급".equals(lcncHstryVO.getCrsGrd().trim())) {
							qlfcVO.setCrsGrdCdv("CRS_GRD_3");
						}
						qlfcVO.setCrsGrd(lcncHstryVO.getCrsGrd());
						qlfcVO.setQlfcRsltCode(lcncHstryVO.getLcncRsltCode());
						if (!IMStringUtil.isEmpty(lcncHstryVO.getBgnDt())) {
							String bdt = lcncHstryVO.getBgnDt().replaceAll("[.-]", "");
							if (bdt.trim().length() == 8) {
								qlfcVO.setCntneduBgnDt(bdt.trim() + "000000");
							}
						}
						if (!IMStringUtil.isEmpty(lcncHstryVO.getEndDt())) {
							String edt = lcncHstryVO.getEndDt().replaceAll("[.-]", "");
							if (edt.trim().length() == 8) {
								qlfcVO.setCntneduEndDt(edt.trim() + "235959");
							}
						}

						if (!IMStringUtil.isEmpty(lcncHstryVO.getLcncAcqsDt())) {

							String[] arr = lcncHstryVO.getLcncAcqsDt().split("[.]");

							if (arr.length >= 3) {
								String edt = arr[0];
								if (arr[1].length() == 1) {
									edt = edt + "0" + arr[1];
								} else {
									edt = edt + arr[1];
								}
								if (arr[2].length() == 1) {
									edt = edt + "0" + arr[2];
								} else {
									edt = edt + arr[2];
								}
								if (edt.trim().length() == 8) {
									qlfcVO.setLcncAcqsYmd(edt.trim() + "000000");
								}
							} else {
								String edt = lcncHstryVO.getLcncAcqsDt().replaceAll("[.-]", "");
								if (edt.trim().length() == 8) {
									qlfcVO.setLcncAcqsYmd(edt.trim() + "000000");
								}
							}

						}

						if (!IMStringUtil.isEmpty(lcncHstryVO.getCntneduCnt())) {
							qlfcVO.setCntneduCnt(Long.parseLong(lcncHstryVO.getCntneduCnt()));
						}

						qlfcVO.setLcncEndYmd(lcncHstryVO.getLcncEndYmd());

						if (!IMStringUtil.isEmpty(lcncHstryVO.getAgncyCode())) {

							IMAgncyCondition agncyCondition = new IMAgncyCondition();
							// 연계 포탈 코드 확인 후 유지보수 기관 체크
							agncyCondition.setScLinkCode(lcncHstryVO.getAgncyCode());
							BasePage<BaseResultSet> baseResult = agncyService.selectListAgncy(agncyCondition);

							if (baseResult != null && baseResult.getTotalRecordCount() > 0) {

								IMAgncyResultSet rs3 = (IMAgncyResultSet)baseResult.getList().get(0);
								qlfcVO.setCntneduAgncyId(rs3.getAgncy().getAgncyId());
							}

						}

						if (mmbrQlfcMapper.update(qlfcVO) == 0) {

							mmbrQlfcMapper.insert(qlfcVO);

						}
						success++;
						
					}

				}
				
			}

		}

		IMMmbrHstryVO mmbrHstryVO = new IMMmbrHstryVO();
		mmbrHstryVO.copyAudit(vo);
		mmbrHstryVO.setTrnsfYn("Y");
		mmbrHstryVO.setMemberSrl(rs.getMmbrHstry().getMemberSrl());
		mmbrHstryMapper.update(mmbrHstryVO);  // 마이그레이션 여부업데이트 
		
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#updateMmbrQlfc(com.intermorph.uss.qlfc.vo.IMMmbrQlfcVO)
	 */
	public int updateMmbrQlfc(IMMmbrQlfcVO vo) throws Exception {
		int success = 0;
		success = mmbrQlfcMapper.update(vo);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#updateMmbrQlfcCrs(IMCrsAplcntVO,String,String,String)
	 */
	public int updateMmbrQlfcCrs(IMCrsAplcntVO aplcnt, String rsltCodeDvsn, String crsGrdCdv, String crsDvsnCdv) throws Exception {
		int success = 0;
		IMMmbrQlfcVO vo = new IMMmbrQlfcVO();

		boolean updateChek = true;
		vo.copyAudit(aplcnt);
		vo.setEsntlId(aplcnt.getMmbrEsntlId());
		vo.setCrsGrdCdv(crsGrdCdv);

		IMMmbrQlfcVO nowVO = mmbrQlfcMapper.selectDetail(vo.getEsntlId(), vo.getCrsGrdCdv());

		System.out.println("vo.getEsntlId()  : " + vo.getEsntlId());
		System.out.println("vo.getCrsGrdCdv() : " + vo.getCrsGrdCdv());
		if (nowVO == null) {
			nowVO = new IMMmbrQlfcVO();
		}

		if ("CRS_DVSN_001".equals(crsDvsnCdv)) { // 기본 교육

			if ("02".equals(aplcnt.getFnshSttsCdv()) && !"02".equals(nowVO.getBscCrsSttsCdv())) {

				vo.setBscCrsAplcntId(aplcnt.getCrsAplcntId());
				vo.setBscCrsSttsCdv(aplcnt.getFnshSttsCdv());
				vo.setBscCrsCmpltnRsltCode(aplcnt.getFnshRsltCode());
				vo.setBscCrsCmpltnYmd(aplcnt.getFnshYmd());
			} else if (!"02".equals(aplcnt.getFnshSttsCdv()) && "02".equals(nowVO.getBscCrsSttsCdv())) {
				vo.setBscCrsAplcntId(aplcnt.getCrsAplcntId());
				vo.setBscCrsSttsCdv(aplcnt.getFnshSttsCdv());
			} else {
				updateChek = false;
			}

		} else if ("CRS_DVSN_002".equals(crsDvsnCdv)) { // 실무 교육
			if ("FNSH".equals(rsltCodeDvsn)) {
				if ("02".equals(aplcnt.getFnshSttsCdv()) && !"02".equals(nowVO.getExcnCrsSttsCdv())) {
					vo.setExcnCrsAplcntId(aplcnt.getCrsAplcntId());
					vo.setExcnCrsSttsCdv(aplcnt.getFnshSttsCdv());
					vo.setExcnCrsCmpltnRsltCode(aplcnt.getFnshRsltCode());
					vo.setExcnCrsCmpltnYmd(aplcnt.getFnshYmd());
				} else if (!"02".equals(aplcnt.getFnshSttsCdv()) && "02".equals(nowVO.getExcnCrsSttsCdv())) {
					vo.setExcnCrsAplcntId(aplcnt.getCrsAplcntId());
					vo.setExcnCrsSttsCdv(aplcnt.getFnshSttsCdv());
				} else {
					updateChek = false;
				}

			} else if ("QLFC".equals(rsltCodeDvsn)) {
				if ("02".equals(aplcnt.getQlfcSttsCdv()) && !"02".equals(nowVO.getLcncSttsCdv())) {
					vo.setLcncAplcntId(aplcnt.getCrsAplcntId());
					vo.setLcncSttsCdv(aplcnt.getQlfcSttsCdv());
					vo.setQlfcRsltCode(aplcnt.getQlfcRsltCode());
					vo.setLcncIssuYmd(aplcnt.getQlfcAcqsYmd());
					int year = Integer.parseInt(aplcnt.getQlfcAcqsYmd().substring(0, 4)) + 3;
					// 취득년 +3 12월 31일
					vo.setLcncEndYmd(year + "1231235959");

				} else if (!"02".equals(aplcnt.getQlfcSttsCdv()) && "02".equals(nowVO.getLcncSttsCdv())) {
					vo.setLcncAplcntId(aplcnt.getCrsAplcntId());
					vo.setLcncSttsCdv(aplcnt.getQlfcSttsCdv());
					vo.setLcncEndYmd(null);
				} else {
					updateChek = false;
				}

			} else {
				updateChek = false;
			}

		} else if ("CRS_DVSN_003".equals(crsDvsnCdv)) { // 보수 교육
			vo.setCntneduAplcntId(aplcnt.getCrsAplcntId());
			vo.setCntneduSttsCdv(aplcnt.getFnshSttsCdv());
			vo.setCntneduCmpltnRsltCode(aplcnt.getFnshRsltCode());
			vo.setCntneduCmpltnYmd(aplcnt.getFnshYmd());
			vo.setCntneduCnt(aplcnt.getCntneduCnt());

			if ("02".equals(aplcnt.getFnshSttsCdv()) && !"02".equals(nowVO.getCntneduSttsCdv())) {
				int year = Integer.parseInt(aplcnt.getFnshYmd().substring(0, 4)) + 3;
				vo.setLcncEndYmd(year + "1231000000");
				vo.setCntneduAgncyId(aplcnt.getCntneduAgncyId());
				vo.setCntneduBgnDt(aplcnt.getCntneduBgnDt());
				vo.setCntneduEndDt(aplcnt.getCntneduEndDt());
				Gson gson = new Gson();
				cmmnDescService.insertCmmnDesc("im_mmbr_qlfc", aplcnt.getCrsAplcntId(), "cntnedu", gson.toJson(nowVO), vo);

			} else if (!"02".equals(aplcnt.getFnshSttsCdv()) && "02".equals(nowVO.getCntneduSttsCdv())) {
				HashMap<String, String> bakMap = cmmnDescService.selectListCmmnDescResultMap("im_mmbr_qlfc", aplcnt.getCrsAplcntId());
				// 구자료 가져와서 업데이트 처리
				// 이전 만료일 백업 데이터
				if (bakMap.get("cntnedu") != null) {
					Gson gson = new Gson();
					IMMmbrQlfcVO backVO = gson.fromJson(bakMap.get("cntnedu"), IMMmbrQlfcVO.class);
					vo.setLcncEndYmd(backVO.getLcncEndYmd());
					vo.setCntneduAgncyId(backVO.getCntneduAgncyId());
					vo.setCntneduBgnDt(backVO.getCntneduBgnDt());
					vo.setCntneduEndDt(backVO.getCntneduEndDt());

					vo.setCntneduAplcntId(backVO.getCntneduAplcntId());
					vo.setCntneduSttsCdv(backVO.getCntneduSttsCdv());
					vo.setCntneduCmpltnRsltCode(backVO.getCntneduCmpltnRsltCode());
					vo.setCntneduCmpltnYmd(backVO.getCntneduCmpltnYmd());

				}
			} else {
				updateChek = false;
			}
		} else {
			updateChek = false;
		}

		System.out.println("updateChek : " + updateChek);
		if (updateChek) {
			success = mmbrQlfcMapper.update(vo);
			if (success == 0) {
				mmbrQlfcMapper.insert(vo);
			}
			String hstryId = IMDateUtil.getImToday("yyyyMMddHHmmss") + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
			vo.setHstryId(hstryId);
			mmbrQlfcMapper.insertHstry(vo);

		}
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#updateMmbrQlfcWtst(IMWtstAplcntVO,String,String,String)
	 */
	public int updateMmbrQlfcWtst(IMWtstAplcntVO aplcnt, String rsltCode, String crsGrdCdv) throws Exception {
		int success = 0;

		IMMmbrQlfcVO vo = new IMMmbrQlfcVO();
		vo.copyAudit(aplcnt);

		vo.setEsntlId(aplcnt.getMmbrEsntlId());
		vo.setCrsGrdCdv(crsGrdCdv);
		vo.setWtstAplcntId(aplcnt.getWtstAplcntId());
		vo.setWtstSttsCdv(rsltCode);

		if ("02".equals(rsltCode)) {
			vo.setWtstPassYmd(aplcnt.getPassYmd());
			vo.setWtstVldEndYmd(aplcnt.getVldEndYmd());
		}

		success = mmbrQlfcMapper.update(vo);
		if (success == 0) {
			success = mmbrQlfcMapper.insert(vo);
		}
		String hstryId = IMDateUtil.getImToday("yyyyMMddHHmmss") + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
		vo.setHstryId(hstryId);
		mmbrQlfcMapper.insertHstry(vo);

		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#updateMmbrQlfcIssue(IMIssuAplyVO,String,String,String)
	 */
	public int updateMmbrQlfcIssue(IMIssuAplyVO issuAply, String lcncIssuCode, String crsGrdCdv, String lcncIssuYmd) throws Exception {
		int success = 0;

		IMMmbrQlfcVO vo = new IMMmbrQlfcVO();
		vo.copyAudit(issuAply);

		vo.setEsntlId(issuAply.getEsntlId());

		vo.setCrsGrdCdv(crsGrdCdv);
		vo.setIssuAplyId(issuAply.getIssuAplyId());
		vo.setLcncIssuYmd(lcncIssuYmd);
		vo.setLcncIssuCode(lcncIssuCode);

		success = mmbrQlfcMapper.update(vo);
		if (success == 0) {
			success = mmbrQlfcMapper.insert(vo);
		}
		String hstryId = IMDateUtil.getImToday("yyyyMMddHHmmss") + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
		vo.setHstryId(hstryId);
		mmbrQlfcMapper.insertHstry(vo);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#updateMmbrQlfcPstpnd(IMPstpndAplyVO,String)
	 */
	public int updateMmbrQlfcPstpnd(IMPstpndAplyVO pstpndAply, String qlfclcncEndYmd) throws Exception {
		int success = 0;

		IMMmbrQlfcVO vo = new IMMmbrQlfcVO();
		vo.copyAudit(pstpndAply);

		vo.setEsntlId(pstpndAply.getEsntlId());

		vo.setCrsGrdCdv(pstpndAply.getCrsGrdCdv());
		vo.setPstpndAplyId(pstpndAply.getPstpndAplyId());
		vo.setLcncEndYmd(qlfclcncEndYmd);

		success = mmbrQlfcMapper.update(vo);
		if (success == 0) {
			success = mmbrQlfcMapper.insert(vo);
		}
		String hstryId = IMDateUtil.getImToday("yyyyMMddHHmmss") + UUID.randomUUID().toString().replaceAll("-", "").substring(0, 6);
		vo.setHstryId(hstryId);
		mmbrQlfcMapper.insertHstry(vo);
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#selectListLastMmbrQlfc(String)
	 */
	@Override
	public IMMmbrQlfcVO selectListLastMmbrQlfc(String esntlId) throws Exception {
		return mmbrQlfcMapper.selectLastDetail(esntlId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#selectListUserMmbrQlfc(String)
	 */
	@Override
	public List<IMMmbrQlfcVO> selectListUserMmbrQlfc(String esntlId) throws Exception {
		return mmbrQlfcMapper.selectUserList(esntlId);
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#selectListUserMmbrQlfc(String)
	 */
	@Override
	public List<IMMmbrQlfcVO> selectListUserMmbrQlfcPass(String esntlId) throws Exception {
		List<IMMmbrQlfcVO> listMmbrQlfcDB = mmbrQlfcMapper.selectUserList(esntlId);
		List<IMMmbrQlfcVO> listMmbrQlfc = new ArrayList<IMMmbrQlfcVO>();
		for(IMMmbrQlfcVO vo : listMmbrQlfcDB) {
			if(!IMStringUtil.isEmpty(vo.getQlfcRsltCode())) {
				listMmbrQlfc.add(vo);
			}
		}
		return listMmbrQlfc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#selectDetailMmbrQlfc(String,String)
	 */
	@Override
	public IMMmbrQlfcVO selectDetailMmbrQlfc(String esntlId, String crsGrdCdv) throws Exception {

		return mmbrQlfcMapper.selectDetail(esntlId, crsGrdCdv);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.intermorph.uss.qlfc.service.IMMmbrQlfcService#selectListMmbrQlfc(com.intermorph.cmmn.base.BaseCondition)
	 */
	public BasePage<BaseResultSet> selectListMmbrQlfc(BaseCondition condition) throws Exception {
		int totalCount = 0;
		BasePage<BaseResultSet> paginateInfo = new BasePage<BaseResultSet>();
		if (condition.checkPage()) {
			totalCount = mmbrQlfcMapper.selectListCount(condition);

			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(mmbrQlfcMapper.selectList(condition), totalCount, condition);

		} else {
			condition.setCurrentPageNo(0);
			List<BaseResultSet> list = mmbrQlfcMapper.selectList(condition);
			totalCount = list.size();
			paginateInfo.adjustPage(totalCount, condition);
			paginateInfo.page(list, totalCount, condition);

		}
		return paginateInfo;

	}

}