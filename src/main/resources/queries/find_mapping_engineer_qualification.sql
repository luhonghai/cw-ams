select q.qualification_id  as m_id FROM
  (((qualification as q INNER JOIN engineer_qualification_mapping as eq on eq.qualification_id = q.qualification_id)
      INNER JOIN engineer as e on e.user_id = eq.engineer_id)
     INNER JOIN `user` as u on u.user_id = e.user_id)
where e.deleted = FALSE and u.deleted = FALSE
  and eq.deleted = FALSE and q.deleted = FALSE
 and e.user_id = ?