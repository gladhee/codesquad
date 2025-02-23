# GLAD - Functional Programming

## ⨐ Refactoring to Functional Code

- 주어진 코드를 함수형으로 리펙토링 해보자.

## 🚀 기능 요구사항

- 주어진 코드를 함수형으로 리펙토링한다.
- 불변성, 참조 투명성, 순수 함수를 지향한다.
- 최대한 중복 코드를 제거한다.

## 💻 기능 구현 목록

- Functional Interface 를 사용하여 코드를 리펙토링한다.
- **PrimeAlpha class**
  - `isPrime` 메서드를 Predicate 로 변경한다.
- **ClassifierAlpha**
  - `isPerfect`, `isAbundant`, `isDeficient` 인지 확인하는 Predicate 를 만든다.
- **Application**
  - `IntStream` 을 사용하여 **2 ~ 100** 까지의 수를 순회하며 각 수가 소수인지, 완전수인지, 과잉수인지, 부족수인지 확인한다x.
  - 각 메소드에 대한 결과를 Stream을 사용해 묶고, reduce를 이용하여 각 결과를 `,`로 구분하여 출력한다.
