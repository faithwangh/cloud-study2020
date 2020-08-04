package com.example.springcloud.controller;

import com.example.springcloud.entities.CommentResult;
import com.example.springcloud.entities.Payment;
import com.example.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    private PaymentService paymentService;

    @Resource
    private DiscoveryClient discoveryClint;

    @PostMapping("/payment/create")
    public CommentResult create(@RequestBody Payment payment) {
        int result = paymentService.create(payment);
        log.info("****插入结果：" + result);

        if (result > 0) {
            return new CommentResult(200, "插入成功", null);
        } else {
            return new CommentResult(500, "插入失败", null);
        }
    }

    @GetMapping("/payment/get/{id}")
    public CommentResult getPaymentById(@PathVariable("id") Long id) {

        Payment payment = paymentService.getPaymentById(id);

        if (payment != null) {
            return new CommentResult(200, "查询成功", payment);
        } else {
            return new CommentResult(500, "没有对应的记录", null);
        }
    }

    @GetMapping("/payment/discovery")
    public Object discovery() {
        List<String> services = discoveryClint.getServices();
        for (String element : services) {
            log.info("***element : " + element);
        }
        List<ServiceInstance> instances = discoveryClint.getInstances("CLOUD-PAYMENT-SERVICE");

        for (ServiceInstance instance : instances) {
            log.info(instance.getServiceId() + "\t" + instance.getHost() + "\t" + instance.getPort()
                    + "\t" + instance.getUri() + "\t" + instance.getMetadata() + "\t" + instance.getUri());

        }

        return this.discoveryClint;
    }


}
