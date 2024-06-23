create user c##myclasss  identified by c##myclasss;
grant connect, resource, dba to c##myclasss;
DROP TABLE MEMBER;
DROP TABLE NOTICE;
DROP TABLE BOARD;
DROP TABLE REPLY;

DROP SEQUENCE SEQ_NNO;      -- �������׹�ȣ �߻���ų ������
DROP SEQUENCE SEQ_BNO;      -- �Խ��ǹ�ȣ �߻���ų ������
DROP SEQUENCE SEQ_RNO;      -- ��۹�ȣ �߻���ų ������


--------------------------------------------------
--------------     MEMBER ����	------------------	
--------------------------------------------------

CREATE TABLE MEMBER (            
  USER_ID VARCHAR2(30) PRIMARY KEY,   
  USER_PWD VARCHAR2(100) NOT NULL,  
  USER_NAME VARCHAR2(15) NOT NULL,            
  EMAIL VARCHAR2(100),   
  GENDER VARCHAR2(1) CHECK (GENDER IN('M', 'F')),
  AGE NUMBER,
  PHONE VARCHAR2(13),
  ADDRESS VARCHAR2(100),
  ENROLL_DATE DATE DEFAULT SYSDATE,
  MODIFY_DATE DATE DEFAULT SYSDATE,
  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N'))
);


 		 UPDATE
  		 		MEMBER
  		 	SET
  		 		STATUS = 'Y'
  		 		,MODIFY_DATE = SYSDATE
  		 WHERE
  		 		USER_ID = 'user04';


commit;



select * from member;
delete from member where user_id = 'abcde';

COMMENT ON COLUMN MEMBER.USER_ID IS 'ȸ�����̵�';
COMMENT ON COLUMN MEMBER.USER_PWD IS 'ȸ����й�ȣ';
COMMENT ON COLUMN MEMBER.USER_NAME IS 'ȸ���̸�';
COMMENT ON COLUMN MEMBER.EMAIL IS 'ȸ���̸���';
COMMENT ON COLUMN MEMBER.GENDER IS 'ȸ������';
COMMENT ON COLUMN MEMBER.AGE IS 'ȸ������';
COMMENT ON COLUMN MEMBER.PHONE IS 'ȸ����ȭ��ȣ';
COMMENT ON COLUMN MEMBER.ADDRESS IS 'ȸ���ּ�';
COMMENT ON COLUMN MEMBER.ENROLL_DATE IS 'ȸ�����Գ�¥';
COMMENT ON COLUMN MEMBER.MODIFY_DATE IS 'ȸ��������¥';
COMMENT ON COLUMN MEMBER.STATUS IS 'ȸ�����°�';

INSERT INTO MEMBER
VALUES ('admin', '1234', '������', 'admin@kh.or.kr', 'F', 30, '010-1111-2222', '����� ������ ���ﵿ', '20240101', '20240601', DEFAULT);

INSERT INTO MEMBER
VALUES ('user01', 'pass01', 'ȫ�浿', 'user01@kh.or.kr', 'M', 25, '010-3333-4444', '����� ������ ������', '20240201', '20240602', DEFAULT);

INSERT INTO MEMBER
VALUES ('user02', 'pass02', '�踻��', 'user02@kh.or.kr', 'F', 23, '010-5555-6666', '����� �������� ����', '20240301', '20240603', DEFAULT);


--------------------------------------------------
--------------     NOTICE ����	-------------------
--------------------------------------------------

CREATE TABLE NOTICE(
  NOTICE_NO NUMBER PRIMARY KEY,
  NOTICE_TITLE VARCHAR2(30) NOT NULL,
  NOTICE_WRITER VARCHAR2(30) NOT NULL,
  NOTICE_CONTENT VARCHAR2(200) NOT NULL,
  CREATE_DATE DATE DEFAULT SYSDATE,
  FOREIGN KEY(NOTICE_WRITER) REFERENCES MEMBER ON DELETE CASCADE
);

select * from notice;

COMMENT ON COLUMN NOTICE.NOTICE_NO IS '�������׹�ȣ';
COMMENT ON COLUMN NOTICE.NOTICE_TITLE IS '������������';
COMMENT ON COLUMN NOTICE.NOTICE_WRITER IS '���������ۼ��ھ��̵�';
COMMENT ON COLUMN NOTICE.NOTICE_CONTENT IS '�������׳���';
COMMENT ON COLUMN NOTICE.CREATE_DATE IS '���������ۼ���¥';

CREATE SEQUENCE SEQ_NNO NOCACHE;

INSERT INTO NOTICE
VALUES (SEQ_NNO.NEXTVAL, '������ ����', 'admin', '�������񽺸� �Խ��մϴ�. ���� �̿��� �ּ���', '20240603');

INSERT INTO NOTICE
VALUES (SEQ_NNO.NEXTVAL, '�������� ���� ȯ��', 'admin', '���� ���µǾ�����. ���� �̿��ϰڽ��ϴ�.', '20240603');

INSERT INTO NOTICE
VALUES (SEQ_NNO.NEXTVAL, '�������� �̿� �ȳ�', 'admin', '�������񽺴� ȸ���� �̿��� �� �ֽ��ϴ�. ȸ�� �����ϼ���.', '20240603');


----------------------------------------------------
----------------     BOARD ����     -----------------
----------------------------------------------------

CREATE TABLE BOARD(
  BOARD_NO NUMBER PRIMARY KEY,
  BOARD_TITLE VARCHAR2(100) NOT NULL,
  BOARD_WRITER VARCHAR2(4000) NOT NULL,
  BOARD_CONTENT VARCHAR2(4000) NOT NULL,
  ORIGIN_NAME VARCHAR2(100),
  CHANGE_NAME VARCHAR2(100),
  COUNT NUMBER DEFAULT 0,
  CREATE_DATE DATE DEFAULT SYSDATE,
  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),
  FOREIGN KEY (BOARD_WRITER) REFERENCES MEMBER ON DELETE CASCADE
);
ALTER TABLE BOARD
MODIFY CHANGE_NAME VARCHAR2(300);

commit;

SELECT 
        BOARD_NO,
        BOARD_TITLE,
        BOARD_WRITER,
        COUNT,
        CREATE_DATE,
        ORIGIN_NAME,
        RNUM
   FROM 


(
SELECT 
        BOARD_NO,
        BOARD_TITLE,
        BOARD_WRITER,
        COUNT,
        CREATE_DATE,
        ORIGIN_NAME,
        ROWNUM RNUM
   FROM 
        (

SELECT 
        BOARD_NO,
        BOARD_TITLE,
        BOARD_WRITER,
        COUNT,
        CREATE_DATE,
        ORIGIN_NAME
   FROM
        BOARD
  WHERE
        STATUS = 'Y'
  ORDER 
     BY
        BOARD_NO DESC
        )
        )
  WHERE
        RNUM BETWEEN 1 AND 10
        ;
        
        
        
        
SELECT 
       COUNT(BOARD_NO)
FROM 
     BOARD
WHERE 
     STATUS = 'Y'
AND
    BOARD_WRITER LIKE '%' || 'u' || '%'
AND
    BOARD_TITLE LIKE '%' || 'Java' || '%'     
AND
    BOARD_CONTENT LIKE '%' || '��' || '%';
        
COMMENT ON COLUMN BOARD.BOARD_NO IS '�Խñ۹�ȣ';
COMMENT ON COLUMN BOARD.BOARD_TITLE IS '�Խñ�����';
COMMENT ON COLUMN BOARD.BOARD_WRITER IS '�Խñ��ۼ��ھ��̵�';
COMMENT ON COLUMN BOARD.BOARD_CONTENT IS '�Խñ۳���';
COMMENT ON COLUMN BOARD.ORIGIN_NAME IS '÷�����Ͽ����̸�';
COMMENT ON COLUMN BOARD.CHANGE_NAME IS '÷�����Ϻ����̸�';
COMMENT ON COLUMN BOARD.COUNT IS '�Խñ���ȸ��';
COMMENT ON COLUMN BOARD.CREATE_DATE IS '�Խñ��ۼ���¥';
COMMENT ON COLUMN BOARD.STATUS IS '�Խñۻ��°�';

CREATE SEQUENCE SEQ_BNO NOCACHE;

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, '������ �Խñ�', 'admin', '���� ����Ʈ�� �̿��� �ּż� �����մϴ�.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'JAVA��?', 'user01', '��ü������ ���α׷��� ����Դϴ�.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'ORACLE�� �����ΰ���', 'user02', '��ǥ���� ������ �����ͺ��̽� �Դϴ�..', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'JDBC', 'admin', '�ڹٶ� ����Ŭ�� ������ �� �ִ� API�Դϴ�..', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'HTML�� ȭ���� �����ô�!', 'admin', 'HTML �±׸� �̿��ؼ� ȭ���� �������? HTML �� ������ ������ ��µ� ����մϴ�.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'CSS��?', 'user01', '����� ��Ÿ���� �����ִ� ����Դϴ�. ���ڰ� �ٸ��ּ���!', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'JavaScript �� Java �� ����Ѱ���?', 'user02', '���� ������ ��� �Դϴ�..', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'jQuery', 'user02', '�ڹٽ�ũ��Ʈ�� ������ ������ ª�� �����ϰ� �� �� �ְ� �����ִ� ���̺귯���Դϴ�.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'JSP', 'user01', 'HTML�ڵ忡 JAVA�ڵ带 ���� �� �ֽ��ϴ�.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'Servlet �� ����?', 'admin', '�ڹ� �� �̿��� ���� ����� ���� ����Դϴ�.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'EL�� �̿��ϸ� ���ؿ�', 'admin', '������ JSP ���尴ü�κ��� ���� �̾ƿö� �� ���ϴٰ� �ϳ׿�', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'JSTL�� ������ �� �ؾ�����?', 'user01', 'taglib ���þ �̿��ؼ� ���ξ�� uri �� ��������� �մϴ�.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'Framework �� ����', 'user01', '���Ӽ�, �ڹ�, ȭ�鱸��, ��� ���� �ֽ��ϴ�.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'MyBatis..', 'user02', 'JAVA ORM�� �� ������, ���Ӽ� �����ӿ�ũ �� CRUD �۾��� ���ϰ� �����ִ� �����ӿ�ũ�Դϴ�.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, 'Spring!', 'user01', '�ڹٷ� ������Ʈ ������ �� �����ϰ� �� �� �ֵ��� �����ִ� �����ӿ�ũ�Դϴ�!', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);

INSERT INTO BOARD
VALUES (SEQ_BNO.NEXTVAL, '���θ� �սô�.', 'admin', '�����ϼ���.', NULL, NULL, DEFAULT, DEFAULT, DEFAULT);


----------------------------------------------------
---------------     REPLY ����    -------------------	
----------------------------------------------------

CREATE TABLE REPLY(
  REPLY_NO NUMBER PRIMARY KEY,
  REPLY_CONTENT VARCHAR2(400) NOT NULL,
  REF_BNO NUMBER NOT NULL,
  REPLY_WRITER VARCHAR2(30) NOT NULL,
  CREATE_DATE DATE DEFAULT SYSDATE NOT NULL,
  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN ('Y', 'N')),
  FOREIGN KEY (REF_BNO) REFERENCES BOARD(BOARD_NO),
  FOREIGN KEY (REPLY_WRITER) REFERENCES MEMBER ON DELETE CASCADE
);

COMMENT ON COLUMN REPLY.REPLY_NO IS '��۹�ȣ';
COMMENT ON COLUMN REPLY.REPLY_CONTENT IS '��۳���';
COMMENT ON COLUMN REPLY.REF_BNO IS '�����Խñ۹�ȣ';
COMMENT ON COLUMN REPLY.REPLY_WRITER IS '����ۼ��ھ��̵�';
COMMENT ON COLUMN REPLY.CREATE_DATE IS '����ۼ���¥';
COMMENT ON COLUMN REPLY.STATUS IS '��ۻ��°�';

CREATE SEQUENCE SEQ_RNO NOCACHE;

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, 'ù��° ����Դϴ�.', 1, 'admin', '20240603', DEFAULT);

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, 'ù��° ����Դϴ�.', 13, 'user01', '20240603', DEFAULT);

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, '�ι�° ����Դϴ�.', 13, 'user02', '20240603', DEFAULT);

INSERT INTO REPLY
VALUES (SEQ_RNO.NEXTVAL, '����° ����Դϴ�.', 13, 'admin', '20240603', DEFAULT);

COMMIT;