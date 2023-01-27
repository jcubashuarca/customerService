package com.whiz.customermanagement.customerService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "clients")
public class Clients {

    @Id
    private UUID id;
    private String firstName;
    private String lastName;
    private BigDecimal age;
    private Date birthDate;
    private Date attemptDeathDate;

}