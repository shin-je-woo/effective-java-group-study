# 💡 equals 메서드를 재정의할 때 반드시 따라야 할 일반 규약 5가지
**null이 아닌 모든 참조 값 x, y, z에 대해**
### 반사성(reflexivity)
* x.equals(x) == true
### 대칭성(symmetry)
* x.equals(y) == true
* y.equals(x) == true
### 추이성(transitivity)
* x.equals(y) == true
* y.equals(z) == true
* x.equals(z) == true
### 일관성(consistency)
* x.equals(y)를 반복호출하면 항상 true거나 항상 false이다.
### null-아님(non-null)
* x.equals(null) == false
