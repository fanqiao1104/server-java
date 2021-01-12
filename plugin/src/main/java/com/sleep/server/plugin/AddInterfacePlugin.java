package com.sleep.server.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;

import java.util.List;

/**
 * @author zhangjing
 * @date 2020/6/22
 */
public class AddInterfacePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }
    /**
     * 为每个Example类添加接口，并且实现setLimit、setOffset方法
     * @param topLevelClass
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addSuperInterface(new FullyQualifiedJavaType("com.sleep.server.dao.IExample"));
        topLevelClass.getMethods().forEach(v -> {
            if (v.getName().equals("setLimit") || v.getName().equals("setOffset") || v.getName().equals("setOrderByClause")) {
                v.addAnnotation("@Override");
            }
        });
        return true;
    }

    /**
     * 为每个Mapper继承接口
     * @param interfaze
     * @param introspectedTable
     * @return
     */
    @Override
    public boolean clientGenerated(Interface interfaze,  IntrospectedTable introspectedTable) {
        FullyQualifiedJavaType fullyQualifiedJavaType = new FullyQualifiedJavaType("com.sleep.server.dao.IMapper");
        fullyQualifiedJavaType.addTypeArgument(new FullyQualifiedJavaType(introspectedTable.getFullyQualifiedTable().getDomainObjectName()));
        interfaze.addSuperInterface(fullyQualifiedJavaType);
        return true;
    }
}
