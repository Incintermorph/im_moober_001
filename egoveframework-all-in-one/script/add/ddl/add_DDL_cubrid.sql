-- 전자 정부 최초  생성 후 추가 해야 하는컬럼 

ALTER TABLE comtnemplyrinfo
  ADD COLUMN CHG_PWD_LAST_PNTTM DATETIME ; 
  
  ALTER TABLE comtngnrlmber
ADD COLUMN CHG_PWD_LAST_PNTTM DATETIME;




-- im 추가 테이블 및 관련 스크립트 

CREATE TABLE [im_crs_mstr] (
       [crs_mstr_id] CHARACTER VARYING(20) NOT NULL COMMENT '과정마스터ID',
       [crs_grd_cdv] CHARACTER VARYING(50) NOT NULL COMMENT '과정등급코드값',
       [crs_dvsn_cdv] CHARACTER VARYING(50) NOT NULL COMMENT '과정구분코드값',
       [ttnfee]  NUMERIC(10,0) NOT NULL COMMENT '교육수수료',
       [edu_hrs] NUMERIC(3,0) NOT NULL COMMENT '교육시간',
       [stts_cdv] CHARACTER VARYING(50) COMMENT '상태코드값',
       [del_yn] CHARACTER(1) NOT NULL COMMENT '삭제여부',
       [frst_reger_id] CHARACTER VARYING(20) NOT NULL COMMENT '최초등록자ID',
       [frst_reg_dt] CHARACTER VARYING(14) NOT NULL COMMENT '최초등록일시',
       [frst_reger_ip] CHARACTER VARYING(50) NOT NULL COMMENT '최초등록자IP',
       [last_mdfer_id] CHARACTER VARYING(20) COMMENT '최종수정자ID',
       [last_mdfcn_dt] CHARACTER VARYING(14) COMMENT '최종수정일시',
       [last_mdfer_ip] CHARACTER VARYING(50) COMMENT '최종수정자IP',
       CONSTRAINT [im_crs_mstr_pk]
              PRIMARY KEY ([crs_mstr_id])
)
COLLATE utf8_bin COMMENT='과정마스터'



CREATE TABLE [im_cmmn_desc] (
       [tbl_id] CHARACTER VARYING(255) NOT NULL COMMENT '테이블ID',
       [tbl_ref_id] CHARACTER VARYING(20) NOT NULL COMMENT '테이블참조ID',
       [ref_nm] CHARACTER VARYING(50) NOT NULL COMMENT '참조이름',
       [cmmn_desc] CHARACTER VARYING(1073741823) COMMENT '공통설명',
       [frst_reger_id] CHARACTER VARYING(20) NOT NULL COMMENT '최초등록자ID',
       [frst_reg_dt] CHARACTER VARYING(14) NOT NULL COMMENT '최초등록일시',
       [frst_reger_ip] CHARACTER VARYING(50) NOT NULL COMMENT '최초등록자IP',
       [last_mdfer_id] CHARACTER VARYING(20) COMMENT '최종수정자ID',
       [last_mdfcn_dt] CHARACTER VARYING(14) COMMENT '최종수정일시',
       [last_mdfer_ip] CHARACTER VARYING(50) COMMENT '최종수정자IP',
       CONSTRAINT [im_cmmn_desc_pk]
              PRIMARY KEY ([tbl_id], [tbl_ref_id], [ref_nm])
)
COLLATE utf8_bin COMMENT='공통설명'