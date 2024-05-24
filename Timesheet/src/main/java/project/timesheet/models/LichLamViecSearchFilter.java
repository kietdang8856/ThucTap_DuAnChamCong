package project.timesheet.models;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
@Getter
@Setter
public class LichLamViecSearchFilter {
    private Date tuNgay;
    private Date denNgay;
    private Integer  vpCongTac_id;
    private Integer trangThaiLamViec_Id;
    private String tenNV;
}
