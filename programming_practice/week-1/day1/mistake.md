**문제 상황: scanner 사용시 nextInt 사용후 바로 nextLine 사용시 에러**
- 왜 에러가 발생했는지 
  - nextInt()로 정수를 입력받은 후 nextLine()으로 문자열을 입력받을 때, nextInt()는 개행문자를 읽지 않고 개행문자를 남겨둡니다. 그래서 nextLine()이 개행문자를 읽고 바로 종료되어 원하는 문자열 입력이 들어오지 않았습니다.
- 해결 방법
  - nextInt() 사용후 nextLine() 사용전에 한 번더 사용해서 버퍼를 지워주어야함.
  - TokenScanner랑 LineScanner를 잘 구분해서 사용하기