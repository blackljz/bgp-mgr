package com.bgp.mgr.common.utils;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.codegen.AbstractJavaGenerator;

import java.util.List;

public class MysqlLimitPlugin extends PluginAdapter {

    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        addLimit(topLevelClass, introspectedTable, "limitStart");
        addLimit(topLevelClass, introspectedTable, "limitSize");
        return super.modelExampleClassGenerated(topLevelClass, introspectedTable);
    }

    /**
     * 为selectByExample添加limitStart和limitSize
     */
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", "limitStart != null and limitSize >= 0"));
        isNotNullElement.addElement(new TextElement("limit #{limitStart} , #{limitSize}"));
        element.addElement(isNotNullElement);
        return super.sqlMapSelectByExampleWithoutBLOBsElementGenerated(element, introspectedTable);
    }

    /**
     * 为selectByExampleWithBLOBs添加limitStart和limitSize
     */
    @Override
    public boolean sqlMapSelectByExampleWithBLOBsElementGenerated(XmlElement element, IntrospectedTable introspectedTable) {
        XmlElement isNotNullElement = new XmlElement("if");
        isNotNullElement.addAttribute(new Attribute("test", "limitStart != null and limitSize >= 0"));
        isNotNullElement.addElement(new TextElement("limit #{limitStart} , #{limitSize}"));
        element.addElement(isNotNullElement);
        return super.sqlMapSelectByExampleWithBLOBsElementGenerated(element, introspectedTable);
    }

    private void addLimit(TopLevelClass topLevelClass, IntrospectedTable introspectedTable, String name) {

        CommentGenerator commentGenerator = context.getCommentGenerator();

        /* 创建类成员变量 如protected Integer limitStart; */
        Field field = new Field();
        field.setVisibility(JavaVisibility.PROTECTED);
        field.setType(PrimitiveTypeWrapper.getIntegerInstance());
        field.setName(name);
        commentGenerator.addFieldComment(field, introspectedTable);
        topLevelClass.addField(field);
        /* 首字母大写 */
        char c = name.charAt(0);
        String camel = Character.toUpperCase(c) + name.substring(1);

        /* 添加Setter方法 */
        Method method = new Method();
        method.setVisibility(JavaVisibility.PUBLIC);
        method.setName("set" + camel);
        method.addParameter(new Parameter(PrimitiveTypeWrapper.getIntegerInstance(), name));

        /* 如 this.limitStart = limitStart; */
        method.addBodyLine("this." + name + " = " + name + ";");

        commentGenerator.addGeneralMethodComment(method, introspectedTable);
        topLevelClass.addMethod(method);

        /* 添加Getter Method 直接调用AbstractJavaGenerator的getGetter方法 */
        Method getterMethod = AbstractJavaGenerator.getGetter(field);
        commentGenerator.addGeneralMethodComment(getterMethod, introspectedTable);
        topLevelClass.addMethod(getterMethod);
    }

    public boolean validate(List<String> warnings) {
        return true;
    }

}