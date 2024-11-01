package com.example.webservice.entities;


import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;

@XmlEnum
public enum TypeCompte {
    @XmlEnumValue("COURANT")
    COURANT,
    @XmlEnumValue("EPARGNE")
    EPARGNE
}