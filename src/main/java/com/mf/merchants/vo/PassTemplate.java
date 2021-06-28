package com.mf.merchants.vo;

import com.mf.merchants.constant.ErrorCode;
import com.mf.merchants.dao.MerchantsDao;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassTemplate {

    private Integer id;

    private String title;

    private String summary;

    private String desc;

    private Long limit;

    private Boolean hasToken;

    private Integer background;

    private Date start;

    private Date end;

    public ErrorCode validate (MerchantsDao merchantsDao) {
        if (null == merchantsDao.findById(id)) {
            return ErrorCode.MERCHANTS_NOT_EXIST;
        }
        return ErrorCode.SUCCESS;
    }
}
