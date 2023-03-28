# discodeBot : 스마트미디어 학과 학생을 위한 AI 디스코드 봇 제작

## 제작 개요
학과 동아리에서 운영 중에 있는 디스코드 채널이 있는데, 해당 채널에 학과 정보와 공부 자료를 공유하여 노출해주는 AI 디스코드 봇을 제작하고자 하였습니다.
이로 인해 한명의 해석자가 데이터의 신뢰성을 파악하는 과정을 거치게 된다면 학습자들은 검증된 자료를 학습할 수 있게 될 것입니다.

# 기능 추가 & 업데이트 !!
### 2023-03-14 (화)
- 디스코드 봇의 이미지와 상태 설정
- 디스코드 토큰 추가 후 디스코드에 봇 적용
- 댓글에 이모션을 달면 누가 어떤 채널에 이모션을 달았는지 default 채널에 공지해주는 기능 추가 ( 추후 모아서 시간이 지난 뒤 공지하도록 변경 예정 )

### 2023-03-15 (수)
- 디스코드 봇이 댓글을 입력할 수 있는 통신망을 열어줌
- 1학년, 2학년, 3학년, 4학년을 포함한 단어를 입력하면 학년별 정보를 노출시켜주는 댓글기능 추가( 추후 버튼이나 시그니처를 통해 입력할 수 있게 변경할 예정 )

### 2023-03-16 (목)
- 유저 캐싱기능 추가

### 2023-03-17 (금)
- 슬래쉬( / ) 커맨드로 교수님 성함 검색하면 연구실이 어디인지 전화번호는 어떻게 되는지 개인만 볼 수 있는 메세지를 출력하는 기능 추가( 추후 / 이외의 커맨드로 변경 예정 )

### 2023-03-20 (월)
- 슬래쉬( / ) 커맨드에 항목을 미리 보여주는 기능 추가

### 2023-03-21 (화)
- 슬래쉬 이벤트 2개 추가: 별관리스트, 주전공관리스트

### 2023-03-22 (수)
- 가독성을 위해 메인 클래스 메서드 분리
- addChoice의 변수로 String, String 을 변수로 넣어줘야했으나 배열을 넣기 위해서 라이브러리 커스텀 작업 진행

### 2023-03-27 (월)
- embed 형식으로 보여주는 기능 추가

### 2023-03-28 (화)
- slf4j를 사용해 log를 남기는 설정 추가
