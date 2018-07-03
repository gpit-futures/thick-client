package com.answerdigital.thick.dto;


public class CoreGp
{
    private CoreAddress address;

    private String name;

    public CoreAddress getAddress ()
    {
        return address;
    }

    public void setAddress (CoreAddress address)
    {
        this.address = address;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [address = "+address+", name = "+name+"]";
    }
}
