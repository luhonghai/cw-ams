update `user`
       set `first_name` = ?,
           `last_name` = ?,
           `email` = ?,
           `gender` = ?,
           `username` = ?
where `user_id`=? AND `deleted` = FALSE;