```java
class MedicalStaff{}
class Doctor extends MedicalStaff{}
class Nurse extends MedicalStaff{}
class HeadDoctor extends Doctor{}
```

|   | correct | incorrect | explanation |
|-----|:---:|:---:|---|
| ```java Doctor doctor1 = new Doctor();``` | [x] | [ ] | Nothing illegal here. |
| ```java Doctor doctor2 = new MedicalStaff();``` | [ ] | [x] | Doctor capabilities expands. Can be casted to Doctor thought. |
| ```java Doctor doctor3 = new HeadDoctor();``` | [x] | [ ] | Doctor capabilities narrows. It's fine. |
| ```java Object object1 = new HeadDoctor();``` | [x] | [ ] | Object capabilities narrows. It's fine. |
| ```java HeadDoctor doctor5 = new Object();``` | [ ] | [x] | HeadDoctor capabilities expands. Can be casted to HeadDoctor thought. |
| ```java Doctor doctor6  = new Nurse();``` | [ ] | [x] | Although Doctor and Nurse have common ancestor, they are not directly related. |
| ```java Nurse nurse = new Doctor();``` | [ ] | [x] | Although Doctor and Nurse have common ancestor, they are not directly related. |
| ```java Object object2 = new Nurse();``` | [x] | [ ] | Object capabilities narrows. It's fine.  |
|   |   |   |   |
| ```java List<Doctor> list1= new ArrayList<Doctor>();``` | [x] | [ ] | ArrayList implements List. Argument type is same. So, it is legal.  |
| ```java List<MedicalStaff> list2 = new ArrayList<Doctor>();``` | [ ] | [x] | List of MedicalStaff has no relationship to ArrayList of Doctor. |
| ```java List<Doctor> list3 = new ArrayList<MedicalStaff>();``` | [ ] | [x] | List of MedicalStaff has no relationship to ArrayList of Doctor. |
| ```java List<Object> list4 = new ArrayList<Doctor>();``` | [ ] | [x] | List of MedicalStaff has no relationship to ArrayList of Doctor. |
| ```java List<Object> list5 = new ArrayList<Object>();``` | [x] | [ ] | ArrayList implements List. Argument type is same. So, it is legal. |
