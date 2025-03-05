# be-chess

2025 마스터즈 체스 프로젝트

## 주의할 점

- Lucas 컨텐츠의 내용을 공개된 저장소 또는 웹에 올리는 것은 엄격하게 금지합니다.
- 커밋메시지는 한글로 두 줄 이상 작성합니다.
- MVC 패턴은 사용하지 않습니다.

## Step 2 - 1

### 체스 프로젝트 시작

- [ ] PawnTest 클래스 생성
  - 매개변수 생성자를 이용해 Pawn 생성
  - 생성된 Pawn 이 매개변수로 넣은 값이 잘 들어갔는지 확인
- [ ] Pawn 클래스 생성
  - Pawn 의 생성자를 만들어 매개변수로 받은 값을 저장
- [ ] refactoring
  - `create()` 메소드 내에 중복된 코드를 메소드로 추출

## Step 2 - 2

### 체스판 생성

- [ ] `Pawn class` 클래스 변경
  - `default constructor` 추가
  - `white`와 `black`을 **상수**로 변경
- [ ] `BoardTest class` 추가
  - `Board` 를 생성
  - `board` 안에 `Pawn`을 추가 시키고 현재 `piece`가 몇 개인지 확인
  - `board` 안에 `piece`가 잘 들어갔는지 확인
- [ ] 패키지 분리
  - `chess` 패키지 생성
  - `chess` 패키지 안에 `piece` 패키지 생성
- [ ] extra test
  -  `Board`안에 `Pawn` 외의 객체 생성시 예외 처리

## Step 2 - 3

### 체스판 초기화

- [ ] `Board` 클래스 기능 추가
  - `initialize(): void` 메소드 추가
    - `Board`에 `Pawn`을 초기화
    - 검은색 `Pawn`은 대문자 `P`로 표시
    - 흰색 `Pawn`은 소문자 `p`로 표시
  - `print(): void` 메소드 추가
    - `Board`에 있는 `Pawn`을 출력
- [ ] `BoardTest` 클래스 추가
  - `initialize` 메소드 테스트
  - `print` 메소드 테스트
- [ ] `main()` 메소드를 갖는 클래스 추가
  - `Scanner`를 이용해 사용자로부터 입력을 받음
    - `start` 입력시 체스 게임 시작하고 **현재 상태**를 출력
    - `end` 입력시 체스 게임 종료
    - `while`문을 이용해 반복