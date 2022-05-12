create table user_profile
(
    id          serial
        constraint user_profile_pk
            primary key,
    username    varchar(255)             not null,
    password    varchar(500)             not null,
    email       varchar(50),
    description varchar(500),
    first_name varchar(255) not null ,
    last_name varchar(255) not null ,
    created_at  timestamp default now(),
    updated_at  timestamp default now(),
    is_deleted  boolean    default false not null
);

create unique index user_profile_id_uindex
    on user_profile (id);


create table message
(
    id          serial
        constraint message_pk
            primary key,
    sender_id   int                        not null
        constraint message_user_profile_sender_id___fk
            references user_profile,
    receiver_id int                        not null
        constraint message_user_profile_receiver_id___fk
            references user_profile,
    type        varchar(50) default 'text' not null,
    content     varchar(1000),
    created_at  timestamp   default now()  not null,
    updated_at  timestamp   default now(),
    is_deleted  boolean     default false  not null
);


create table friend
(
    id              serial
        constraint friend_pk
            primary key,
    sender_id       int                            not null
        constraint friend_sender_id___fk
            references user_profile,
    receiver_id     int                            not null
        constraint friend_receiver_id___fk
            references user_profile,
    status          varchar(255) default 'pending' not null,
    last_message_id int,
    created_at      timestamp    default now(),
    updated_at      timestamp    default now()
);