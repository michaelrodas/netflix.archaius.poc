package com.ntflxarch.pocna.controller;

import com.ntflxarch.pocna.bean.ApplicationConfig;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.*;

public class LocalTest {

    @Autowired
    private ApplicationConfig applicationConfig;

    public void testLocally(){
        ExecutorService executorService = null;
        String result;
        Instant start = null;
        Instant finish = null;
        int counter=0;
        try{
            executorService = Executors.newSingleThreadExecutor();
            result = applicationConfig.getStringProperty("hello.world.message", null);
            while (result.equals("Hello Archaius World!")){
                counter++;
                Callable<String> callableTask = () -> {
                    TimeUnit.MILLISECONDS.sleep(10000);
                    return applicationConfig.getStringProperty("hello.world.message", null);
                };

                Future<String> future = executorService.submit(callableTask); //Future
                result = future.get();
                System.out.println(String.format("The value of property %s is %s", "hello.world.message", result));
                if(counter==2){
                    Thread.sleep(2000);
                }
                if(counter==3)
                    start = Instant.now();
            }
            finish = Instant.now();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }
        finally {
            long duration = Duration.between(start, finish).toMillis()/1000;
            if(executorService!= null){ //risk of NullPointer
                //executorService.shutdown();
                List<Runnable> notExecutedTasks = executorService.shutdownNow();
                System.out.println("Shutdown size: " + notExecutedTasks.size());
                System.out.println("Elapsed time to read change: " + duration);
            }
        }
    }


}
