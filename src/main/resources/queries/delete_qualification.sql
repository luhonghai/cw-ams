UPDATE `qualification`
       set `deleted` = TRUE
   where `qualification_id` = ? and `deleted` = false;