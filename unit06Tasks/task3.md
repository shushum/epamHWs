```java
class MedicalStaff{}
class Doctor extends MedicalStaff{}
class Nurse extends MedicalStaff{}
class HeadDoctor extends Doctor{}
```

|   | correct | incorrect | explanation |
|-----|:---:|:---:|---|
| Doctor doctor1 = new Doctor(); | [x] | [ ] | Nothing illegal here. |
| Doctor doctor2 = new MedicalStaff(); | [ ] | [x] | Doctor capabilities expands. Can be casted to Doctor thought. |
| Doctor doctor3 = new HeadDoctor(); | [x] | [ ] | Doctor capabilities narrows. It's fine. |
| Object object1 = new HeadDoctor(); | [x] | [ ] | Object capabilities narrows. It's fine. |
| HeadDoctor doctor5 = new Object(); | [ ] | [x] | HeadDoctor capabilities expands. Can be casted to HeadDoctor thought. |
| Doctor doctor6  = new Nurse(); | [ ] | [x] | Although Doctor and Nurse have common ancestor, they are not directly related. |
| Nurse nurse = new Doctor(); | [ ] | [x] | Although Doctor and Nurse have common ancestor, they are not directly related. |
| Object object2 = new Nurse(); | [x] | [ ] | Object capabilities narrows. It's fine.  |
|   |   |   |   |
| List<Doctor> list1= new ArrayList<Doctor>(); | [x] | [ ] | ArrayList implements List. Argument type is same. So, it is legal.  |
| List<MedicalStaff> list2 = new ArrayList<Doctor>(); | [ ] | [x] | List<MedicalStaff> has no relationship to ArrayList<Doctor>. |
| List<Doctor> list3 = new ArrayList<MedicalStaff>(); | [ ] | [x] | List<MedicalStaff> has no relationship to ArrayList<Doctor>. |
| List<Object> list4 = new ArrayList<Doctor>(); | [ ] | [x] | List<MedicalStaff> has no relationship to ArrayList<Doctor>. |
| List<Object> list5 = new ArrayList<Object>(); | [x] | [ ] | ArrayList implements List. Argument type is same. So, it is legal. |
