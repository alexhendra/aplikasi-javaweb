package com.alex.belajar.javaweb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MahasiswaDao {

    private Connection koneksiDatabase;
    private String dbDriver = "com.mysql.jdbc.Driver";
    private String dbUrl = "jdbc:mysql://localhost/belajarjava?useLegacyDatetimeCode=false&serverTimezone=America/New_York";
    private String dbUserName = "root";
    private String dbPassword = "4l3*z@42";

    public void connect() {
        try {
            Class.forName(dbDriver);
            try {
                koneksiDatabase = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
            } catch (SQLException ex) {
                Logger.getLogger(MahasiswaDao.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MahasiswaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void disconnect() {
        try {
            if (koneksiDatabase != null) {
                koneksiDatabase.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(MahasiswaDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void simpan(Mahasiswa m) {
        try {
            connect();
            
            String sql = "insert into mahasiswa(npm, nama, tempat_lahir, tanggal_lahir,"
                    + " jenis_kelamin, alamat) values(?,?,?,?,?,?)";
            
            PreparedStatement ps = koneksiDatabase.prepareStatement(sql);
            ps.setString(1, m.getNpm());
            ps.setString(2, m.getNama());
            ps.setString(3, m.getTempatLahir());
            ps.setDate(4, new java.sql.Date(m.getTanggalLahir().getTime()));
            ps.setString(5, m.getJenisKelamin().toString());
            ps.setString(6, m.getAlamat());
            
            ps.executeUpdate();
            
            disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(MahasiswaDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
