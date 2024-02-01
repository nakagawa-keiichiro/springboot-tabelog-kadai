INSERT IGNORE INTO nagoyameshi_db.store_information
(store_id, category_id, store_name, image_name, remarks, price_lower_limit, price_upper_limit, business_hours_open, business_hours_close, postal_code, address, phone_number, regular_holiday, registration_at, updated_at)
VALUES(1, 1, 'けむり', 'StoreInformation.jpg', '卵焼きが名物', 2000, 4000, '17:00', '2:00', '073-0145', '北海道砂川市西五条南X-XX-XX', '012-345-678', '水曜日', '2024-01-02 15:41:53', '2024-01-02 15:41:53');

INSERT IGNORE INTO nagoyameshi_db.category
(category_id, category_name, registration_at, updated_at)
VALUES(1, 'けむり', '2024-01-02 15:41:50', '2024-01-02 15:41:50');

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

-- reservationsテーブル
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (1, 1, 1, '2023-04-01', 2);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (2, 2, 1, '2023-04-01', 3);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (3, 3, 1, '2023-04-01', 4);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (4, 4, 1, '2023-04-01', 5);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (5, 5, 1, '2023-04-01', 6);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (6, 6, 1, '2023-04-01', 2);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (7, 7, 1, '2023-04-01', 3);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (8, 8, 1, '2023-04-01', 4);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (9, 9, 1, '2023-04-01', 5);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (10, 10, 1, '2023-04-01', 6);
INSERT IGNORE INTO reservations (id, store_id, user_id, reservation_date, number_of_people) VALUES (11, 11, 1, '2023-04-01', 2);