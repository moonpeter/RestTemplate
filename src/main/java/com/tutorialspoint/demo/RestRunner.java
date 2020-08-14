package com.tutorialspoint.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class RestRunner implements ApplicationRunner {

    @Autowired
    WebClient.Builder builder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        WebClient webClient = builder.build();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // TODO '/hello' 호출
        Mono<String> helloMono = webClient.get().uri("http://localhost:80/hello")
                .retrieve() // 결과 값을 가져와라
                .bodyToMono(String.class); // 모노 타입으로 변경해라
        helloMono.subscribe(s->{
            System.out.println(s);

            if(stopWatch.isRunning()) {
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start(); // 어떤 것이 먼저 끝날지 모르므로, StopWatch를 다시 돌려줘야함
        });

        // TODO '/world' 호출
        Mono<String> worldMono = webClient.get().uri("http://localhost:80/world")
                .retrieve()
                .bodyToMono(String.class);
        worldMono.subscribe(s->{
            System.out.println(s);

            if(stopWatch.isRunning()) {
                stopWatch.stop();
            }
            System.out.println(stopWatch.prettyPrint());
            stopWatch.start(); // 어떤 것이 먼저 끝날지 모르므로, StopWatch를 다시 돌려줘야함
        });
    }
}
