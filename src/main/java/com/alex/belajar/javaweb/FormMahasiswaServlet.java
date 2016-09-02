package com.alex.belajar.javaweb;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormMahasiswaServlet extends HttpServlet {
    private MahasiswaDao mahasiswaDao = new MahasiswaDao();
    
    public void doGet(HttpServletRequest req, HttpServletResponse res) {
        
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse res) {
        try {
            Mahasiswa m = new Mahasiswa();
            m.setNpm(req.getParameter("npm"));
            m.setNama(req.getParameter("nama"));
            m.setTempatLahir(req.getParameter("tempat_lahir"));
            
            String strTanggalLahir = req.getParameter("tgl_lahir");
            SimpleDateFormat formaterTanggal = new SimpleDateFormat("yyyy-MM-dd");
            m.setTanggalLahir(formaterTanggal.parse(strTanggalLahir));
            
            m.setJenisKelamin(JenisKelamin.valueOf(req.getParameter("jenis_kelamin").toUpperCase()));
            m.setAlamat(req.getParameter("alamat"));
            
            mahasiswaDao.simpan(m);
            
            try {
                res.sendRedirect("form.html");
            } catch (IOException ex) {
                Logger.getLogger(FormMahasiswaServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (ParseException ex) {
            Logger.getLogger(FormMahasiswaServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
