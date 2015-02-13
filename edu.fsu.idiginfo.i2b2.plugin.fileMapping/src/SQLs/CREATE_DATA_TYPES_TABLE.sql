create or replace PROCEDURE              CREATE_DATA_TYPES_TABLE AS 

BEGIN 
	execute immediate 'create table DATA_TYPES  (
      TYPE_NAME varchar(200) not null,
      Target_Table varchar(200) not null, 
      IS_DIM_TABLE varchar(20),
      IS_ACTIVE varchar(20) 
     
	) ';

    
    execute immediate 'CREATE INDEX idx_DATA_TYPES_pk ON DATA_TYPES ( TARGET_TABLE, TYPE_NAME)';
   
EXCEPTION
	WHEN OTHERS THEN
		dbms_output.put_line(SQLCODE|| ' - ' ||SQLERRM);

END CREATE_DATA_TYPES_TABLE;