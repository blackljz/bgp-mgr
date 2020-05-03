create schema bgp collate utf8_general_ci;

create table assessInfo
(
	id bigint unsigned auto_increment comment '评价Id'
		primary key,
	userContentId bigint null comment '发布内容Id(圈子Id,评价Id)',
	userId bigint null comment '发布人Id',
	gameId bigint null comment '游戏ID',
	replyUserId bigint null comment '被回复人ID',
	userName varchar(50) null comment '用户名',
	releaseTime datetime null comment '发布时间',
	content varchar(200) default '' null comment '内容',
	score varchar(50) default '' null comment '评分',
	replyUserName varchar(50) default '' null comment '被回复人名字',
	userContentTime datetime null comment '回复内容时间',
	type varchar(20) default '' null comment '类型：1：圈子。2：游戏.',
	replyType varchar(20) default '' null comment '回复类型:1:评价,2:回复',
	messageType varchar(20) default '' null comment '我的---消息通知,消息类型：1:点赞。2:回复.3:关注我了',
	likeNumber varchar(50) default '' null comment '点赞数量'
);

create table attentionInfo
(
	id bigint unsigned auto_increment comment '好友关系Id'
		primary key,
	userId bigint not null comment '用户ID',
	friendId bigint not null comment '关注朋友Id',
	friendType varchar(20) not null comment '类型：1：好友。2：关注',
	type varchar(20) null comment '关注状态：1已关注，2：互相关注'
);

create table doLikeInfo
(
	id bigint unsigned auto_increment comment '点赞Id'
		primary key,
	userId varchar(20) null comment '用户ID',
	gameId varchar(20) null comment '游戏Id',
	friendsCircleId varchar(20) null comment '圈子ID',
	userContentId varchar(20) null comment '朋友Id',
	type varchar(20) not null comment '点赞状态：1：点赞，2：取消点赞'
);

create table ex_record
(
	id bigint unsigned auto_increment comment '战绩扩展表,1对多'
		primary key,
	userId bigint null comment '用户ID',
	recordId bigint not null comment '战绩Id',
	friendName varchar(20) null comment '好友名称',
	score varchar(20) null comment '得分',
	gameWin varchar(20) null comment '胜负,1:胜利，2：失败',
	createdDate datetime null
);

create table fileInfo
(
	id bigint auto_increment comment '文件ID'
		primary key,
	userId bigint null comment '用户ID',
	friendsCircleId bigint null comment '圈子Id',
	gameId bigint null comment '游戏ID',
	recordId bigint null comment '战绩Id',
	fileName varchar(200) null comment '文件名',
	fileAddress varchar(500) null comment '文件地址',
	type varchar(20) not null comment '图片类型1:圈子,2:游戏,3,战绩,4,用户头像',
	fileType varchar(20) not null comment '文件类型：1,图片,2:视频,3:pdf',
	fileUseType varchar(20) null comment '文件使用类型：1是icon图,2是详情图,3是游戏墙,4是介绍图（多张）'
);

create table friend
(
	id bigint auto_increment comment '好友列表'
		primary key,
	userId varchar(20) not null comment '用户Id',
	friendId varchar(20) not null comment '朋友Id',
	type varchar(20) not null comment '好友状态：1：好友，2：关注，3：粉丝',
	modified_date datetime not null
);

create table friendsCircleInfo
(
	id bigint unsigned auto_increment comment '圈子列表(朋友圈、发布)ID'
		primary key,
	userId bigint null comment '用户Id',
	content varchar(200) charset utf8mb4 default '' null comment '用户发布内容',
	record varchar(50) default '' null comment '战绩',
	likeNumber varchar(50) default '' null comment '点赞数量',
	ranking varchar(50) default '' null comment '排名',
	score varchar(50) default '' null comment '得分',
	gameWin varchar(50) default '' null comment '游戏胜负,1:胜利.2:失败',
	gameRating varchar(50) default '' null comment '游戏评分',
	type varchar(50) default '' not null comment '类型，1:圈子，2:战绩,3:评分,',
	created_date datetime not null
);

create table gameInfo
(
	id bigint unsigned auto_increment comment '游戏信息Id'
		primary key,
	relatedGameId varchar(100) null comment '相关游戏ID',
	gameName varchar(50) null comment '游戏名称',
	gameEnName varchar(100) null comment '游戏英文名称',
	type varchar(50) null comment '游戏类型',
	label varchar(50) null comment '游戏标签',
	gameImage varchar(100) null comment '游戏图片',
	gameIntroduction varchar(1000) null comment '游戏简介',
	gameEnIntroduction varchar(1000) null comment '游戏英文简介',
	category varchar(100) null comment '游戏类别',
	mechanism varchar(100) null comment '游戏机制',
	weight varchar(20) null comment '游戏重度',
	duration varchar(20) null comment '游戏时长',
	age varchar(20) null comment '建议年龄',
	playerNumMin int default '0' null comment '最少游戏人数',
	playerNumMax int default '0' null comment '最多游戏人数',
	playerNumSuggested int default '0' null comment '建议游戏人数',
	isEntity int null comment '实体或电子：1实体；2电子；',
	isDlc int null comment '是否扩展：1本体；2扩展；',
	designer varchar(50) null comment '设计师',
	artist varchar(50) null comment '美工',
	publisher varchar(50) null comment '出版商',
	publishYear varchar(20) null comment '出版年份',
	hasChinese int default '1' null comment '是否有中文版',
	chinesePublisher varchar(50) null comment '中文出版商',
	language varchar(20) null comment '原始语言',
	languageDependence varchar(20) null comment '语言依赖',
	rating varchar(20) null comment '游戏评分',
	bggRank varchar(20) null comment 'BGG排名',
	bggScore varchar(20) null comment 'BGG评分',
	bggLink varchar(100) null comment 'BGG链接',
	sleeve varchar(200) null comment '牌套（高|宽|数量）',
	status int default '-1' not null comment '状态：1启用；0制作中；-1停用；',
	createdBy varchar(20) not null comment '创建人',
	createdDate datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	modifiedBy varchar(20) not null comment '修改人',
	modifiedDate datetime default CURRENT_TIMESTAMP not null comment '修改时间'
)
comment '游戏信息表';

create table messageInfo
(
	id bigint auto_increment comment '消息通知'
		primary key,
	userId varchar(20) not null comment '用户Id',
	userName varchar(50) not null comment '用户名',
	type varchar(20) not null comment '消息状态:1:点赞、2:回复、3:关注我了',
	status varchar(20) not null comment '状态,1:未查看，2:已查看',
	content varchar(250) charset utf8mb4 null comment '内容',
	createDate datetime null comment '创建时间',
	modifiedDate datetime null comment '修改时间'
);

create table recordInfo
(
	id bigint unsigned auto_increment comment '战绩Id'
		primary key,
	gameId bigint not null comment '游戏Id',
	userId bigint not null comment '用户ID',
	gameName varchar(200) null comment '游戏名称',
	gameTeam varchar(200) null comment '队伍',
	role varchar(200) null comment '角色',
	position varchar(200) null comment '位置',
	duration varchar(200) null comment '游戏时长',
	content varchar(500) charset utf8mb4 null comment '内容',
	result varchar(200) null comment '结果,1:胜负,2:得分',
	ranking varchar(200) null comment '排名',
	score varchar(200) null comment '得分',
	type varchar(20) null comment '类型，1:正常,2:战绩,3:评分',
	gameWin varchar(20) null comment '游戏胜负,1：胜利，2：失败',
	gameType varchar(20) null comment '游戏类型.1：得分数。有排名，2：只有胜利、失败',
	createdDate datetime not null
);

create table sys_user
(
	id int auto_increment comment 'id'
		primary key,
	pin varchar(20) not null comment '用户名',
	pwd varchar(256) not null comment '密码',
	constraint sys_user_pin_uindex
		unique (pin)
)
comment '管理端用户表';

create table userInfo
(
	userId bigint unsigned auto_increment comment '用户Id'
		primary key,
	unionId varchar(100) null comment '微信小程序获取用户unionId',
	openId varchar(100) null comment '微信小程序获取用户openid',
	userName varchar(50) null comment '用户名',
	userImage varchar(200) null comment '用户头像',
	age varchar(20) null comment '年龄',
	sex varchar(20) null comment '性别',
	area varchar(100) null comment '地区',
	sign varchar(200) null comment '签名',
	modifiedId varchar(20) null comment '修改人',
	modifiedDate datetime null comment '修改时间',
	createDate datetime null comment '创建时间'
);

create table userWish
(
	id bigint not null comment '心愿单'
		primary key,
	userId bigint not null comment '用户Id',
	gameId bigint not null comment '游戏ID',
	type varchar(20) not null comment '1：加入心愿，2：取消心愿，3：添加桌游，4：取消添加桌游',
	createdDate datetime not null comment '创建时间'
);

