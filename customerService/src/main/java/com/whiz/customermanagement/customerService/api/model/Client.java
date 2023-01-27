package com.whiz.customermanagement.customerService.api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.whiz.customermanagement.customerService.config.validator.BirthDateConstraint;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @NotBlank(message = "El nombre es requerido")
    private String name;

    @NotBlank(message = "El apellido es requerido")
    private String lastName;

    @Min(value = 1, message = "La edad minima es 1")
    @Max(value = 150, message = "La edad maxima debe ser menor a 150")
    private int age;

    @NotBlank(message = "La fecha de nacimiento es requerida")
    @BirthDateConstraint
    private String birthDate;

    private String attemptDeathDate;

}
