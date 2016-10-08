import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Char8 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Char8 extends Character
{
    String hairColor = hairColorOptions[5];
    String hairLength = hairLengthOptions[2];
    Boolean hasHat = getHasHat(1);
    Boolean hasCoat = getHasCoat(1);
    Boolean hasSpecs = getHasSpectacles(0);   
    Boolean hasEarings = getHasEarings(1);
    
    public Char8()
    {
        putOption("HairColor","Red");
        putOption("HairLength","Medium");
        putOption("Hat","Yes");
    }
}
