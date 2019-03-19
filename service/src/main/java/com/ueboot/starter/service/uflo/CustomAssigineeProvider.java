package com.ueboot.starter.service.uflo;

import com.bstek.uflo.env.Context;
import com.bstek.uflo.model.ProcessInstance;
import com.bstek.uflo.process.assign.AssigneeProvider;
import com.bstek.uflo.process.assign.Entity;
import com.bstek.uflo.process.assign.PageQuery;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * @author yangkui
 * createTime:2019/3/183:25 PM
 */
@Service
public class CustomAssigineeProvider implements AssigneeProvider {
    /**
     * 设计器层面是否要用树形结构进行展示
     *
     * @return 返回true，表示设计器会用树形加载当前任务处理人列表
     */
    @Override
    public boolean isTree() {
        return false;
    }

    /**
     * @return 返回当前任务处理人提供者名称，比如员工列表，部门列表等
     */
    @Override
    public String getName() {
        return "员工列表";
    }

    /**
     * 分页方式查询返回具体的任务处理人，可以是具体的人，也可以是部门等之类容器型对象
     *
     * @param pageQuery 用于包装分页信息的查询对象
     * @param parentId  上级实体对象的ID，可能为空
     */
    @Override
    public void queryEntities(PageQuery<Entity> pageQuery, String parentId) {
        Collection collection = this.getUserList();
        pageQuery.setResult(collection);
        pageQuery.setPageIndex(0);
        pageQuery.setPageSize(100);
    }

    /**
     * 根据指定的处理人ID，返回具体的任务处理人用户名
     *
     * @param entityId        处理人ID，可能是一个用户的用户名，这样就是直接返回这个用户名，也可能是一个部门的ID，那么就是返回这个部门下的所有用户的用户名等
     * @param context         context 流程上下文对象
     * @param processInstance 流程实例对象
     * @return 返回一个或多个任务处理人的ID
     */
    @Override
    public Collection<String> getUsers(String entityId, Context context, ProcessInstance processInstance) {
        Collection<Entity> collection = this.getUserList();
        Collection<String> users = Collections.EMPTY_LIST;
        collection.forEach((entity)->{
            users.add(entity.getName());
        });
        return users;
    }

    /**
     * @return 是否禁用当前任务处理人提供器
     */
    @Override
    public boolean disable() {
        return false;
    }

    private Collection<Entity> getUserList(){
        List<Entity> list = new ArrayList<>();
        Entity entity = new Entity("1","分公司审核人员-张三");
        Entity entity2 = new Entity("2","总公司审核人员-李四");
        Entity entity3 = new Entity("3","分公司领导审核人员-王五");
        list.add(entity);
        list.add(entity2);
        list.add(entity3);
        return  list;
    }
}
