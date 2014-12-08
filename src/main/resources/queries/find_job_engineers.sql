select e.user_id, e.availability, u.first_name, u.last_name, u.email, u.gender,
  u.username, u.created_date, u.password FROM
  (((engineer_job_mapping as ej INNER join engineer as e on e.user_id = ej.engineer_id)
    INNER JOIN `user` as u on u.user_id = e.user_id)
    INNER JOIN `job` as j on j.job_id = ej.job_id)
WHERE ej.deleted = FALSE  and e.deleted = FALSE and u.deleted = FALSE
  AND j.deleted = FALSE AND j.job_id = ?