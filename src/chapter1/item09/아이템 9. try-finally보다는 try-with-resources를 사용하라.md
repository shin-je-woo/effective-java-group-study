# 💡 try-finally보다는 try-with-resources를 사용하라

### 개요
* 자바 라이브러리에는 **close 메소드를 호출해 직접 닫아줘야하는 자원**들이 많다.
* 예를들면 InputStream. OutputStream sql.Connection 등 이 좋은 예시인데, 해당 API들은 **운영체제 또는 네트워크 장비 등과 같은 외부 시스템과의 연결을 필요**로 하기 때문에  종료될 때 해당 자원들을 해제해주어야 합니다.

* 자원을 닫아 주지 않는다면, 자원이 계속해서 유지되어 시스템 자원을 소모하게되어 **시스템의 전반적인 성능 저하나, 안정성 이슈를 발생**시킵니다. 또한 해당 자원을 다른 애플리케이션에서 사용할 수 없으므로 메모리 누수와 같은 문제가 발생한다고 합니다.

## ✅ try-finally

## 코드 try-finally
```java
      FileInputStream inputStream = null;
		try {
		    inputStream = new FileInputStream("filename.txt");
		    // 파일에서 데이터를 읽는 코드 작성
		} catch (IOException e) {
		    // 예외 처리
		} finally {
		    if (inputStream != null) {
		        try {
		            inputStream.close();
		        } catch (IOException e) {
		            // 예외 처리
		        }
		    }
		}
```

### 코드를 통해 이해하기
* FileInputStream에서 파일을 읽고, 기본적인 예외처리 후 finally 에서 close()를 통해 자원을 해제하는 간단한 예시이다. 
* 하나의 자원해제 예제만봐도, **코드자체가 지저분하고, 가독성이 낮다.**


## 코드 try-finally

```java
       try (FileInputStream inputStream = new FileInputStream("filename.txt");
				FileOutputStream outputStream = new FileOutputStream("filename.txt")
				) {
		    // 파일에서 데이터를 읽는 코드 작성
		} catch (IOException e) {
		    // 예외 처리
		}
```

### 코드를 통해 이해하기
* 자바 7 이상부터 제공하는 try-with-resources는 자원을 정말 간단하게 해제할 수 있다.
* 심지어 해당 구문은 두개의 자원을 해제하는 경우이고, try-finally와는 다르게 코드가 간결하고 가독성까지 좋다.


### 그렇다면 어떻게해서 close()를 호출 하지 않고 자원이 해제 가능한가?
* 그건바로 try()에 선언된 객체 자체가 **AutoCloseable을 구현 받기 때문**이다.
* AutoCloseable를 구현 받은 객체는  **close()메소드를 정의**하고 있고, try()가 종료될 때 **자동으로 close()가 호출 되어 자원이 해제**되는 경우이다.

![image](https://user-images.githubusercontent.com/91134556/235330996-52c89df3-05c1-4b21-afd2-c1cfa6eb55f2.png)


## 정리
* 꼭 회수해야 하는 자원을 다룰 때는 try-finally 말고, try-with-resources를 사용하자. 예외는 없다. 코드는 더 짧고 분명해지고, 만들어지는 예외 정보도 훨씬 유용하다. 
* try-finally로 작성하면 실용적이지 못할 만큼 코드가 지저분해지는 경우라도, **try-with-resources로는 정확하고 쉽게 자원을 회수**할 수 있다.
