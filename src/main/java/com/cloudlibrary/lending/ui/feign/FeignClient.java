package com.cloudlibrary.lending.ui.feign;

import com.cloudlibrary.lending.ui.requestBody.FeignLendingStatusUpdateRequest;
import com.cloudlibrary.lending.ui.requestBody.FeignReservationRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@org.springframework.cloud.openfeign.FeignClient(name="feign"
        , url="http://ecs-alb-167470959.us-east-1.elb.amazonaws.com/v1/composite")
public interface FeignClient {


    @PutMapping(value = "/lending/{bookId}", consumes = "application/json")
    String updateLendingStatus(@PathVariable("bookId") Long bookId, @RequestBody FeignLendingStatusUpdateRequest lendingStatus);

    @PutMapping(value = "/reservation", consumes = "application/json")
    String updateReservationInfo(@RequestBody FeignReservationRequest feignReservationRequest, @RequestParam Long bookId);
}
