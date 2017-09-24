--IF EXISTS (SELECT * FROM User WHERE user_id = 1)
--BEGIN
	INSERT INTO User (user_id, name) VALUES (1,'User1')
		ON DUPLICATE KEY UPDATE user_id = 1;
	INSERT INTO User (user_id, name) VALUES (2,'User2')
		ON DUPLICATE KEY UPDATE user_id = 2;
	INSERT INTO User (user_id, name) VALUES (3,'User3')
		ON DUPLICATE KEY UPDATE user_id = 3;
	INSERT INTO User (user_id, name) VALUES (4,'User4')
		ON DUPLICATE KEY UPDATE user_id = 4;
--END