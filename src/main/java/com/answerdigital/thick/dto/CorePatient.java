package com.answerdigital.thick.dto;

import com.answerdigital.pcaap.dto.CoreGp;


public class CorePatient
{
    private String dateOfBirth;

    private String id;

    private String lastName;

    private String phone;

    private String title;

    private CoreAddress address;

    private String gender;

    private String nhsNumber;

    private String firstName;

    private CoreGp gp;

    private String pasNumber;

    public String getDateOfBirth ()
    {
        return dateOfBirth;
    }

    public void setDateOfBirth (String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
    }

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getLastName ()
    {
        return lastName;
    }

    public void setLastName (String lastName)
    {
        this.lastName = lastName;
    }

    public String getPhone ()
    {
        return phone;
    }

    public void setPhone (String phone)
    {
        this.phone = phone;
    }

    public String getTitle ()
    {
        return title;
    }

    public void setTitle (String title)
    {
        this.title = title;
    }

    public CoreAddress getAddress ()
    {
        return address;
    }

    public void setAddress (CoreAddress address)
    {
        this.address = address;
    }

    public String getGender ()
    {
        return gender;
    }

    public void setGender (String gender)
    {
        this.gender = gender;
    }

    public String getNhsNumber ()
    {
        return nhsNumber;
    }

    public void setNhsNumber (String nhsNumber)
    {
        this.nhsNumber = nhsNumber;
    }

    public String getFirstName ()
    {
        return firstName;
    }

    public void setFirstName (String firstName)
    {
        this.firstName = firstName;
    }

    public CoreGp getGp ()
    {
        return gp;
    }

    public void setGp (CoreGp gp)
    {
        this.gp = gp;
    }

    public String getPasNumber ()
    {
        return pasNumber;
    }

    public void setPasNumber (String pasNumber)
    {
        this.pasNumber = pasNumber;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [dateOfBirth = "+dateOfBirth+", id = "+id+", lastName = "+lastName+", phone = "+phone+", title = "+title+", address = "+address+", gender = "+gender+", nhsNumber = "+nhsNumber+", firstName = "+firstName+", gp = "+gp+", pasNumber = "+pasNumber+"]";
    }
}