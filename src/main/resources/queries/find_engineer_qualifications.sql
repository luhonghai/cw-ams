select q.`qualification_id`, q.`name`, q.`description`, q.`created_date`
  from (((`qualification` as q INNER JOIN `engineer_qualification_mapping` as eq on q.`qualification_id` = eq.`qualification_id`)
    INNER JOIN `engineer` as e on e.`user_id` = eq.`engineer_id`)
    INNER JOIN `user` as u on u.`user_id` = e.`user_id`)
where q.deleted = FALSE and eq.deleted = FALSE and q.deleted = FALSE and e.deleted = FALSE
  and u.deleted = FALSE AND e.user_id=?;