package chapter5.item26;

public class Box {
    private Object contents;

    public void setContents(Object contents) {
        this.contents = contents;
    }

    public Object getContents() {
        return contents;
    }
    
    public static void main(String [] args) {
    
    	Box box = new Box();  // 로 타입 사용
    	box.setContents("Hello");  // String 인자로 전달
    	Integer i = (Integer) box.getContents();  // 런타임 에러 발생
    	
    }
}