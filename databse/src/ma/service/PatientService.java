/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ma.beans.Patient;
import ma.beans.Service;
import ma.connexion.Connexion;
import ma.dao.IDao;

/**
 *
 * @author Lachgar
 */
public class PatientService implements IDao<Patient> {

    private ServiceService ss;

    public PatientService() {
        ss = new ServiceService();
    }

    @Override
public boolean create(Patient o) {
    try {
        String req = "insert into patient values (null, ?, ?, ?, ?)";
        PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
        ps.setString(1, o.getNom());
        ps.setString(2, o.getPrenom());
        ps.setDate(3, new Date(o.getDate().getTime()));
        ps.setInt(4, o.getService().getId()); // Assurez-vous que getId() retourne l'ID du service

        if (ps.executeUpdate() == 1) {
            return true;
        }
    } catch (SQLException ex) {
        System.out.println("Erreur d'ajout d'un patient:" + ex.getMessage());
    }
    return false;
}


    @Override
    public boolean update(Patient o) {
        try {
            String req = "UPDATE patient SET nom=?, prenom=?, date=?, service=? WHERE id=?";
            PreparedStatement ps = Connexion.getConnection().prepareStatement(req);
            ps.setString(1, o.getNom());
            ps.setString(2, o.getPrenom());
            ps.setDate(3, new Date(o.getDate().getTime()));
            ps.setInt(4, o.getService().getId());
            ps.setInt(5, o.getId());

            int rowsUpdated = ps.executeUpdate();

            return rowsUpdated > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur de mise à jour d'un patient : " + ex.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Patient o) {
        String req = "delete from patient where id = " + o.getId();
        try {
            Statement st = Connexion.getConnection().createStatement();
            st.executeUpdate(req);
            return true;
        } catch (SQLException ex) {
            System.out.println("Erreur de suppression d'un patient:" + ex.getMessage());
        }
        return false;
    }

    @Override
    public Patient findById(int id) {
        Patient patient = null;
        ResultSet rs = null;
        String req = "select * from patient where id = " + id;
        try {
            Statement st = Connexion.getConnection().createStatement();
            rs = st.executeQuery(req);
            if (rs.next()) {
                patient = new Patient(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("date"), ss.findById(rs.getInt("service")));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur findById patient:" + ex.getMessage());
        }
        return patient;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = new ArrayList<>();
        ResultSet rs = null;
        String req = "select * from patient";
        try {
            Statement st = Connexion.getConnection().createStatement();
            rs = st.executeQuery(req);
            while (rs.next()) {
                patients.add(new Patient(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), rs.getDate("date"), ss.findById(rs.getInt("service"))));
            }
        } catch (SQLException ex) {
            System.out.println("Erreur findAll patient:" + ex.getMessage());
        }
        return patients;

    }

}
