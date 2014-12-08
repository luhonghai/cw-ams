select j.job_id as m_id FROM
  (((job as j INNER JOIN engineer_job_mapping as ej on ej.job_id = j.job_id)
    INNER JOIN engineer as e on e.user_id = ej.engineer_id)
    INNER JOIN `user` as u on u.user_id = e.user_id)
where u.deleted = FALSE and e.deleted = FALSE and ej.deleted = FALSE
  and j.deleted = FALSE
  and e.user_id = ?