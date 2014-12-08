update `job`
  set deleted = TRUE
  where job_id = ? AND deleted=FALSE;