package ma.test;

import java.util.Date;
import ma.beans.Patient;
import ma.beans.Service;
import ma.service.PatientService;
import ma.service.ServiceService;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Lachgar
 */
public class Test {

    public static void main(String[] args) {
        PatientService cs = new PatientService();
        ServiceService ss = new ServiceService();
        Patient c = new Patient(1,"rami", "ali", new Date());
                            System.out.println("#####hi " );

        cs.create(c);

//        Patient c = new Patient("rami", "ali", new Date(), ss.findById(2));
//       cs.create(c);
//        Patient c1 = new Patient("meryem", "rachyq", new Date(), ss.findById(12));
//        cs.create(c1);
//        Patient c2 = new Patient("kenza", "raki", new Date(), ss.findById(13));
//        cs.create(c2);
//        Patient c3 = new Patient("samya", "kouame", new Date(), ss.findById(14));
//        cs.create(c3);
//        Patient c4 = new Patient("aya", "kasmi", new Date(), ss.findById(2));
//        cs.create(c4);
//        System.out.println(cs.findById(2));
//        cs.delete(cs.findById(2));
//        for (Service s : ss.findAll()) {
//            System.out.println("Service :" + s.getNom());
//            for (Patient e : cs.findAll()) {
//                if (e.getService().getId() == s.getId()) {
//                    System.out.println("########## " + e.getNom() + " " + e.getPrenom());
//                }
//            }
//        }

        //ss.delete(ss.findById(7));
//       ss.create(new Service(1,"Marketing"));
//        ss.create(new Service(2,"Comptablite"));
//        ss.create(new Service("RH"));
        //ss.create(new Service("Informatqiue"));
    }
}
//}

