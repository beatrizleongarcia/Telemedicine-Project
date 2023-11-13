/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BITalino;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.bluetooth.RemoteDevice;

/**
 *
 * @author maria
 */
public class BitalinoDemo {
    public static Frame[] frame;
    public static ArrayList<Integer> list = new ArrayList<Integer>(); //lista ecg
    public int i = 3;

    public void recordSignal(String MAC) {
        BITalino bitalino = null;
        try {
            bitalino = new BITalino();
            Vector<RemoteDevice> devices = bitalino.findDevices(); //CHECK QUE EL BITALINO ESTA CONECTADO
            int SamplingRate = 10;
            bitalino.open(MAC, SamplingRate);

            // Start acquisition on analog channels A2 and A6
            // For example, If you want A1, A3 and A4 you should use {0,2,3}
            int[] channelsToAcquire = {1, 5};
            bitalino.start(channelsToAcquire);

                //Read in total 10000000 times
                for (int j = 0; j < 1000; j++) {
                    int block_size = 10;
                    frame = bitalino.read(block_size);
                    for (int i = 0; i < frame.length; i++) {
                        System.out.println((j * block_size + i) + " seq: " + frame[i].seq + " " + frame[i].analog[0] + " " + frame[i].analog[1] + " ");
                        list.add(frame[i].analog[0]); //coge los datos analogicos del channel 1 que es el del ECG y se almacenan los valores del ECG leidos
                    }

                }
                setList(list); //la lista la asigna a valores del EEG
                bitalino.stop(); //el bitalino para despues de haber terminado de leer con el frame cada canal

            } catch (BITalinoException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InterruptedException ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Throwable ex) {
            Logger.getLogger(BitalinoDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setList(ArrayList<Integer> list) {
        this.list=list;
    }

    public ArrayList<Integer> getList() {
        return list;
    }

    }

