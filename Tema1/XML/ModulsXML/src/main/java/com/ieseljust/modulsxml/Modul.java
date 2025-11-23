/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ieseljust.modulsxml;

import java.io.Serializable;

/**
 *
 * @author jasb
 */
/**
    Class to storage a single module in memory
*/

class Modul implements Serializable {
    private String nom;
    private int hores;
    private double nota;

    public Modul(){
        // Constructor buit
    }

    public Modul(String nom, int hores, double nota){
        this.nom=nom;
        this.hores=hores;
        this.nota=nota;
    }

    public String getModul() {
        return this.nom;
    }
    
    public int getHores() {
        return this.hores;
    }
    
    public double getNota() {
        return this.nota;
    }
} 

