import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Char1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Char1 extends Character
{
    String hairColor = hairColorOptions[2];
    String hairLength = hairLengthOptions[2];
    Boolean hasHat = getHasHat(0);
    Boolean hasCoat = getHasCoat(1);
    Boolean hasSpecs = getHasSpectacles(0);   
    
    public Char1()
    {
        putOption("HairColor","Grey");
        putOption("HairLength","Short");
        putOption("Hat","No");
    }
}
