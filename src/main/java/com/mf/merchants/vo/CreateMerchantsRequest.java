package com.mf.merchants.vo;

import com.mf.merchants.constant.ErrorCode;
import com.mf.merchants.dao.MerchantsDao;
import com.mf.merchants.entity.Merchants;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMerchantsRequest {

    private String name;

    private String logUrl;

    private String businessLicenseUrl;

    private String phone;

    private String address;

    public ErrorCode validate (MerchantsDao dao) {
        if (dao.findByName(this.name) != null) {
            return ErrorCode.DUPLICATE_NAME;
        }

        if (null == this.address) {
            return ErrorCode.EMPTY_ADDRESS;
        }
        if (null == this.businessLicenseUrl) {
            return ErrorCode.EMPTY_BUSINESS_LICENSE;
        }

        if (null == this.logUrl) {
            return ErrorCode.EMPTY_LOGO;
        }

        if (null == this.phone) {
            return ErrorCode.ERROR_PHONE;
        }

        return ErrorCode.SUCCESS;
    }

    public Merchants toMerchants () {
        Merchants merchants = new Merchants();
        merchants.setAddress(this.address);
        merchants.setName(this.name);
        merchants.setBusinessLicenseUrl(this.businessLicenseUrl);
        merchants.setPhone(this.phone);
        merchants.setLogUrl(this.logUrl);
        return  merchants;
    }

}
