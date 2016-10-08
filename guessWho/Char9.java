import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Char9 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Char9 extends Character
{
    String hairColor = hairColorOptions[3];
    String hairLength = hairLengthOptions[2];
    Boolean hasHat = getHasHat(0);
    Boolean hasCoat = getHasCoat(0);
    Boolean hasSpecs = getHasSpectacles(0);   
    Boolean hasEarings = getHasEarings(0);
    public Char9()
    {
        putOption("HairColor","Black");
        putOption("HairLength","Medium");
        putOption("Hat","No");
    }
}
