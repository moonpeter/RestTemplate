package com.tutorialspoint.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

@Component
public class RestRunner implements ApplicationRunner {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
//    private RestTemplate restTemplate;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        RestTemplate = restTemplate = restTemplateBuilder.build();  // build를 하면 RestTemplate가 나온다.

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // TODO '/hello' 호출
        String helloResult = restTemplate.getForObject("http://localhost:8080/hello", String.class); // 두 번째 인자는 원하는 응답의 타입
        System.out.println(helloResult);

        // TODO '/world' 호출
        String worldResult = restTemplate.getForObject("http://localhost:8080/world", String.class);
        System.out.println(worldResult);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}
