import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LezinTest {

    public static void main(String[] args) {

        List<String> listOutput = new ArrayList<>();

        List<List<String>> listCate = new ArrayList<List<String>>();
        List<String> listCate_1 = new ArrayList<>();
        listCate_1.add("s1");
        listCate_1.add("s2");
        listCate_1.add("s3");
        listCate_1.add("s4");
        listCate_1.add("s5");
        listCate_1.add("s6");



        List<String> listCate_2 = new ArrayList<>();
        listCate_2.add("g1");
        listCate_2.add("g2");
        listCate_2.add("g3");


        List<String> listCate_3 = new ArrayList<>();
        listCate_3.add("f1");

        listCate.add(listCate_1);
        listCate.add(listCate_2);
        listCate.add(listCate_3);





        int cntProc = 0;
        while(true) {
            Collections.sort(listCate, new Comparator<List<String>>() {
                @Override
                public int compare(List<String> o1, List<String> o2) {

                    int sizeO1 = o1.size();
                    int sizeO2 = o2.size();

                    int returnValue = 0;

                    if(sizeO1 > sizeO2) {
                        returnValue = -1;
                    }
                    else if(sizeO1 < sizeO2)   {
                        returnValue = 1;
                    }


                    return returnValue;
                }
            });

            ViewArray(listCate);

            int sizeLong = listCate.get(0).size();
            int sizeEach = 0;
            for(int i = 1; i<listCate.size(); i++)  {
                sizeEach += listCate.get(i).size();
            }

            if(sizeLong > sizeEach) {
                if(listCate.get(0).size() > 0)  {
                    listOutput.add(listCate.get(0).get(0));
                    listCate.get(0).remove(0);
                }

                if(listCate.get(1).size() > 0)  {
                    listOutput.add(listCate.get(1).get(0));
                    listCate.get(1).remove(0);
                }
            }
            else    {
                for(List<String> rowCate : listCate)    {

                    if(rowCate.size() > 0)  {
                        listOutput.add(rowCate.get(0));
                        rowCate.remove(0);
                    }
                }
            }


            if(listCate.get(0).size() < 1)  {
                break;
            }


            cntProc += 1;

            if(cntProc > 10)    {
                break;
            }
        }

        ViewArray(listOutput);

    }


    static void ViewArray(Object value)    {
        System.out.println(value);
    }



}
