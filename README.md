# ThucTap_DuAnChamCong
1.Để chạy được dự án thì 
+ Phải cài đặt mysql sever và mysql workben và tạo 1 mysql connection riêng (nhớ user và pass để setup )
+ Vào src/main/resuources 
->Chọn application.properties 
->Thay đổi thuộc tính: spring.datasource.url=jdbc:mysql://localhost:3307/Timesheet?useSSL=false (xem port sever bao nhiêu)
                       spring.datasource.username=hung (tên của connection)
                       spring.datasource.password=111112113 

2. Để bật Spring security 
-> thì bỏ (exclude = SecurityAutoConfiguration.class) trong src/main/java/TimesheetApplication

update 07/05/2024
-Entity chuyển thành models
 + Thêm CustomUserDetail để custom user trong spring security
-thêm Package Repository -> Service (khi controller chạy sẽ gọi service ,0 gọi repo)

update 08/05/2024
+ Thêm User_role (Mục đích phân quyền)
+ chỉnh sửa lại model của user và role 
+ Tiến hành tạo file SecurityConfig

update 09/05/2024
+ Login thành công

update 11/06/2024
+ Hoàn thiện chức năng đăng nhập , tạo tài khoản user trong admin 
+ Hoàn thiện chức năng đăg lịch , sửa , xóa
+ Admin có thể xem và tìm tất cả lịch của toàn bộ user , User chỉ có thể xem lịch của bản thân 
+ tạo văn phòng , tạo chức vụ 
+ thực hiện deloy lên aws 
+ http://datlichchamcong-env.eba-k2xrewug.ap-northeast-1.elasticbeanstalk.com/

+ Các công nghệ sử dụng 
+ Spring boot mvc , api , thymeleaf , Mysql , aws 