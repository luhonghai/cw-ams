UPDATE `engineer_job_mapping`
  set `deleted`=TRUE
WHERE `engineer_id` = ?
  and `job_id` = ? AND `deleted`=FALSE;