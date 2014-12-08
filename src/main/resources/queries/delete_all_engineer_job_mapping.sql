update engineer_job_mapping
set deleted=TRUE
where engineer_id = ? and deleted = FALSE;