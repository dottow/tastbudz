use tastbudz;

create table tstbdz_coordinates (
    id binary(16) not null, 
    date_created timestamp not null, 
    date_updated timestamp, 
    version integer not null, 
    latitude double not null, 
    longitude double not null, 
    location_id binary(16) not null, 
primary key (id));


create table tstbdz_cuisine (
    id binary(16) not null, 
    date_created timestamp not null, 
    date_updated timestamp, 
    version integer not null, 
    name varchar(255) not null, 
primary key (id));


create table tstbdz_dish (
    id binary(16) not null, 
    restaurant_id binary(16) not null, 
    date_created timestamp not null, 
    date_updated timestamp, 
    version integer not null, 
    name varchar(255) not null, 
    calories double, 
    amount numeric(19,2), 
    currency varchar(3), 
primary key (id));


create table tstbdz_drink (
    id binary(16) not null, 
    restaurant_id binary(16) not null, 
    date_created timestamp not null, 
    date_updated timestamp, 
    version integer not null, 
    name varchar(255) not null, 
    calories double, 
    amount numeric(19,2), 
    currency varchar(3), 
primary key (id));


create table tstbdz_location (
    restaurant_id binary(16) not null, 
    city varchar(255) not null, 
    country_code varchar(2) not null, 
    cross_street varchar(255), 
    postal_code varchar(16), 
    state_code varchar(16) not null, 
primary key (restaurant_id));


create table tstbdz_restaurant (
    id binary(16) not null, 
    date_created timestamp not null, 
    date_updated timestamp, 
    version integer not null, 
    name varchar(255) not null, 
    phone varchar(255), 
    url varchar(255), 
primary key (id));


create table Location_streetAddressList (
    Location_restaurant_id binary(16) not null, 
    street_address varchar(255) not null
);


create table tstbdz_restaurant_tstbdz_cuisine (
    tstbdz_restaurant_id binary(16) not null, 
    cuisines_id binary(16) not null, 
primary key (tstbdz_restaurant_id, cuisines_id));


alter table Location_streetAddressList add constraint FK_ADDRESS_LOCATION foreign key (Location_restaurant_id) references tstbdz_location;

alter table tstbdz_coordinates add constraint FK_COORDINATES_LOCATION foreign key (location_id) references tstbdz_location;

alter table tstbdz_dish add constraint FK_DISH_RESTAURANT foreign key (restaurant_id) references tstbdz_restaurant;

alter table tstbdz_drink add constraint FK_DRINK_RESTAURANT foreign key (restaurant_id) references tstbdz_restaurant;

alter table tstbdz_restaurant_tstbdz_cuisine add constraint FK_CUISINE_RESTAURANT foreign key (tstbdz_restaurant_id) references tstbdz_restaurant;

alter table tstbdz_restaurant_tstbdz_cuisine add constraint FK_RESTAURANT_CUSINE foreign key (cuisines_id) references tstbdz_cuisine;

