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