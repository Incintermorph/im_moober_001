package com.intermorph.study.cnts.web;

public enum IMStudyCntsDataEnum {
	cmi_launch_data("cmi.launch_data", "", "cmi"), cmi_attempt("cmi.attempt", "0", "cmi"),
	cmi_progress_measure("cmi.progress_measure", "0.0", "cmi"),
	cmi_completion_status("cmi.completion_status", "not attempted", "cmi"),
	cmi_score_scaled("cmi.score_scaled", "0", "cmi"), 
	cmi_success_status("cmi.success_status", "unknown", "cmi"),
	cmi_location("cmi.location", "", "cmi"), 
	cmi_learner_id("cmi.learner_id", "", "cmi"),
	cmi_learner_name("cmi.learner_name", "", "cmi"), 
	cmi_exit("cmi.exit", "", "cmi"),
	cmi_max_time_allowed("cmi.max_time_allowed", "", "cmi"),
	cmi_completion_threshold("cmi.completion_threshold", "0.9", "cmi"),
	cmi_credit("cmi.credit", "no-credit", "cmi"),
	cmi_entry("cmi.entry", "", "cmi"),
	cmi_time_limit_action("cmi.time_limit_action", "", "cmi"),
	cmi_suspend_data("cmi.suspend_data", "", "cmi"),
	cmi_session_time("cmi.session_time", "0", "cmi"),
	cmi_total_time("cmi.total_time", "0", "cmi"),
	im_accessToken("accessToken", "", "im"),
	im_startTime("startTime", "", "im"),
	im_studyTime("studyTime", "0", "im"),
	im_resultCode("resultCode", "0000", "im"),
	im_saveProgressMeasure("save_progress_measure", "0.0", "im"),
	cmi_scaled_passing_score("cmi.scaled_passing_score", "", "cmi");

	/**
	 * 데이터 키
	 */
	public final String dataKey;

	/**
	 * 기본값
	 */
	public final String defaultValue;
	/**
	 * type
	 */
	public final String dataType;

	IMStudyCntsDataEnum(final String dataKey, final String defaultValue, final String dataType) {
		this.dataKey = dataKey;
		this.defaultValue = defaultValue;
		this.dataType = dataType;
	}
}
