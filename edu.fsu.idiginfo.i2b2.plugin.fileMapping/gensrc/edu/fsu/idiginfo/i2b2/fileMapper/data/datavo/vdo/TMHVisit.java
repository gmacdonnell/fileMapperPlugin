//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.2-b01-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.05.06 at 03:37:38 PM EDT 
//


package edu.fsu.idiginfo.i2b2.fileMapper.data.datavo.vdo;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TMH_Visit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TMH_Visit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HSP_MD_NUM" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="chf_cmpln" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="encounterdiagdescr" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="lchl_ntk" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smk_hw_mch" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smkng_frmr_smkrs_qt_tm" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="smkng_stts" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tbcc_yrs_fs" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="tbcc_yrs_fs_dt" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="surg_hist_proc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="visit_id" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="visit_status" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TMH_Visit", propOrder = {
    "hspmdnum",
    "chfCmpln",
    "encounterdiagdescr",
    "lchlNtk",
    "smkHwMch",
    "smkngFrmrSmkrsQtTm",
    "smkngStts",
    "tbccYrsFs",
    "tbccYrsFsDt",
    "surgHistProc",
    "visitId",
    "visitStatus"
})
public class TMHVisit {

    @XmlElement(name = "HSP_MD_NUM", required = true)
    protected String hspmdnum;
    @XmlElement(name = "chf_cmpln")
    protected List<String> chfCmpln;
    @XmlElement(required = true)
    protected String encounterdiagdescr;
    @XmlElement(name = "lchl_ntk", required = true)
    protected String lchlNtk;
    @XmlElement(name = "smk_hw_mch", required = true)
    protected String smkHwMch;
    @XmlElement(name = "smkng_frmr_smkrs_qt_tm", required = true)
    protected String smkngFrmrSmkrsQtTm;
    @XmlElement(name = "smkng_stts", required = true)
    protected String smkngStts;
    @XmlElement(name = "tbcc_yrs_fs", required = true)
    protected String tbccYrsFs;
    @XmlElement(name = "tbcc_yrs_fs_dt", required = true)
    protected String tbccYrsFsDt;
    @XmlElement(name = "surg_hist_proc", required = true)
    protected String surgHistProc;
    @XmlElement(name = "visit_id", required = true)
    protected String visitId;
    @XmlElement(name = "visit_status", required = true)
    protected String visitStatus;

    /**
     * Gets the value of the hspmdnum property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHSPMDNUM() {
        return hspmdnum;
    }

    /**
     * Sets the value of the hspmdnum property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHSPMDNUM(String value) {
        this.hspmdnum = value;
    }

    /**
     * Gets the value of the chfCmpln property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the chfCmpln property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getChfCmpln().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getChfCmpln() {
        if (chfCmpln == null) {
            chfCmpln = new ArrayList<String>();
        }
        return this.chfCmpln;
    }

    /**
     * Gets the value of the encounterdiagdescr property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncounterdiagdescr() {
        return encounterdiagdescr;
    }

    /**
     * Sets the value of the encounterdiagdescr property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncounterdiagdescr(String value) {
        this.encounterdiagdescr = value;
    }

    /**
     * Gets the value of the lchlNtk property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLchlNtk() {
        return lchlNtk;
    }

    /**
     * Sets the value of the lchlNtk property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLchlNtk(String value) {
        this.lchlNtk = value;
    }

    /**
     * Gets the value of the smkHwMch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmkHwMch() {
        return smkHwMch;
    }

    /**
     * Sets the value of the smkHwMch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmkHwMch(String value) {
        this.smkHwMch = value;
    }

    /**
     * Gets the value of the smkngFrmrSmkrsQtTm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmkngFrmrSmkrsQtTm() {
        return smkngFrmrSmkrsQtTm;
    }

    /**
     * Sets the value of the smkngFrmrSmkrsQtTm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmkngFrmrSmkrsQtTm(String value) {
        this.smkngFrmrSmkrsQtTm = value;
    }

    /**
     * Gets the value of the smkngStts property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSmkngStts() {
        return smkngStts;
    }

    /**
     * Sets the value of the smkngStts property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSmkngStts(String value) {
        this.smkngStts = value;
    }

    /**
     * Gets the value of the tbccYrsFs property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTbccYrsFs() {
        return tbccYrsFs;
    }

    /**
     * Sets the value of the tbccYrsFs property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTbccYrsFs(String value) {
        this.tbccYrsFs = value;
    }

    /**
     * Gets the value of the tbccYrsFsDt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTbccYrsFsDt() {
        return tbccYrsFsDt;
    }

    /**
     * Sets the value of the tbccYrsFsDt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTbccYrsFsDt(String value) {
        this.tbccYrsFsDt = value;
    }

    /**
     * Gets the value of the surgHistProc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSurgHistProc() {
        return surgHistProc;
    }

    /**
     * Sets the value of the surgHistProc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSurgHistProc(String value) {
        this.surgHistProc = value;
    }

    /**
     * Gets the value of the visitId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitId() {
        return visitId;
    }

    /**
     * Sets the value of the visitId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitId(String value) {
        this.visitId = value;
    }

    /**
     * Gets the value of the visitStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVisitStatus() {
        return visitStatus;
    }

    /**
     * Sets the value of the visitStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVisitStatus(String value) {
        this.visitStatus = value;
    }

}
