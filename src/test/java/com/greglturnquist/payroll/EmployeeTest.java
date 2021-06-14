package com.greglturnquist.payroll;


import com.greglturnquist.payroll.Employee;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class EmployeeTest {

    String INVALIDEMAIL = "Inserted email isn't valid";
    String INVALIDSTRING = "Inserted field can't be empty";

    @DisplayName("Assert a valid email is accepted")
    @Test
    void shouldBeSameCreateEmployeeWithValidEmail() {
        String expected = "email@domain.com";
        Employee employee = new Employee("Legolas", "Thranduilion", "Elf", "Fighter", "email@domain.com");
        String result = employee.getEmail();
        assertEquals(expected, result);

    }

    @DisplayName("Assert a null, blank or empty email is replaced with the error message")
    @ParameterizedTest
    @ValueSource(strings = {"   "})
    @NullAndEmptySource
    void shouldBeSameNullBlankEmptyEmailReplacedByError(String value) {
        String expected = INVALIDEMAIL;
        Employee employee = new Employee("Gimli", "son of Gloin", "Dwarf", "Barbarian", value);
        String result = employee.getEmail();
        assertEquals(expected, result);
    }


    @DisplayName("Assert an invalid email is replaced with the error message")
    @ParameterizedTest
    @ValueSource(strings = {"aragorn", "aragorn.middleearth.com", "aragorn@@rivendel.me", "!aragorn@rivendel.me"})
    @NullAndEmptySource
    void shouldBeSameInvalidEmailReplacedByError(String value) {
        String expected = INVALIDEMAIL;
        Employee employee = new Employee("Aragorn", "Telcontar", "Human", "Ranger", value);
        String result = employee.getEmail();
        assertEquals(expected, result);
    }


    @DisplayName("Assert a valid first name is accepted")
    @Test
    void shouldBeSameCreateEmployeeWithValidFirstname() {
        String expected = "Legolas";
        Employee employee = new Employee("Legolas", "Thranduilion", "Elf", "Fighter", "email@domain.com");
        String result = employee.getFirstName();
        assertEquals(expected, result);

    }

    @DisplayName("Assert a null, blank or empty first name is replaced with the error message")
    @ParameterizedTest
    @ValueSource(strings = {"   "})
    @NullAndEmptySource
    void shouldBeSameNullBlankEmptyFirstNameReplacedByError(String value) {
        String expected = INVALIDSTRING;
        Employee employee = new Employee(value, "son of Gloin", "Dwarf", "Barbarian", "gimli@minastirith.me");
        String result = employee.getFirstName();
        assertEquals(expected, result);
    }

    @DisplayName("Assert a valid last name is accepted")
    @Test
    void shouldBeSameCreateEmployeeWithValidLastName() {
        String expected = "Thranduilion";
        Employee employee = new Employee("Legolas", "Thranduilion", "Elf", "Fighter", "email@domain.com");
        String result = employee.getLastName();
        assertEquals(expected, result);

    }

    @DisplayName("Assert a null, blank or empty last name is replaced with the error message")
    @ParameterizedTest
    @ValueSource(strings = {"   "})
    @NullAndEmptySource
    void shouldBeSameNullBlankEmptylastNameReplacedByError(String value) {
        String expected = INVALIDSTRING;
        Employee employee = new Employee("Gimli", value, "Dwarf", "Barbarian", "gimli@minastirith.me");
        String result = employee.getLastName();
        assertEquals(expected, result);
    }

    @DisplayName("Assert a valid description is accepted")
    @Test
    void shouldBeSameCreateEmployeeWithValidDescription() {
        String expected = "Elf";
        Employee employee = new Employee("Legolas", "Thranduilion", "Elf", "Fighter", "email@domain.com");
        String result = employee.getDescription();
        assertEquals(expected, result);

    }

    @DisplayName("Assert a null, blank or empty description is replaced with the error message")
    @ParameterizedTest
    @ValueSource(strings = {"   "})
    @NullAndEmptySource
    void shouldBeSameNullBlankEmptyDescriptionReplacedByError(String value) {
        String expected = INVALIDSTRING;
        Employee employee = new Employee("Gimli", "son of Gloin", value, "Barbarian", "gimli@minastirith.me");
        String result = employee.getDescription();
        assertEquals(expected, result);
    }

    @DisplayName("Assert a valid job title is accepted")
    @Test
    void shouldBeSameCreateEmployeeWithValidJobTitle() {
        String expected = "Fighter";
        Employee employee = new Employee("Legolas", "Thranduilion", "Elf", "Fighter", "email@domain.com");
        String result = employee.getJobTitle();
        assertEquals(expected, result);

    }

    @DisplayName("Assert a null, blank or empty job title is replaced with the error message")
    @ParameterizedTest
    @ValueSource(strings = {"   "})
    @NullAndEmptySource
    void shouldBeSameNullBlankEmptyJobTitleReplacedByError(String value) {
        String expected = INVALIDSTRING;
        Employee employee = new Employee("Gimli", "son of Gloin", "Dwarf", value, "gimli@minastirith.me");
        String result = employee.getJobTitle();
        assertEquals(expected, result);
    }


}

