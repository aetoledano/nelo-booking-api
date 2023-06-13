create table if not exists restaurants
(
    id
    uuid
    not
    null,
    name
    varchar
(
    255
) unique,
    two_seat_table_capacity int,
    four_seat_table_capacity int,
    six_seat_table_capacity int,
    primary key
(
    id
)
    );

create table if not exists dietary_groups
(
    id
    bigint
    not
    null,
    name
    varchar
    unique,
    primary
    key
(
    id
)
    );

create table if not exists restaurant_dietary_group
(
    restaurant_id
    uuid
    not
    null,
    dietary_group_id
    bigint
    not
    null,
    primary
    key
(
    restaurant_id,
    dietary_group_id
)
    );

create table if not exists clients
(
    id
    uuid
    not
    null,
    name
    varchar
    unique,
    primary
    key
(
    id
)
    );

create table if not exists client_dietary_group
(
    client_id
    uuid
    not
    null,
    dietary_group_id
    bigint
    not
    null,
    primary
    key
(
    client_id,
    dietary_group_id
)
    );

create sequence if not exists dietary_groups_seq start with 1 increment by 50;

-- Adds fk constraint
ALTER TABLE restaurant_dietary_group
DROP
CONSTRAINT IF EXISTS FK_RDG_DIETARY_GROUPS_REFERENCE;

alter table if exists restaurant_dietary_group
    add constraint FK_RDG_DIETARY_GROUPS_REFERENCE foreign key (dietary_group_id) references dietary_groups;

-- Adds fk constraint
ALTER TABLE restaurant_dietary_group
DROP
CONSTRAINT IF EXISTS FK_RDG_RESTAURANTS_REFERENCE;

alter table if exists restaurant_dietary_group
    add constraint FK_RDG_RESTAURANTS_REFERENCE foreign key (restaurant_id) references restaurants;

-- Adds fk constraint
ALTER TABLE client_dietary_group
DROP
CONSTRAINT IF EXISTS FK_CDG_CLIENTS_REFERENCE;

alter table if exists client_dietary_group
    add constraint FK_CDG_CLIENTS_REFERENCE foreign key (client_id) references clients;

-- Adds fk constraint
ALTER TABLE client_dietary_group
DROP
CONSTRAINT IF EXISTS FK_CDG_DIETARY_GROUPS_REFERENCE;

alter table if exists client_dietary_group
    add constraint FK_CDG_DIETARY_GROUPS_REFERENCE foreign key (dietary_group_id) references dietary_groups;

-- Adds reservations
create table if not exists reservations
(
    id            uuid    not null,
    date          timestamp(6),
    interval      int4range,
    table_size    integer not null,
    restaurant_id uuid    not null,
    primary key (id)
);

ALTER TABLE reservations
DROP
CONSTRAINT IF EXISTS FK_RESERVATION_RESTAURANT;

alter table if exists reservations
    add constraint FK_RESERVATION_RESTAURANT foreign key (restaurant_id) references restaurants;

CREATE EXTENSION btree_gist;
create table if not exists client_reservation_data
(
    id             serial,
    date           timestamp(6),
    interval       int4range,
    reservation_id uuid not null,
    client_id      uuid not null,
    EXCLUDE USING GIST (client_id WITH =, date WITH =, interval WITH &&),
    primary key (id)
);

ALTER TABLE client_reservation_data
DROP
CONSTRAINT IF EXISTS FK_RESERVATION;

alter table if exists client_reservation_data
    add constraint FK_RESERVATION foreign key (reservation_id) references reservations;

ALTER TABLE client_reservation_data
DROP
CONSTRAINT IF EXISTS FK_CLIENTS;

alter table if exists client_reservation_data
    add constraint FK_CLIENTS foreign key (client_id) references clients;
