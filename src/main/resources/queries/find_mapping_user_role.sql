select u.`user_id`, r.`role_id`, r.`name`
  from ((role as r inner join `user_role_mapping` as ur on r.`role_id`=ur.`role_id`)
        inner join `user` as u on u.`user_id` = ur.`user_id`)
  WHERE u.deleted=false and ur.deleted = false and r.deleted = false
    and u.`user_id`=?;