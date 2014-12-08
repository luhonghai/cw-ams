update `job` set
  `name` = ?,
  `description` = ?,
  `number_of_engineer` = ?,
  `qualification_id` = ?,
  `priority_id` = ?,
  `status_id` = ?
where `job_id` = ? and `deleted` = false;