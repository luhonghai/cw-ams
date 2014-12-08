UPDATE `user`
  set deleted=TRUE
WHERE `user_id` = ? and deleted=FALSE;