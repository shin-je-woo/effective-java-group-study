package chapter10.item75;

public class DataException extends Exception {
    private int errorCode;
    private String errorMessage;

    public DataException(int errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
    
    public static void main(String [] args) throws DataException{
        try {
            // 데이터 처리 로직
            // ...

            // 실패 발생 시 예외를 던집니다.
            throw new DataException(500, "데이터 처리 실패");
        } catch (DataException e) {
            // 실패에 대한 정보 활용
            System.out.println("에러 코드: " + e.getErrorCode());
            System.out.println("에러 메시지: " + e.getErrorMessage());
        }
        
    }
}
