create table account (
                         id bigint auto_increment primary key,
                         account_num varchar(255),
                         account_password varchar(255),
                         account_holder varchar(255),
                         account_holder_info varchar(255),
                         bank_name varchar(255),
                         is_default boolean not null,
                         is_verified boolean not null,
                         member_id bigint not null
);
create table member (
                        id bigint auto_increment primary key,
                        name varchar(255)
);
alter table account
       add constraint FK_ACCOUNT_MEMBER
       foreign key (member_id)
       references member (id)