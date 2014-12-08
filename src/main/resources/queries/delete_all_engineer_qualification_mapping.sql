update engineer_qualification_mapping
set deleted=TRUE
where engineer_id = ? and deleted = FALSE;