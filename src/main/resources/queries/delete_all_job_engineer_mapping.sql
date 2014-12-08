update engineer_job_mapping
set deleted = true WHERE  deleted=FALSE
and job_id = ?