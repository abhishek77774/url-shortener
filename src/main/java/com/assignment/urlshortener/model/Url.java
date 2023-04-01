package com.assignment.urlshortener.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "url")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String fullUrl;
    private String shortUrl;
}
