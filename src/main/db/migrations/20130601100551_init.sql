    drop table if exists answer;

    drop table if exists answer_content_holder;

    drop table if exists attachment;

    drop table if exists blog;

    drop table if exists blog_content_holder;

    drop table if exists board;

    drop table if exists board_content_holder;

    create table attachment (
        id varchar(255) not null,
        created_date datetime not null,
        extension varchar(8) not null,
        original_filename varchar(255) not null,
        primary key (id)
    ) ENGINE=InnoDB;

    create table blog (
        blog_id bigint not null auto_increment,
        created_date datetime not null,
        title varchar(100) not null,
        primary key (blog_id)
    ) ENGINE=InnoDB;

    create table blog_content_holder (
        blog_id bigint not null unique,
        contents longtext not null
    ) ENGINE=InnoDB;

    create table board (
        board_id bigint not null auto_increment,
        answer_count integer not null,
        board_type enum('free','review'),
        created_date datetime not null,
        deleted bit not null,
        name varchar(20) not null,
        password varchar(100) not null,
        show_count integer not null,
        title varchar(100) not null,
        updated_date datetime not null,
        primary key (board_id)
    ) ENGINE=InnoDB;

    create table board_content_holder (
        board_id bigint not null unique,
        contents longtext not null
    ) ENGINE=InnoDB;
    
    create table answer (
        answer_id bigint not null auto_increment,
        created_date datetime not null,
        name varchar(20) not null,
        password varchar(100) not null,
        updated_date datetime not null,
        board bigint,
        primary key (answer_id)
    ) ENGINE=InnoDB;

    create table answer_content_holder (
        answer_id bigint not null unique,
        contents longtext not null
    ) ENGINE=InnoDB;

    alter table answer 
        add index fk_answer_parent_id (board), 
        add constraint fk_answer_parent_id 
        foreign key (board) 
        references board (board_id);

    alter table answer_content_holder 
        add index fk_answer_content_holder_answer_id (answer_id), 
        add constraint fk_answer_content_holder_answer_id 
        foreign key (answer_id) 
        references answer (answer_id);

    alter table blog_content_holder 
        add index fk_blog_content_holder_blog_id (blog_id), 
        add constraint fk_blog_content_holder_blog_id 
        foreign key (blog_id) 
        references blog (blog_id);

    alter table board_content_holder 
        add index fk_board_content_holder_board_id (board_id), 
        add constraint fk_board_content_holder_board_id 
        foreign key (board_id) 
        references board (board_id);
