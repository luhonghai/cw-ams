update `user_role_mapping`
SET `deleted` = TRUE
WHERE `user_id` = ?
  and deleted = FALSE;