package com.ueboot.starter.service.uflo;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.handler.NodeEventHandler;
import com.bstek.uflo.process.node.Node;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 提交入司申请事件监听
 * @author yangkui
 * createTime:2019/3/183:21 PM
 */
@Service
@Slf4j
public class SubmitApplicationEventHandler implements NodeEventHandler {
    /**
     * 进入节点后触发的方法
     *
     * @param node            当前节点对象
     * @param processInstance 当前流程实例对象
     * @param context         流程上下文
     */
    @Override
    public void enter(Node node, ProcessInstance processInstance, Context context) {
        //TODO 保存数据
        log.info("进入提交入司申请流程");
    }

    /**
     * 离开节点后触发的方法
     *
     * @param node            当前节点对象
     * @param processInstance 当前流程实例对象
     * @param context         流程上下文
     */
    @Override
    public void leave(Node node, ProcessInstance processInstance, Context context) {
        log.info("离开提交入司申请流程");

    }
}
