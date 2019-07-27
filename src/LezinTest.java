import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LezinTest {

    public static void main(String[] args) {


        //출력할 변수 정의
        List<String> listOutput = new ArrayList<>();

        //데이터 입력
        List<String> listInput = new ArrayList<>();
        listInput.add("sports,s1,s2,s3");
        listInput.add("gag,g1,g2");
        listInput.add("fantasy,f1,f2,f3");


        //데이터 처리
        TestProcess testProcess = new TestProcess();
        List<List<String>> listCate = testProcess.InputProc(listInput);
        listOutput = testProcess.getOutput( listCate );


        System.out.println(listOutput);

    }

    static class TestProcess    {

        List<List<String>> InputProc(List<String> paramInput) {
            List<List<String>> listCate = new ArrayList<List<String>>();
            for(String rowInput : paramInput)    {
                String[] listInputSplit = rowInput.split(",");

                int cntInputSplit = listInputSplit.length;

                //입력된 값의 첫번째 항목은 장르이름이고 실제 데이터는 두번째 부터 있으므로 변수값이 2개 이상 인것만 가공함.
                if(cntInputSplit > 2)   {
                    List<String> rowCate = new ArrayList<>();
                    for(int i=1; i<cntInputSplit; i++)  {
                        rowCate.add(listInputSplit[i]);
                    }

                    listCate.add(rowCate);
                }

            }
            return listCate;
        }

        List<String> getOutput(List<List<String>> listCate)    {
            List<String> listOutput = new ArrayList<>();

            if(listCate.size() > 0) {

                while(true) {

                    //장르별 작품 수에 따라 리스트를 정렬함
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

                    //리스트의 첫번째 항목이 가장 작품수가 가장 많으므로 기준을 삼음
                    int sizeLong = listCate.get(0).size();

                    //리스트 나머지 항목의 작품수의 합을 구함
                    int sizeEach = 0;
                    for(int i = 1; i<listCate.size(); i++)  {
                        sizeEach += listCate.get(i).size();
                    }

                    //작품 수가 가장 많은 장르 (A)와 나머지 장르의 작품수(B)를 비교
                    //A와 B를 비교하여 A가 더 클 경우 작품수가 가장 많은 장르과 그 다음으로 많은 장르의 값만 출력시킴
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
                    //A와 B를 비교하여 B가 더 클 경우 모든 장르의 작품을 순차적으로 출력시킴
                    else    {
                        for(List<String> rowCate : listCate)    {

                            if(rowCate.size() > 0)  {
                                listOutput.add(rowCate.get(0));
                                rowCate.remove(0);
                            }
                        }
                    }

                    //작품수가 가장 많은 장르의 리스트가 비었을 경우 다른 장르의 리스트도 비었기 때문에 프로세서를 종료시킴
                    if(listCate.get(0).size() < 1)  {
                        break;
                    }
                }
            }


            return listOutput;
        }
    }



}
