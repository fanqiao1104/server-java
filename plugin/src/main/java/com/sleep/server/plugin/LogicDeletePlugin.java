package com.sleep.server.plugin;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * @author zhangjing
 * @date 2020/6/22
 */
public class LogicDeletePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }
    /**
     * 修改Mapper类
     */
    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        addLogicDeleteMethod(interfaze);
        return true;
    }
    private void addLogicDeleteMethod(Interface interfaze) {
        // 方法的返回值
        FullyQualifiedJavaType returnTypeInt = FullyQualifiedJavaType.getIntInstance();
        Method logicDeleteMethod = new Method("logicDelete");
        // 1.设置方法可见性
        logicDeleteMethod.setVisibility(JavaVisibility.PUBLIC);
        // 2.设置返回值类型 int类型
        logicDeleteMethod.setReturnType(returnTypeInt);
        logicDeleteMethod.setAbstract(true);
        // 3.设置参数列表
        FullyQualifiedJavaType paramType = PrimitiveTypeWrapper.getLongInstance();
        logicDeleteMethod.addParameter(new Parameter(paramType, "id"));
        interfaze.addMethod(logicDeleteMethod);
        Method logicDeleteWithUIndexMethod = new Method("logicDeleteWithUIndex");
        // 1.设置方法可见性
        logicDeleteWithUIndexMethod.setVisibility(JavaVisibility.PUBLIC);
        // 2.设置返回值类型 int类型
        logicDeleteWithUIndexMethod.setReturnType(returnTypeInt);
        logicDeleteWithUIndexMethod.setAbstract(true);
        // 3.设置参数列表
        FullyQualifiedJavaType paramTypeSelective = PrimitiveTypeWrapper.getLongInstance();
        logicDeleteWithUIndexMethod.addParameter(new Parameter(paramTypeSelective, "id", "@Param(\"id\")"));
        logicDeleteWithUIndexMethod.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "uIndexes", "@Param(\"uIndexes\")", true));
        interfaze.addMethod(logicDeleteWithUIndexMethod);
    }
    /**
     * 修改Mapper.xml
     */
    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        addLogicDeleteXml(document, introspectedTable);
        addLogicDeleteWithUIndexXml(document, introspectedTable);
        return true;
    }
    private void addLogicDeleteXml(Document document, IntrospectedTable introspectedTable) {
        XmlElement logicDeleteElement = new XmlElement("update");
        logicDeleteElement.addAttribute(new Attribute("id", "logicDelete"));
        logicDeleteElement.addAttribute(new Attribute("parameterType", "java.lang.Long"));
        logicDeleteElement.addElement(new TextElement("update " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));
        logicDeleteElement.addElement(new TextElement("set deleted = 1, update_time = REPLACE(unix_timestamp(current_timestamp(3)),'.','') where id = #{id,jdbcType=BIGINT}"));
        document.getRootElement().addElement(logicDeleteElement);
    }
    private void addLogicDeleteWithUIndexXml(Document document, IntrospectedTable introspectedTable) {
        XmlElement logicDeleteWithUIndexElement = new XmlElement("update");
        logicDeleteWithUIndexElement.addAttribute(new Attribute("id", "logicDeleteWithUIndex"));
        logicDeleteWithUIndexElement.addAttribute(new Attribute("parameterType", "map"));
        logicDeleteWithUIndexElement.addElement(new TextElement("update " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() + " A,"));
        logicDeleteWithUIndexElement.addElement(new TextElement("(select C.deleted  from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() + " C,"));
        logicDeleteWithUIndexElement.addElement(new TextElement("(select"));
        XmlElement foreachElement = new XmlElement("foreach");
        foreachElement.addAttribute(new Attribute("collection", "uIndexes"));
        foreachElement.addAttribute(new Attribute("index", "index"));
        foreachElement.addAttribute(new Attribute("item", "column"));
        foreachElement.addAttribute(new Attribute("separator", ","));
        foreachElement.addElement(new TextElement("${column}"));
        logicDeleteWithUIndexElement.addElement(foreachElement);
        logicDeleteWithUIndexElement.addElement(new TextElement("from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime() + " where id = #{id,jdbcType=BIGINT}) D"));
        XmlElement whereElement = new XmlElement("where");
        XmlElement whereForachElement = new XmlElement("foreach");
        whereForachElement.addAttribute(new Attribute("collection", "uIndexes"));
        whereForachElement.addAttribute(new Attribute("index", "index"));
        whereForachElement.addAttribute(new Attribute("item", "column"));
        whereForachElement.addElement(new TextElement(" and C.${column} = D.${column}"));
        whereElement.addElement(whereForachElement);
        logicDeleteWithUIndexElement.addElement(whereElement);
        logicDeleteWithUIndexElement.addElement(new TextElement("order by C.deleted desc limit 1) B"));
        logicDeleteWithUIndexElement.addElement(new TextElement("set A.deleted = B.deleted + 1, A.update_time = REPLACE(unix_timestamp(current_timestamp(3)),'.','')"));
        logicDeleteWithUIndexElement.addElement(new TextElement("where A.id = #{id,jdbcType=BIGINT}"));
        logicDeleteWithUIndexElement.addElement(new TextElement("and A.deleted = 0"));
        document.getRootElement().addElement(logicDeleteWithUIndexElement);
    }
}
