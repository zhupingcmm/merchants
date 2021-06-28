package com.mf.merchants.service.impl;

import com.alibaba.fastjson.JSON;
import com.mf.merchants.constant.Constants;
import com.mf.merchants.constant.ErrorCode;
import com.mf.merchants.dao.MerchantsDao;
import com.mf.merchants.entity.Merchants;
import com.mf.merchants.service.IMerchantsServ;
import com.mf.merchants.vo.CreateMerchantsRequest;
import com.mf.merchants.vo.CreateMerchantsResponse;
import com.mf.merchants.vo.PassTemplate;
import com.mf.merchants.vo.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class MerchantsImpl implements IMerchantsServ {

    /** kafka 客户端 */
    private final KafkaTemplate<String, String> kafkaTemplate;

    private MerchantsDao merchantsDao;

    @Autowired
    public MerchantsImpl(KafkaTemplate<String, String> kafkaTemplate, MerchantsDao merchantsDao) {
        this.kafkaTemplate = kafkaTemplate;
        this.merchantsDao = merchantsDao;
    }

    @Override
    @Transactional
    public Response createMerchants(CreateMerchantsRequest request) {
        Response response = new Response();
        CreateMerchantsResponse merchantsResponse = new CreateMerchantsResponse();
        ErrorCode errorCode = request.validate(merchantsDao);

        if (errorCode != ErrorCode.SUCCESS) {
            merchantsResponse.setId(-1);
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());

        } else {
            merchantsResponse.setId(merchantsDao.save(request.toMerchants()).getId());
        }
        response.setData(merchantsResponse);
        return response;
    }

    @Override
    @Transactional
    public Response buildMerchantsById(Integer id) {
        Response response = new Response();

        Optional<Merchants> merchantsOptional = merchantsDao.findById(id);
        try {
            Merchants merchants = merchantsOptional.get();
            response.setData(merchants);
        }catch (Exception e) {
            response.setErrorCode(ErrorCode.MERCHANTS_NOT_EXIST.getCode());
            response.setErrorMsg(ErrorCode.MERCHANTS_NOT_EXIST.getDesc());
        }
        return response;
    }

    @Override
    @Transactional
    public Response dropPassTemplate(PassTemplate template) {
        Response response = new Response();
        ErrorCode errorCode = template.validate(merchantsDao);
        if (errorCode != ErrorCode.SUCCESS) {
            response.setErrorCode(errorCode.getCode());
            response.setErrorMsg(errorCode.getDesc());
        } else {
            String passTemplate = JSON.toJSONString(template);
            kafkaTemplate.send(
                    Constants.TEMPLATE_TOPIC,
                    Constants.TEMPLATE_TOPIC,
                    passTemplate
            );
            log.info("DropPassTemplates: {}", passTemplate);
        }
        return response;
    }
}
