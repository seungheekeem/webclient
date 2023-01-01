package com.example.webclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/")
public class TestController {
    final WebClientAsync webClientAsync;

    int orderKey = 0;
    final BusinessService businessService;


    /**
     * 3초짜리 처리
     *
     * @return
     */
    @RequestMapping("wait3sec")
    public String waitResponse() {
        StopWatch watch = new StopWatch();
        watch.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
        watch.stop();
        log.info("Process done!! : " + watch.getTotalTimeMillis() + " ms");
        return "Process done!! : " + watch.getTotalTimeMillis() + " ms";
    }

    /**
     * non-blocking 처리가 구현된 url
     *
     * @return
     */
    @RequestMapping("webclient")
    public String connectWebClient() {
        StopWatch watch = new StopWatch();
        watch.start();
        webClientAsync.webClientConnect();
        webClientAsync.webClientConnect();
        webClientAsync.webClientConnect();
        watch.stop();
        return "webClient : " + watch.getTotalTimeSeconds();
    }

    @GetMapping("async/{param}")
    public String asyncTest(@PathVariable boolean param){
        StopWatch watch = new StopWatch();
        watch.start();
        webClientAsync.webClientConnect();

        if (businessService.sample(param)) {
            businessService.nextBusiness();
        }

        watch.stop();
        return "webClient : " + watch.getTotalTimeSeconds();
    }

    @GetMapping("async2")
    public String async2Test(){
        log.info("Controller Begin");
        businessService.sample2();
        webClientAsync.simpleWebConnection();

        log.info("Controller End");
        return "webClient Success!! ";
    }


}
