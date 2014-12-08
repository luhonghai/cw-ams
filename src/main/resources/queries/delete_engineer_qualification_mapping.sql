update `engineer_qualification_mapping`
set `deleted` = TRUE
WHERE `engineer_id` = ?
and `qualification_id` = ?
and `deleted` = FALSE;