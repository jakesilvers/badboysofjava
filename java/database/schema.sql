start transaction;

DROP TABLE IF EXISTS users, course, league, league_player, match, match_player, scoretable;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

create table course(
	course_id serial primary key,
	course_name varchar(50) not null,
	location_lat varchar(50) not null,
	location_long varchar(50) not null,
	address varchar(50) not null,
	city varchar(50) not null,
	state varchar(50) not null,
	country varchar(50) not null
);

create table league(
	league_id serial primary key,
	league_name varchar(50) unique not null,
	description varchar(200) not null,
	course_id int,
	admin_id int,
	
	constraint fk_home_course_id foreign key (course_id) references course (course_id),
	constraint fk_league_admin_id foreign key (admin_id) references users (user_id)
);

create table league_player(
	league_id int,
	player_id int,
	
	constraint league_id foreign key (league_id) references league (league_id),
	constraint player_id foreign key (player_id) references users (user_id)
);

create table match(
	match_id serial primary key,
	league_id int,
	start_time timestamp not null,
	is_completed boolean not null,
	
	constraint league_id foreign key (league_id) references league (league_id)
);

create table match_player(
	match_id int,
	player_id int,
	
	constraint match_id foreign key (match_id) references match (match_id),
	constraint player_id foreign key (player_id) references users (user_id)
);

create table score_card(
	scoretable_id serial primary key,
	match_id int,
	player_id int,
	score int not null,
	
	constraint match_id foreign key (match_id) references match (match_id),
	constraint player_id foreign key (player_id) references users (user_id)
);

commit;