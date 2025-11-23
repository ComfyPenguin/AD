/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ad.ieseljust.monaco2017_schedule;


import org.w3c.dom.Element;

/**
 *
 * @author jasb
 */

/**
 * POJO class
 */
public class Driver {
    private int number;
    private String name;
    private String surname;
    private String nationality;
    
    /** Constructor
    Método que carga una estructura conductor, segun el esquema

    <Driver driverId="hamilton" code="HAM" url="http://en.wikipedia.org/wiki/Lewis_Hamilton">
        <PermanentNumber>44</PermanentNumber>
        <GivenName>Lewis</GivenName>
        <FamilyName>Hamilton</FamilyName>
        <Nationality>British</Nationality>
    </Driver>
    */
    public Driver(Element driver){
        this.name=driver.getElementsByTagName("GivenName").item(0).getTextContent();
        this.surname=driver.getElementsByTagName("FamilyName").item(0).getTextContent();
        this.number=Integer.parseInt(driver.getElementsByTagName("PermanentNumber").item(0).getTextContent());
        this.nationality=driver.getElementsByTagName("Nationality").item(0).getTextContent();
    }

    @Override
    public String toString() {
        return "Driver{" + "number=" + number + ": name=" + name + " surname=" + surname + ", nationality=" + nationality + '}';
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNationality() {
        return nationality;
    }
    
   
              
}

