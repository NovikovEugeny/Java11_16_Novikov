package by.tc.online_pharmacy.service.util;


import by.tc.online_pharmacy.bean.Drug;
import by.tc.online_pharmacy.bean.OrderDescription;

import java.util.*;

public final class ReportMaker {

    private static Date roundDate(Date date) {
        Calendar roundedDate = new GregorianCalendar();
        roundedDate.setTime(date);
        roundedDate.set(Calendar.HOUR, 0);
        roundedDate.set(Calendar.MINUTE, 0);
        roundedDate.set(Calendar.SECOND, 0);
        roundedDate.set(Calendar.MILLISECOND, 0);
        return new Date(roundedDate.getTimeInMillis());
    }

    private static void transformODList(List<OrderDescription> list) {
        for (OrderDescription od : list) {
            od.setResponseDate(roundDate(od.getResponseDate()));
        }
    }

    private static void regularizeMap(Map<Date, List<Drug>> map) {

        for (Map.Entry<Date, List<Drug>> item : map.entrySet()) {

            if (item.getValue().size() > 1) {

                for (int i = 0; i < item.getValue().size(); i++) {

                    Drug refDrug = item.getValue().get(i);

                    for (int j = i + 1; j < item.getValue().size(); j++) {
                        int n = 1;

                        if (item.getValue().get(j).equals(refDrug)) {
                            n++;
                        }
                        if (n > 1) {
                            int quantity = item.getValue().get(j).getQuantity();
                            item.getValue().remove(j);
                            j = j - 1;

                            int previousQuantity = item.getValue().get(i).getQuantity();
                            item.getValue().get(i).setQuantity(quantity + previousQuantity);
                        }
                    }
                }
            }
        }
    }

    public static Map<Date, List<Drug>> makeReport(List<OrderDescription> odList) {

        transformODList(odList);

        Map<Date, List<Drug>> map = new LinkedHashMap<>();

        for (int i = 0; i < odList.size(); i++) {

            Date refDate = odList.get(i).getResponseDate();

            if (!map.containsKey(refDate)) {
                List<Drug> drugList = new ArrayList<>();

                Drug drug = new Drug();
                drug.setName(odList.get(i).getDrugName());
                drug.setGroup(odList.get(i).getPharmacologicalGroup());
                drug.setDrugAmount(odList.get(i).getDrugAmount());
                drug.setCountry(odList.get(i).getProductingCountry());
                drug.setActiveSubstances(odList.get(i).getActiveSubstances());
                drug.setQuantity(odList.get(i).getQuantity());

                drugList.add(drug);

                for (int j = i + 1; j < odList.size(); j++) {
                    if (odList.get(j).getResponseDate().equals(refDate)) {
                        Drug nextDrug = new Drug();
                        nextDrug.setName(odList.get(j).getDrugName());
                        nextDrug.setGroup(odList.get(j).getPharmacologicalGroup());
                        nextDrug.setDrugAmount(odList.get(j).getDrugAmount());
                        nextDrug.setCountry(odList.get(j).getProductingCountry());
                        nextDrug.setActiveSubstances(odList.get(j).getActiveSubstances());
                        nextDrug.setQuantity(odList.get(j).getQuantity());

                        drugList.add(nextDrug);
                    }
                }
                map.put(refDate, drugList);
            }
        }

        regularizeMap(map);

        return map;
    }
}