package functional;

import model.Country;
import model.Holiday;
import model.Tradition;

import java.util.ArrayList;
import java.util.List;

public class Change {
    //Правка страны.
    public static List<Country> editCountry(Country country, String newName, List<Country> list) {
        //int index = Search.searchIndex(country, list);
        int index = list.indexOf(country);
        country.setName(newName);
        //for (int i = 0; i < index.length; i++){
        list.set(index, country);
        //}
        return list;
    }

    public static List<Holiday> editHoliday(int id, Holiday newHoliday,
                                            List<Holiday> list) {
        list.set(id, newHoliday);
        return list;
    }

    public static List<Tradition> editTradition(String newStr, int id, int param, List<Tradition> list, List<Country> countries) {
        Tradition tradition = list.get(id);
        switch (param) {
            case 1:
                tradition.setDescription(newStr);
                break;
            case 2:
                tradition.getCountry().setName(newStr);
                break;
            case 3:
                tradition.getHoliday().setName(newStr);
                break;
            case 4:
                ArrayList<Tradition> traditions = Search.getCountryTraditions(id,countries,list);
                //int count = 0;
                for (Tradition trad : list) {
                    for (int i = 0; i < traditions.size(); i++) {
                        if (trad.equals(traditions.get(i)))
                            list.set(list.indexOf(
                                            traditions.get(i)),
                                    new Tradition(tradition.getHoliday(),
                                            new Country(newStr), tradition.getDescription()));
                    }
                    //count++;
                }
                break;
            default:
                break;
        }
        list.set(id, tradition);
        return list;
    }

    public static List<Tradition> editTradition(Holiday holiday, Holiday newHoliday, List<Tradition> list) {
        ArrayList<Tradition> traditions = Search.getTraditions(holiday,list);
        //int count = 0;
        for (Tradition tradition : list) {
            for (int i = 0; i < traditions.size(); i++) {
                if (tradition.equals(traditions.get(i)))
                    list.set(list.indexOf(traditions.get(i)), new Tradition(newHoliday, tradition.getCountry(), tradition.getDescription()));
            }
            //count++;
        }
        return list;
    }

    public static List<Tradition> editTradition(int id, Holiday newHoliday, Country newCountry, String description, List<Tradition> list) {

        Tradition changing = list.get(id);
        changing.setHoliday(newHoliday);
        changing.setCountry(newCountry);
        changing.setDescription(description);
        list.set(id,changing);
        return list;
    }
}
