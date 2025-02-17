# GLAD - Log Analysis

## 📊 Log Analysis Tool
- 로그파일을 분석하여 특정 조건에 맞는 로그를 출력하는 프로그램을 구현한다.

## 🚀 기능 요구사항

- 로그는 다음과 같은 형식으로 구성되어 있다.
  ```
  error	14:22:30.608355+0900	airportd	[corewifi] END REQ [GET BSSID] (pid=5617 proc=iStat Menus Status service=com.apple.corewlan-xpc intf=en1 uuid=92833 err=1)
  default	14:22:33.784903+0900	Airmail	[C264.1 Hostname#9e7acbfe:993 initial path ((null))] event: path:start @0.000s
  info	14:23:16.765320+0900	bluetoothd	canScanNow session:<private>(Unspecified) allowed:1  deviceLocked:0 deviceFirstUnlocked:1 allowedInBKG:1 sessionState:daemon <private>
  ```
- 로그 문자열 한 줄마다 하나의 데이터로 구분한다.
- 로그는 다음과 같은 필드로 구성되어 있다.
  - 로그 레벨: `error`, `default`, `info`
  - 시간: `14:22:30.608355+0900`
  - 프로세스 이름: `airportd`, `Airmail`, `bluetoothd`
  - 로그 메시지: `[corewifi] END REQ [GET BSSID] (pid...`
  - 각 필드는 **`\t`** 을 기준으로 구분된다.
- 각 로그 데이터 값을 **객체**로 선언해야한다.
- **로그 레벨 유형별로 필터링**할 수 있어야 한다.
- **로그 시각으로 정렬**할 수 있어야 한다.
- **프로세스 이름으로 필터링**할 수 있어야한다
- **프로세스 이름으로 정렬**할 수 있어야 한다.
- **로그레벨, 프로세스 별로 카운트 값**을 가져올 수 있어야 한다

## 💻 기능 구현 목록

### 파일 읽기

- [ ] 파일을 읽어서 로그 데이터를 가져온다.
- [ ] `\t` 기준 4칸으로 구분된는지 검사한다.
- [ ] 4칸이 아닐 경우 마지막에 만든 로그의 메세지에 추가해준다
- [ ] 로그 데이터를 객체로 만들어서 배열에 담는다.

### 로그 필터링

- [ ] 로그 레벨 유형별로 필터링한다.
- [ ] 로그 시각으로 정렬한다.
- [ ] 프로세스 이름으로 필터링한다.
- [ ] 프로세스 이름으로 정렬한다.
- [ ] 로그레벨, 프로세스 별로 카운트 값을 가져온다.
- [ ] 각 메소드별로 새로운 Logs를 만들어서 반환한다.
- [ ] `toString` 메소드를 통해 출력한다.

## Mistake Party

Log 파일 중간에 멀티라인이 존재해서 로그를 제대로 읽지 못하는 경우가 발생했다.
```
info	14:23:43.373717+0900	searchpartyd	"Reconciled advertisement <mask.hash: '0gtR0VUtyKLUHLjssnTCWg=='>, multiPart: 000, hint: none, Type18: {type: apple, battery: high, maintained: false multipart: 000},
index: 4024, sequence: .primary, beacon: <mask.hash: '/+5od1uUK02A328zcF0PcQ=='>,
hasLocation: false.
```
그래서 한 줄 읽을 때마다 `\t` 으로 split해서 사이즈가 4가 아니면 마지막 로그에 추가해주는 방식으로 해결했다.