//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.01.30 at 10:43:09 AM EST 
//


package fileMapper.data.datavo.pdo.query;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for constrainValueType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="constrainValueType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="NUMBER"/>
 *     &lt;enumeration value="TEXT"/>
 *     &lt;enumeration value="LARGETEXT"/>
 *     &lt;enumeration value="FLAG"/>
 *     &lt;enumeration value="MODIFIER"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "constrainValueType")
@XmlEnum
public enum ConstrainValueType {

    NUMBER,
    TEXT,
    LARGETEXT,
    FLAG,
    MODIFIER;

    public String value() {
        return name();
    }

    public static ConstrainValueType fromValue(String v) {
        return valueOf(v);
    }

}