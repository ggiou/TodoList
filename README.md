# TodoList
practice spring web, todolist

###  기초 DataBase 설정
#### (1) Todolist를 담기 위한 table 생성
```mysql
create table tbl_todo (
    tno int auto_increment primary key ,
    title varchar(100) not null, 
    dueDate date not null, 
    writer varchar(50) not null,
    finished tinyint default 0);
```
* **tbl_todo**
  * **tno** &rarr; 몇 번째 행인지 구분하기 위한 인덱스,  primary key로 설정  
  mySQL의 auto_increment를 통해  
  데이터를 추가할때마다 1씩증가하는 자동 인덱싱을 설정해주었다.
  * **title** &rarr; todo 리스트의 제목,  
  varchar(문자열)로 100byte의 사이즈를 지정하며,  
  리스트 작성을 위해 제목은 필수로 필요하니 not null 처리해 주었다. 
  * **dueDate** &rarr; todo의 기한으로 마감일,  
  date(날짜) 형식과 마찬가지로 필수로 필요하니 not null 처리해 주었다.
  * **writer** &rarr; todo의 작성자,  
  varchar(문자열)로 50byte와 마찬가지로 필수니 not null 처리해 주었다.
  * **finished** &rarr; todo의 완료 여부,  
  0은 진행중, 1은 완료를 의미 - default를 0으로줘 새로 만든 todo는 진행중 처리해 주었다.
            