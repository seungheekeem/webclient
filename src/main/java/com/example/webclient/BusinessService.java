package com.example.webclient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class BusinessService {
    WebClientAsync webClientAsync;

    public boolean sample(boolean result){
      log.info("비지니스로직!");

      return result;
    }


    public void sample2(){
        log.info("비지니스로직3!!");
        nextBusiness();
    }

    public void nextBusiness(){
        log.info("nextBusiness 수행!!");
    }
}
