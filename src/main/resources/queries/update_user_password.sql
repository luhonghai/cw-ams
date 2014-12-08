UPDATE `user`
set `password` = ?
WHERE `user_id` = ?
and `deleted` = FALSE;