-- rolesテーブル
INSERT IGNORE INTO roles (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT IGNORE INTO roles (id, name) VALUES (2, 'ROLE_MEMBER');
INSERT IGNORE INTO roles (id, name) VALUES (3, 'ROLE_PREMIUM_MEMBER');


-- usersテーブル
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (1, '名古屋 太郎', 'ナゴヤ タロウ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'taro.nagoya@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 1, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (2, '名古屋 花子', 'ナゴヤ ハナコ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'hanako.nagoya@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 2, true);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (3, '名古屋 義勝', 'ナゴヤ ヨシカツ', '638-0644', '奈良県五條市西吉野町湯川X-XX-XX', '090-1234-5678', 'yoshikatsu.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (4, '名古屋 幸美', 'ナゴヤ サチミ', '342-0006', '埼玉県吉川市南広島X-XX-XX', '090-1234-5678', 'sachimi.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (5, '名古屋 雅', 'ナゴヤ ミヤビ', '527-0209', '滋賀県東近江市佐目町X-XX-XX', '090-1234-5678', 'miyabi.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (6, '名古屋 正保', 'ナゴヤ マサヤス', '989-1203', '宮城県柴田郡大河原町旭町X-XX-XX', '090-1234-5678', 'masayasu.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (7, '名古屋 真由美', 'ナゴヤ マユミ', '951-8015', '新潟県新潟市松岡町X-XX-XX', '090-1234-5678', 'mayumi.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (8, '名古屋 安民', 'ナゴヤ ヤスタミ', '241-0033', '神奈川県横浜市旭区今川町X-XX-XX', '090-1234-5678', 'yasutami.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (9, '名古屋 章緒', 'ナゴヤ アキオ', '739-2103', '広島県東広島市高屋町宮領X-XX-XX', '090-1234-5678', 'akio.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (10, '名古屋 祐子', 'ナゴヤ ユウコ', '601-0761', '京都府南丹市美山町高野X-XX-XX', '090-1234-5678', 'yuko.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (11, '名古屋 秋美', 'ナゴヤ アキミ', '606-8235', '京都府京都市左京区田中西春菜町X-XX-XX', '090-1234-5678', 'akimi.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (12, '名古屋 信平', 'ナゴヤ シンペイ', '673-1324', '兵庫県加東市新定X-XX-XX', '090-1234-5678', 'shinpei.nagoya@example.com', 'password', 1, false);
INSERT IGNORE INTO users (id, name, furigana, postal_code, address, phone_number, email, password, role_id, enabled) VALUES (13, '名古屋 次郎', 'ナゴヤ ジロウ', '101-0022', '東京都千代田区神田練塀町300番地', '090-1234-5678', 'jiro.nagoyameshi@example.com', '$2a$10$2JNjTwZBwo7fprL2X4sv.OEKqxnVtsVQvuXDkI8xVGix.U3W5B7CO', 3, true);

-- reservationsテーブル
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (1, 1, 1, '2023-04-01', 2, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (2, 2, 1, '2023-04-01', 3, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (3, 3, 1, '2023-04-01', 4, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (4, 4, 1, '2023-04-01', 5, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (5, 5, 1, '2023-04-01', 6, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (6, 6, 1, '2023-04-01', 2, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (7, 7, 1, '2023-04-01', 3, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (8, 8, 1, '2023-04-01', 4, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (9, 9, 1, '2023-04-01', 5, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (10, 10, 1, '2023-04-01', 6, 0);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people, business_hours) VALUES (11, 11, 1, '2023-04-01', 2, 0);

--categoryテーブル
INSERT IGNORE INTO category (category_id, category_name) VALUES (1, '居酒屋');
INSERT IGNORE INTO category (category_id, category_name) VALUES (2, 'ラーメン');
INSERT IGNORE INTO category (category_id, category_name) VALUES (3, '寿司');
INSERT IGNORE INTO category (category_id, category_name) VALUES (4, '海鮮丼');


--store_information
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(1, 1, 'けむり', '/storage/StoreInformation.jpg', '卵焼きが名物', 2000, 4000, 17, 2, '073-0145', '北海道砂川市西五条南X-XX-XX', '012-345-679', '水曜日');
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(2, 2, 'じろう', '/storage/StoreInformation1.jpg', 'こってり系', 1000, 2000, 11, 15, '030-0945', '青森県青森市桜川X-XX-XX', '012-345-678', '木曜日');
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(3, 3, 'みやこ寿司', '/storage/StoreInformation2.jpg', '厳選された魚をご堪能ください', 7000, 8000, 18, 23, '029-5618', '岩手県和賀郡西和賀町沢内両沢X-XX-XX', '012-345-678', '月曜日');
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(4, 4, '日本海鮮丼', '/storage/StoreInformation3.jpg', '分厚く新鮮なネタは他ではなかなかありません', 1000, 2000, 10, 20, '989-0555', '宮城県刈田郡七ヶ宿町滝ノ上X-XX-XX', '012-345-678', '月曜日');
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(5, 1, 'なごや飯', '/storage/2629700a-668d-43ba-bfd2-3a63ac877cb9.jpg', '最寄り駅から徒歩10分。', 1000, 5000, 18, 2, '086-0072', '北海道根室市川口X-XX-XX', '012-345-678', '水曜日');
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(7, 1, 'あらい', '/storage/21cac1e9-5c36-4f93-90bd-0ca54ba72029.jpg', 'おいしい', 1000, 2000, 18, 2, '086-0072', '北海道根室市川口X-XX-XX', '012-345-678', '水曜日');
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(8, 2, 'さわ田', '/storage/f82de799-ed14-4f30-be65-4c6e374b3c79.jpg', 'うまい', 5000, 8000, 18, 2, '086-0072', '北海道根室市川口X-XX-XX', '012-345-678', '水曜日');
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(9, 2, '倉木屋', '/storage/52764192-7219-4b60-bede-fd8ee2d6f05a.jpg', 'からい', 500, 1000, 8, 17, '086-0072', '北海道根室市川口X-XX-XX', '012-345-678', '月曜日');
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(10, 2, '山小屋', '/storage/611033cd-566e-4480-aa4e-89d6273ed3da.jpg', '渋い店', 1000, 3000, 15, 20, '086-0072', '北海道根室市川口X-XX-XX', '012-345-678', '月曜日');
INSERT IGNORE INTO store_information (store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday) VALUES(11, 4, 'トラフグ', '/storage/1e24d1a7-a5c5-4272-99fd-7234904f976a.jpg', '甘い', 5000, 10000, 18, 2, '086-0072', '北海道根室市川口X-XX-XX', '012-345-678', '水曜日');


--verification_tokens
INSERT IGNORE INTO verification_tokens (id, user_id, token) VALUES (1, 14, 'e37060c8-57d6-41e0-a454-d25793bfa5be');

--reviewテーブル
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (1, 1, 1, 4, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (2, 1, 2, 5, '最高に美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (3, 1, 3, 4, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (4, 1, 4, 3, '普通でしたです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (5, 1, 5, 5, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (6, 1, 6, 2, 'まあまでした。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (7, 1, 7, 4, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (8, 1, 8, 4, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (9, 1, 9, 3, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (10, 1, 10, 4, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (11, 1, 11, 5, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (12, 2, 1, 5, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (13, 3, 1, 5, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (14, 4, 1, 5, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (15, 5, 1, 5, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (16, 6, 1, 5, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (17, 2, 2, 5, '最高に美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (18, 2, 3, 4, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (19, 2, 4, 3, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (20, 2, 5, 5, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (21, 2, 6, 2, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (22, 2, 7, 4, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (23, 2, 8, 4, '美味しかったです。');
INSERT IGNORE INTO review (id, store_id, user_id, evaluation, comments) VALUES (24, 2, 9, 1, 'う～ん...');

--favoriteテーブル
INSERT IGNORE INTO favorite (id, store_id, user_id) VALUES (1, 1, 1);
INSERT IGNORE INTO favorite (id, store_id, user_id) VALUES (2, 2, 1);
INSERT IGNORE INTO favorite (id, store_id, user_id) VALUES (3, 3, 1);
INSERT IGNORE INTO favorite (id, store_id, user_id) VALUES (4, 4, 1);
INSERT IGNORE INTO favorite (id, store_id, user_id) VALUES (5, 5, 1);
INSERT IGNORE INTO favorite (id, store_id, user_id) VALUES (6, 6, 1);
