//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.24 at 02:21:37 PM EDT 
//


package edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DataTypeField complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DataTypeField">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="table_CD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="field_CD" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="dataType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="isKey" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="isSystemSet" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="DESCR" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DataTypeField", propOrder = {
    "tableCD",
    "fieldCD",
    "dataType",
    "isKey",
    "isSystemSet",
    "descr"
})
public class DataTypeField {

    @XmlElement(name = "table_CD", required = true)
    protected String tableCD;
    @XmlElement(name = "field_CD", required = true)
    protected String fieldCD;
    @XmlElement(required = true)
    protected String dataType;
    protected boolean isKey;
    protected boolean isSystemSet;
    @XmlElement(name = "DESCR", required = true)
    protected String descr;

    /**
     * Gets the value of the tableCD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTableCD() {
        return tableCD;
    }

    /**
     * Sets the value of the tableCD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTableCD(String value) {
        this.tableCD = value;
    }

    /**
     * Gets the value of the fieldCD property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldCD() {
        return fieldCD;
    }

    /**
     * Sets the value of the fieldCD property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldCD(String value) {
        this.fieldCD = value;
    }

    /**
     * Gets the value of the dataType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * Sets the value of the dataType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDataType(String value) {
        this.dataType = value;
    }

    /**
     * Gets the value of the isKey property.
     * 
     */
    public boolean isIsKey() {
        return isKey;
    }

    /**
     * Sets the value of the isKey property.
     * 
     */
    public void setIsKey(boolean value) {
        this.isKey = value;
    }

    /**
     * Gets the value of the isSystemSet property.
     * 
     */
    public boolean isIsSystemSet() {
        return isSystemSet;
    }

    /**
     * Sets the value of the isSystemSet property.
     * 
     */
    public void setIsSystemSet(boolean value) {
        this.isSystemSet = value;
    }

    /**
     * Gets the value of the descr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDESCR() {
        return descr;
    }

    /**
     * Sets the value of the descr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDESCR(String value) {
        this.descr = value;
    }

}
