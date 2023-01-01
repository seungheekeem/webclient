package com.example.webclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
@RequiredArgsConstructor
public class WebClientAsync {
    private final WebClient webClient;

    public void simpleWebConnection(){
        StopWatch watch = new StopWatch();
        watch.start();
        webClientConnect();
        watch.stop();
    }
    public void webClientConnect(){
        StopWatch stopWatch = new StopWatch();
        log.info("---- webClientConnect start!");
        stopWatch.start();

        Mono<String> resultFor3Sec = webClient.get()
                .uri("http://localhost:8080/wait3sec", String.class)
                .retrieve()
                .bodyToMono(String.class);

        resultFor3Sec.subscribe(result -> {
            //log.info("resultFor3Sec: {}", result);
            if (stopWatch.isRunning()) {
                stopWatch.stop();
            }
            log.info("Total result(3Sec): {}", stopWatch.getTotalTimeMillis());
        });

        log.info("---- webClientConnect over!! ");
    }
}
