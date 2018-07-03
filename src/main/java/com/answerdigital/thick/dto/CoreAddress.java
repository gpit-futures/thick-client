package com.answerdigital.thick.dto;


public class CoreAddress
{

    private String postcode;

    private String line1;

    private String line3;

    private String line2;
    
    private String line4;

    public String getLine4 ()
    {
        return line4;
    }

    public void setLine4 (String line4)
    {
        this.line4 = line4;
    }

    public String getPostcode ()
    {
        return postcode;
    }

    public void setPostcode (String postcode)
    {
        this.postcode = postcode;
    }

    public String getLine1 ()
    {
        return line1;
    }

    public void setLine1 (String line1)
    {
        this.line1 = line1;
    }

    public String getLine3 ()
    {
        return line3;
    }

    public void setLine3 (String line3)
    {
        this.line3 = line3;
    }

    public String getLine2 ()
    {
        return line2;
    }

    public void setLine2 (String line2)
    {
        this.line2 = line2;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [line4 = "+line4+", postcode = "+postcode+", line1 = "+line1+", line3 = "+line3+", line2 = "+line2+"]";
    }
}
