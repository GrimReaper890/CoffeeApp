package com.fyppractice.fyppractice.rvData;

/**
 * Created by GrimReaper on 7/8/2017.
 */
import com.fyppractice.fyppractice.rvmodels.crClassDataModel;

import java.util.ArrayList;
import java.util.List;

public class crClassActivityData {
    //the array size for incrementing and decrementing on runntime
    public static int stdArray;
    public static String[] stdntName;
    public static String[] stdntRollNumber;
    public static String[] stdntContact;


    public crClassActivityData(int i) {
//        i is the size of data coming in the arrays ... this will help to dynamically initiallize my arrays

        stdntName = new String[i];
        stdntRollNumber = new String[i];
        stdntContact = new String[i];
        stdArray = i;
    }

    public static List<crClassDataModel> getListData() {

        List<crClassDataModel> data = new ArrayList<>();
        for (int i = 0; i < (stdArray); i++) {
            crClassDataModel DataMdl = new crClassDataModel();
            DataMdl.setStdName(stdntName[i]);
            DataMdl.setStdRollNo(stdntRollNumber[i]);
            DataMdl.setStdContact(stdntContact[i]);
            DataMdl.setStdEditAction("Edit");
            DataMdl.setStdViewAction("View");
            data.add(DataMdl);
        }
        return data;
    }
}
