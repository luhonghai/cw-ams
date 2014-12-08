update `user`
       set `first_name` = ?,
           `last_name` = ?,
           `email` = ?,
           `gender` = ?,
           `username` = ?,
           `password` = ?
where `user_id`=? AND `deleted` = FALSE;