package functional;

import model.Country;
import model.Holiday;
import model.Tradition;

import java.util.List;

public class Remove {

    public static void removeListTradition(List<Tradition> traditionList, List<Tradition> from){
        if (traditionList != null){
            for(Tradition item : traditionList) {
                from.remove(item);
            }
        }
    }

    public static void removeListCountry(List<Country> countryList, List<Country> from){
        if (countryList != null){
            for(Country item : countryList) {
                from.remove(item);
            }
        }
    }

    public static void removeListHoliday(List<Holiday> holidayList, List<Holiday> from){
        if (holidayList != null){
            for(Holiday item : holidayList) {
                from.remove(item);
            }
        }
    }

    public static void removeTradition(int id, List<Tradition> traditions) {
        if (id >= UserVoids.traditionCount && id < traditions.size()) {
            traditions.remove(id);
        }
        else throw new IndexOutOfBoundsException();
    }

    public static void removeTraditionGui(int id, List<Tradition> traditions, List<Tradition> allTraditions) {
        if (id < allTraditions.size()) {
            traditions.remove(id);
        }
        else throw new IndexOutOfBoundsException();
    }

    public static void removeCountry(int country, List<Country> c_list, List<Tradition> from){
        //поиск традиций с этой страной
        removeListTradition(Search.getCountryTraditions(country,c_list,from),from);
        c_list.remove(country);
    }

    public static void removeHoliday(int holiday, List<Holiday> h_list, List<Tradition> from){
        // поиск и удаление традиций с этим праздником
        removeListTradition(Search.getTraditions(h_list.get(holiday), from),from);
        h_list.remove(holiday);
    }
}