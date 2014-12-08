update engineer_qualification_mapping
set deleted = TRUE WHERE  deleted=FALSE
and qualification_id = ?