package com.cloudlibrary.lending.ui.feign;

import com.cloudlibrary.lending.ui.requestBody.FeignLendingStatusUpdateRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.cloud.openfeign.FeignClient(name="feign"
        , url="http://ecs-alb-167470959.us-east-1.elb.amazonaws.com/v1/composite/lending")
public interface FeignClient {


    @PutMapping(value = "/{bookId}", consumes = "application/json")
    String updateLendingStatus(@PathVariable("bookId") Long bookId, @RequestBody FeignLendingStatusUpdateRequest lendingStatus);


}
