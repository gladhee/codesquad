# GLAD - CSV File

## 📂 CSV 파일 활용한 SQL 처리 프로그램
- CSV 데이터베이스를 활용한 SQL 처리 프로그램을 구현한다.

## 🚀 기능 요구사항

- CSV 파일을 읽어들여 **SQL 처리**를 수행하는 프로그램을 구현한다.
- **대소문자 구분 없이 동작**하도록 처리
- **SQL 처리**는 다음과 같은 기능을 지원한다.
  - `CREATE TABLE table_name (column1 datatype, column2 datatype, column3 datatype)`
    - `table_name`과 동일한 **CSV 파일**을 생성한다.
    - `column1`, `column2`, `column3`은 **CSV 파일의 첫번째 라인**에 해당한다.
    - 지원하는 datatype은 **`Numeric`, `String`** 두 가지이다.
    - 내부적으로 Numeric타입 **id 컬럼 추가**한다.
    - **id 컬럼**은 **자동으로 증가**한다.
    - 모든 컬럼은 **`NOT NULL`**이다.
    - 기존에 동일한 이름의 테이블이 존재하는 경우 **오류**를 발생시킨다.
      - `ERROR: Table table_name already exists`
    - 컬럼은 **최대 9개**까지 생성할 수 있다.
    - 다음 양식을 따른다
      - ```
        name,age,fruit
        -----------
        ```
    - 저장할 때 **따옴표 없이** 저장한다.
  - `INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...)`
    - `table_name`에 **데이터를 추가**한다.
    - `INSERT`할 때마다 **id 컬럼**은 **자동으로 증가**한다.
    - 숫자는 **" " 없이**, 문자열은 **" "로 감싸서** 사용한다.
    - **컬럼의 수**와 **VALUES의 수**가 **다른 경우 오류**를 발생시킨다.
      - `ERROR: 컬럼 갯수가 일치하지 않습니다.`
    - **컬럼의 타입**과 **VALUES의 타입**이 **다른 경우 오류**를 발생시킨다.
      - `ERROR: 컬럼의 타입이 일치하지 않습니다.`
    - 성공한 경우 **레코드 전체값을 출력**한다.
    - 예시
    ```
    INSERT INTO billboard (singer, year, song) VALUES ("BTS", 2020, "Dynamite")
    >
    INSERTED (1, "BTS", 2020, "Dynamite")
    
    INSERT INTO billboard (singer) VALUES ("BTS")
    >
    컬럼 갯수가 일치하지 않습니다.
    ```
  - `DELETE FROM table_name WHERE condition`
    - `table_name`에서 **condition 에 맞는 데이터를 삭제**한다.
    - condition 해당하는 레코드가 없으면 **오류**를 발생시킨다.
      - `ERROR: 조건에 맞는 데이터가 존재하지 않습니다.`
    - 성공한 경우 **삭제한 레코드의 수를 출력**한다.
    - 예시
    ```
    DELETE FROM billboard WHERE id = 1
    >
    DELETED (1, "BTS", 2020, "Dynamite")

    DELETE FROM billboard WHERE id = 1
    >
    조건에 맞는 데이터가 존재하지 않습니다.

    ```


## 💻 기능 구현 목록

### Lexer

- [ ] 입력값을 받아서 **Token**으로 변환한다.
  - _ex_
    - `CREATE TABLE table_name (column1 datatype, column2 datatype, column3 datatype)`
    - `CREATE`, `TABLE`, `IDENTIFIER`, `LPAREN`, `IDENTIFIER`, `DATATYPE`, `COMMA`, `IDENTIFIER`, `DATATYPE`, `COMMA`, `IDENTIFIER`, `DATATYPE`, `RPAREN`
- [ ] 정의되지 않은 **Token**이 들어오면 **오류**를 발생시킨다.
- [ ] **대소문자 구분 없이** 동작한다.

### SQL Parser

- [ ] **Token**을 받아서 **SQL 문법**에 맞게 **파싱**한다.
  - Keyword 를 기준으로 키워드에 맞게 파싱한다.
  - _ex_
    - `CREATE TABLE table_name (column1 datatype, column2 datatype, column3 datatype)`
    - `INSERT INTO table_name (column1, column2, column3, ...) VALUES (value1, value2, value3, ...)`
    - `DELETE FROM table_name WHERE condition`
    - `DROP TABLE table_name`
    - `UPDATE table_name SET column1 = value1, column2 = value2 WHERE condition`

- [ ] **SQL 문법**에 맞지 않는 경우 **오류**를 발생시킨다.

### execute engine

- [ ] `visitor` 패턴을 활용하여 **SQL 처리**를 수행한다.
  - `CREATE TABLE`
    - [ ] **테이블**을 생성한다.
    - [ ] **테이블 정보**를 저장한다.
  - `INSERT INTO`
    - [ ] **데이터**를 추가한다.
  - `DELETE FROM`
    - [ ] **데이터**를 삭제한다.
  - `DROP TABLE`
    - [ ] **테이블**을 삭제한다.
  - `UPDATE`
    - [ ] **데이터**를 수정한다.
- [ ] `.meta` 파일을 이용해 **테이블 정보**를 저장한다.
  - 테이블 이름 및 컬럼에 저장되어야할 **타입**을 저장한다.
- [ ] **SQL 처리**를 수행한 결과를 **출력**한다.

### 입력

- [ ] `SQL >` 프롬프트를 통해 **SQL 문장**을 입력받는다.
- [ ] `EXIT`를 입력하면 **프로그램을 종료**한다.
