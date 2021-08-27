-- 초기화 스크립트, 매번 db구동시 변하지 않는 값을 등록

-- 사용자
   INSERT INTO USER(USER_ID, REAL_NAME, SEX, BIRTH_DAY, MOBILE_PHONE_NUMBER, EMAIL, PASSWORD, NICK_NAME, SIGN_UP_DT) VALUES(1, '이세인', false, '2000-02-11', '01094069717', 'test1@gmail.com', 'dltpdls00*', '23in', '2021-08-01T15:41:20');
   INSERT INTO USER(USER_ID, REAL_NAME, SEX, BIRTH_DAY, MOBILE_PHONE_NUMBER, EMAIL, PASSWORD, NICK_NAME, SIGN_UP_DT) VALUES(2, '마시', true, '2000-02-11', '01011112222', 'test2@gmail.com', 'dltpdls00*', '마로', '2021-08-01T15:41:20');


--사용자 해시태그
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(1, 1, 1, '공기 좋은');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(2, 1, 2, '분위기 있는');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(3, 1, 3, '깔끔한');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(4, 2, 4, '감성적인');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(5, 2, 5, '이색적인');
    INSERT INTO MY_HASH_TAG(MY_HASH_TAG_LIST_ID, USER_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(6, 2, 6, '인생샷');

--사용자 찜(게시물)
--    INSERT INTO MY_WISH_POST(MY_WISH_POST_ID, USER_ID, POST_ID) VALUES();


-- 해시태그
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(1, '공기 좋은');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(2, '분위기 있는');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(3, '깔끔한');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(4, '감성적인');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(5, '이색적인');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(6, '인생샷');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(7, '전문적인');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(8, '캠핑');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(9, '차박');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(10, '뚜벅이');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(11, '드라이브');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(12, '자전거');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(13, '한적한');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(14, '근교');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(15, '도심 속');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(16, '연인');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(17, '가족');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(18, '친구');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(19, '혼자');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(20, '가성비');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(21, '소확행');
    INSERT INTO HASH_TAG (HASH_TAG_ID, HASH_TAG_NAME) VALUES(22, '럭셔리한');

--관측지
    INSERT INTO OBSERVATION (OBSERVATION_ID, OBSERVATION_NAME, LINK, LATITUDE, LONGITUDE, ADDRESS, PHONE_NUMBER, OPERATING_HOUR, PARKING, OBSERVE_TYPE, OUTLINE, GUIDE, CLOSED_DAY, LIGHT, NATURE ) VALUES (1, '천문대1', 'https://www.yao.or.kr:451/', 37.54892296550104, 127.00557633, '강원 영월군 영월읍 천문대길 397', '010-1111-1111', '13:00~16:00', '주차는 여기에', '천문대', '개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 ', '이용안내', '월요일',36.66, false );
    INSERT INTO OBSERVATION (OBSERVATION_ID, OBSERVATION_NAME, LINK, LATITUDE, LONGITUDE, ADDRESS, PHONE_NUMBER, OPERATING_HOUR, PARKING, OBSERVE_TYPE, OUTLINE, GUIDE, CLOSED_DAY, LIGHT, NATURE ) VALUES (2, '천문대2', 'https://www.yao.or.kr:451/', 37.54892296550104, 127.00557633, '강원 영월군 영월읍 천문대길 397', '010-1111-1111', '13:00~16:00', '주차는 여기에', '천문대', '개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 개애요가 너무길어 ', '이용안내', '월요일',36.66, true );

--관측지 해쉬태그
    INSERT INTO OBSERVE_HASH_TAG (OBSERVE_HASH_TAG_LIST_ID, OBSERVATION_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(1,1,1,'공기좋은');
    INSERT INTO OBSERVE_HASH_TAG (OBSERVE_HASH_TAG_LIST_ID, OBSERVATION_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(2,1,2,'분위기 있는');
    INSERT INTO OBSERVE_HASH_TAG (OBSERVE_HASH_TAG_LIST_ID, OBSERVATION_ID, HASH_TAG_ID, HASH_TAG_NAME) VALUES(3,1,3,'깔끔한');

--관측지 이미지

    INSERT INTO OBSERVE_IMAGE (OBSERVE_IMAGE_LIST_ID, OBSERVATION_ID, IMAGE, IMAGE_SOURCE) VALUES (1, 1, 'https://wallpapercave.com/wp/YVnW7ve.jpg');
    INSERT INTO OBSERVE_IMAGE (OBSERVE_IMAGE_LIST_ID, OBSERVATION_ID, IMAGE, IMAGE_SOURCE) VALUES (2, 1, 'https://www.cnet.com/a/img/Aosra4Cz8-Kvz3a5mYrUCVaf5VQ=/1200x630/2020/05/15/101c537d-d2e5-4ee5-ae6f-21f18eb3e3ad/gettyimages-1210618112.jpg', 'www.source.com');
    INSERT INTO OBSERVE_IMAGE (OBSERVE_IMAGE_LIST_ID, OBSERVATION_ID, IMAGE, IMAGE_SOURCE) VALUES (3, 1, 'https://img.freepik.com/free-photo/vibrant-night-sky-with-stars-nebula-galaxy_146671-19188.jpg?size=626&ext=jpg&ga=GA1.2.1933447203.1629590400', 'www.source.com');
    INSERT INTO OBSERVE_IMAGE (OBSERVE_IMAGE_LIST_ID, OBSERVATION_ID, IMAGE, IMAGE_SOURCE) VALUES (4, 2, 'https://wallpapercave.com/wp/YVnW7ve.jpg');
    INSERT INTO OBSERVE_IMAGE (OBSERVE_IMAGE_LIST_ID, OBSERVATION_ID, IMAGE, IMAGE_SOURCE) VALUES (5, 2, 'https://www.cnet.com/a/img/Aosra4Cz8-Kvz3a5mYrUCVaf5VQ=/1200x630/2020/05/15/101c537d-d2e5-4ee5-ae6f-21f18eb3e3ad/gettyimages-1210618112.jpg', 'www.source.com');
    INSERT INTO OBSERVE_IMAGE (OBSERVE_IMAGE_LIST_ID, OBSERVATION_ID, IMAGE, IMAGE_SOURCE) VALUES (6, 2, 'https://img.freepik.com/free-photo/vibrant-night-sky-with-stars-nebula-galaxy_146671-19188.jpg?size=626&ext=jpg&ga=GA1.2.1933447203.1629590400', 'www.source.com');
    INSERT INTO OBSERVE_IMAGE (OBSERVE_IMAGE_LIST_ID, OBSERVATION_ID, IMAGE, IMAGE_SOURCE) VALUES (7, 2, 'https://images.unsplash.com/photo-1444080748397-f442aa95c3e5?ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTN8fG5pZ2h0JTIwc2t5fGVufDB8fDB8fA%3D%3D&ixlib=rb-1.2.1&w=1000&q=80', 'www.source.com');

--관측지 입장료
    INSERT INTO OBSERVE_FEE (OBSERVE_FEE_LIST_ID, OBSERVATION_ID, FEE_NAME, ENTRANCE_FEE) VALUES (1, 1, '청소년', '6000원');
    INSERT INTO OBSERVE_FEE (OBSERVE_FEE_LIST_ID, OBSERVATION_ID, FEE_NAME, ENTRANCE_FEE) VALUES (2, 1, '어른', '6000원');
    INSERT INTO OBSERVE_FEE (OBSERVE_FEE_LIST_ID, OBSERVATION_ID, FEE_NAME, ENTRANCE_FEE) VALUES (3, 1, '단체', '홈페이지 참고');

-- 별자리
    INSERT INTO CONSTELLATION (CONST_ID, CONST_BEST_MONTH, CONST_IMAGE, CONST_NAME, CONST_STORY, SPRING_CONST_MTD, SUMMER_CONST_MTD, FALL_CONST_MTD, WINTER_CONST_MTD, START_DATE, END_DATE, CONST_FEATURE ) VALUES (0, '1,3월에 잘보임', 'https://wallpapercave.com/wp/YVnW7ve.jpg', '물병자리', '물병자리 설화', '물병자리 봄 관측법', '물병자리 여름 관측법', '물병자리 가을 관측법', '물병자리 겨울 관측법',  '2021-08-25', '2021-08-26', 'https://wallpapercave.com/wp/YVnW7ve.jpg');
    INSERT INTO CONSTELLATION (CONST_ID, CONST_BEST_MONTH, CONST_IMAGE, CONST_NAME, CONST_PERSONALITY, CONST_STORY, SPRING_CONST_MTD, SUMMER_CONST_MTD, FALL_CONST_MTD, WINTER_CONST_MTD, CONST_PERIOD, START_DATE, END_DATE ) VALUES (1, '1,3월에 잘보임', 'https://cdn.pixabay.com/photo/2015/02/17/08/25/horoscope-639127_960_720.jpg', '물고기자리', '물고기자리 성격', '물고기자리 설화', '물고기자리 봄 관측법', '물고기자리 여름 관측법', '물고기자리 가을 관측법', '물고기자리 겨울 관측법', '02.22~08.15', '2021-08-16', '2021-08-17');
    INSERT INTO CONSTELLATION (CONST_ID, CONST_BEST_MONTH, CONST_IMAGE, CONST_NAME, CONST_PERSONALITY, CONST_STORY, SPRING_CONST_MTD, SUMMER_CONST_MTD, FALL_CONST_MTD, WINTER_CONST_MTD, CONST_PERIOD, START_DATE, END_DATE ) VALUES (2, '1,3월에 잘보임', 'https://cdn.pixabay.com/photo/2015/02/17/08/25/horoscope-639127_960_720.jpg', '양자리', '양자리 성격', '양자리 설화', '양자리 봄 관측법', '양자리 여름 관측법', '양자리 가을 관측법', '양자리 겨울 관측법', '03.22~08.15', '2021-08-18', '2021-08-30');
    INSERT INTO CONSTELLATION (CONST_ID, CONST_BEST_MONTH, CONST_IMAGE, CONST_NAME, CONST_PERSONALITY, CONST_STORY, SPRING_CONST_MTD, SUMMER_CONST_MTD, FALL_CONST_MTD, WINTER_CONST_MTD, CONST_PERIOD, START_DATE, END_DATE ) VALUES (3, '1,3월에 잘보임', 'https://cdn.pixabay.com/photo/2015/02/17/08/25/horoscope-639127_960_720.jpg', '황소자리', '황소자리 성격', '황소자리 설화', '황소자리 봄 관측법', '황소자리 여름 관측법', '황소자리 가을 관측법', '황소자리 겨울 관측법', '04.22~08.15', '2021-08-21', '2021-08-30');
    INSERT INTO CONSTELLATION (CONST_ID, CONST_BEST_MONTH, CONST_IMAGE, CONST_NAME, CONST_PERSONALITY, CONST_STORY, SPRING_CONST_MTD, SUMMER_CONST_MTD, FALL_CONST_MTD, WINTER_CONST_MTD, CONST_PERIOD, START_DATE, END_DATE ) VALUES (4, '1,3월에 잘보임', 'https://cdn.pixabay.com/photo/2015/02/17/08/25/horoscope-639127_960_720.jpg', '쌍둥이자리', '쌍둥이자리 성격', '쌍둥이자리 설화', '쌍둥이자리 봄 관측법', '쌍둥이자리 여름 관측법', '쌍둥이자리 가을 관측법', '물쌍둥이자리 겨울 관측법', '05.22~08.15', '2021-08-13', '2021-08-14');











