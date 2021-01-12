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
 * @date 2020/7/13
 */
public class SelectSelectivePlugin extends PluginAdapter {
    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    @Override
    public boolean clientGenerated(Interface interfaze, IntrospectedTable introspectedTable) {
        addSelectByPrimaryKeySelectiveMethod(interfaze, introspectedTable);
        addSelectOneByExampleSelectiveMethod(interfaze, introspectedTable);
        addSelectByExampleSelectiveMethod(interfaze, introspectedTable);
        return true;
    }

    private void addSelectByPrimaryKeySelectiveMethod(Interface interfaze, IntrospectedTable introspectedTable) {
        // selectByPrimaryKeySelective(@Param("id") Long id, @Param("selective") String ... selective);
        Method selectByPrimaryKeySelectiveMethod = new Method("selectByPrimaryKeySelective");
        // 1.设置方法可见性
        selectByPrimaryKeySelectiveMethod.setVisibility(JavaVisibility.PUBLIC);
        // 2.设置返回值类型
        selectByPrimaryKeySelectiveMethod.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        selectByPrimaryKeySelectiveMethod.setAbstract(true);
        // 4.设置参数列表
        selectByPrimaryKeySelectiveMethod.addParameter(new Parameter(introspectedTable.getPrimaryKeyColumns().get(0).getFullyQualifiedJavaType(), "id", "@Param(\"id\")"));
        selectByPrimaryKeySelectiveMethod.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "selective", "@Param(\"selective\")", true));
        interfaze.addMethod(selectByPrimaryKeySelectiveMethod);
    }

    private void addSelectOneByExampleSelectiveMethod(Interface interfaze, IntrospectedTable introspectedTable) {
        // selectOneByExampleSelective(@Param("example") WaybillExample example, @Param("selective") String ... selective);
        Method selectOneBySelectiveMethod = new Method("selectOneByExampleSelective");
        // 1.设置方法可见性
        selectOneBySelectiveMethod.setVisibility(JavaVisibility.PUBLIC);
        // 2.设置返回值类型
        selectOneBySelectiveMethod.setReturnType(new FullyQualifiedJavaType(introspectedTable.getBaseRecordType()));
        selectOneBySelectiveMethod.setAbstract(true);
        // 4.设置参数列表
        selectOneBySelectiveMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example", "@Param(\"example\")"));
        selectOneBySelectiveMethod.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "selective", "@Param(\"selective\")", true));
        interfaze.addMethod(selectOneBySelectiveMethod);
    }

    private void addSelectByExampleSelectiveMethod(Interface interfaze, IntrospectedTable introspectedTable) {
        Method selectByExampleSelectiveMethod = new Method("selectByExampleSelective");
        // 1.设置方法可见性
        selectByExampleSelectiveMethod.setVisibility(JavaVisibility.PUBLIC);
        // 2.设置返回值类型
        FullyQualifiedJavaType paramType = FullyQualifiedJavaType.getNewListInstance();
        FullyQualifiedJavaType paramListType = new FullyQualifiedJavaType(introspectedTable.getBaseRecordType());
        paramType.addTypeArgument(paramListType);
        selectByExampleSelectiveMethod.setReturnType(paramType);
        selectByExampleSelectiveMethod.setAbstract(true);
        // 4.设置参数列表
        selectByExampleSelectiveMethod.addParameter(new Parameter(new FullyQualifiedJavaType(introspectedTable.getExampleType()), "example", "@Param(\"example\")"));
        selectByExampleSelectiveMethod.addParameter(new Parameter(FullyQualifiedJavaType.getStringInstance(), "selective", "@Param(\"selective\")", true));
        interfaze.addMethod(selectByExampleSelectiveMethod);
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {
        addSelectByPrimaryKeySelectiveXml(document, introspectedTable);
        addSelectOneByExampleSelectiveXml(document, introspectedTable);
        addSelectExampleSelectiveXml(document, introspectedTable);
        return true;
    }

    private void addSelectByPrimaryKeySelectiveXml(Document document, IntrospectedTable introspectedTable) {
        XmlElement selectElement = new XmlElement("select");
        selectElement.addAttribute(new Attribute("id", "selectByPrimaryKeySelective"));
        selectElement.addAttribute(new Attribute("parameterType", "map"));
        selectElement.addAttribute(new Attribute("resultMap", "BaseResultMap"));

        XmlElement chooseElement = new XmlElement("choose");
        XmlElement whenElement = new XmlElement("when");
        whenElement.addAttribute(new Attribute("test", "selective != null and selective.length > 0"));
        XmlElement foreachColumn = new XmlElement("foreach");
        foreachColumn.addAttribute(new Attribute("collection", "selective"));
        foreachColumn.addAttribute(new Attribute("item", "column"));
        foreachColumn.addAttribute(new Attribute("separator", ","));
        foreachColumn.addElement(new TextElement("${column}"));
        whenElement.addElement(foreachColumn);
        XmlElement otherwiseElement = new XmlElement("otherwise");
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", "Base_Column_List"));
        otherwiseElement.addElement(includeElement);
        chooseElement.addElement(whenElement);
        chooseElement.addElement(otherwiseElement);

        selectElement.addElement(new TextElement("select"));
        selectElement.addElement(chooseElement);
        selectElement.addElement(new TextElement("from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));
        selectElement.addElement(new TextElement("where id = #{id,jdbcType=BIGINT}"));
        document.getRootElement().addElement(selectElement);
    }

    private void addSelectOneByExampleSelectiveXml(Document document, IntrospectedTable introspectedTable) {
        XmlElement selectElement = new XmlElement("select");
        selectElement.addAttribute(new Attribute("id", "selectOneByExampleSelective"));
        selectElement.addAttribute(new Attribute("parameterType", "map"));
        selectElement.addAttribute(new Attribute("resultMap", "BaseResultMap"));

        XmlElement chooseElement = new XmlElement("choose");
        XmlElement whenElement = new XmlElement("when");
        whenElement.addAttribute(new Attribute("test", "selective != null and selective.length > 0"));
        XmlElement foreachColumn = new XmlElement("foreach");
        foreachColumn.addAttribute(new Attribute("collection", "selective"));
        foreachColumn.addAttribute(new Attribute("item", "column"));
        foreachColumn.addAttribute(new Attribute("separator", ","));
        foreachColumn.addElement(new TextElement("${column}"));
        whenElement.addElement(foreachColumn);
        XmlElement otherwiseElement = new XmlElement("otherwise");
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", "Base_Column_List"));
        otherwiseElement.addElement(includeElement);
        chooseElement.addElement(whenElement);
        chooseElement.addElement(otherwiseElement);

        selectElement.addElement(new TextElement("select"));
        selectElement.addElement(chooseElement);
        selectElement.addElement(new TextElement("from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));

        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "example != null"));
        XmlElement includeWhereElement = new XmlElement("include");
        includeWhereElement.addAttribute(new Attribute("refid", "Update_By_Example_Where_Clause"));
        ifElement.addElement(includeWhereElement);
        XmlElement ifOrderByElement = new XmlElement("if");
        ifOrderByElement.addAttribute(new Attribute("test", "example != null and example.orderByClause != null"));
        ifOrderByElement.addElement(new TextElement("order by ${example.orderByClause}"));

        selectElement.addElement(ifElement);
        selectElement.addElement(ifOrderByElement);
        selectElement.addElement(new TextElement("limit 1"));
        document.getRootElement().addElement(selectElement);
    }

    private void addSelectExampleSelectiveXml(Document document, IntrospectedTable introspectedTable) {
        XmlElement selectElement = new XmlElement("select");
        selectElement.addAttribute(new Attribute("id", "selectByExampleSelective"));
        selectElement.addAttribute(new Attribute("parameterType", "map"));
        selectElement.addAttribute(new Attribute("resultMap", "BaseResultMap"));

        selectElement.addElement(new TextElement("select"));

        XmlElement ifDistinct = new XmlElement("if");
        ifDistinct.addAttribute(new Attribute("test", "example != null and example.distinct"));
        ifDistinct.addElement(new TextElement("distinct"));
        selectElement.addElement(ifDistinct);

        XmlElement chooseElement = new XmlElement("choose");
        XmlElement whenElement = new XmlElement("when");
        whenElement.addAttribute(new Attribute("test", "selective != null and selective.length > 0"));
        XmlElement foreachColumn = new XmlElement("foreach");
        foreachColumn.addAttribute(new Attribute("collection", "selective"));
        foreachColumn.addAttribute(new Attribute("item", "column"));
        foreachColumn.addAttribute(new Attribute("separator", ","));
        foreachColumn.addElement(new TextElement("${column}"));
        whenElement.addElement(foreachColumn);
        XmlElement otherwiseElement = new XmlElement("otherwise");
        XmlElement includeElement = new XmlElement("include");
        includeElement.addAttribute(new Attribute("refid", "Base_Column_List"));
        otherwiseElement.addElement(includeElement);
        chooseElement.addElement(whenElement);
        chooseElement.addElement(otherwiseElement);

        selectElement.addElement(chooseElement);
        selectElement.addElement(new TextElement("from " + introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime()));
        XmlElement ifElement = new XmlElement("if");
        ifElement.addAttribute(new Attribute("test", "example != null"));
        XmlElement includeWhereElement = new XmlElement("include");
        includeWhereElement.addAttribute(new Attribute("refid", "Update_By_Example_Where_Clause"));
        ifElement.addElement(includeWhereElement);
        XmlElement ifOrderByElement = new XmlElement("if");
        ifOrderByElement.addAttribute(new Attribute("test", "example != null and example.orderByClause != null"));
        ifOrderByElement.addElement(new TextElement("order by ${example.orderByClause}"));

        XmlElement ifLimitNotNullElement = new XmlElement("if");
        ifLimitNotNullElement.addAttribute(new Attribute("test", "example != null and example.limit != null"));
        XmlElement ifOffsetNotNullElement = new XmlElement("if");
        ifOffsetNotNullElement.addAttribute(new Attribute("test", "example.offset != null"));
        ifOffsetNotNullElement.addElement(new TextElement("limit ${example.offset}, ${example.limit}"));
        ifLimitNotNullElement.addElement(ifOffsetNotNullElement);
        XmlElement ifOffsetNullElement = new XmlElement("if");
        ifOffsetNullElement.addAttribute(new Attribute("test", "example.offset == null"));
        ifOffsetNullElement.addElement(new TextElement("limit ${example.limit}"));
        ifLimitNotNullElement.addElement(ifOffsetNullElement);

        selectElement.addElement(ifElement);
        selectElement.addElement(ifOrderByElement);
        selectElement.addElement(ifLimitNotNullElement);
        document.getRootElement().addElement(selectElement);
    }
}
