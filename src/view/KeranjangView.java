package view;

import controller.KeranjangController;
import node.NodeClass.NodeProduk;
import node.NodeClass.NodeUser;

import java.util.ArrayList;
import java.util.Scanner;

public class KeranjangView {
    KeranjangController keranjang;
    Scanner input;

    public KeranjangView(ArrayList<NodeProduk> lProduk, ArrayList<NodeUser> lUser) {
        keranjang = new KeranjangController(lProduk, lUser);
        input = new Scanner(System.in);
    }

    public void addKeranjang() {

    }

    public void addBarang() {

    }

    public void viewAllKeranjang() {

    }
}
