# GALD - QR Code Decoder

## 🔓QR Code Decoder

- 2차원 배열로 들어오는 qr코드를 디코딩하는 프로그램

## 🚀 기능 요구사항

- QR코드는 2차원 배열로 주어진다.
- 모든 비트는 좌측부터 `12345678`비트 순으로 읽어야한다.
- QR코드의 패턴은 다음과 같다.
  - 패턴 1
  ```
  6543
  8721
  ```
  - 패턴 2
  ```
  21
  43
  65
  87
  ```
  - 패턴 3
  ```
  8721
  6543
  ```
  - 패턴 4
  ```
  87
  65
  43
  21
  ```
- 각 패턴에 알맞는 데이터 비트를 읽어서 디코딩한다.
- 표현된 데이터는 **전용 코드값**으로 변환한다.
- 시작 영역과 종료 영역이 규칙에 맞지 않으면 **빈 배열**을 리턴한다
- 데이터와 에러값 모두 **대문자**로 출력한다

## 💻 기능 구현 목록

### 입력

- [ ] 입력으로 들어오는 2차원 배열을 받는다.
- [ ] 따옴표 및 대괄호, 쉼표를 제거한다.
- [ ] **char[][] 로 변환**한다

### 디코딩

- [ ] 데이터의 순서 및 패턴을 사전에 메소드로 정의한다
- [ ] `Queue` 를 이용해서 읽어야할 Index 데이터 생성 메소드를 순서대로 삽입한다.
- [ ] `Queue` 에서 데이터 Index 들을 꺼내서 8비트를 읽어서 **문자열**로 변환한다.
- [ ] 시작 영역과 종료 영역이 규칙에 맞지 않으면 **빈 배열**을 리턴한다
- [ ] **전용 코드값**으로 변환한다.

## Mistake Party

decode 를 에러까지 포함해서 결과에 담고 출력을 하다보니 `indexOutOfRange` 가 발생했다.
데이터는 44까지만 표현할 수 있는 반면에 에러코드는 255까지 자유럽게 표현이 되기 떄문에 전용코드로 변환하는 과정에서 에러가 발생한 것이었다 .
그래서 data 부분과 error 부분을 따로 분리해서 처리하니 에러가 발생하지 않았다.
```java
private String getData() {
    int len = decode.get(LEN_INDEX);

    return decode.subList(DATA_START, len + DATA_START)
            .stream()
            .map(Convertor::convertCode)
            .collect(Collectors.joining());
}

private String getError() {
    return decode.subList(ERROR_START, decode.size())
            .stream()
            .map(Convertor::convertHex)
            .collect(Collectors.joining());
}
```

## 📝 회고

## 📌 오늘의 학습 내용
- QR code 디코딩 방식
- Functional Interface를 이용한 람다식

## 💡 좋았던 점
- ✅ 다른 날보다 Functional 를 적재적소에 잘 사용한 느낌이 들어서 좋았다.

## 🤔 아쉬웠던 점
- ❌ 더 간결하게 쓸 수 있을 거 같은 생각을 많이 했는데 떠올린 방법들이 다 지저분해서 아쉬웠다.

📝 개선할 점 & 내일의 목표
- 더 간결하게 쓸 수 있는 방법을 생각해보자.
