//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.03.27 at 11:59:19 AM EDT 
//


package edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FieldColumnMatch complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FieldColumnMatch">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataField" type="{http://www.fsu.edu/data/datavo/vdo/}DataTypeField"/>
 *         &lt;element name="Columns" type="{http://www.fsu.edu/data/datavo/vdo/}ColumnMatch"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FieldColumnMatch", propOrder = {
    "dataField",
    "columns"
})
public class FieldColumnMatch {

    @XmlElement(name = "DataField", required = true)
    protected DataTypeField dataField;
    @XmlElement(name = "Columns", required = true)
    protected ColumnMatch columns;

    /**
     * Gets the value of the dataField property.
     * 
     * @return
     *     possible object is
     *     {@link DataTypeField }
     *     
     */
    public DataTypeField getDataField() {
        return dataField;
    }

    /**
     * Sets the value of the dataField property.
     * 
     * @param value
     *     allowed object is
     *     {@link DataTypeField }
     *     
     */
    public void setDataField(DataTypeField value) {
        this.dataField = value;
    }

    /**
     * Gets the value of the columns property.
     * 
     * @return
     *     possible object is
     *     {@link ColumnMatch }
     *     
     */
    public ColumnMatch getColumns() {
        return columns;
    }

    /**
     * Sets the value of the columns property.
     * 
     * @param value
     *     allowed object is
     *     {@link ColumnMatch }
     *     
     */
    public void setColumns(ColumnMatch value) {
        this.columns = value;
    }

}
