package functional;

import model.*;
import org.jdom2.JDOMException;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.math.BigInteger;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
* Created by root on 15.03.15.
*/
public class UserVoids {

    public static int traditionCount = 0;
    public static int countryCount = 0;
    public static int holidayCount = 0;
    public static RSA rsa = new RSA();
    public static User currentUser;

    public static ArrayList<User> users = new ArrayList<User>();

    private static boolean authorizate(String login, String pass) {
        int index = 0;
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getLogin().equals(login)) {
                index = i;
            }
        }

        BigInteger message = new BigInteger(pass.getBytes());
        BigInteger encrypt = rsa.encrypt(message);
        return encrypt.equals(users.get(index).getPass());
    }

    private static boolean checkLogin(String login) {
        boolean result = false;
        for (int i = 0; i < users.size(); i++) {
            if (login.equals(users.get(i).getLogin())) {
                result = true;
            }
        }
        return result;
    }

    public static void registration(String login, String pass1, String pass2,ArrayList<Tradition> traditions, LinkedList<Country> countries, LinkedList<Holiday> holidays
    ) throws IllegalArgumentException, IOException, JDOMException, SAXException, ParseException {
        if (pass1.equals(pass2)) {

            BigInteger pass = new BigInteger(pass1.getBytes());
            User user = new User(login, pass, rsa.getPublicKey(),
                    rsa.getModulus(),rsa);
            users.add(user);
            loadData(login, pass1, traditions, countries, holidays);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static String loadData(String login, String pass, ArrayList<Tradition> traditions, LinkedList<Country> countries, LinkedList<Holiday> holidays) throws JDOMException, SAXException, ParseException, IOException {
        if (authorizate(login, pass)) {
            traditionCount = traditions.size();
            countryCount = countries.size();
            holidayCount = holidays.size();

            currentUser = users.get(Search.searchIndex(users, login));

            new XmlFileWorking().loadUser(traditions,countries,holidays);
        }
        return "";
    }

    public static void logOut(ArrayList<Tradition> traditions, List<Country> countries, List<Holiday> holidays) throws IOException {
        ArrayList<Tradition> tr_list = new ArrayList<Tradition>();
        for (int i = traditionCount; i < traditions.size(); i++) {
            tr_list.add(traditions.get(i));
        }
        currentUser.setTraditionList(tr_list);
        Remove.removeListTradition(tr_list,traditions);
        LinkedList<Country> c_list = new LinkedList<Country>();
        for (int i = countryCount; i < countries.size(); i++) {
            c_list.add(countries.get(i));
        }
        currentUser.setCountryList(c_list);
        Remove.removeListCountry(c_list,countries);
        LinkedList<Holiday> h_list = new LinkedList<Holiday>();
        for (int i = holidayCount; i < holidays.size(); i++) {
            h_list.add(holidays.get(i));
        }
        currentUser.setHolidayList(h_list);
        Remove.removeListHoliday(h_list,holidays);

        new XmlFileWorking().saveUser(traditions,holidays,countries);
        traditionCount = 0;
        countryCount = 0;
        holidayCount = 0;
    }

}