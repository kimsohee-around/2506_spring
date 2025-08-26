-- JPA 수정 : Gender 타입이 문자열로 변환할 때 char 타입은 ❌
-- 컬럼 타입을 varchar2 로 변경
ALTER TABLE user_account MODIFY gender VARCHAR2(4);
-- 열거형 타입은 Entity 에서 @Enumerated(EnumType.STRING) 하여 문자열로 저장.


-- TODO_TEST 테이블에 기본값 추가
ALTER TABLE TODO_TEST MODIFY created_at default sysdate;