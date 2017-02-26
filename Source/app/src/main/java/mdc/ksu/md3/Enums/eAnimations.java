package mdc.ksu.md3.Enums;


public enum eAnimations
{
    MANUAL,
    ROTATE_LEFT,
    ROTATE_RIGHT,
    FEINT,
    FREEZE;

    public static String[] getStringArray()
    {
        eAnimations[] lValues = values();
        String[] lResult = new String[lValues.length];

        for (int i = 0; i < lValues.length; i++)
        {
            lResult[i] = lValues[i].toString();
        }

        return lResult;
    }
}


