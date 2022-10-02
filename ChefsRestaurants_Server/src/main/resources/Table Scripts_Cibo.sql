DROP DATABASE if exists CIBO;
CREATE DATABASE CIBO;
USE CIBO;


CREATE TABLE USERS (

	USER_ID INT AUTO_INCREMENT,
	USER_NAME VARCHAR(20) NOT NULL,
	EMAIL_ID VARCHAR(30) NOT NULL,
	CONTACT_NUMBER VARCHAR(10) NOT NULL,
	PASSWORD VARCHAR(70) NOT NULL,  
	constraint CIBO_USERS_Pk primary key ( USER_ID )
);

CREATE TABLE ROLES(
ROLE_ID INT AUTO_INCREMENT,
ROLE_TYPE VARCHAR(10) not null,
USER_ID INT,
constraint CIBO_ROLES_Pk primary key (ROLE_ID)
);


CREATE TABLE Restaurant (
	restaurant_id int AUTO_INCREMENT,
	USER_ID int,
	restaurant_name varchar(20) NOT NULL,
	restaurant_contact varchar(30) NOT NULL,
	restaurant_type varchar(10) NOT NULL,
	address_line1 varchar(20) NOT NULL,
	area varchar(15) NOT NULL,
	city varchar(15) NOT NULL,
	res_state varchar(17) NOT NULL,
	pincode int NOT NULL,
	approval_status varchar(10) not null,
	avg_rating double,
	photo_urls varchar(200) not null,
	constraint CIBO_RESTAURANT_Pk primary key ( RESTAURANT_ID )
);
CREATE TABLE Restaurant_Transaction(
	restaurant_transaction_id int AUTO_INCREMENT,
	restaurant_id int,
	restaurant_order_counter int NOT NULL,
	restaurant_approx_cost int NOT NULL,
	restaurant_status boolean DEFAULT false,
	constraint CIBO_RESTAURANT_T_Pk primary key (restaurant_transaction_id)
);

CREATE TABLE Coupon (
	coupon_id int AUTO_INCREMENT,
	coupon_code varchar(10) NOT NULL,
	minimum_bill int NOT NULL,
	maximum_redemption int NOT NULL,
	start_date datetime default CURRENT_TIMESTAMP,
	end_date datetime default CURRENT_TIMESTAMP,
	constraint CIBO_COUPON_Pk primary key ( COUPON_ID )
);

CREATE TABLE User_Address (
	User_Address_id int AUTO_INCREMENT,
	user_id int,
	User_Address_name varchar(10) NOT NULL,
	address_line1 varchar(30) NOT NULL,
	address_line2 varchar(30) NOT NULL,
	area varchar(20) NOT NULL,
	city varchar(17) NOT NULL,
	user_state varchar(20) NOT NULL,
	pincode int NOT NULL,
	constraint CIBO_USER_address_Pk primary key ( USER_ADDRESS_ID )
);


create table orders (
	order_id int AUTO_INCREMENT,
	user_id int,
	order_bill int not null,
	order_status varchar(30) not null,
	constraint CIBO_order_pk primary key (order_id)
);

create table order_items (
	order_items_id int AUTO_INCREMENT,
	dish_id int,
	order_id int,
	qty int not null,
	constraint CIBO_order_items_pk primary key (order_items_id)	
);

create table dish (
	dish_id int AUTO_INCREMENT,
	restaurant_id int,
	dish_name varchar(30) not null,
	dish_cuisine varchar(20) not null,
	dish_type varchar(10) not null,
	dish_description varchar(150) not null,
	speciality varchar(30) not null,
	price double not null,
	avg_rating double not null,
	image_url varchar(50) not null,
	constraint CIBO_dish_pk primary key (dish_id)
	
);


create table dish_rating (
	dish_rating_id int AUTO_INCREMENT,
	dish_id int,
	user_id int NOT NULL,
	rating int default 0,
	constraint CIBO_dish_rating_fk primary key (dish_rating_id)
);

create table wallet (
	wallet_id int AUTO_INCREMENT,
	user_id int NOT NULL,
	available_amount int(10) not null,
	constraint CIBO_wallet_pk primary key (wallet_id)
);

CREATE TABLE USER_LIKES(
LIKE_ID INT AUTO_INCREMENT,
VEG_NONVEG VARCHAR(10) NOT NULL,
DISH_ID INT,
USER_ID INT NOT NULL,
restaurant_id int NOT NULL,
CONSTRAINT CIBO_USER_LIKES_PK primary key (LIKE_ID)
);

CREATE TABLE TABLE_BOOKING(
BOOKING_ID INT AUTO_INCREMENT,
BOOKING_DATE date NOT NULL,
NO_OF_PEOPLE INT NOT NULL,
TIME_OF_BOOKING datetime default CURRENT_TIMESTAMP,
RESTAURANT_ID int NOT NULL,
USER_ID INT NOT NULL,
constraint CIBO_BOOKINGS_Pk primary key (BOOKING_ID)
);



ALTER TABLE ROLES ADD CONSTRAINT CIBO_USERS_ROLE_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);
ALTER TABLE ORDERS ADD CONSTRAINT CIBO_ORDER_USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);
ALTER TABLE DISH ADD CONSTRAINT CIBO_RESTAURANT_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANT (RESTAURANT_ID);
ALTER TABLE DISH_RATING ADD CONSTRAINT CIBO_DISH_RATING_DISH_FK FOREIGN KEY (DISH_ID) REFERENCES DISH (DISH_ID);
ALTER TABLE DISH_RATING ADD CONSTRAINT CIBO_DISH_RATING_USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);
ALTER TABLE WALLET ADD CONSTRAINT CIBO_WALLET_USER_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);
ALTER TABLE RESTAURANT_TRANSACTION ADD CONSTRAINT CIBO_REST_TRANSACTION_REST_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANT (RESTAURANT_ID);
ALTER TABLE USER_ADDRESS ADD CONSTRAINT CIBO_USER_ADDRESS_USERS_FK FOREIGN KEY (USER_ID) REFERENCES USERS (USER_ID);
ALTER TABLE ORDER_ITEMS ADD CONSTRAINT CIBO_ORDER_ITEMS_DISH_FK FOREIGN KEY (DISH_ID) REFERENCES DISH(DISH_ID);
ALTER TABLE ORDER_ITEMS ADD CONSTRAINT CIBO_ORDER_ITEMS_ORDERS_FK FOREIGN KEY (ORDER_ID) REFERENCES ORDERS(ORDER_ID);
ALTER TABLE RESTAURANT ADD CONSTRAINT CIBO_RESTAURANT_OWNER_FK FOREIGN KEY ( USER_ID ) REFERENCES USERS ( USER_ID);
ALTER TABLE TABLE_BOOKING ADD CONSTRAINT CIBO_BOOKING_USER_FK FOREIGN KEY ( USER_ID ) REFERENCES USERS ( USER_ID );
ALTER TABLE TABLE_BOOKING ADD CONSTRAINT CIBO_BOOKING_RESTAURANT_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANT (RESTAURANT_ID);
ALTER TABLE USER_LIKES ADD CONSTRAINT CIBO_USER_LIKES_USER_FK FOREIGN KEY(USER_ID ) REFERENCES USERS(USER_ID);
ALTER TABLE USER_LIKES ADD CONSTRAINT CIBO_USER_LIKES_DISH_FK FOREIGN KEY(DISH_ID) REFERENCES DISH (DISH_ID);
ALTER TABLE USER_LIKES ADD CONSTRAINT CIBO_USER_LIKES_RESTAURANT_FK FOREIGN KEY (RESTAURANT_ID) REFERENCES RESTAURANT (RESTAURANT_ID);



-- INSERT CODE TO USERS TABLE
INSERT INTO USERS VALUES (101, 'SCOTT', 'scott@stark.com', '8884967823', '3284cbd43ac6fc733d7c3d2176e7a52bbaeba81471702ec332a0a65689cf16e3');
-- Scott@123
INSERT INTO USERS VALUES (102, 'TONY', 'tony@stark.com', '8875632142', '1f7cbaa9168ffce48872d8fc4e5429dee55ed8f21d8d84bccd6aaa2a72ae1d79');
-- Tony@123
INSERT INTO USERS VALUES (103, 'STEVE', 'steve@gmail.com', '9880253413', '97661249431ccedba1711b78fb58eceb2c03054a07a7b684ad53048691b34435');
-- Steve@123
INSERT INTO USERS VALUES (104, 'ISABELLE', 'banner@Uv.com', '8882039476', '9a8d7e96acac7b73f1f9c994dd512df9068bb0549961e42333745c67a994e6f1');
-- Banner@123
INSERT INTO USERS VALUES (105, 'ROSE', 'rose@Uv.com', '9476888203', '9a8d7e96acac7b73f1f9c994dd512df9068bb0549961e42333745c67a994e6f1');
-- Banner@123

-- INSERT CODE TO ROLES TABLE
INSERT INTO ROLES VALUES (1,'CUSTOMER',104);
INSERT INTO ROLES VALUES (2,'VENDOR',102);
INSERT INTO ROLES VALUES (3,'CUSTOMER',101);
INSERT INTO ROLES VALUES (4,'VENDOR',104);
INSERT INTO ROLES VALUES (5,'CUSTOMER',103);
INSERT INTO ROLES VALUES (6,'ADMIN',105);

-- INSERT CODE TO RESTAURANT TABLE
INSERT INTO RESTAURANT VALUES (1,102,'KFC','9823414141','Nonveg','23, Shastri Nagar','Baner','Pune','Maharashtra',411041,'Accepted',4.2,'assets/kfca1.jpg-assets/kfca2.jpg-assets/kfca3.jpg');
INSERT INTO RESTAURANT VALUES (2,102,'KFC','8934217843','Nonveg','3, Gajanan Nagar','Kothrud','Pune','Maharashtra',411038,'Accepted',4.2,'assets/kfcb1.jpg-assets/kfcb2.jpg-assets/kfcb3.jpg');
INSERT INTO RESTAURANT VALUES (3,104,'Pizza Hut','8784393421','Veg','21, Adalat road','Rajouri Garden','Delhi','Delhi',110027,'Accepted',4.5,'assets/pizzahut1.jpg-assets/pizzahut2.jpg-assets/pizzahut3.jpg');
INSERT INTO RESTAURANT VALUES (4,104,'Master Kitchen','8777772771','Nonveg','52, Sandesh road','Vasant Vihar','Delhi','Delhi',110057,'Accepted',4.1,'assets/masterkitchen1.jpg-assets/masterkitchen2.jpg-assets/masterkitchen3.jpg');
INSERT INTO RESTAURANT VALUES (5,104,'Diamond Cafe','8977772771','Veg','11, Bandana circle','Vasant Vihar','Delhi','Delhi',110057,'Accepted',4.3,'assets/cafe1.jpg-assets/cafe2.jpg-assets/cafe3.jpg');
INSERT INTO RESTAURANT VALUES (6,104,'Empire Restaurant','9877226354','Veg','2, Amol Complex','Vasant Vihar','Delhi','Delhi',110057,'Accepted',4.3,'assets/empire1.jpg-assets/empire2.jpg');
-- INSERT INTO RESTAURANT VALUES (7,102,'Barbeque Nation','8823414141','Nonveg',33,'Mayura Circle','Baner','Pune','Maharashtra',411041,'Pending',4.2,'assets/kfca3.jpg-assets/kfca2.jpg-assets/kfca1.jpg');
-- INSERT INTO RESTAURANT VALUES (8,102,'Kamat','7823414141','Nonveg',3,'Madge Circle','Kothrud','Pune','Maharashtra',411038,'Pending',1.0,'assets/kfca2.jpg-assets/kfca1.jpg-assets/kfca3.jpg');



-- INSERT CODE TO RESTAURANT_TRANSACTION TABLE
INSERT INTO RESTAURANT_TRANSACTION VALUES (1,1,2,500,true);
INSERT INTO RESTAURANT_TRANSACTION VALUES (2,2,1,500,false);
INSERT INTO RESTAURANT_TRANSACTION VALUES (3,3,2,350,true);
INSERT INTO RESTAURANT_TRANSACTION VALUES (4,4,1,500,false);
INSERT INTO RESTAURANT_TRANSACTION VALUES (5,5,2,350,true);
INSERT INTO RESTAURANT_TRANSACTION VALUES (6,6,0,400,true);

-- INSERT CODE TO COUPON TABLE
INSERT INTO COUPON VALUES (1001,'CIBO20',200,100,sysdate()- interval 12 day,sysdate()- interval 5  day);
INSERT INTO COUPON VALUES (1002,'FOOD30',300,125,sysdate()- interval 15 day,sysdate()- interval 3  day);
INSERT INTO COUPON VALUES (1003,'HUNGRY25',250,100,sysdate()- interval 10 day,sysdate()- interval 2  day);

-- INSERT CODE TO USER_ADDRESS TABLE
INSERT INTO USER_ADDRESS VALUES (1,101,'Home','407, Rajhans Apartments','Lashkar Mohalla','Vasant Vihar','Delhi','Delhi',110029);
INSERT INTO USER_ADDRESS VALUES (2,101,'Work','Bajaj Industrial Area','Navneeth nagar','Vasant Vihar','Delhi','Delhi',110029);
INSERT INTO USER_ADDRESS VALUES (3,103,'Home','A2/1, Indira Soceity','Lalvani nagar','Baner','Pune','Maharashtra',411037);
INSERT INTO USER_ADDRESS VALUES (4,103,'Work','A705, AjayDeep Complex','Lalvani nagar','Kothrud','Pune','Maharashtra',411037);
INSERT INTO USER_ADDRESS VALUES (5,105,'Home','309, Leo Janani Apartments','Lakshmikanth nagar','Rajouri Garden','Delhi','Delhi',110027);
INSERT INTO USER_ADDRESS VALUES (6,104,'Home','304, Rajhans Apartments','Lashkar Mohalla','Vasant Vihar','Delhi','Delhi',110029);

-- INSERT CODE TO ORDERS TABLE
INSERT INTO ORDERS VALUES (1001,101,300,'INACTIVE');
INSERT INTO ORDERS VALUES (1002,105,750,'ACTIVE');
INSERT INTO ORDERS VALUES (1003,103,800,'ACTIVE');
INSERT INTO ORDERS VALUES (1004,103,550,'INACTIVE');
INSERT INTO ORDERS VALUES (1005,105,700,'INACTIVE');


-- INSERT CODE TO DISH TABLE
INSERT INTO DISH VALUES (1001,1,'Chicken Burger','Burger','Nonveg','Spicy and chrunchy chicken tikki in soft bun with fresh lettuce and mustard sauce','Chef Special',150.0,4.5,'assets/chicken_burger.jpg');
INSERT INTO DISH VALUES (1002,1,'Chicken Cheese Burger','Burger','Nonveg','Spicy and chrunchy chicken tikki with soft bun with fresh lettuce and extra cheese','Chef Special',200.0,4.2,'assets/chicken_cheese_burger.jpg');
INSERT INTO DISH VALUES (1003,1,'Chicken Wings','chicken','Nonveg','Spicy and chrunchy chicken wings','Chef Special',550.0,4.6,'assets/chicken_wings.jpg');
INSERT INTO DISH VALUES (1004,3,'Margarita Pizza','Pizza','Veg','Plain and classic cheese pizza','Chef Special',250.0,4.4,'assets/margarita_pizza.jpg');
INSERT INTO DISH VALUES (1005,3,'Peppy Paneer Pizza','Pizza','Veg','Pizza topped with fresh cottage cheese, capsicum and red paprika','Chef Special',350.0,4.3,'assets/paneer_pizza.jpg');
INSERT INTO DISH VALUES (1006,2,'Chicken Burger','Burger','Nonveg','Spicy and chrunchy chicken tikki with soft bun with fresh lettuce and mustard sauce','Chef Special',150.0,4.5,'assets/chicken_burger.jpg');
INSERT INTO DISH VALUES (1007,2,'Chicken Cheese Burger','Burger','Nonveg','Spicy and chrunchy chicken tikki with soft bun with fresh lettuce and extra cheese','Chef Special',200.0,4.2,'assets/chicken_cheese_burger.jpg');
INSERT INTO DISH VALUES (1008,2,'Chicken Wings','chicken','Nonveg','Spicy and chrunchy chicken wings','Chef Special',550.0,4.6,'assets/chicken_wings.jpg');
INSERT INTO DISH VALUES (1009,3,'Farmhouse Pizza','Pizza','Veg','Pizza topped with fresh green olives, tomatoes and onions','Chef Special',400.0,3.8,'assets/farmhouse_pizza.jpg');
INSERT INTO DISH VALUES (1010,3,'Exotica Pizza','Pizza','Veg','Pizza topped with fresh cottage cheese, onions, capsicum, tomatoes and corn','Chef Special',410.0,4.3,'assets/exotica_pizza.jpg');
INSERT INTO DISH VALUES (1011,3,'Veg Loaded Pizza','Pizza','Veg','Pizza topped with fresh corn, black olives, zuchini, capsicum, yellow and red bell peppers','Chef Special',450.0,4.5,'assets/veg_loaded_pizza.jpg');
INSERT INTO DISH VALUES (1012,1,'French Fries','Fries','Veg','Classic crunchy and tasty french fries','Chef Special',200.0,4.5,'assets/french_fries.jpg');
INSERT INTO DISH VALUES (1013,2,'French Fries','Fries','Veg','Classic crunchy and tasty french fries','Chef Special',200.0,4.5,'assets/french_fries.jpg');
INSERT INTO DISH VALUES (1014,6,'Veg Biryani','Biryani','Veg','Flavoured rice with marinated vegetables garnished with fried onions and cashews','Chef Special',450.0,4.5,'assets/veg_biryani.jpg');
INSERT INTO DISH VALUES (1015,6,'Paneer Chilly','Starter','Veg','Fresh cottage cheese tossed in tasty hot and sweet sauce','Chef Special',350.0,4.5,'assets/paneer_chilly.jpg');
INSERT INTO DISH VALUES (1016,6,'Caesar Salad','Salad','Veg','Romaine lettuce and croutons dressed with lemon juice, worcestershire sauce, olive oil and parmesan cheese','Chef Special',550.0,4.5,'assets/caesar_salad.jpg');
-- INSERT INTO DISH VALUES (1017,7,'Tandoori Paneer','Tandoor','Veg','Fresh cottage cheese marinated in spices and cooked in the tandoor','Chef Special',450.0,4.5,'assets/tandoori_paneer.jpg');

-- INSERT CODE TO ORDER_ITEMS TABLE
INSERT INTO ORDER_ITEMS VALUES (1,1001,1001,2);
INSERT INTO ORDER_ITEMS VALUES (2,1004,1002,3);
INSERT INTO ORDER_ITEMS VALUES (3,1002,1003,4);
INSERT INTO ORDER_ITEMS VALUES (4,1003,1004,1);
INSERT INTO ORDER_ITEMS VALUES (5,1005,1005,2);

-- INSERT CODE TO DISH_RATING TABLE
INSERT INTO DISH_RATING VALUES (1,1002,101,4);
INSERT INTO DISH_RATING VALUES (2,1001,101,4);
INSERT INTO DISH_RATING VALUES (3,1002,102,4);
INSERT INTO DISH_RATING VALUES (4,1002,103,4);
INSERT INTO DISH_RATING VALUES (5,1003,101,4);
INSERT INTO DISH_RATING VALUES (6,1004,101,4);

-- INSERT CODE TO WALLET TABLE
INSERT INTO WALLET VALUES (1,101,700);
INSERT INTO WALLET VALUES (2,102,700);
INSERT INTO WALLET VALUES (3,103,700);
INSERT INTO WALLET VALUES (4,104,700);

-- INSERT CODE TO USER_LIKES TABLE
INSERT INTO USER_LIKES VALUES (1,'Nonveg',1001,101,1);
INSERT INTO USER_LIKES VALUES (2,'Nonveg',1002,103,2);
INSERT INTO USER_LIKES VALUES (3,'Veg',1004,101,3);
INSERT INTO USER_LIKES VALUES (4,'Veg',1005,105,3);

-- INSERT CODE TO TABLE_BOOKING TABLE
INSERT INTO TABLE_BOOKING VALUES (1,sysdate(),4,current_time(),1,101);
INSERT INTO TABLE_BOOKING VALUES (2,sysdate(),2,current_time(),2,103);
INSERT INTO TABLE_BOOKING VALUES (3,sysdate(),4,current_time(),3,105);

-- SELECT STATEMENTS FOR ALL TABLES
SELECT * FROM ROLES;
SELECT * FROM USERS;
SELECT * FROM RESTAURANT;
SELECT * FROM RESTAURANT_TRANSACTION;
SELECT * FROM COUPON;
SELECT * FROM USER_ADDRESS;
SELECT * FROM ORDERS;
SELECT * FROM ORDER_ITEMS;
SELECT * FROM DISH;
SELECT * FROM DISH_RATING;
SELECT * FROM WALLET;
SELECT * FROM USER_LIKES;
SELECT * FROM TABLE_BOOKING;


