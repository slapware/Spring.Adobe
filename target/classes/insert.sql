DELIMITER $$
CREATE PROCEDURE adept_userEntry

(IN password VARCHAR(250), 
 IN username VARCHAR(250),
 IN uuid VARCHAR(250),
 OUT rcount INT
)

BEGIN

DECLARE tcount INT;

IF EXISTS (SELECT * FROM auser au WHERE au.username = username AND au.password = md5(password)) THEN
SET tcount = 0;
ELSE 
INSERT INTO auser (password, username, uuid)
VALUES (md5(password), username, uuid);
end if;
SELECT ROW_COUNT() INTO rcount;

END$$
