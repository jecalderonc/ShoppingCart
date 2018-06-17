create database shopppingcart;

create table shopppingcart.cart (
	 id int not null auto_increment,
     description varchar(200) null,
    PRIMARY KEY (ID)
);

create table shopppingcart.itemcart (
	 id int not null auto_increment,
     cartid int not null,
	 itemid int not null,
	 itemtitle varchar(200),
	 itemprice double,
	 quantity int,
    PRIMARY KEY (ID),
	FOREIGN KEY (cartid) REFERENCES cart(id)
);