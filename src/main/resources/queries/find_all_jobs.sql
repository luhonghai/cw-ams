select j.job_id, j.name, j.description, j.number_of_engineer, j.qualification_id,
  j.priority_id,
  j.created_date, j.status_id,
  q.name as qualification_name, q.description as qualification_description,
  q.created_date as qualification_created_date,
  js.name as job_status_name, js.level as job_status_level,
  p.name as priority_name, p.level as priority_level
from (((job as j left join qualification as q on j.qualification_id = q.qualification_id)
  left join priority as p on p.priority_id = j.priority_id)
  left join job_status as js on js.job_status_id = j.status_id)
where q.deleted = false and j.deleted = false and js.deleted = false
      and p.deleted = false;