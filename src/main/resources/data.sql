--CREATE TABLE beer (
--id VARCHAR NOT NULL,
--beer_name VARCHAR(255),
--beer_style VARCHAR(255),
--created_date TIMESTAMP,
--last_modified_date TIMESTAMP,
--min_on_hand INTEGER,
--quantity_to_brew INTEGER,
--price DOUBLE,
--upc VARCHAR(255),
--version INTEGER,
--PRIMARY KEY (id)
--);


INSERT INTO beer (id, beer_name, beer_style, created_date, last_modified_date, min_on_hand, quantity_to_brew, price, upc, version ) values ('0a818933-087d-47f2-ad83-2f986ed087eb', 'Mango Bobs', 'IPA', CURRENT_TIMESTAMP , CURRENT_TIMESTAMP , 12,  200, 12.95, '0631234200036', 1);
INSERT INTO beer (id, beer_name, beer_style, created_date, last_modified_date, min_on_hand, quantity_to_brew, price, upc, version ) values ('a712d914-61ea-4623-8bd0-32c0f6545bfd', 'Galaxy Cat', 'PALE_ALE', CURRENT_TIMESTAMP , CURRENT_TIMESTAMP , 12,  200, 12.95, '0631234300019', 1);
INSERT INTO beer (id, beer_name, beer_style, created_date, last_modified_date, min_on_hand, quantity_to_brew, price, upc, version ) values ('026cc3c8-3a0c-4083-a05b-e908048c1b08', 'Pinball Porter', 'PORTER', CURRENT_TIMESTAMP , CURRENT_TIMESTAMP , 12,  200, 12.95, '0083783375213', 1);
