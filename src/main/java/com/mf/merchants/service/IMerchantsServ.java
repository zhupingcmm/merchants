package com.mf.merchants.service;

import com.mf.merchants.vo.CreateMerchantsRequest;
import com.mf.merchants.vo.PassTemplate;
import com.mf.merchants.vo.Response;

public interface IMerchantsServ {
    Response createMerchants(CreateMerchantsRequest request);

    Response buildMerchantsById(Integer id);

    Response dropPassTemplate (PassTemplate template);
}
