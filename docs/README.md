### 입력

- [x]  시작 지점 
- [x]  평일 순서 및 휴일 순서
  • 평일 순번 또는 휴일 순번의 입력 값이 올바르지 않은 경우, '평일 순번'부터 다시 입력 받는다.
- [x]  올바르지 않은 입력을 할 경우 `[ERROR]`로 시작하는 에러 메시지를 출력 후 다시 입력받는다.
    - `[ERROR] 유효하지 않은 입력 값입니다. 다시 입력해 주세요.`

---

### InputManager

- 입력의 모든 과정 총괄
    1. 콘솔 입력
    2. 파싱
    3. 검증
    4. 검증된 도메인 추출
- InputManager가 입력에서 얻어내야 하는 값(도메인)
    1. startDate → Date 타입
    2. weekDayPermutation & holidayPermutation → WorkPermutation 타입

---

### Date

- 공휴일 유무 검증  (법정 공휴일, 토, 일)
- 달 별 마지막 날짜 정보 반환
    - 31일 : 1,3,5,7,8,10,12
    - 30일 : 4,6,9
    - 28일 : 2
- 법정 공휴일을 따로 enum타입으로 갖고있음

### Worker

- name을 private key로 갖는 (중복을 허용하지 않는) 근무자 정보

### WorkPermutation

- Worker 타입  일급 컬렉션