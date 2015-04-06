package model;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by root on 12.03.15.
 */
public class User {
    private final String ROOT = "resources/users/";
    private final String TRADITION_FILE = "/traditionSave.xml";
    private final String HOLIDAY_FILE = "/holidaySave.xml";
    private final String COUNTRY_FILE = "/countrySave.xml";
    private String login;
    private BigInteger pass;
    private BigInteger modules;
    //private RSA rsa;
    private static LinkedList<Country> countries = new LinkedList<Country>();
    private static LinkedList<Holiday> holidays = new LinkedList<Holiday>();
    private static ArrayList<Tradition> traditions = new ArrayList<Tradition>();

    public User(String login, BigInteger pass, BigInteger key, BigInteger modules, RSA rsa) throws IOException {
        this.login = login;
        //rsa = new RSA();

        this.modules = modules;
        rsa.setModulus(modules);
        rsa.setPublicKey(key);

        this.pass = rsa.encrypt(pass);

        File folder = new File(ROOT + this.login);
        if (folder.mkdir()) {

            File tradition = new File(ROOT + this.login + TRADITION_FILE);
            File country = new File(ROOT + this.login + COUNTRY_FILE);
            File holiday = new File(ROOT + this.login + HOLIDAY_FILE);

                if (!(tradition.createNewFile() & country.createNewFile() & holiday.createNewFile()))
                    throw new IOException();
        }
    }

    public User(String login, String pass, RSA rsa) {
        this.login = login;

        this.modules = new BigInteger("114300212443049308755638385038607092399228059171843074638659728066396329731870812301666900170326603999649607364454783561463395729169397992550553334308251756497995161575531048559625701582012129417669546314420880750128408561569822198960212709010390091463374475374736305384151906473683969549684741213893356703077");
        rsa.setModulus(this.modules);
        rsa.setPublicKey(rsa.getPublicKey());
        BigInteger pass_value = new BigInteger(pass);
        this.pass = pass_value;
    }

    public LinkedList<Country> getCountryList() {
        return countries;
    }

    public LinkedList<Holiday> getHolidayList() {
        return holidays;
    }

    public static ArrayList<Tradition> getTraditionList() {
        return traditions;
    }

    public void setCountryList(LinkedList<Country> list) {
        countries = list;
    }

    public void setHolidayList(LinkedList<Holiday> list) {
        holidays = list;
    }

    public void setTraditionList(ArrayList<Tradition> list) {
        traditions = list;
    }

    public void setModules(String value, RSA rsa) {
        rsa.setModulus(new BigInteger(value));
    }

    public void setPass(String pass) {
        this.pass = new BigInteger(pass);
    }

    public BigInteger getPass() {
        return pass;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLogin() {
        return login;
    }

    public BigInteger getModules() {
        return this.modules;
    }

    public boolean isAdmin() {
        return "admin".equals(login);
    }

}
