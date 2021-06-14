/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.greglturnquist.payroll;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 * @author Greg Turnquist
 */
// tag::code[]
@Entity // <1>
public class Employee {

    private static final String INVALIDEMAIL = "Inserted email isn't valid";
    private static final String INVALIDSTRING = "Inserted field can't be empty";
    private @Id
    @GeneratedValue
    Long id; // <2>
    private String firstName;
    private String lastName;
    private String description;
    private String jobTitle;
    private String email;

    private Employee() {
    }

    public Employee(String firstName, String lastName, String description, String jobTitle, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.jobTitle = jobTitle;
        this.email = email;
        validateData();


    }

    private void validateData() {
        checkEmail();
        this.firstName = validateString(this.firstName);
        this.lastName = validateString(this.lastName);
        this.description = validateString(this.description);
        this.jobTitle = validateString(this.jobTitle);
    }

    /**
     * Method to validate if a String isnt empty, blank or null
     * @param string
     * @return
     */
    private String validateString(String string) {
        if (string == null) {
            return INVALIDSTRING;
        } else if (string.isEmpty() || string.trim().length() == 0) {
            return INVALIDSTRING;
        } else {
            return string;
        }
    }

    private void checkEmail() {
        if (!isValidEmail(this.email)) {
            this.email = INVALIDEMAIL;
        }
    }

    public String getEmail() {
        return email;
    }

    /**
     * Method to validate the format of a string. True if it matches a valid email format
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        if (email == null)
            return false;
        if (email.isEmpty() || email.trim().length() == 0)
            return false;
        String emailRegex = "[A-Z0-9a-z._%-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}";

        Pattern pat = Pattern.compile(emailRegex);
        return pat.matcher(email).matches();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(id, employee.id) &&
                Objects.equals(firstName, employee.firstName) &&
                Objects.equals(lastName, employee.lastName) &&
                Objects.equals(description, employee.description) &&
                Objects.equals(jobTitle, employee.jobTitle);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, description, jobTitle);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", description='" + description + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                '}';
    }
}
// end::code[]
