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