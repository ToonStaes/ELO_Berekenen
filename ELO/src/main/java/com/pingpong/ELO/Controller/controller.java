package com.pingpong.ELO.Controller;

import com.pingpong.ELO.Model.Speler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class controller {
    public Speler speler;
    public int elo;
    @RequestMapping("/VerwelkomingSpeler")
    public String verwelkomingSpeler(Model model, HttpServletRequest request) {
        String naam = request.getParameter("naam");
        String eloString = request.getParameter("ELO");

        elo = Integer.parseInt(eloString);

        speler = new Speler(naam);
        speler.setPunten(elo);

        model.addAttribute("speler", speler);

        return "verwelkoming";
    }

    @RequestMapping("/spelers_invullen")
    public String spelersInvullen(Model model, HttpServletRequest request) {
        String returnen = null;
        String jeugd = request.getParameter("jeugd");
        if (jeugd != null) {
            returnen = "jeugd_tegenstanders";
        } else {
            returnen = "volwassenen_tegenstanders";
        }
        return returnen;
    }

    @RequestMapping("/wedstrijdJeugd")
    public String wedstrijdJeugd(Model model, HttpServletRequest request) {
        String naam1 = request.getParameter("naam1");
        String naam2 = request.getParameter("naam2");
        String naam3 = request.getParameter("naam3");

        Speler tegenstander1 = new Speler(naam1);
        Speler tegenstander2 = new Speler(naam2);
        Speler tegenstander3 = new Speler(naam3);

        model.addAttribute("Tegenstander1", tegenstander1);
        model.addAttribute("Tegenstander2", tegenstander2);
        model.addAttribute("Tegenstander3", tegenstander3);

        return "wedstrijdJeugd";
    }

    @RequestMapping("/berekenenJeugd")
    public String berekenenjeugd(Model model, HttpServletRequest request) {
        int elo1 = Integer.parseInt(request.getParameter("elo1"));
        int elo2 = Integer.parseInt(request.getParameter("elo2"));
        int elo3 = Integer.parseInt(request.getParameter("elo3"));

        String gewonnen1 = request.getParameter("gewonnen1");
        String gewonnen2 = request.getParameter("gewonnen2");
        String gewonnen3 = request.getParameter("gewonnen3");

        int gewonnen = 0;

        int beginELO = elo;
        int mijnELO = elo;
        if (gewonnen1 != null) {
            gewonnen = 1;
        } else {
            gewonnen = 0;
        }
        int nieuweELO = (int) (mijnELO + 36*(gewonnen-(1/(1+Math.pow(Math.E, (0.00693*(elo1-mijnELO)))))));
        mijnELO = nieuweELO;
        if (gewonnen2 != null) {
            gewonnen = 1;
        } else {
            gewonnen = 0;
        }
        nieuweELO = (int) (mijnELO + 36*(gewonnen-(1/(1+Math.pow(Math.E, (0.00693*(elo2-mijnELO)))))));
        mijnELO = nieuweELO;
        if (gewonnen3 != null) {
            gewonnen = 1;
        } else {
            gewonnen = 0;
        }
        nieuweELO = (int) (mijnELO + 36*(gewonnen-(1/(1+Math.pow(Math.E, (0.00693*(elo3-mijnELO)))))));
        mijnELO = nieuweELO;

        int verschilELO = mijnELO-beginELO;

        model.addAttribute("verschil", verschilELO);
        model.addAttribute("mijnELO", mijnELO);

        return "resultaat";
    }

    @RequestMapping("/wedstrijdVolwassenen")
    public String wedstrijdVolwassenen(Model model, HttpServletRequest request) {
        String naam1 = request.getParameter("naam1");
        String naam2 = request.getParameter("naam2");
        String naam3 = request.getParameter("naam3");
        String naam4 = request.getParameter("naam4");

        Speler tegenstander1 = new Speler(naam1);
        Speler tegenstander2 = new Speler(naam2);
        Speler tegenstander3 = new Speler(naam3);
        Speler tegenstander4 = new Speler(naam4);

        model.addAttribute("Tegenstander1", tegenstander1);
        model.addAttribute("Tegenstander2", tegenstander2);
        model.addAttribute("Tegenstander3", tegenstander3);
        model.addAttribute("Tegenstander4", tegenstander4);

        return "wedstrijdVolwassenen";
    }

    @RequestMapping("/berekenenVolwassenen")
    public String berekenenVolwassenen(Model model, HttpServletRequest request) {
        int elo1 = Integer.parseInt(request.getParameter("elo1"));
        int elo2 = Integer.parseInt(request.getParameter("elo2"));
        int elo3 = Integer.parseInt(request.getParameter("elo3"));
        int elo4 = Integer.parseInt(request.getParameter("elo4"));

        String gewonnen1 = request.getParameter("gewonnen1");
        String gewonnen2 = request.getParameter("gewonnen2");
        String gewonnen3 = request.getParameter("gewonnen3");
        String gewonnen4 = request.getParameter("gewonnen4");

        int gewonnen;

        int beginELO = elo;
        int mijnELO = elo;
        if (gewonnen1 != null) {
            gewonnen = 1;
        } else {
            gewonnen = 0;
        }
        int nieuweELO = (int) (mijnELO + 36*(gewonnen-(1/(1+Math.pow(Math.E, (0.00693*(elo1-mijnELO)))))));
        mijnELO = nieuweELO;

        if (gewonnen2 != null) {
            gewonnen = 1;
        } else {
            gewonnen = 0;
        }
        nieuweELO = (int) (mijnELO + 36*(gewonnen-(1/(1+Math.pow(Math.E, (0.00693*(elo2-mijnELO)))))));
        mijnELO = nieuweELO;

        if (gewonnen3 != null) {
            gewonnen = 1;
        } else {
            gewonnen = 0;
        }
        nieuweELO = (int) (mijnELO + 36*(gewonnen-(1/(1+Math.pow(Math.E, (0.00693*(elo3-mijnELO)))))));
        mijnELO = nieuweELO;

        if (gewonnen4 != null) {
            gewonnen = 1;
        } else {
            gewonnen = 0;
        }
        nieuweELO = (int) (mijnELO + 36*(gewonnen-(1/(1+Math.pow(Math.E, (0.00693*(elo4-mijnELO)))))));
        mijnELO = nieuweELO;

        int verschilELO = mijnELO-beginELO;

        model.addAttribute("verschil", verschilELO);
        model.addAttribute("mijnELO", mijnELO);
//        model.addAttribute("speler", speler);

        return "resultaat";
    }
}
