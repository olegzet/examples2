package com.olegzet.spring.ciklum;

import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.ZonedDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Math.pow;

/**
 * Created by oleg.zorin on 28.12.2017.
 */

@Service
public class MathServiceImpl implements MathService {
    private ExecutorService threadPool = Executors.newFixedThreadPool(10);
    private ConcurrentHashMap<String, Long> results = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
    }

    @Override
    public String processNumber(String id, short num) {
        threadPool.submit(() -> {
            long time = ZonedDateTime.now().toInstant().toEpochMilli();
            System.out.println(Thread.currentThread().getName() + " is started to calculate a task: " + id + ".");
            long result = calculate(num);
            results.put(id, result);
            System.out.println(Thread.currentThread().getName() + " is finished to calculate a task: " + id + ". Score: "
                    + (ZonedDateTime.now().toInstant().toEpochMilli() - time) + "ms.");
        });
        return id;
    }

    @Override
    public Map<String, Long> getResults() {
        return results;
    }

    public long getResult(String id) {
        return results.getOrDefault(id, -1L);
    }

    public long extractResult(String id) {
        Long extracted = results.remove(id);
        return (extracted != null) ? extracted : -1L;
    }

    private long calculate(short num) {
        long result = 0;
        for (int i = 0; i < num; i++) {
            result += (i * num + i * pow(num, 50) + i * pow(num, 100));
        }
        return (num > 1) ? result + calculate((short) (num - 1)) : result;
    }
}
