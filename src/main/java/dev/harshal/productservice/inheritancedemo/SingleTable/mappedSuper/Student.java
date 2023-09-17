package dev.harshal.productservice.inheritancedemo.SingleTable.mappedSuper;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity(name = "st_student")
@DiscriminatorValue(value = "2")
public class Student extends User {
    private double psp;
    private double attendance;
}
