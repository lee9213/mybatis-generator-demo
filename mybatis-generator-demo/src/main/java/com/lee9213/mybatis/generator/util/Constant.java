package com.lee9213.mybatis.generator.util;


import java.nio.charset.Charset;

/**
 * <p>常量类</p>
 *
 * @author lee9213@163.com
 * @version 1.0
 * @date 2018-10-13 23:28
 */
public interface Constant {
    String AMPERSAND = "&";
    String AND = "and";
    String AT = "@";
    String ASTERISK = "*";
    String STAR = ASTERISK;
    String BACK_SLASH = "\\";
    String COLON = ":";
    String COMMA = ",";
    String DASH = "-";
    String DOLLAR = "$";
    String DOT = ".";
    String DOTDOT = "..";
    String DOT_CLASS = ".class";
    String DOT_JAVA = ".java";
    String DOT_XML = ".xml";
    String EMPTY = "";
    String EQUALS = "=";
    String FALSE = "false";
    String SLASH = "/";
    String HASH = "#";
    String HAT = "^";
    String LEFT_BRACE = "{";
    String LEFT_BRACKET = "(";
    String LEFT_CHEV = "<";
    String NEWLINE = "\n";
    String N = "n";
    String NO = "no";
    String NULL = "null";
    String OFF = "off";
    String ON = "on";
    String PERCENT = "%";
    String PIPE = "|";
    String PLUS = "+";
    String QUESTION_MARK = "?";
    String EXCLAMATION_MARK = "!";
    String QUOTE = "\"";
    String RETURN = "\r";
    String TAB = "\t";
    String RIGHT_BRACE = "}";
    String RIGHT_BRACKET = ")";
    String RIGHT_CHEV = ">";
    String SEMICOLON = ";";
    String SINGLE_QUOTE = "'";
    String BACKTICK = "`";
    String SPACE = " ";
    String TILDA = "~";
    String LEFT_SQ_BRACKET = "[";
    String RIGHT_SQ_BRACKET = "]";
    String TRUE = "true";
    String UNDERSCORE = "_";
    String UTF_8 = "UTF-8";
    String US_ASCII = "US-ASCII";
    String ISO_8859_1 = "ISO-8859-1";
    String Y = "y";
    String YES = "yes";
    String ONE = "1";
    String ZERO = "0";
    String DOLLAR_LEFT_BRACE = "${";
    String HASH_LEFT_BRACE = "#{";
    String CRLF = "\r\n";

    String HTML_NBSP = "&nbsp;";
    String HTML_AMP = "&amp";
    String HTML_QUOTE = "&quot;";
    String HTML_LT = "&lt;";
    String HTML_GT = "&gt;";

    // ---------------------------------------------------------------- array

    String[] EMPTY_ARRAY = new String[0];

    byte[] BYTES_NEW_LINE = NEWLINE.getBytes();

    String MODULE_NAME = "ModuleName";

    String GENERATOR = "Generator";
    String GENERATOR_NAME = "generatorConfig.xml";
    String ENTITY = "Entity";
    String VO = "Vo";
    String SERVICE = "Service";
    String SERVICE_IMPL = "ServiceImpl";
    String MAPPER = "Mapper";
    String MAPPER_XML = "Mapper";
    String EXTEND_MAPPER_XML = "ExtendMapper";
    String CONTROLLER = "Controller";

    String GENERATOR_PATH = "generator_path";
    String VO_PATH = "vo_path";
    String ENTITY_PATH = "entity_path";
    String SERVICE_PATH = "service_path";
    String SERVICE_IMPL_PATH = "service_impl_path";
    String MAPPER_PATH = "mapper_path";
    String MAPPER_XML_PATH = "xml_path";
    String CONTROLLER_PATH = "controller_path";

    String JAVA_TMPDIR = "java.io.tmpdir";
    String UTF8 = Charset.forName("UTF-8").name();
    String UNDERLINE = "_";

    String JAVA_SUFFIX = DOT_JAVA;
    String KT_SUFFIX = ".kt";
    String XML_SUFFIX = ".xml";

    String GENERATOR_CONFIG = "/templates/generatorConfig.xml.ftl";
    String TEMPLATE_ENTITY_JAVA = "/templates/entity.java.ftl";
    String TEMPLATE_VO_JAVA = "/templates/vo.java.ftl";
    String TEMPLATE_MAPPER = "/templates/mapper.java.ftl";
    String TEMPLATE_MAPPER_XML = "/templates/mapper.xml.ftl";
    String TEMPLATE_EXTEND_MAPPER_XML = "/templates/extend.mapper.xml.ftl";
    String TEMPLATE_SERVICE = "/templates/service.java.ftl";
    String TEMPLATE_SERVICE_IMPL = "/templates/serviceImpl.java.ftl";
    String TEMPLATE_CONTROLLER = "/templates/controller.java.ftl";

    String VM_LOAD_PATH_KEY = "file.resource.loader.class";
    String VM_LOAD_PATH_VALUE = "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader";

    String SUPER_MAPPER_CLASS = "com.lee9213.core.mapper.BaseMapper";
    String SUPER_SERVICE_CLASS = "com.lee9213.extension.service.IService";
    String SUPER_SERVICE_IMPL_CLASS = "com.lee9213.extension.service.impl.ServiceImpl";


}
