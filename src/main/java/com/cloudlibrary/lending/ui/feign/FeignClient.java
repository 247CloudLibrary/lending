package com.cloudlibrary.lending.ui.feign;

import com.cloudlibrary.lending.application.domain.LendingStatus;
import com.cloudlibrary.lending.ui.view.ApiResponseView;
import com.cloudlibrary.lending.ui.view.lending.LendingView;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.cloud.openfeign.FeignClient(name="feign"
        , url="http://ecs-alb-167470959.us-east-1.elb.amazonaws.com/v1/composite/lending")
public interface FeignClient {

    @PatchMapping("/{bookId}")
    String compositeBookOut(@PathVariable("bookId") Long bookId, @RequestBody LendingStatus lendingStatus);


}
