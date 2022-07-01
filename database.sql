CREATE TABLE Admin
( 
  a_name VARCHAR(20), 
  a_password VARCHAR(20), 
  PRIMARY KEY (a_name)
  
);

CREATE TABLE client
(
  c_name VARCHAR(20), 
  c_password VARCHAR(20), 
  phone int ,
  address varchar(50),
  gender ENUM('male', 'female') NOT NULL,
  cash INT ,
  PRIMARY KEY (c_name)
  
);
------------------------------------------------------------------------
CREATE TABLE category
(
  cat_name VARCHAR(20) NOT NULL,
  description VARCHAR(50) NOT NULL,
  PRIMARY KEY (cat_name)
);


CREATE TABLE Product
(
  quantity INT NOT NULL,
  price FLOAT NOT NULL,
  pname VARCHAR(20) NOT NULL,
  cat_name VARCHAR(20) ,
  PRIMARY KEY (pname),
  FOREIGN KEY (cat_name) REFERENCES category (cat_name) on delete restrict on update cascade
);

------------------------------------------------------------------------

CREATE TABLE cart
(
  cart_id INT NOT NULL auto_increment,
  total_price FLOAT NOT NULL,
  no_of_products INT NOT NULL,
  c_name VARCHAR(20), 
  pname VARCHAR(20) ,
  PRIMARY KEY (cart_id),
  FOREIGN KEY (c_name) REFERENCES client (c_name) on delete restrict on update cascade,
  FOREIGN KEY (pname) REFERENCES product(pname) on delete restrict on update cascade
);

CREATE TABLE payment
( pay_id INT NOT NULL,
  deposite INT ,
  c_name VARCHAR(20), 
  PRIMARY KEY (pay_id),
  FOREIGN KEY (c_name) REFERENCES client(c_name) on delete restrict on update cascade

);


CREATE TABLE orders (
  o_id INT NOT NULL auto_increment,
  price FLOAT NOT NULL,
  c_name VARCHAR(20), 
  cart_id INT ,
  PRIMARY KEY (o_id),
  FOREIGN KEY (c_name) REFERENCES client(c_name) on delete restrict on update cascade,
  FOREIGN KEY (cart_id) REFERENCES cart(cart_id)on delete restrict on update cascade
);
