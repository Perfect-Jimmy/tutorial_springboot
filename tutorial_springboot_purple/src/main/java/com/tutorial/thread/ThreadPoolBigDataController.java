package com.tutorial.thread;

import com.tutorial.configuration.GlobalExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:Jimmy
 * @DATE:17:13 2017/12/11
 */
@RestController
public class ThreadPoolBigDataController {
    private static final Logger logger = LoggerFactory.getLogger(ThreadPoolBigDataController.class);
    @Autowired
    private ThreadPoolTaskExecutor taskExecutor;


    @RequestMapping("/bigData")
    public String  bigData(){
        Long start = System.currentTimeMillis();
        logger.info("big data start..");
        for(int i=1;i<500;i++){
            final int num = i;
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(1000*6);
                        logger.info(Thread.currentThread().getName()+"--执行"+num+"--active:"+taskExecutor.getActiveCount()+"--poolSize:"+taskExecutor.getPoolSize());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        logger.info("end "+(System.currentTimeMillis()-start));
        return "success";
    }
}
