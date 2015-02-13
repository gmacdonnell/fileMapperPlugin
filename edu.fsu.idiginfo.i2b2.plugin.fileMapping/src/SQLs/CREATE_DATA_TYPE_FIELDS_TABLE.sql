create or replace PROCEDURE                           CREATE_DATA_TYPE_FIELDS_TABLE AS 
BEGIN
 
	execute immediate 'create table DATA_TYPE_FIELDS  (
	    DESCR varchar(200),
      FIELD_NAME varchar(200) not null,
      TARGET_TABLE varchar(200) not null, 
      IS_KEY varchar(20),
      FIELD_TYPE varchar(200)
     
	) ';

    
    execute immediate 'CREATE INDEX idx_DATA_TYPE_FIELDS_pk ON DATA_TYPE_FIELDS ( FIELD_NAME, TARGET_TABLE)';
   
EXCEPTION
	WHEN OTHERS THEN
		dbms_output.put_line(SQLCODE|| ' - ' ||SQLERRM);
END CREATE_DATA_TYPE_FIELDS_TABLE;