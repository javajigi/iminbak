    drop table if exists attachment;

    drop table if exists answer_content_holder;
    
    drop table if exists answer;

    drop table if exists question_content_holder;

    drop table if exists question;
    
    drop table if exists social_user;
    
    create table social_user (
        id bigint not null auto_increment,
        access_token varchar(255) not null,
        create_date datetime,
        display_name varchar(255),
        expire_time bigint,
        image_url varchar(255),
        profile_url varchar(255),
        provider_id varchar(255) not null,
        provider_user_id varchar(255),
        rank integer not null,
        refresh_token varchar(255),
        secret varchar(255),
        user_id varchar(255),
        primary key (id),
        unique (user_id, provider_id, rank),
        unique (user_id, provider_id, provider_user_id)
    ) ENGINE=InnoDB;
    
    create table answer (
        answer_id bigint not null auto_increment,
        created_date datetime not null,
        sum_like integer DEFAULT 0,
        updated_date datetime not null,
        question bigint,
        writer bigint,
        primary key (answer_id)
    ) ENGINE=InnoDB;

    create table answer_content_holder (
        answer_id bigint not null unique,
        contents longtext not null
    ) ENGINE=InnoDB;

    create table attachment (
        id varchar(255) not null,
        created_date datetime not null,
        extension varchar(8) not null,
        original_filename varchar(255) not null,
        uploader bigint,
        primary key (id)
    ) ENGINE=InnoDB;

    create table question (
        question_id bigint not null auto_increment,
        answer_count integer not null,
        created_date datetime not null,
        deleted bit not null,
        show_count integer not null,
        title varchar(100) not null,
        updated_date datetime not null,
        writer bigint,
        primary key (question_id)
    ) ENGINE=InnoDB;

    create table question_content_holder (
        question_id bigint not null unique,
        contents longtext not null
    ) ENGINE=InnoDB;

    alter table answer 
        add index fk_answer_writer (writer), 
        add constraint fk_answer_writer 
        foreign key (writer) 
        references social_user (id);

    alter table answer 
        add index fk_answer_parent_id (question), 
        add constraint fk_answer_parent_id 
        foreign key (question) 
        references question (question_id);

    alter table answer_content_holder 
        add index fk_answer_content_holder_answer_id (answer_id), 
        add constraint fk_answer_content_holder_answer_id 
        foreign key (answer_id) 
        references answer (answer_id);

    alter table attachment 
        add index fk_attachment_writer (uploader), 
        add constraint fk_attachment_writer 
        foreign key (uploader) 
        references social_user (id);

    alter table question 
        add index fk_question_writer (writer), 
        add constraint fk_question_writer 
        foreign key (writer) 
        references social_user (id);

    alter table question_content_holder 
        add index fk_question_content_holder_question_id (question_id), 
        add constraint fk_question_content_holder_question_id 
        foreign key (question_id) 
        references question (question_id);