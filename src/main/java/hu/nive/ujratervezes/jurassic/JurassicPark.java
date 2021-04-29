package hu.nive.ujratervezes.jurassic;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
        Az adatbázis egyetlen táblát tartalmaz, dinosaur néven, az alábbi oszlopokkal:

        breed varchar (elsődleges kulcs)
        expected int
        actual int
        A checkOverpopulation metódus feladata, hogy térjen vissza azoknak a fajtáknak a nevével,
        amiknél a valós létszám magasabb, mint az elvárt (a fenti példában a Maiasaurus,
        a Velociraptor és a Hypsilophodontida), ABC szerint növekvő sorrendben.
 */

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    public List<String> checkOverpopulation() {
        List<String> dinos = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword)) {
            String query = "SELECT breed FROM dinosaur where actual > expected ORDER BY breed;";
            PreparedStatement st = connection.prepareStatement(query);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                dinos.add(rs.getString(1));
            }
            return dinos;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
