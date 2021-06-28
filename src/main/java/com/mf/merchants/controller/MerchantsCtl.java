package com.mf.merchants.controller;

import com.alibaba.fastjson.JSON;
import com.mf.merchants.service.IMerchantsServ;
import com.mf.merchants.vo.CreateMerchantsRequest;
import com.mf.merchants.vo.PassTemplate;
import com.mf.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/merchants")
public class MerchantsCtl {
    private final IMerchantsServ merchantsServ;

    @Autowired
    public MerchantsCtl(IMerchantsServ merchantsServ) {
        this.merchantsServ = merchantsServ;
    }

    @ResponseBody
    @PostMapping("/create")
    public Response createMerchants (@RequestBody CreateMerchantsRequest request) {
        log.info("CreateMerchants: {}", JSON.toJSONString(request));
        return  merchantsServ.createMerchants(request);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public Response buildMerchantsById (@PathVariable Integer id) {
        log.info("Merchants id: {}", id);
        return merchantsServ.buildMerchantsById(id);
    }

    @ResponseBody
    @PostMapping("/drop")
    public Response dropPassTemplate (@RequestBody PassTemplate template) {
        log.info("PassTemplate: {}", JSON.toJSONString(template));
        return merchantsServ.dropPassTemplate(template);
    }
}
