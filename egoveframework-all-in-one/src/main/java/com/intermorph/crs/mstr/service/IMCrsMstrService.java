package com.intermorph.crs.mstr.service;

import java.util.List;

import com.intermorph.cmmn.base.BaseCondition;
import com.intermorph.cmmn.base.BasePage;
import com.intermorph.cmmn.base.BaseResultSet;

public interface IMCrsMstrService {
	/**
	 * 등록
	 * 
	 * @param vo
	 * @return int
	 */
	int insertCrsMstr(IMCrsMstrVO vo) throws Exception;

	/**
	 * 수정
	 * 
	 * @param vo
	 * @return int
	 */
	int updateCrsMstr(IMCrsMstrVO vo) throws Exception;

	/**
	 * 삭제
	 * 
	 * @param vo
	 * @return int
	 */
	int deleteCrsMstr(IMCrsMstrVO vo) throws Exception;
	/**
	 * 다중 삭제
	 * @param list
	 * @return
	 * @throws Exception
	 */
	int deleteCrsMstrlist(List<IMCrsMstrVO> list) throws Exception;

	/**
	 * 목록 조회
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BasePage<BaseResultSet> selectListCrsMstr(BaseCondition condition) throws Exception;
	

	/**
	 * 사용가능 목록 
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	List<BaseResultSet> selectListCrsMstr() throws Exception;
	
	/**
	 * 상세 조회 (단건)
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	BaseResultSet selectDetailCrsMstr(IMCrsMstrVO vo) throws Exception;
	
	
	
	
}
