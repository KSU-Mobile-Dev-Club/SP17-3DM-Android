package mdc.ksu.md3.Enums;

public enum ePokemon
{
    Cubone,
    Cyndaquil,
    Throh;

    public static String[] getStringArray()
    {
        ePokemon[] lValues = values();
        String[] lResult = new String[lValues.length];

        for (int i = 0; i < lValues.length; i++)
        {
            lResult[i] = lValues[i].toString();
        }

        return lResult;
    }

}
