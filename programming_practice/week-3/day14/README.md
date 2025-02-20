# GLAD - Heap Memory

## 💾 Heap Memory Simulation

- Heap Memory 구조를 처리하는 프로그램 구현한다.

## 🚀 기능 요구사항

- 다음과 같은 함수를 구현하는 객체 구현
  - `init(heapSize): int`
    - heapSize만큼의 메모리를 할당한다.
    - 할당된 메모리의 시작 주소를 반환한다.
    - `0x0000 ~ 0xA000` 사이에서 지정
  - `setSize(type, length): void`
    - `type` 별로 **고유한 사이즈 등록**
    - 스스로 필요한 타입 지정
    - 이미 등록한 타입은 **길이 변경 불가**
    - 사이즈는 **`1,2,4,8,16,32`** 중 하나
  - `malloc(type, count): int`
    - `type`에 해당하는 사이즈로 `count`개의 메모리를 할당한다.
    - 할당된 메모리의 **시작 주소(상대 주소)를 반환**한다.
    - 8바이트보다 작은 경우 **패딩을 추가**한다
  - `free(pointer): int`
    - `pointer`에 해당하는 메모리를 해제한다.
    - **해제된 메모리는 재사용 가능**하다.
    - 할당한 길이만큼 반환
  - `usage(): List<String>`
    - **힙 영역 전체크기**, **사용중인 용량**, **남은 용량**을 순서대로 반환한다.
  - `heapdump(): List<String>`
    - **힙 영역의 상태**를 반환한다.
    - **할당된 메모리의 시작 주소**, **타입**, **길이**를 반환한다.

## 💻 기능 구현 목록

### Heap Memory

- `init()`
  - [ ] 실제 메모리처럼 byte[CAPACITY]만큼 할당
  - [ ] `0x0000 ~ 0xA000` 사이에서 랜덤 주소 할당
  - [ ] 할당된 메모리의 시작 주소 반환
- `setSize()`
  - [ ] 이미 등록한 타입은 **예외처리**
  - [ ] 사이즈는 **`1,2,4,8,16,32`** 중 하나
- `malloc()`
  - [ ] memory 를 순회하면서 **할당 가능한 공간** 찾기
  - [ ] **할당 가능한 공간이 없는 경우** 예외처리
  - [ ] 8바이트보다 작은 경우 **패딩 추가**
- `free()`
  - [ ] ALLOCATED 상태를 NON_ALLOCATED 로 변경
  - [ ] 할당한 길이만큼 반환
- `usage()`
  - [ ] Allocate 된 메모리 사이즈로 **사용중인 용량** 계산
- `heapdump()`
  - [ ] Allocate `toString` 으로 반환
- `writeBytes()`
  - [ ] 주소와 값을 받아서 해당 메모리에 **메모리에 값을 직접 삽입**
  - [ ] 할당되지 않는 공간일 경우 **예외처리**
- `readBytes()`
  - [ ] 주소를 받아서 해당 메모리에 **저장된 값을 반환**
  - [ ] 할당되지 않는 공간일 경우 **예외처리**

## Mistake Party

`byte[] memory` 에 직접 값을 `writeByte(int ptr, byte[] data)`  를 이용해 메모리에 값을 삽입 후
`readByte(int ptr)` 로 값을 읽어오는데, `ArrayIndexOutOfBoundsException` 발생
```
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 1024 out of bounds for length 1024
	at Memory.readBytes(Memory.java:134)
	at Application.main(Application.java:28)
```
c언어와 유사하게 Null-terminated 하게 readBytes 를 구현했는데 writeBytes 에서 `NULL`값을 넣지 않아서 발생한 문제

writeBytes 에서 끝에 `NULL`값을 넣어주니 정상적으로 동작
