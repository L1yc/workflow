package com.lyc.workflow.listenter;

import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

/**
 * @author liyc
 */
@Component(value = "completedTestTask")
public class CustomizeListener implements JavaDelegate {
    
    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.out.println("监听捕获，可以执行一些业务逻辑");
    }
}
