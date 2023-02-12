drop table if exists cafe CASCADE;
create table cafe (
    cafe_id bigint generated by default as identity,
    cafe_name varchar(255),
    primary key (cafe_id)
);

drop table if exists member CASCADE;
create table member (
    member_id bigint generated by default as identity,
    member_name varchar(255),
    primary key (member_id)
);

drop table if exists party CASCADE;
create table party (
    party_id bigint generated by default as identity,
    party_name varchar(255),
    primary key (party_id)
);

drop table if exists cafe_order CASCADE;
create table cafe_order (
    order_id bigint generated by default as identity,
    order_name varchar(255),
    order_date TIMESTAMP,
    customer_id bigint,
    primary key (order_id)
);