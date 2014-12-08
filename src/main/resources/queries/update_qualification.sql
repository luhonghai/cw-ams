UPDATE `qualification`
       set `name` = ?,
           `description` = ?
   where `qualification_id` = ? and `deleted` = false;