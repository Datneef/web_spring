package com.spring.entities;

import com.spring.valiation.CreateGroup;
import com.spring.valiation.UpdateGroup;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.ISBN;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@ToString
@XmlRootElement(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "{Book.blank.title}", groups = {UpdateGroup.class, CreateGroup.class})
    @Column(columnDefinition = "NVARCHAR(50) NOT NULL")
    private String title;

    private String image;

//    @ISBN(groups = {UpdateGroup.class, CreateGroup.class})
//    private String isbn;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal price;

}