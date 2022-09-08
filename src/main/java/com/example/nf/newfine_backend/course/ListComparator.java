package com.example.nf.newfine_backend.course;

import java.util.Comparator;

public class ListComparator implements Comparator<Listener> {

    @Override
    public int compare(Listener o1, Listener o2) {

        String name1=((Listener)o1).getStudent().getName();
        String name2=((Listener)o2).getStudent().getName();

//        int todayTotal1 = Integer.parseInt(((Listener)o1).getTodayTotal());
//        int todayTotal2 = Integer.parseInt(((Listener)o2).getTodayTotal());

//        if(name1 > name2){
//            return -1;
//        }else if(name1 < name2){
//            return 1;
//        }else{
//            return 0;
//        }
        return name1.compareTo(name2);
    }
}
