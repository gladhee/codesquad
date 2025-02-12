# GLAD - J64061 프로그래머스

## 아이디어

- 바구니 모양이 스택과 동작하는게 동일하니 인형(숫자)를 뽑으면 스택에 **push** 를 하고 **pop** 를 두 번해서 값 비교를 해야겟다
- moves 안에 있는 x좌표 모음집을 하나 씩 순회
- y index를 하나씩 내려가며 인형이 있으면 해당 인덱스를 `0`으로 변경 후 스택에 `push`
- 스택 최상단 숫자와 일치하면 없애고 다를시 다시 넣기

## 구현

- `for (int move : moves)` 로 하나씩 인형 집기 시도
- `grepDoll(int[][] board, int move, Stack<Integer> stack): int` 에서 y좌표 순회 후 없애면 2리턴 아니면0 리턴
- `removeDoll(Stack<Integer> stack): int` 스택 내부에서 인형값 비교
